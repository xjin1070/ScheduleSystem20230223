package com.song.schedulesystem;
import com.song.schedulesystem.bean.Emp;
import com.song.schedulesystem.bean.Shop;
import com.song.schedulesystem.bean.schedule.Clazz;
import com.song.schedulesystem.bean.schedule.Predict;
import com.song.schedulesystem.bean.schedule.TimePeople;
import com.song.schedulesystem.service.EmpService;
import com.song.schedulesystem.service.PredictService;
import com.song.schedulesystem.service.ShopService;
import org.apache.commons.codec.digest.DigestUtils;
import com.song.schedulesystem.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ScheduleSystemApplicationTests {

    @Resource
    LoginService loginService;
    @Resource
    ShopService shopService;
    @Resource
    PredictService predictService;
    @Test
    void contextLoads() {
    }

    //生成员工测试数据
    @Test
    public void insertEmp(){
        int id=110;
        for (int j = 1; j < 9; j++) {
            for (int i = 1; i < 10; i++) {
                Emp emp = new Emp();
                emp.setID(id++);
                emp.setPassword(DigestUtils.md5Hex("11"+i));
                emp.setEmail("222333"+id+"@qq.com");
                emp.setShopId(j);
                emp.setPosition("普通员工");
                emp.setName("员工"+i);
                loginService.save(emp);
            }
        }
    }

    //生成门店测试数据
    @Test
    public void insertShop(){
        for (int i = 2; i < 10; i++) {
            Shop shop = new Shop();
            shop.setID(i);
            shop.setName("测试数据"+i);
            shop.setAddress("测试数据"+i);
            shop.setSize(50d);
            shopService.save(shop);
        }
    }

    @Test
    public void testGetTimeList(){
        List<Predict> list = predictService.list();
        for (Predict predict : list) {
            System.out.println(predict);
        }
    }

    @Test
    public void shiftScheduling() {
        List<Clazz> classes=new ArrayList<>();
        //读取客流量

        List<Predict> predicts=predictService.list();
        List<TimePeople> timePeoples=new ArrayList<>();
        int readyMinute=30;
        int size=100;
        Predict predict = predicts.get(0);
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
                startNum=Math.ceil(predict1.getPassengerTraffic()/3.8);
            }
            else if(count==1){
                count=0;
                endTime=predict1.getEndTime();
                endNum=Math.ceil(predict1.getPassengerTraffic()/3.8);
                timePeoples.add(new TimePeople(startTime,endTime,Math.ceil((startNum+endNum)/2)));
            }
        }
        for (TimePeople timePeople : timePeoples) {
            System.out.println(timePeople);
        }
    }

    int h=60*60*1000;
    int m=60*1000;
    int s=1000;
    List<Predict> predicts;
    List<Clazz> classes=new ArrayList<>();
    List<TimePeople> timePeoples=new ArrayList<>();
    public void preData(double predictNum) {
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
                startNum=Math.ceil(predict1.getPassengerTraffic()/predictNum);
            }
            else if(count==1){
                count=0;
                endTime=predict1.getEndTime();
                endNum=Math.ceil(predict1.getPassengerTraffic()/predictNum);
                timePeoples.add(new TimePeople(startTime,endTime,Math.ceil((startNum+endNum)/2)));
            }
        }
    }
    @Test
    public void createSchedule(){
        predicts=predictService.list();
        preData(3.8);
        //开店之前必须要做准备，所以直接取班次就可以了
        int isFullNum=-1;
        TimePeople timePeopleStart = timePeoples.get(0);
        Time startTime = new Time(timePeopleStart.getStartTime().getTime()-1*h);
        /**
         * 至于关店操作直接增加客流量就好了
         */
        TimePeople timePeopleEnd = timePeoples.get( timePeoples.size() -1 );//获取最后一个人
        Time endTime=new Time(timePeopleEnd.getEndTime().getTime());
        int endNeedTime=2;
        double offShopNum=Math.ceil(100/30+2);//需要的班次的数量
        for (int i = 0; i < endNeedTime; i++) {
            //增加客流量，但是实际的客流量是没有的所以，但是通过增加客流量来模拟关店的所需要的客流量
            TimePeople timePeople = new TimePeople(new Time(endTime.getTime() + i * h), new Time(endTime.getTime() + (i + 1) * h), offShopNum);
            timePeoples.add(timePeople);
        }

        for (TimePeople timePeople : timePeoples) {
            System.out.println(timePeople);
        }
        int preClasses=100/50; //开店前的需要的班次，门店面积/50，50可调节
        for (int i = 0; i < preClasses; i++) {
            Clazz clazz = new Clazz(startTime, new Time(startTime.getTime() + 2*h), 2);
            classes.add(clazz);
        }
        int count=0;
        for (TimePeople people : timePeoples) {
            count++;

            //这里0次和一次是相同的
            Double peopleNum = people.getPeopleNum();
            if(peopleNum==0) peopleNum =1d;

            int workNum=isFullNum;
            for (int i = 0; i < peopleNum; i++) {//num加的同时 isfullnum也加了所以造成了错误
                Clazz clazz=new Clazz();
                try{
                    clazz = classes.get(workNum + i+1);
                }catch (Exception e){
                    System.out.println("错误");
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
                    System.out.println("进来之后的，班次开始时间："+clazz.getStartTime()+" 流量开始："+people.getStartTime()+" 班次结束："+clazz.getEndTime()+" 流量结束："+people.getEndTime());
                    if(new Time((long)((4-clazz.getHours())*h+clazz.getEndTime().getTime())).after(new Time(people.getStartTime().getTime()))&&clazz.getHours()!=4) {//没加满出在这个判断上面

                            clazz.setEndTime(new Time(clazz.getEndTime().getTime() + 1 * h));
                            clazz.setHours(clazz.getHours() + 1);
                            if(clazz.getHours()==4) isFullNum++;
                            //如果不是的话，我们需要开启一个新的班次开始时间就是从当前客流量的时间+2小时
                    }else {
                        //如果够不上需要开设新的班次，
                        System.out.println("当前班次时间"+clazz.getStartTime()+"当前班次结束时间"+ clazz.getEndTime()+"已使用"+clazz.getHours());
                        isFullNum++;
                        Clazz clazz1 = new Clazz(people.getStartTime(), new Time(people.getStartTime().getTime() + 2 * h), 2);
                        classes.add(clazz1);
                    }

                }
            }
        }
        //关店之后怎么操作

        //关店人数


        for (Clazz aClass : classes) {
            System.out.println(aClass);
        }
    }
//批量删除
    @Resource
    EmpService empService;
    @Test
    public void testMutilDelete(){

    }



    public void createSchedule(double shopSize,double predictNum,Long preNeedTime,Long endNeedTime,double startNeedPeoNum,double offShopNumOne,double offShopNumTwo,int defaultNum){
        predicts=predictService.list();
        preData(predictNum);
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
        int count=0;
        for (TimePeople people : timePeoples) {
            count++;

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
        //关店之后怎么操作

        //关店人数


        for (Clazz aClass : classes) {
            System.out.println(aClass);
        }
    }

    @Test
    public void testCreate(){
        createSchedule(100d,3.8d,1l,2l,50d,30,2,1);
    }

}
