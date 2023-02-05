package com.song.schedulesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.song.schedulesystem.bean.Emp;
import com.song.schedulesystem.bean.Shop;
import com.song.schedulesystem.mapper.EmpMapper;
import com.song.schedulesystem.mapper.ShopMapper;
import com.song.schedulesystem.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    EmpMapper empMapper;
    public Integer getRecommendId(){
        List<Integer> allId = shopMapper.getAllId();
        Integer max = Collections.max(allId);
        return max+1;
    }

    //删除门店同时需要删除门店里面的员工
    @Transactional
    public Boolean delShop(Integer id){
        //1.删除所有id这个id的员工
        LambdaQueryWrapper<Emp> emplqw = new LambdaQueryWrapper<>();
        emplqw.eq(Emp::getShopId,id);
        empMapper.delete(emplqw);
        shopMapper.deleteById(id);
        return true;
    }
}
