package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterAdvice {
	
	@After("CommonPointcut.implPointcut()")
	public void afterLog(JoinPoint jp) {
		String className = jp.getTarget().getClass().getSimpleName();
		String methodName = jp.getSignature().getName();
		
		System.out.println("[후처리] : " + className + " - " + methodName + "() - End");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	}

}
