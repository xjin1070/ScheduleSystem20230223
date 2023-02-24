package com.song.schedulesystem.bean.schedule;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.song.schedulesystem.service.PredictService;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Schedule {
    //排班次
    List<List<Clazz>> AllClazzes=new ArrayList<>();
    //读取客流量
    @Resource
    PredictService predictService;
    //这里也是获取一天的数据
    List<Predict> predicts=predictService.list();//这里只需要每次获取的list都是
    List<TimePeople> timePeoples=new ArrayList<>();
    int readyMinute=30;
    int size=100;
    public void preData(double num,List<Predict>predicts) {
        //读取两个算一个
        int count=0;
        Time startTime=new Time(0);
        Time endTime=new Time(0);
        double startNum=0;
        double endNum=0;
        for (Predict predict1 : predicts) {
            if(count==0){
                count++;
                startTime=predict1.getStartTime();
                startNum=Math.ceil(predict1.getPassengerTraffic()/num);
            }
            else if(count==1){
                count=0;
                endTime=predict1.getEndTime();
                endNum=Math.ceil(predict1.getPassengerTraffic()/num);
                timePeoples.add(new TimePeople(startTime,endTime,Math.ceil((startNum+endNum)/2)));
            }
        }
    }



    /**
     * 这个是生成一天的班次信息
     * 用来排班次的函数
     * 思路：
     * 1·首先判断，是不是在之前的班次里面可以已经包含了这次的人数所需要的班次
     * 2·那么就不要重新开设班次，直接跳过就好了
     * 3·如果之前的班次里面没有找到，看看能不能从之前的班次里面来延长，
     * 4·如果不能开设新的班次
     * @param shopSize 门店面积
     * @param predictNum 预测客流量的数字 默认3.8
     * @param preNeedTime 门店开始的准备需要的时间
     * @param endNeedTime 门店关店准备需要的时间
     * @param startNeedPeoNum 计算门店开始准备所需要人数的数字
     * @param offShopNumOne 计算门店关闭所需要的人数的第一个数字
     * @param offShopNumTwo 计算门店关闭所需要的第二个数字
     * @param defaultNum   当没有客流量的时候需要多少个人数
     */

    public void createSchedule(List<Predict> predicts,double shopSize,double predictNum,Long preNeedTime,Long endNeedTime,double startNeedPeoNum,double offShopNumOne,double offShopNumTwo,int defaultNum){
        int h=60*60*1000;
        int m=60*1000;
        int s=1000;
        List<Clazz> classes=new ArrayList<>();
        predicts=predictService.list();
        preData(predictNum,predicts);
        //开店之前必须要做准备，所以直接取班次就可以了
        int isFullNum=-1;
        TimePeople timePeopleStart = timePeoples.get(0);
        Time startTime = new Time(timePeopleStart.getStartTime().getTime()-preNeedTime*h);
        /**
         * 至于关店操作直接增加客流量就好了
         */
        TimePeople timePeopleEnd = timePeoples.get( timePeoples.size() -1 );//获取最后一个人
        Time endTime=new Time(timePeopleEnd.getEndTime().getTime());
        int endNeedTime1=2;
        double offShopNum=Math.ceil(shopSize/offShopNumOne+offShopNumTwo);//需要的班次的数量
        for (int i = 0; i < endNeedTime; i++) {
            //增加客流量，但是实际的客流量是没有的所以，但是通过增加客流量来模拟关店的所需要的客流量
            TimePeople timePeople = new TimePeople(new Time(endTime.getTime() + i * h), new Time(endTime.getTime() + (i + 1) * h), offShopNum);
            timePeoples.add(timePeople);
        }

        double preClasses=shopSize/startNeedPeoNum; //开店前的需要的班次，门店面积/50，50可调节
        for (int i = 0; i < preClasses; i++) {
            Clazz clazz = new Clazz(startTime, new Time(startTime.getTime() + 2*h), 2);
            classes.add(clazz);
        }
        for (TimePeople people : timePeoples) {
            //这里0次和一次是相同的
            Double peopleNum = people.getPeopleNum();
            if(peopleNum==0) peopleNum =(double)defaultNum;

            int workNum=isFullNum;
            for (int i = 0; i < peopleNum; i++) {//num加的同时 isfullnum也加了所以造成了错误
                Clazz clazz=new Clazz();
                try{
                    clazz = classes.get(workNum + i+1);
                }catch (Exception e){
//                    System.out.println("错误");
                    //这里说明没有找到表示之前的已经没有了，这个时候需要开辟新的数组，---可能问题出在这里
                    clazz = new Clazz(people.getStartTime(), new Time(people.getStartTime().getTime() + 2 * h), 2);
                    classes.add(clazz);
                }
                //这个问题就是在get之前没有创建班次就获取不到
                /**
                 * 首先判断在0流量的时候，判断在不在没有加满的班次之类，如果加满了
                 * 判断，当前班次满四个小时，能不能够上，当前需要班次的开始时间
                 * 如果够上了，那么就增加当前班次的时间，
                 * 没有够上就开一个新的班次
                 *
                 *  多个班次怎么开？
                 *  我们就需要找到下一个班次
                 *  所以在一个周期里面，需要有个计数的循环
                 *
                 *  为什么在10：00 到十一点 的时候加时间？
                 */
                if(clazz.getStartTime().before(new Time(people.getStartTime().getTime()+1*s))&&clazz.getEndTime().after(new Time(people.getEndTime().getTime()-1*s))) {
                    continue;
                }
                else if((clazz.getEndTime().before(new Time(people.getStartTime().getTime()+1*s)))){//问题在这里
//                    System.out.println("进来之后的，班次开始时间："+clazz.getStartTime()+" 流量开始："+people.getStartTime()+" 班次结束："+clazz.getEndTime()+" 流量结束："+people.getEndTime());
                    if(new Time((long)((4-clazz.getHours())*h+clazz.getEndTime().getTime())).after(new Time(people.getStartTime().getTime()))&&clazz.getHours()!=4) {//没加满出在这个判断上面

                        clazz.setEndTime(new Time(clazz.getEndTime().getTime() + 1 * h));
                        clazz.setHours(clazz.getHours() + 1);
                        if(clazz.getHours()==4) isFullNum++;
                        //如果不是的话，我们需要开启一个新的班次开始时间就是从当前客流量的时间+2小时
                    }else {
                        //如果够不上需要开设新的班次，
//                        System.out.println("当前班次时间"+clazz.getStartTime()+"当前班次结束时间"+ clazz.getEndTime()+"已使用"+clazz.getHours());
                        isFullNum++;
                        Clazz clazz1 = new Clazz(people.getStartTime(), new Time(people.getStartTime().getTime() + 2 * h), 2);
                        classes.add(clazz1);
                    }

                }
            }
        }
        AllClazzes.add(classes); //把生成一天的班次信息放到总班次信息里面
    }
    // 生成排班表


    //获取遍历的天数，生成月班次表
    public void totalSchedule(){
        //首先获取全部相同的日期去重
        QueryWrapper<Predict> oqw  = new QueryWrapper<Predict>();
        oqw.select("distinct date");
        List<Predict> list = predictService.list(oqw);
        for (Predict predict : list) {
            LambdaQueryWrapper<Predict> lqw  = new LambdaQueryWrapper<>();
            lqw.eq(Predict::getDate,predict.getDate());
            List<Predict> list1 = predictService.list(lqw);
            //接下来调用，list1 给之前的赋值
        }
    }
}
