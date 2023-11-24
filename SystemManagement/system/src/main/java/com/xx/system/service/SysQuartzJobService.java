package com.xx.system.service;

import com.xx.system.entity.SysQuartzJob;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 23:24
 */
public interface SysQuartzJobService extends IService<SysQuartzJob> {
    void addQuartzJob(SysQuartzJob quartzJob);
    void deleteQuartzJobById(Long jobId);
    void updateQuartzJob(SysQuartzJob quartzJob);
    List<SysQuartzJob> getAllQuartzJobs();
    SysQuartzJob getQuartzJobById(Long jobId);

    // execute jobs
    void executeQuartzJob(Long jobId) throws SchedulerException;
    void pauseQuartzJob(Long jobId) throws SchedulerException;

    // reschedule a Quartz job
    void rescheduleQuartzJob(Long jobId);

}
