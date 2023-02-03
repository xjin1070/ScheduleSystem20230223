package com.song.schedulesystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import javax.validation.constraints.Email;


@Data
public class Emp {
    @TableId(type = IdType.ASSIGN_ID)//雪花算法的使用
    private Integer ID;
    private String name;
    @Email
    private String Email;
    private Integer shopId;
}
