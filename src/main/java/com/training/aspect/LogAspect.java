package com.training.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

	@Pointcut("execution(* com.training..*..*(..))")
	public void doPointCut() {
	}

	@Before(value = "doPointCut()", argNames = "joinPoint")
	public void before(JoinPoint joinPoint) {
		System.out.println("Before method " + joinPoint.getSignature().getName() + "() in class "
				+ joinPoint.getTarget().getClass().getName());
	}

	@After(value = "doPointCut()", argNames = "joinPoint")
	public void after(JoinPoint joinPoint) {
		System.out.println("After method " + joinPoint.getSignature().getName() + "() in class "
				+ joinPoint.getTarget().getClass().getName());
	}
}
