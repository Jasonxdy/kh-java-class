package com.kh.chap03_throws.controller;

import java.io.IOException;

public class ThrowsController {
	
	public void method1 () {
		System.out.println("method1() 호출");
		try {
			method2();			
		} catch (IOException e) {
			System.out.println("예외 발생");
			e.printStackTrace();
		}
	}
	
	public void method2 () throws IOException {
		System.out.println("method2() 호출");
		method3();
	}
	
	public void method3 () throws IOException, ArithmeticException {
								// 여러 Exception을 전가시킬 수도 있음
		// 이때 전가되는 Exception이 Checked인 경우
		// 호출부에서 반드시 예외처리가 필요하지만,
		// Unchecked인 경우 호출부에서 예외처리를 선택적으로 하면 됨
		// method2에서 ArithmeticException을 throws하지 않아도 컴파일 에러가 안나는 이유는
		// IOException은 Checked이고, ArithmeticException은 Unchecked이기 때문!.
		
		System.out.println("method3() 호출");
		method4();
		
		int num = 2/0;
	}
	
	public void method4 () throws IOException {
		System.out.println("method4() 호출");
		
		// throw : 강제로 Exception 발생시키는 키워드
		// throws : 발생된 Exception을 호출부로 전가하는 키워드
		throw new IOException();
	}
	
	
	

}
