package com.kh.spring.common.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component // Spring 컨테이너가 @Aspect가 적용된 객체를 제어해야 하므로 (IOC) Bean으로 등록되어 있어야 함.
@Aspect // 해당 클래스가 AOP에 사용될 것이란걸 명시
		// aspect = advice + pointcut
public class LogAdvice {
	
	// Pointcut 지정 : advice가 적용될 부분 지정
	// execution : 특정 객체 (메소드)가 실행 (호출)되는 시점 
	// execution ([접근 제한자] [리턴타입] [클래스명] [메소드명] [파라미터])
	
	// * : 모두
	// .. : 이하 모두
	// *Impl : 클래스명 마지막이 Impl인 클래스
	
//	// 별도의 Pointcut을 지정하여 필요할 때 호출하여 사용
//	@Pointcut("execution( * com.kh.spring..*Impl.* (..) )")
//	public void implPointcut() {}
	
	
	// before advice
	// @Before("execution( * com.kh.spring..*Impl.* (..) )")
	@Before("CommonPointcut.implPointcut()")
	public void startLog() {
		System.out.println("[log] : 비즈니스 로직 시작");
	}
	
	
	// @After : 예외 발생 여부와 관계 없이 무조건 실행됨.
	// after advice
	@After("CommonPointcut.implPointcut()")
	public void endLog() {
		System.out.println("[log] : 비즈니스 로직 종료");
	}
	
	
}
