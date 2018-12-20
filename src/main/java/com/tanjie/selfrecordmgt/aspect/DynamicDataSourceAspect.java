package com.tanjie.selfrecordmgt.aspect;

import com.tanjie.selfrecordmgt.config.dataconfig.DynamicDataSourceContextHolder;
import com.tanjie.selfrecordmgt.model.constant.VersionInfo;
import com.tanjie.selfrecordmgt.utils.HeaderProcessUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Order(1)
@Component
@Slf4j
public class DynamicDataSourceAspect {


    /**
     * Dao aspect.
     */
    @Pointcut("within(com.tanjie.selfrecordmgt.controller.*)")
    public void daoAspect() {
    }

    /**
     * Switch DataSource
     *
     * @param point the point
     */
    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("切换数据源的切面");
        String accept = attributes.getRequest().getHeader(HttpHeaders.ACCEPT);
        log.info(accept);
        String versionInfo = HeaderProcessUtils.handleAccept(accept);
        log.info("versionInfo ===>" + versionInfo);
        //请求头中有正确的版本号信息
        if (StringUtils.isNotEmpty(versionInfo) && HeaderProcessUtils.isContainVersionInfo(versionInfo)) {
            log.info("当前版本号为 :" + versionInfo);
            if (StringUtils.equals(versionInfo, VersionInfo.DEV.getName())) {
                DynamicDataSourceContextHolder.useDevDataSource();
            } else if (StringUtils.equals(versionInfo, VersionInfo.PROD.getName())) {
                DynamicDataSourceContextHolder.useProdDataSource();
            } else {
                DynamicDataSourceContextHolder.useDevDataSource();
            }
        }

    }

    /**
     * Restore DataSource
     *
     * @param point the point
     */
    @After("daoAspect())")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.debug("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }
}
