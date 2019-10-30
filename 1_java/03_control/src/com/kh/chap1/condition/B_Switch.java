package com.kh.chap1.condition;

import java.util.Scanner;

public class B_Switch {
	
		// switch 문은 if - else if - else와 비슷한 조건문임.
		// 단, 조건을 범위로 지정할 수 없음.
		// ex) input>10 (X)
		/*
		 * switch문 표현식
		 * 
		 * switch(조건식) {
		 * case 값1 : 실행코드1; break;
		 * case 값2 : 실행코드2; break;
		 * case 값3 : 실행코드3; break;
		 * ...
		 * default : 실행코드;
		 * }
		 * 
		 * switch문의 조건식
		 * --> if문처럼 결과가 true/false가 아닌 동등 비교가 되는 식을 작성
		 * 
		 * */
	
	public void method1() {
		
		// 정수를 입력받아
		// 1이면 "1층입니다."
		// 2이면 "2층입니다."
		// 3이면 "3층입니다."
		// 잘못 입력한 경우 "잘못 입력하셨습니다."
		
		Scanner sc = new Scanner(System.in);
		System.out.print("층수 입력 : ");
		int floor = sc.nextInt();
		
		switch(floor) {
		case 1: System.out.println("1층입니다"); break; // break 없을 시 다음 break까지 쭉 실행되어버림
		case 2: System.out.println("2층입니다"); break;
		case 3: System.out.println("3층입니다"); break;
		
		default: System.out.println("잘못 입력하셨습니다");
		}
		
	}
	
	public void method2() {
		Scanner sc = new Scanner(System.in);
		System.out.print("등급을 작성하세요 : ");
		// 관리자, 매니저, 회원
		String grade = sc.nextLine();
		
		switch(grade) {
		case "관리자": System.out.println("강퇴");
		case "매니저": System.out.println("등록");
		case "회원": System.out.println("조회"); break;
		default: System.out.println("잘못 입력함");
		}
	}
	
	public void method3() {
		// 입력받은 월(달)에 맞는 계절 출력하기
		// 단, 1~12 사이의 값이 아니면 "해당하는 계절이 없습니다" 출력
		Scanner sc= new Scanner(System.in);
		System.out.print("1-12월 중 하나를 입력해주세요 : ");
		int month = sc.nextInt();
		String season;
		switch(month) {
		case 3 : case 4 : case 5 : season ="봄"; break;
		case 6 : case 7 : case 8 : season ="여름"; break;
		case 9 : case 10 : case 11 : season ="가을"; break;
		case 12 : case 1 : case 2 : season ="겨울"; break;
		default : season = "잘못 입력하셨습니다.";
		/*
		 * case 3 : season="봄"; break;
		case 4 : season="봄"; break;
		case 5 : season="봄"; break;
		보다 더 효율적이다!! -> 어떻게하면 코드를 줄일지 생각해보기
		 * */
		}
		
		System.out.println(season);
		
	}
	
	public void method4() {
		//1~12월까지 입력받아 해당하는 달의 일수를 출력하세요 (switch문 사용)
		/*
		 * ex) 
		 * 달 입력 : 10
		 * 10월은 31일입니다.
		 * 
		 * 달 입력 : 4
		 * 4월은 30일입니다.
		 * 
		 * 달 입력 : 2
		 * 4월은 28일 또는 29일입니다.
		 * 
		 * 잘못 입력시 : "잘못 입력하셨습니다."
		 * 
		 * */
		
		Scanner sc = new Scanner (System.in);
		System.out.print("달 입력 : ");
		int month = sc.nextInt();
		switch(month) {
		case 1 : case 3 : case 5 : case 7 : case 8 : case 10 : case 12 : System.out.println(month+"월은 31일입니다."); break;
		case 4 : case 6 : case 9 : case 11 : System.out.println(month+"월은 30일입니다."); break;
		case 2 : System.out.println(month+"월은 28일 또는 29일입니다."); break;
		default : System.out.println("잘못 입력하셨습니다");
		}
		
	}

	
	
	
	
	
	
}
