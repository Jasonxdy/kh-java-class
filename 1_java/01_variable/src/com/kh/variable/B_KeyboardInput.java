package com.kh.variable;

import java.util.Scanner;

public class B_KeyboardInput {
	
	public void inputScanner1() {
	
		Scanner sc = new Scanner(System.in);
		
		System.out.print("당신의 이름은 무엇입니까? : ");

		String name = sc.nextLine();
		
		System.out.println("당신의 이름은 " + name + "입니다.");
		
	}
	
	public void inputScanner2() {
		
		Scanner sc = new Scanner(System.in); // System.in = 키보드 입력을 받음
		// 이름, 나이, 키 입력받고 출력
		
		System.out.print("이름 : ");
		String name = sc.nextLine(); //nextLine = 문자열
		
		System.out.print("나이 : ");
		int age = sc.nextInt(); // nextInt = 정수형
		
		System.out.print("키 : ");
		double height = sc.nextDouble(); //nextDouble = 실수형
		
		System.out.println(name + "님은 " + age + "세 이며, 키는 " + height + "cm입니다.");
		
	}

}
