package com.lee.logwithaop.service.impl;

import com.lee.logwithaop.entity.SysLogEntity;
import com.lee.logwithaop.service.LogService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author WangLe
 * @date 2019/12/12 16:26
 * @description
 */
@Service
public class LogServiceImpl implements LogService {
    /**
     * 这里使用Map模拟数据库的存储功能
     */
    private Map<Long, SysLogEntity> logDb = new HashMap<>();

    @Override
    public void saveLog(SysLogEntity logEntity) {
        logDb.put(logEntity.getId(), logEntity);
    }

    @Override
    public List<SysLogEntity> getAllLogs() {
        List<SysLogEntity> logs = new ArrayList<>();
        Set<Map.Entry<Long, SysLogEntity>> entries = logDb.entrySet();
        for (Map.Entry<Long, SysLogEntity> entry : entries) {
            logs.add(entry.getValue());
        }

        return logs;
    }
}
