package com.kh.practice1;

import java.util.Scanner;

public class VariablePractice1 {
	
	public void practice1 () {
		Scanner sc = new Scanner (System.in);
		
		System.out.print("이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.print("성별을 입력하세요 (남/여): ");
		char gender = sc.nextLine().charAt(0);
		// .charAt(0) : 문자열의 0번재 (제일 앞) 문자를 가져옴;
		System.out.print("나이를 입력하세요 : ");
		int age = sc.nextInt();
		System.out.print("키를 입력하세요(cm) : ");
		double height = sc.nextDouble();
		
		System.out.print("키 " + height + "cm 인 " + age + "살 "+
		gender + "자 " + name + "님 반갑습니다 ^^");
	}

}
