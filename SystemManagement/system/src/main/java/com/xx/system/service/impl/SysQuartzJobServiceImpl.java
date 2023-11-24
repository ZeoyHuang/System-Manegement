package com.xx.system.service.impl;

import com.xx.system.entity.SysQuartzJob;
import com.xx.system.mapper.SysQuartzJobMapper;
import com.xx.system.quartz.QuartzJobExecutor;
import com.xx.system.service.SysQuartzJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
@Service
public class SysQuartzJobServiceImpl extends ServiceImpl<SysQuartzJobMapper, SysQuartzJob> implements SysQuartzJobService {
    @Autowired
    private Scheduler quartzScheduler;

    // Add
    @Override
    public void addQuartzJob(SysQuartzJob quartzJob) {
        save(quartzJob);
    }

    // Delete
    @Override
    public void deleteQuartzJobById(Long jobId) {
        removeById(jobId);
    }

    // Update
    @Override
    public void updateQuartzJob(SysQuartzJob quartzJob) {
        updateById(quartzJob);
    }

    // Retrieve
    @Override
    public List<SysQuartzJob> getAllQuartzJobs() {
        return list();
    }
    @Override
    public SysQuartzJob getQuartzJobById(Long jobId) {
        return getById(jobId);
    }

    // execute jobs
    @Override
    public void executeQuartzJob(Long jobId) {
        Optional<SysQuartzJob> jobOptional = Optional.ofNullable(getById(jobId));
        jobOptional.ifPresent(this::scheduleJob);
    }

    @Override
    public void pauseQuartzJob(Long jobId) throws SchedulerException {
        SysQuartzJob quartzJob = getById(jobId);
        if (quartzJob != null) {
            JobKey jobKey = JobKey.jobKey(quartzJob.getJobName(), quartzJob.getBeanName());
            quartzScheduler.pauseJob(jobKey);
        }
    }

    private void scheduleJob(SysQuartzJob quartzJob) {
        JobDetail jobDetail = buildJobDetail(quartzJob);
        Trigger trigger = buildJobTrigger(quartzJob);

        try {
            quartzScheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void unscheduleJob(Long jobId) {
        SysQuartzJob quartzJob = getById(jobId);
        if (quartzJob != null) {
            JobKey jobKey = JobKey.jobKey(quartzJob.getJobName(), quartzJob.getBeanName());
            try {
                quartzScheduler.deleteJob(jobKey);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void rescheduleQuartzJob(Long jobId) {
        SysQuartzJob quartzJob = getById(jobId);
        if (quartzJob != null) {
            unscheduleJob(jobId);
            scheduleJob(quartzJob);
        }
    }

    private JobDetail buildJobDetail(SysQuartzJob quartzJob) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobId", quartzJob.getJobId()); // Pass any job-related data here

        return JobBuilder.newJob(QuartzJobExecutor.class)
                .withIdentity(quartzJob.getJobName(), quartzJob.getBeanName())
                .withDescription(quartzJob.getDescription())
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(SysQuartzJob quartzJob) {
        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(quartzJob.getJobName() + "_trigger", quartzJob.getBeanName() + "_group")
                .withDescription("Trigger for job: " + quartzJob.getJobName())
                .forJob(buildJobDetail(quartzJob))
                .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()));

        if (quartzJob.getIsPause()) {
            triggerBuilder.startNow();
        } else {
            triggerBuilder.startAt(Date.from(Instant.now().plusSeconds(1)));
        }

        return triggerBuilder.build();
    }
}
