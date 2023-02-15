package com.song.schedulesystem.bean;

import com.song.schedulesystem.bean.value.PreferValue;
import lombok.AllArgsConstructor;
import lombok.Data;
//员工偏好
@Data
@AllArgsConstructor
public class EmpPrefer {
    private Integer empId;
    private PreferValue preferValue;
}
