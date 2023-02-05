package com.song.schedulesystem.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Shop {
    @JsonProperty(value = "ID")
    private Integer ID;
    private String name;
    private String address;
    private Double size;
}
