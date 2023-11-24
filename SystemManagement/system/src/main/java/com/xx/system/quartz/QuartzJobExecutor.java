package com.xx.system.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.xx.system.entity.SysQuartzJob;
import com.xx.system.service.SysQuartzJobService;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobExecutor implements Job {
    @Autowired
    private SysQuartzJobService sysQuartzJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Retrieve jobId from the JobDataMap
        Long jobId = context.getJobDetail().getJobDataMap().getLong("jobId");

        // Fetch job details from the database
        SysQuartzJob quartzJob = sysQuartzJobService.getById(jobId);

        if (quartzJob != null) {
            // Implement your job logic here based on quartzJob configuration
        } else {
            // Handle the case when the job details are not found
        }
    }
}
