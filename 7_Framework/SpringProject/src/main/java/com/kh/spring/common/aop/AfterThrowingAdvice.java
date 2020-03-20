package com.kh.spring.common.aop;

import java.sql.SQLSyntaxErrorException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

@Component
@Aspect
public class AfterThrowingAdvice {
	
	@AfterThrowing (pointcut = "CommonPointcut.implPointcut()",
					throwing = "exceptionObj")
	public void exceptionLog(JoinPoint jp, Exception exceptionObj) {
		
		// SQLSyntaxErrorException -> "SQL 구문 에러" 이런식으로 보여주고 싶을 때
		
		String logMsg = "예외 발생 내용 : ";
		
		if(exceptionObj instanceof IllegalArgumentException) {
			logMsg += "부적합한 값 입력";
		} else if (exceptionObj instanceof BadSqlGrammarException) {
			logMsg += "SQL 구문 오류";
		} else {
			logMsg += "기타 예외 발생";
		}
		
		System.out.println(logMsg);
		
	}
	

}
