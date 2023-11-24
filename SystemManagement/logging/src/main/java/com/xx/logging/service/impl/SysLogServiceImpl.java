package com.xx.logging.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xx.logging.entity.SysLog;
import com.xx.logging.mapper.SysLogMapper;
import com.xx.logging.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lotey
 * @since 2023-08-12 12:23
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Async
    @Override
    public void asyncSaveLog(SysLog sysLog) {
        save(sysLog);
    }

    // querying by username
    @Override
    public List<SysLog> getLogsByUser(String username) {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return list(queryWrapper);
    }

    // querying by date range
    @Override
    public List<SysLog> getLogsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("create_time", startDate, endDate);
        return list(queryWrapper);
    }

    // querying exception logs
    @Override
    public List<SysLog> getExceptionLogs() {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("exception_detail");
        return list(queryWrapper);
    }

    @Override
    public SysLog getExceptionLogById(Long logId) {
        return getById(logId);
    }
}
