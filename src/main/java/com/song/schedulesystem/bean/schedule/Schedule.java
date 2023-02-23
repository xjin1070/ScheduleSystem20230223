package com.song.schedulesystem.bean.schedule;

import com.song.schedulesystem.service.PredictService;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Schedule {
    //排班次
    List<Clazz> classes=new ArrayList<>();
    //读取客流量
    @Resource
    PredictService predictService;
    List<Predict> predicts=predictService.list();
    List<TimePeople> timePeoples=new ArrayList<>();
    int readyMinute=30;
    int size=100;
    public void preData() {
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
    }

    int h=60*60*1000;
    int m=60*1000;
    int s=1000;

    public void createSchedule(){
        //开店之前必须要做准备，所以直接取班次就可以了
        int isFullNum=-1;
        TimePeople timePeople = timePeoples.get(0);
        Time startTime = new Time(timePeople.getStartTime().getTime()-30*m);
        int preClasses=100/50; //开店前的需要的班次，门店面积/50，50可调节
        for (int i = 0; i < preClasses; i++) {
            Clazz clazz = new Clazz(startTime, new Time(startTime.getTime() + 2*h), 2);
            classes.add(clazz);
        }
        for (TimePeople people : timePeoples) {
            //这里0次和一次是相同的
            Double peopleNum = people.getPeopleNum();
            if(peopleNum==0) peopleNum =1d;
            int peopleCount=0;
            for (int i = 0; i < peopleNum; i++) {
                Clazz clazz = classes.get(isFullNum + peopleCount+1);
                /**
                 * 首先判断在0流量的时候，判断在不在没有加满的班次之类，如果加满了
                 * 判断，当前班次满四个小时，能不能够上，当前需要班次的开始时间
                 * 如果够上了，那么就增加当前班次的时间，
                 * 没有够上就开一个新的班次
                 *
                 *  多个班次怎么开？
                 *  我们就需要找到下一个班次
                 *  所以在一个周期里面，需要有个计数的循环
                 */
                if(!(clazz.getStartTime().before(people.getStartTime())&&clazz.getEndTime().after(people.getEndTime()))){
                    if(peopleCount==peopleNum) peopleCount=0;
                    if(new Time((long)((4-clazz.getHours())*h+clazz.getEndTime().getTime())).after(new Date(people.getStartTime().getTime()+1*h))){
                        //这个时候，我们能够满足的花我们不需要，重新开辟班次只需要，给之前找到的班次+1
                        clazz.setHours(clazz.getHours()+1);
                        if(clazz.getHours()==4) isFullNum++;
                    }else {
                        //如果不是的话，我们需要开启一个新的班次开始时间就是从当前客流量的时间+2小时
                        Clazz clazz1 = new Clazz(people.getStartTime(), new Time(people.getStartTime().getTime() + 2 * h), 2);
                        classes.add(clazz1);
                    }
                }
            }
        }
        for (Clazz aClass : classes) {
            System.out.println(aClass);
        }
    }
    // 生成排班表
}
