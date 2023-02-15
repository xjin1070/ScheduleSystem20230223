package com.song.schedulesystem.bean.value;

import lombok.AllArgsConstructor;
import lombok.Data;

//用户自定义的规则放在这里，
//如果是固定规则则放在程序里面
//这里都会以json数据来进行存储
@Data
@AllArgsConstructor
public class PreferValue {
    //开店规则做的准备
    private double prepareTime;
    private double handleNumber=50;
    private String preparePosition;

    //店员需求量的缺省值
    private double shopEmpNeedNumber=3.8;

    //没有客流量的时候默认的需要的电源
    private Integer noGuestEmpNumber=1;

    //关店的首位工作
    private double endTime=2;
    //设置关店的职位
    private String endPosition;

}
