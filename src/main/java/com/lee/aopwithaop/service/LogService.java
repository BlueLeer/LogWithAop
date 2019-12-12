package com.lee.aopwithaop.service;

import com.lee.aopwithaop.entity.SysLogEntity;

import java.util.List;

/**
 * @author WangLe
 * @date 2019/12/12 16:24
 * @description
 */
public interface LogService {
    /**
     * 保存日志
     *
     * @param logEntity
     */
    void saveLog(SysLogEntity logEntity);

    /**
     * 获取所有的日志
     * @return
     */
    List<SysLogEntity> getAllLogs();
}
