package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AroundAdvice {
	
	// @Around : @Before + @After
	// ProceedingJoinPoint.proceed() : 전, 후처리 기준점 역할
	@Around("CommonPointcut.implPointcut()")
	public Object aroundLogs(ProceedingJoinPoint jp) throws Throwable {
		
		// 메소드 수행시간 체크
		String methodName = jp.getSignature().getName();
		
		// StopWatch : 스프링에서 제공하는 스톱워치 클래스
		StopWatch sw = new StopWatch();
		sw.start(); // 시간 측정 시작
		// 여기 까지가 Before
		
		Object obj = jp.proceed();
		
		
		// 여기 이후가 After
		sw.stop(); // 시간 측정 종료
		System.out.println(methodName + "() 수행 시간 : " + sw.getTotalTimeMillis() + "(ms)");
			
		return obj;
		
	}

}
