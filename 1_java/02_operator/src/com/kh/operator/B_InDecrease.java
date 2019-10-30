package com.kh.operator;

public class B_InDecrease {
	
	// 증감 연산자
	// 피연산자의 값에 1을 더하거나 빼는 연산자
	// 전위, 후위 연산자로 나뉨
	
	public void method1() {
		
		// 후위 연산자 : 다른 연산 우선 실행 후 연산
		int a = 10; // a==10
		int b = a++; // b==10, a==11
		System.out.println(a + ", " + b);
		
		// 전위 연산자 : 먼저 연산 후 다른 연산 실행
		int a2 = 10; // a2 ==10
		int b2 = ++a2; // a2 == 11, b2 = 11
		System.out.println(a2 + ", " + b2);
		
	}
	
	public void method2() {
		
		// 전위 증가 연산자 테스트
		int iNum = 10;
		System.out.println("연산 진행 전 : " + iNum);
		System.out.println("++iNum 1회 수행 : " + ++iNum); // 11
		System.out.println("++iNum 2회 수행 : " + ++iNum); // 12
		System.out.println("++iNum 3회 수행 : " + ++iNum); // 13
		System.out.println("++iNum 4회 수행 : " + ++iNum); // 14
		System.out.println("++iNum 5회 수행 : " + ++iNum); // 15		
		System.out.println("최종 iNum 값 : " + iNum); 
		System.out.println();
		System.out.println();
		
		
		// 후위 감소 연산자 테스트
		int iNum2 = 10;
		System.out.println("연산 진행 전 : " + iNum2);
		System.out.println("iNum2-- 1회 수행 : " + iNum2--);
		System.out.println("iNum2-- 2회 수행 : " + iNum2--);
		System.out.println("iNum2-- 3회 수행 : " + iNum2--);
		System.out.println("iNum2-- 4회 수행 : " + iNum2--);
		System.out.println("iNum2-- 5회 수행 : " + iNum2--);
		System.out.println("최종 iNum2 값 : " + iNum2);
		
	}
	
	public void method3() {
		
		int a = 10;
		int b = 20;
		int c = 30;
		
		System.out.println(a++); // 10
		// a = 11, b = 20, c = 30
		System.out.println(++a + b++); // 32
		// a = 12, b = 21, c = 30
		System.out.println(a++ + --b + --c); // 12 + 20 + 29 = 61
		// a = 13, b = 20, c =29
		
		System.out.println("a == " + a); // 13
		System.out.println("b == " + b); // 20
		System.out.println("c == " + c); // 29
		
	}
	
}
