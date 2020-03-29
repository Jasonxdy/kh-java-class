package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeAdvice {
	
	@Before("CommonPointcut.implPointcut()")
	public void beforeLog(JoinPoint jp) { // JoinPoint : Spring Container가 알아서 작성해줌 --> Spring AOP pdf 18pg 참조 
		
		// jp.getTarget() : 대상 객체 반환
		// jp.getSigniture() : 대상 객체 메소드 정보 반환
		String className = jp.getTarget().getClass().getSimpleName();
		String methodName = jp.getSignature().getName();
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("[전처리] : " + className + " - " + methodName + "() - Start");
		
	}
	
	

}
