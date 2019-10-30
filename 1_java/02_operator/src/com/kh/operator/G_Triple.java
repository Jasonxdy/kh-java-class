package com.kh.operator;

import java.util.Scanner;

public class G_Triple {
	
	// 삼항 연산자
	
	// 조건식? 식1 : 식2;
	
	// 조건식 : 반드시 결과가 true 또는 false가 나오는 식
	// 식 1 : 조건식이 true일 때 수행되는 식
	// 식 2 : 조건식이 false일 때 수행되는 식 
	
	public void method1() {
		// 입력받은 정수가 짝수면 "짝수입니다" 홀수면 "홀수입니다" 출력하기
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		String result = (input%2 == 0)? "짝수입니다" : "홀수입니다";
		System.out.println(result);
	}
	
	public void method2() {
		// 입력받은 정수가 양수인지 음수인지 0인지 판별하기
		Scanner sc = new Scanner (System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		String result = input>0 ? "양수" : input==0? "0" : "음수"; // 삼항 연산자 중첩 사용
		System.out.println(result + "입니다.");
	}
	
	public void method3() {
		
		// 두 수와 '+' 또는 '-' 를 입력 받아 알맞은 계산 결과 출력하기
		// 단, '+' 또는 '-' 이외의 연산자 입력 시 "잘못 입력하셨습니다" 출력하기
		
		Scanner sc = new Scanner(System.in);
		System.out.print("첫번째 정수 입력 : ");
		int num1 = sc.nextInt();
		System.out.print("두번째 정수 입력 : ");
		int num2 = sc.nextInt();
		sc.nextLine(); // 버퍼에 남아있는 줄바꿈 문자 (\n) 제거
		System.out.print("연산자 (+ or -) : ");
		char op = sc.nextLine().charAt(0);
		// System.out.println(num1 + " / " + num2 + " / " + op);
		
		String result = op=='+'? num1 + num2 +"": op=='-'? num1-num2 + "" :"잘못 입력하셨습니다";
		
		System.out.println(result);
		
		
		
		
		
		
	}

	
	
}
