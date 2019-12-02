package com.kh.variable;

public class C_Constant {
	
	//상수 테스트
	public void finalKeyword() {
		
		// 변수 선언 및 초기화
		int num1 = 23;

		// 상수 선언 및 초기화
		final int NUM2 = 100;
		// --> 상수의 이름은 무조건 대문자!!!
		
		System.out.println("[변경전]");
		System.out.println("num1 : " + num1);
		System.out.println("num2 : " + NUM2);
		
		// 변수, 상수값 변경
		num1 = 24;
		//NUM2 = 200;
		// 에러 발생! 상수는 처음 초기화 이후 값 변경 불가
		System.out.println("[변경후]");
		System.out.println("num1 : " + num1);
		System.out.println("num2 : " + NUM2);
		
		final double PI = 3.141592;
		
		
	}

}
