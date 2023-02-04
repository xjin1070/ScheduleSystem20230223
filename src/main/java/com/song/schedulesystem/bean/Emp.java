package com.song.schedulesystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    @JsonProperty(value = "ID")
    private Integer ID;
    private String name;
    private String password;
    private String position;
    @Email
    private String Email;
    private Integer shopId;
}
