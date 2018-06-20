package com.training.aspect;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.training.utils.LogUtil;

@Component
@Aspect
public class LogAspect {

	public static final Logger log = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("within(com.training.controller.*) || within(com.training.service.*)")
	public void doPointCut() {
	}

	@Before(value = "doPointCut()", argNames = "joinPoint")
	public void before(JoinPoint joinPoint) {
		LogUtil.debug(log, "Before method " + joinPoint.getSignature().getName() + "() in class "
				+ joinPoint.getTarget().getClass().getName());
	}

	@After(value = "doPointCut()", argNames = "joinPoint")
	public void after(JoinPoint joinPoint) {
		LogUtil.debug(log, "After method " + joinPoint.getSignature().getName() + "() in class "
				+ joinPoint.getTarget().getClass().getName());
	}
}
