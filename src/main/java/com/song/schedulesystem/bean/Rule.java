package com.song.schedulesystem.bean;

import com.song.schedulesystem.bean.value.RuleValue;
import lombok.AllArgsConstructor;
import lombok.Data;
//门店规则
@Data
@AllArgsConstructor
public class Rule {
    private Integer shopId;
    private RuleValue ruleValue;
}
