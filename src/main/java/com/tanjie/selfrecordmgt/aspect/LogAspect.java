package com.tanjie.selfrecordmgt.aspect;

import com.tanjie.selfrecordmgt.anno.LoggerManage;
import com.tanjie.selfrecordmgt.model.UserDetailImpl;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tanjie
 */
@Aspect
@Order(2)
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ThreadLocal<Long> startTime = new ThreadLocal<>();


    /**
     * 定义AOP扫描路径
     * 第一个注解只扫描aopTest方法
     */
    @Pointcut("within(com.tanjie.selfrecordmgt.controller.*)")
    public void log() {
    }

    @Before("log()&& @annotation(loggerManage)")
    public void logBefore(JoinPoint joinPoint, LoggerManage loggerManage) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer requestURL = request.getRequestURL();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String controllerName = joinPoint.getSignature().getDeclaringType().getName();

        logger.info("执行Controller --" + loggerManage.description() + " -- 开始");
        startTime.set(System.currentTimeMillis());
        logger.info("请求URL ==> " + requestURL);
//        if (authentication.isAuthenticated()){
//            UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
//            logger.info("请求用户名 ==> " + userDetail.getUsername());
//        }
        logger.info("执行的controller ==>" + controllerName);
        logger.info(parseParames(joinPoint.getArgs()));
    }

    @AfterReturning("log()&& @annotation(loggerManage)")
    public void logAfterReturning(JoinPoint joinPoint, LoggerManage loggerManage) {
        logger.info("执行--" + loggerManage.description() + "--结束");
        logger.info("执行时间--" + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }

    @AfterThrowing(pointcut = "log()&& @annotation(loggerManage)", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex) {
        String message = ex.getMessage();
        logger.error("执行--" + loggerManage.description() + "--异常 ===>" + message);
    }

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0) {
            return "";
        }
        StringBuffer param = new StringBuffer("参数--");
        for (Object obj : parames) {
            String va = ToStringBuilder.reflectionToString(obj);
            param.append(va).append("  ");
        }
        return param.toString();
    }


}
