package com.song.schedulesystem.bean.value;

import lombok.AllArgsConstructor;
import lombok.Data;

//这个用来存储员工偏好规则
@Data
@AllArgsConstructor
public class RuleValue {
    private String dayTime; //每天工作时间偏好
    private String weekTime;//每周工作时间偏好
    private String classTime;//每次班次时长偏好
}
