package com.song.schedulesystem.bean.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TimePeople {
    private Date startTime;
    private Date endTime;
    private Double peopleNum;
}
