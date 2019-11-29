package com.kh.operator;

public class A_LogicalNegation {
	
	// 논리 부정 연산자 : !
	// 논리 값 (true / false)을 반대로 바꾸는 연산자
	
	public void method1() {
		boolean bool1 = false;
		boolean bool2 = true;
		System.out.println("bool1 : " + bool1); //false
		System.out.println("!bool1 : " + !bool1); //true
		
		System.out.println("bool2 : " + bool2); //true
		System.out.println("!bool2 : " + !bool2); //false
		
	}

}
