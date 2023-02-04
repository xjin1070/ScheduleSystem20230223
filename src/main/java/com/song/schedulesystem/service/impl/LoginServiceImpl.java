package com.song.schedulesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.song.schedulesystem.bean.Emp;
import com.song.schedulesystem.mapper.EmpMapper;
import com.song.schedulesystem.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<EmpMapper, Emp> implements LoginService {
}
