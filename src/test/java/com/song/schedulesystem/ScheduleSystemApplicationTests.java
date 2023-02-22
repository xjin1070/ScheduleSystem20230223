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

}
