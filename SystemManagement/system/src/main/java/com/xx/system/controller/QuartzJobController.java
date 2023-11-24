package com.xx.system.controller;

import com.xx.system.entity.SysQuartzJob;
import com.xx.system.service.SysQuartzJobService;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartz-job")
public class QuartzJobController {
    @Autowired
    private SysQuartzJobService sysQuartzJobService;

    @Autowired
    private Scheduler quartzScheduler;

    // Add a new QuartzJob
    @PostMapping("/add")
    public ResponseEntity<String> addQuartzJob(@RequestBody SysQuartzJob quartzJob) {
        sysQuartzJobService.save(quartzJob);
        return ResponseEntity.ok("QuartzJob added successfully");
    }

    // Delete a QuartzJob by ID
    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<String> deleteQuartzJob(@PathVariable Long jobId) {
        sysQuartzJobService.removeById(jobId);
        return ResponseEntity.ok("QuartzJob deleted successfully");
    }

    // Update a QuartzJob
    @PutMapping("/update")
    public ResponseEntity<String> updateQuartzJob(@RequestBody SysQuartzJob quartzJob) {
        sysQuartzJobService.updateById(quartzJob);
        return ResponseEntity.ok("QuartzJob updated successfully");
    }

    // Retrieve a QuartzJob by ID
    @GetMapping("/get/{jobId}")
    public ResponseEntity<SysQuartzJob> getQuartzJob(@PathVariable Long jobId) {
        SysQuartzJob quartzJob = sysQuartzJobService.getById(jobId);
        if (quartzJob != null) {
            return ResponseEntity.ok(quartzJob);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //
    @PostMapping("/execute/{jobId}")
    public ResponseEntity<String> executeJob(@PathVariable Long jobId) throws SchedulerException {
        sysQuartzJobService.executeQuartzJob(jobId);
        return ResponseEntity.ok("Job execution triggered.");
    }

    @PostMapping("/pause/{jobId}")
    public ResponseEntity<String> pauseJob(@PathVariable Long jobId) {
        try {
            sysQuartzJobService.pauseQuartzJob(jobId);
            return ResponseEntity.ok("Job paused.");
        } catch (SchedulerException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while pausing the job.");
        }
    }

    @PostMapping("/reschedule/{jobId}")
    public ResponseEntity<String> rescheduleJob(@PathVariable Long jobId) {
        sysQuartzJobService.rescheduleQuartzJob(jobId);
        return ResponseEntity.ok("Job rescheduled.");
    }
}
