package com.kh.spring.common.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {

	// 별도의 Pointcut을 지정하여 필요할 때 호출하여 사용
	@Pointcut("execution( * com.kh.spring..*Impl.* (..) )")
	public void implPointcut() {}
	
}
