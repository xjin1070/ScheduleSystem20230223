package com.song.schedulesystem.controller;

import com.song.schedulesystem.bean.Shop;
import com.song.schedulesystem.controller.utils.R;
import com.song.schedulesystem.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * shop的增删查改
 */
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopServiceImpl shopService;

    @GetMapping("/recommend")
    public R getRecommendId(){
        return new R(true,shopService.getRecommendId());
    }

    //增加门店
    @PostMapping
    public R addShop(@RequestBody Shop shop){
        return new R(shopService.save(shop));
    }

    //删除门店
    @DeleteMapping("/{id}")
    public R delShop(@PathVariable Integer id){
        return new R(shopService.delShop(id));
    }

    //查询单个门店
    @GetMapping("/{id}")
    public R getShopById(@PathVariable Integer id){
        return new R(true,shopService.getById(id));
    }
    //因为门店比较少，就不做分页了，直接返回全部
    @GetMapping
    public R getAllShop(){
        return new R(true,shopService.list());
    }

    @PutMapping
    public R upDateShop(@RequestBody Shop shop){
        return new R(shopService.updateById(shop));
    }


}
