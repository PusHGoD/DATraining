package com.training.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.training.utils.LogUtil;

@Aspect
@Component
public class LogAspect {

	@Pointcut("within(com.training.controller.*) || within(com.training.service.*)")
	public void doPointCut() {
	}

	@Before(value = "doPointCut()", argNames = "joinPoint")
	public void before(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		LogUtil.debug(logger, "Starting method " + joinPoint.getSignature().getName() + "().");
	}

	@After(value = "doPointCut()", argNames = "joinPoint")
	public void after(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		LogUtil.debug(logger, "Ending method " + joinPoint.getSignature().getName() + "().");
	}
}
