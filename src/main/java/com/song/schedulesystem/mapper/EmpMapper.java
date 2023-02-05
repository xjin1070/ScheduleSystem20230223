package com.song.schedulesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.schedulesystem.bean.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
    //得到所有的账号
    @Select("select ID from emp")
    List<Integer> getAllID();
}
