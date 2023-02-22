package com.song.schedulesystem.bean.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Clazz {

    private Date startTime;
    private Date endTime;
    private double hours;


}
