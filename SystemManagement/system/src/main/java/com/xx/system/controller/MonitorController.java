package com.xx.system.controller;

import com.xx.system.entity.Monitor;
import com.xx.system.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    private final MonitorService monitorService;

    @Autowired
    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @GetMapping("/monitor")
    public ResponseEntity<Monitor> getMonitorInfo() {
        Monitor monitor = monitorService.gatherSystemInfo();
        return ResponseEntity.ok(monitor);
    }
}
