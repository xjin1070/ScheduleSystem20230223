package com.song.schedulesystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.song.schedulesystem.bean.Emp;
import com.song.schedulesystem.controller.utils.R;
import com.song.schedulesystem.service.EmpService;
import com.song.schedulesystem.service.impl.EmpServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 这个类做员工的增删查改
 */
@RestController
@RequestMapping("/emp")
public class EmpController {
    @Resource
    private EmpServiceImpl empService;

    /**
     * 保存员工的功能
     * @param emp 发送emp的json数据
     * @return R对象
     */
    @PostMapping
    public R saveEmp(@RequestBody Emp emp){
        return new R(empService.save(emp));
    }

    @DeleteMapping("/{id}")
    public R delEmp(@PathVariable Integer id){
        return new R(empService.removeById(id));
    }

    @GetMapping("/recommend")
    public R getRecommendId(){
        return new R(true,empService.getMaxId());
    }

    @PutMapping
    public R updateEmp(@RequestBody Emp emp){
        return new R(empService.updateById(emp));
    }

    //通过id好查询
    @GetMapping("/{id}")
    public R getEmpById(@PathVariable Integer id){
        return new R(true,empService.getById(id));
    }

    @GetMapping("/{currentPage}/{pageSize}")
    //这里的emp直接放在？后面的所以，到时候需要贴在后面
    public R getPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize,Emp emp){
        LambdaQueryWrapper<Emp> lqw  = new LambdaQueryWrapper<>();
        //通过职位和姓名进行模糊查询
        lqw.like(emp.getName()!=null,Emp::getName,emp.getName())
                .like(emp.getPosition()!=null,Emp::getPosition,emp.getPosition());
        //先分页，后加上限定条件
        Page<Emp> empPage = new Page<>(currentPage,pageSize);
        Page<Emp> page = empService.page(empPage, lqw);
        return new R(true,page);
    }
}
