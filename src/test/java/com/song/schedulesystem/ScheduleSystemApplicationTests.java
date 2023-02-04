package com.song.schedulesystem;
import com.song.schedulesystem.bean.Emp;
import org.apache.commons.codec.digest.DigestUtils;
import com.song.schedulesystem.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ScheduleSystemApplicationTests {

    @Resource
    LoginService loginService;
    @Test
    void contextLoads() {
    }

    //生成测试数据
    @Test
    public void insertEmp(){
        for (int i = 0; i < 10; i++) {
            Emp emp = new Emp();
            emp.setID(110+i);
            emp.setPassword(DigestUtils.md5Hex("11"+i));
            emp.setEmail("222"+i+"@qq.com");
            emp.setShopId(1);
            emp.setPosition("普通员工");
            emp.setName("员工"+i);
            loginService.save(emp);
        }
    }

}
