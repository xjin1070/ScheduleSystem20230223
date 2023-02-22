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
        int isFullNum=-1;
        TimePeople timePeople = timePeoples.get(0);
        Date startTime = new Date(timePeople.getStartTime().getTime()-30*m);
        int preClasses=100/50; //开店前的需要的班次，门店面积/50，50可调节
        for (int i = 0; i < preClasses; i++) {
            Clazz clazz = new Clazz(startTime, new Date(startTime.getTime() + 2*h), 2);
            classes.add(clazz);
        }
        for (TimePeople people : timePeoples) {
            if(people.getPeopleNum()==0){
                Clazz clazz = classes.get(isFullNum + 1);
                /**
                 * 首先判断在0流量的时候，判断在不在没有加满的班次之类，如果加满了
                 * 判断，当前班次满四个小时，能不能够上，当前需要班次的开始时间
                 * 如果够上了，那么就增加当前班次的时间，
                 * 没有够上就开一个新的班次
                 */
                if(!(clazz.getStartTime().before(people.getStartTime())&&clazz.getEndTime().after(people.getEndTime()))){
                    if(new Date((long)((4-clazz.getHours())*h+clazz.getEndTime().getTime())).after(new Date(people.getStartTime().getTime()+1*h))){

                    }
                }
            }
        }

    }
    // 生成排班表
}
