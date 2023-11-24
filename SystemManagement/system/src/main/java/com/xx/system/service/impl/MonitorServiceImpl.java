package com.xx.system.service.impl;

import com.xx.system.entity.Monitor;
import com.xx.system.service.MonitorService;
import com.xx.tools.utils.OshiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.hardware.*;

import java.time.LocalDateTime;

@Service
public class MonitorServiceImpl implements MonitorService {
    private final OshiUtils oshiUtils;

    @Autowired
    public MonitorServiceImpl(OshiUtils oshiUtils) {
        this.oshiUtils = oshiUtils;
    }

    @Override
    public Monitor gatherSystemInfo() {
        Monitor monitor = new Monitor();

        // Get ComputerSystem information
        String computerInfo = oshiUtils.getComputerSystemInfo();
        monitor.setComputerInfo(computerInfo);

        // Get CPU usage
        Double cpuUsage = oshiUtils.getCpuUsage();
        monitor.setCpuUsage(cpuUsage);

        // Get Memory information
        Integer memoryTotal = oshiUtils.getMemoryTotal();
        Integer memoryFree = oshiUtils.getMemoryFree();
        monitor.setMemoryTotal(memoryTotal);
        monitor.setMemoryFree(memoryFree);

        // Get Swap information
        Integer swapTotal = oshiUtils.getSwapTotal();
        Integer swapUsed = oshiUtils.getSwapUsed();
        monitor.setSwapTotal(swapTotal);
        monitor.setSwapUsed(swapUsed);

        // Get Disk information
        Integer diskTotal = oshiUtils.getDiskTotal();
        Integer diskFree = oshiUtils.getDiskFree();
        monitor.setDiskTotal(diskTotal);
        monitor.setDiskFree(diskFree);

        // Set current time
        monitor.setCurrentTime(LocalDateTime.now());

        return monitor;
    }
}
