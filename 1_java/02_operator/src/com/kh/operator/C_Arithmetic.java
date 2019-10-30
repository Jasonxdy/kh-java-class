package com.kh.operator;

import java.util.Scanner;

public class C_Arithmetic {
	
	//산술 연산자
	// 기본적인 사칙연산 (+ - * /) + % (나머지) : 5개로 구성
	// [* / %]가 우선순위가 높음
	
	
	public void method1() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫번째 정수 입력 : ");
		int num1 = sc.nextInt();
		
		System.out.print("두번째 정수 입력 : ");
		int num2 = sc.nextInt();
		
		System.out.println("num1 + num2 : " + (num1 + num2));
		System.out.println("num1 - num2 : " + (num1 - num2));
		System.out.println("num1 * num2 : " + (num1 * num2));
		System.out.println("num1 / num2 : " + (num1 / num2));
		System.out.println("num1 % num2 : " + (num1 % num2));
		
	}
	
	public void method2() {
		
		// '%'를 주로 사용하는 경우! --> 어떤 수의 배수인지를 확인하는 경우에 주로 사용됨.
		
		// ex) 2의 배수인지 아닌지 확인하는 경우  -> 짝수 vs 홀수 구분
		// 10 % 2 == 5
		
		System.out.println("10 % 2 = " + 10%2);
		System.out.println("8795 % 2 = " + 8795%2);

		// 5의 배수인지 확인
		System.out.println("54865 % 5 = " + 54865%5);
	}
	
	public void method3() {

		//산술연산자 + 증감연산자 연습~~
		
		int a = 5;
		int b = 10;
		
		int c = (++a) + b; // c = 16
		// a = 6, b = 10
		
		int d = c / a; // d = 2
		// a = 6, b = 10, c = 16
		
		int e = c % a; // e = 4
		// a = 6, b = 10, c = 16, d = 2
		
		int f = e++; // f = 4
		// a = 6, b = 10, c = 16, d = 2, e = 5
		
		int g = (--b) + (d--); // g = 9 + 2 = 11
		// a = 6, b = 9, c = 16, d = 1, e = 5, f = 4
		
		int h = 2;
		
		int i = a++ + b / (--c / f) * (g-- - d) % (++e + h);
		//  i =  6  + 9/   (15/4)   * (11-1)    %   (6+2) = 6+30%8 = 12
		// a = 7, b = 9, c = 15, d = 1, e = 6, f = 4, g = 10, h = 2
		
		System.out.println("a  : " +a); // 7
		System.out.println("b  : " +b); // 9
		System.out.println("c  : " +c); // 15
		System.out.println("d  : " +d); // 1
		System.out.println("e  : " +e); // 6
		System.out.println("f  : " +f); // 4
		System.out.println("g  : " +g); // 10
		System.out.println("h  : " +h); // 2
		System.out.println("i  : " +i); // 12
		
	}

}
