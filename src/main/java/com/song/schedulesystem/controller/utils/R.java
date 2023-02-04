package com.song.schedulesystem.controller.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R {
    private Boolean flag;
    private String msg;
    public R(Boolean flag) {
        this.flag = flag;
    }

    public R(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public R(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    private Object data;
}
