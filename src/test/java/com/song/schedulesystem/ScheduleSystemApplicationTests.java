package com.song.schedulesystem;
import com.song.schedulesystem.bean.Emp;
import com.song.schedulesystem.bean.Shop;
import com.song.schedulesystem.service.ShopService;
import org.apache.commons.codec.digest.DigestUtils;
import com.song.schedulesystem.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ScheduleSystemApplicationTests {

    @Resource
    LoginService loginService;
    @Resource
    ShopService shopService;
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

}
