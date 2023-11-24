package com.xx.tools.utils;

import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.*;
import oshi.util.FormatUtil;
import oshi.util.Util;
import oshi.hardware.CentralProcessor.TickType;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
public class OshiUtils {

    private final SystemInfo systemInfo;

    public OshiUtils() {
        this.systemInfo = new SystemInfo();
    }

    public String getComputerSystemInfo() {
        ComputerSystem computerSystem = systemInfo.getHardware().getComputerSystem();
        String manufacturer = computerSystem.getManufacturer();
        String model = computerSystem.getModel();
        String serialNumber = computerSystem.getSerialNumber();
        return "Manufacturer: " + manufacturer + ", Model: " + model + ", Serial Number: " + serialNumber;
    }

    public Double getCpuUsage() {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(1000);
        long[] ticks = processor.getSystemCpuLoadTicks();

        long totalPrev = Arrays.stream(prevTicks).sum();
        long total = Arrays.stream(ticks).sum();
        long totalTicks = total - totalPrev;
        long idleTicks = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];

        double cpuUsage = (1.0 - (double) idleTicks / totalTicks) * 100.0;
        return cpuUsage;
    }

    public Integer getMemoryTotal() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        return (int) memory.getTotal();
    }

    public Integer getMemoryFree() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        return (int) memory.getAvailable();
    }

    public Integer getSwapTotal() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        return (int) memory.getVirtualMemory().getSwapTotal();
    }

    public Integer getSwapUsed() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        return (int) memory.getVirtualMemory().getSwapUsed();
    }

    public Integer getDiskTotal() {
        long totalDiskSpace = 0;
        List<HWDiskStore> diskStores = systemInfo.getHardware().getDiskStores();
        for (HWDiskStore disk : diskStores) {
            totalDiskSpace += disk.getSize();
        }
        return (int) totalDiskSpace;
    }

    public Integer getDiskFree() {
        long totalFreeSpace = 0;
        List<HWDiskStore> diskStores = systemInfo.getHardware().getDiskStores();

        for (HWDiskStore disk : diskStores) {
            List<HWPartition> partitions = disk.getPartitions();
            if (partitions != null) {
                for (HWPartition partition : partitions) {
                    File partitionFile = new File(partition.getIdentification());
                    totalFreeSpace += partitionFile.getFreeSpace();
                }
            }
        }
        return (int) totalFreeSpace;
    }
}
