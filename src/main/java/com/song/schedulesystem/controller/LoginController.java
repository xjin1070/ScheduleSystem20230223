package com.song.schedulesystem.controller;

import com.song.schedulesystem.bean.Emp;
import com.song.schedulesystem.controller.utils.R;
import com.song.schedulesystem.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
public class LoginController {
    @Resource
    LoginService loginService;

    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }
    //检查用户是否存在

    /**
     * 这里发现了不知道为什么post请求里面不能使用forward
     * get请求里面不能使用direct
     * 解决方法，在post请求里面定义可以跳转页面的请求，
     * 对应的请求放在外面我疯了真的
     * 导入模板引擎之后
     * 直接用页面就可以使用不带前缀后缀的名称
     * 转发和重定向就可以使用direct 和 forward
     * @return
     */
    @PostMapping ("/login")
    @ResponseBody
    public R checkLogin(@RequestBody Emp emp, HttpSession session)  {
        Integer id = emp.getID();
        Emp emp1 = loginService.getById(id);
        if(emp1.getID().equals(emp.getID()) && emp1.getPassword().equals(emp.getPassword())){
            //方便后面的读取
            session.setAttribute("loginUser",emp1);
            return new R(true,"成功");
        }
        return new R(true,"失败");
    }

}
