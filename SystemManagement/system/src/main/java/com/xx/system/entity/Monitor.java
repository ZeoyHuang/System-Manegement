package com.xx.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Monitor {
    private String computerInfo;
    private Double cpuUsage;
    private Integer memoryTotal;
    private Integer memoryFree;
    private Integer swapTotal;
    private Integer swapUsed;
    private Integer diskTotal;
    private Integer diskFree;
    private LocalDateTime currentTime;
}
