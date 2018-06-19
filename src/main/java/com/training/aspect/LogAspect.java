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

@Component
@Aspect
public class LogAspect {

	public static final Logger log = LoggerFactory.getLogger(LogAspect.class);

	@Pointcut("execution(* com.training..*..*(..))")
	public void doPointCut() {
	}

	@Before(value = "doPointCut()", argNames = "joinPoint")
	public void before(JoinPoint joinPoint) {
		LogUtil.info(log, "Before method " + joinPoint.getSignature().getName() + "() in class "
				+ joinPoint.getTarget().getClass().getName());
	}

	@After(value = "doPointCut()", argNames = "joinPoint")
	public void after(JoinPoint joinPoint) {
		LogUtil.info(log, "After method " + joinPoint.getSignature().getName() + "() in class "
				+ joinPoint.getTarget().getClass().getName());
	}
}
