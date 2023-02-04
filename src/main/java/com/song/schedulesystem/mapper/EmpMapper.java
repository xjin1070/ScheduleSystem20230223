package com.song.schedulesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.schedulesystem.bean.Emp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
}
