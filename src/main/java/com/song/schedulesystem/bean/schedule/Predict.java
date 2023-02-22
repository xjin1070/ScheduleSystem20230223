package com.song.schedulesystem.bean.schedule;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class Predict {
    @TableId(type= IdType.NONE)
    private Integer shopId;
    private Date date;

    private Time startTime;
    private Time endTime;

    private Double passengerTraffic;

}
