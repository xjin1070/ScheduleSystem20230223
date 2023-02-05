package com.song.schedulesystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.song.schedulesystem.bean.Emp;

import java.util.List;


public interface EmpService extends IService<Emp> {
    Integer getMaxId();
}
