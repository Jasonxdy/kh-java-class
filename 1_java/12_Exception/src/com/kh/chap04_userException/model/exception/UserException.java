package com.kh.chap04_userException.model.exception;

public class UserException extends Exception {
	
	// 사용자 정의 예외 클래스 생성 방법
	// -> 기존에 Java가 제공해주는 Exception 클래스 중 하나를 상속받으면 됨.
	// 보통 Exception 또는 RuntimeException을 상속 받음. -- 용도 (Unchecked, Checked)에 따라 다름
	
	public UserException() {
		super();
	}
	
	public UserException(String msg) {
		super(msg);
	}
	
	

}
