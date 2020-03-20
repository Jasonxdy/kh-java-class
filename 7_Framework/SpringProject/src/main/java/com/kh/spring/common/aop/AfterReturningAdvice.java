package com.kh.spring.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kh.spring.member.model.vo.Member;

@Component
@Aspect
public class AfterReturningAdvice {
	
	
	private Logger logger = LoggerFactory.getLogger(AfterReturningAdvice.class);
	
	// login*(*) -> 메소드명이 login으로 시작하면서 매개변수 (파라미터)가 한개인 메소드
	@AfterReturning(pointcut = "execution(* com.kh.spring..*Impl.login*(*))", 
					returning = "returnObj") // returning으로 적어놨기 때문에 파라미터에 있는 returnObj가 알아서 대입이 됨
	public void loginLog(JoinPoint jp, Object returnObj) {
		
		// 접속자 IP 얻어오기
		
		// currentRequestAttributes(): 현재 요청을 보낸 사람?의 정보를 다 가져옴
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String ip = request.getRemoteAddr();
		
		String logMsg = "[IP : " + ip + "] ";  
		
		if(returnObj instanceof Member) {
			
			Member member = (Member)returnObj;
			
			if(member.getMemberId().equals("admin")) {
				logMsg += "관리자 로그인";
			} else {
				logMsg += "ID : " + member.getMemberId() + " 로그인";
			}
			
//			System.out.println(logMsg);
			
			logger.info(logMsg);
			
		}
		
		
	}
	
}
