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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping({"/","/login"})
public class LoginController {
    @Resource
    LoginService loginService;
    //检查用户是否存在
    @PostMapping
    public String checkLogin(@RequestBody Emp emp, Model model)  {
        Integer id = emp.getID();
        Emp emp1 = loginService.getById(id);
        if(emp1.getID().equals(emp.getID()) && emp1.getPassword().equals(emp.getPassword())){
            model.addAttribute("loginUser");
            return "forward:/success.html";
        }
        return "/index.html";
    }

}
