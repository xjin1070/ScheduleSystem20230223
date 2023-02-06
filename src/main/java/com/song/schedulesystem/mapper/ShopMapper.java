package com.song.schedulesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.song.schedulesystem.bean.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ShopMapper extends BaseMapper<Shop> {

    @Select("select ID from shop")
    List<Integer> getAllId();
}
