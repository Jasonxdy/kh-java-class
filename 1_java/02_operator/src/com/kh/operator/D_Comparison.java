package com.kh.operator;

import java.util.Scanner;

public class D_Comparison {
	
	//비교 연산자 (==관계 연산자)
	/* - 두 피연산자를 비교하는 연산자;
	 * - 비교 연산자는 조건을 만족하면 true / 아니면 false를 반환함.
	 * 
	 * a < b : a가 b보다 작은가? (a값이 b값 미만인가)
	 * a > b : a가 b보다 큰가? (a값이 b값 초과인가)
	 * a == b: a와 b가 같은가?
	 * a != b: a와 b가 다른가?
	 * a <= b: a가 b보다 작거나 같은가? (a값이 b값 이하인가)
	 * a >= b: a가 b보다 크거나 같은가? (a값이 b값 이상인가)
	 * (6가지, 부등호는 무조건 등호보다 먼저 나옴)
	 * 
	 * 
	 * 
	 * */
	public void method1() {
		int a = 10;
		int b = 25;
		
		boolean result1, result2, result3;
		// 관례상 좋지 않은 표현 (실무에서 하지 말것)
		
		result1=a==b; // false
		result2=a<=b; // true
		result3=a>b; // false
		
		System.out.println("result1 : " +result1);
		System.out.println("result2 : " +result2);
		System.out.println("result3 : " + result3);
		
		System.out.println("a는 짝수인가? : " + ((a%2)==0)); //true
		System.out.println("b는 홀수인가? : " + ((b%2)!=0)); //true
		
	}
	
	public void method2() {
		//키보드로 입력받은 값이 3의 배수인지 확인하기
		
		Scanner sc = new Scanner(System.in);
		System.out.print("3의 배수인지 확인하고 싶은 숫자 입력 : ");
		int num = sc.nextInt();
		
		System.out.println("해당 숫자가 3의 배수라는 것은 " + (num%3==0) + "입니다.");
		
	}

}
