package com.song.schedulesystem;
import com.song.schedulesystem.bean.Emp;
import com.song.schedulesystem.bean.Shop;
import com.song.schedulesystem.bean.schedule.Clazz;
import com.song.schedulesystem.bean.schedule.Predict;
import com.song.schedulesystem.bean.schedule.TimePeople;
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
    @Test
    public void createSchedule(){
        predicts=predictService.list();
        preData();
        //开店之前必须要做准备，所以直接取班次就可以了
        int isFullNum=-1;
        TimePeople timePeople = timePeoples.get(0);
        Time startTime = new Time(timePeople.getStartTime().getTime()-1*h);
        int preClasses=100/50; //开店前的需要的班次，门店面积/50，50可调节
        for (int i = 0; i < preClasses; i++) {
            Clazz clazz = new Clazz(startTime, new Time(startTime.getTime() + 2*h), 2);
            classes.add(clazz);
        }
        for (TimePeople people : timePeoples) {
            //这里0次和一次是相同的
            Double peopleNum = people.getPeopleNum();
            if(peopleNum==0) peopleNum =1d;
            for (int i = 0; i < peopleNum; i++) {
                Clazz clazz=new Clazz();
                try{
                    clazz = classes.get(isFullNum + i+1);
                }catch (Exception e){
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
                 */
                if(!(clazz.getStartTime().before(new Time(people.getStartTime().getTime()-1*m))&&clazz.getEndTime().after(new Time(people.getEndTime().getTime()-1*m)))){
                    if(new Time((long)((4-clazz.getHours())*h+clazz.getEndTime().getTime())).after(new Time(people.getStartTime().getTime()-1*m))) {
                        //这个时候，我们能够满足的花我们不需要，重新开辟班次只需要，给之前找到的班次+1
                        if (clazz.getHours() != 4) {
                            clazz.setEndTime(new Time(clazz.getEndTime().getTime() + 1 * h));
                            clazz.setHours(clazz.getHours() + 1);
                            if(clazz.getHours()==4) isFullNum++;
                        }
                    }
//                    else {
////                        System.out.println("当前班次时间"+clazz.getStartTime()+"当前班次结束时间"+ clazz.getEndTime()+"已使用"+clazz.getHours());
//                        //如果不是的话，我们需要开启一个新的班次开始时间就是从当前客流量的时间+2小时
//                        Clazz clazz1 = new Clazz(people.getStartTime(), new Time(people.getStartTime().getTime() + 2 * h), 2);
//                        classes.add(clazz1);
//                    }
                }
            }
        }
        for (Clazz aClass : classes) {
            System.out.println(aClass);
        }
    }

}
