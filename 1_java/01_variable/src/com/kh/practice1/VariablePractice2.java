package com.kh.practice1;

import java.util.Scanner;

public class VariablePractice2 {
	
	public void practice2 () {
		Scanner sc = new Scanner (System.in);
		
		System.out.print("첫번째 정수 : ");
		int firstNum = sc.nextInt();
		
		System.out.print("두번재 정수 : ");
		int secondNum = sc.nextInt();
		
		System.out.println("더하기 결과 : " + (firstNum + secondNum));
		System.out.println("빼기 결과 : " + (firstNum - secondNum));
		System.out.println("곱하기 결과 : " + (firstNum*secondNum));
		System.out.println("나누가 몫 결과 : " + (firstNum/secondNum));
	}

}
