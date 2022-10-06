package com.qianzhimu.website.aspect;

import com.qianzhimu.website.entity.Log;
import com.qianzhimu.website.service.LogService;
import com.qianzhimu.website.utils.RequestHolder;
import com.qianzhimu.website.utils.SecurityUtils;
import com.qianzhimu.website.utils.StringUtils;
import com.qianzhimu.website.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * 日志切面
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    private final LogService logService;

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.qianzhimu.website.annotation.Log)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知，使用在方法logPointcut()上注册的切入点
     * @param proceedingJoinPoint
     * @return
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = proceedingJoinPoint.proceed();
        Log log = new Log("INFO", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        logService.save(getUsername(), StringUtils.getBrowser(request), StringUtils.getIp(request), proceedingJoinPoint, log);
        return result;
    }

    /**
     * 配置异常通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointcut()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log("ERROR", System.currentTimeMillis() - currentTime.get());
        log.setExceptionDetail(ThrowableUtil.getStackTrace(e).getBytes());
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        logService.save(getUsername(), StringUtils.getBrowser(request), StringUtils.getIp(request), (ProceedingJoinPoint) joinPoint, log);
    }

    public String getUsername() {
        try {
            return SecurityUtils.getCurrentUsername();
        }catch (Exception e){
            return "";
        }
    }

}
