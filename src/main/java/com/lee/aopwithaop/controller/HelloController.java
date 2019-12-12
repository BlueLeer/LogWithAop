package com.lee.aopwithaop.controller;

import com.lee.aopwithaop.anno.SysLog;
import com.lee.aopwithaop.entity.SysLogEntity;
import com.lee.aopwithaop.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WangLe
 * @date 2019/12/12 16:19
 * @description
 */
@RestController
public class HelloController {
    @Autowired
    private LogService logService;

    @SysLog("打招呼")
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @SysLog("获取所有的日志")
    @GetMapping("/logs")
    public List<SysLogEntity> getLogs() {
        List<SysLogEntity> allLogs = logService.getAllLogs();
        return allLogs;
    }

}
