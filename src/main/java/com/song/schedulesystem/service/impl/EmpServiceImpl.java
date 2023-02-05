package com.song.schedulesystem.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.song.schedulesystem.bean.Emp;
import com.song.schedulesystem.mapper.EmpMapper;
import com.song.schedulesystem.service.EmpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    @Resource
    EmpMapper empMapper;
    //得到最大的id并推荐
    @Override
    public Integer getMaxId() {
        List<Integer> allID = empMapper.getAllID();
        Integer max = Collections.max(allID);
        return max+1;
    }
}
