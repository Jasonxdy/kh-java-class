package com.kh.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	
	// System.out.print -> 속도가 엄청 느림.. 따라서 Logger 이용
	private Logger logger = LoggerFactory.getLogger(LogTest.class);
	
	public void test() {
		/*
		 TRACE < DEBUG < INFO < WARN < ERROR < FATAL 
		# Log Level
		# TRACE : 추적 레벨은 Debug보다 좀더 상세한 정보를 나타냄
		# DEBUG : 프로그램을 디버깅하기 위한 정보 지정
		# INFO :  상태변경과 같은 정보성 메시지를 나타냄 
		# WARN :  처리 가능한 문제, 향후 시스템 에러의 원인이 될 수 있는 경고성 메시지를 나타냄 
		# ERROR :  요청을 처리하는 중 문제가 발생한 경우
		# FATAL :  아주 심각한 에러가 발생한 상태, 시스템적으로 심각한 문제가 발생해서 어플리케이션 작동이 불가능할 경우
		*/
		
		logger.trace("trace 로그");
		logger.debug("debug 로그");
		logger.info("info 로그");
		logger.warn("warn 로그");
		logger.error("error 로그");
//		logger.fatal("fatal 로그");  --> slf4j에서 fatal 레벨은 지원하지 않음
		
	}
	
	public static void main(String[] args) {
		new LogTest().test();
	}
}
