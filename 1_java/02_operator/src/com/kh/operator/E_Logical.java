package com.kh.operator;

import java.util.Scanner;

public class E_Logical {
	
	// 논리 연산자
	/*
	 * 논리 값 두개를 비교하는 연산자
	 * AND (&&) : 그리고, ~이면서
	 * OR (||) : 또는, ~이거나
	 * 
	 * */
	
	public void method1() {
		// 입력받은 정수가 1~100 사이의 수인지 확인하기
		//Scanner필요, 정수니까 nextInt(), 확인하기는 t/f 나오겠구나
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		boolean isTrue = (input>=1)&&(input<=100);
		
		System.out.println(input + "은/는 1부터 100사이의 값 : " + isTrue);
		
		//System.out.println("입력받은 수는 1~100 사이의 값인지? : " + ((1<=input)&&(input<=100)));
	}

	public void method2() {
		
		// 입력받은 문자가 영어 대문자인지 확인하기
		Scanner sc = new Scanner(System.in);
		System.out.print("문자 입력 : ");
		char ch = sc.nextLine().charAt(0);
		
		/*
		int input = ch; // ---> 이게 중요, 자동 형변환됨
		boolean isTrue =  (65<=input)&&(input<=90);
		*/
		
		boolean isTrue = (ch >= 'A')&&(ch <= 'Z'); // ---> char는 정수값으로 저장되므로 char끼리도 비교 연산자 가능함!! 중요!
		
		System.out.println("입력받은 문자 "+ch+"은/는 영어 대문자가 : " + isTrue);
		
	}
	
	public void method3() {
		
		// 입력받은 문자가 영어인지 (대, 소문자 구분 X) 확인하기
		Scanner sc = new Scanner (System.in);
		System.out.print("문자 입력 : ");
		char ch = sc.nextLine().charAt(0);
		boolean isTrue = (('a'<=ch)&&(ch<='z'))||(('A'>=ch)&&(ch<='Z'));
		System.out.println("입력해주신 문자 "+ch+"은/는 알파벳인가 : "+isTrue);
		
	}

}
