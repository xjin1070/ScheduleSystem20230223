package com.song.schedulesystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.song.schedulesystem.bean.schedule.Predict;
import com.song.schedulesystem.mapper.PredictMapper;
import com.song.schedulesystem.service.PredictService;
import org.springframework.stereotype.Service;

@Service
public class PredictServiceImpl extends ServiceImpl<PredictMapper, Predict> implements PredictService {
}
