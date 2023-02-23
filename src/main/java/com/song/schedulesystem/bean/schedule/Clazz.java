package com.song.schedulesystem.bean.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {

    private Date startTime;
    private Date endTime;
    private double hours;


}
