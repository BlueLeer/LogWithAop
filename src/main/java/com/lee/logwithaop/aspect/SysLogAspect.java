package com.lee.logwithaop.aspect;

import com.google.gson.Gson;
import com.lee.logwithaop.anno.SysLog;
import com.lee.logwithaop.entity.SysLogEntity;
import com.lee.logwithaop.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 切面类(切面类包含了切点和通知)
 *
 * @author WangLe
 * @date 2019/12/12 16:30
 * @description
 */
@Aspect
@Component
public class SysLogAspect {
    private AtomicLong idCreator = new AtomicLong(1L);

    @Autowired
    private LogService logService;

    /**
     * 定义了切点,意思是只要方法上有SysLog这个注解的都是切点
     */
    @Pointcut("@annotation(com.lee.logwithaop.anno.SysLog)")
    public void logPointCut() {

    }

    /**
     * 环绕通知 只要是logPointCut()切点满足的都执行此环绕通知
     * point里面记录了被代理方法的各种信息
     *
     * @param point
     * @return 被代理的方法执行的返回值
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 执行开始时间
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        // 执行该方法总共花费的时间
        long time = System.currentTimeMillis() - start;

        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint point, long time) {
        // 方法签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        SysLogEntity log = new SysLogEntity();
        log.setId(idCreator.getAndIncrement());
        SysLog annotation = method.getAnnotation(SysLog.class);
        log.setOperation(annotation.value());

        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethod(className + "." + methodName + "()");

        Object[] args = point.getArgs();
        String argsJson = new Gson().toJson(args);
        log.setParams(argsJson);

        // 剩下的参数就不一一列举了

        // 执行保存
        logService.saveLog(log);
    }
}
