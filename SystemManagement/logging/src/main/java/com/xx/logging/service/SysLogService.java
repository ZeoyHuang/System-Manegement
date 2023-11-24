package com.xx.logging.service;

import com.xx.logging.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 12:23
 */
public interface SysLogService extends IService<SysLog> {

    void asyncSaveLog(SysLog sysLog);


    // querying by username
    List<SysLog> getLogsByUser(String username);
    // querying by date range
    List<SysLog> getLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    // querying exception logs
    List<SysLog> getExceptionLogs();
    SysLog getExceptionLogById(Long logId);
}
