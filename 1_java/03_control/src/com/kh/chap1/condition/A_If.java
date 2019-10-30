package com.kh.chap1.condition;

import java.util.Scanner;

public class A_If {
	
	// 단독 if문 
	/*
	 * if (조건식) {
	 * 	수행할 코드 작성
	 * }
	 * 
	 * --> 조건식이 true이면 블럭({}) 내 코드 수행
	 * --> 조건식이 false이면 블럭({})을 건너뜀
	 * 
	 * */
	
	public void method1() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		
		int num = sc.nextInt();
		
		if (num<0) {
			System.out.println("음수다");
		}
		
		if (num==0) {
			System.out.println("0이다");
		}
		
		if (num>0) {
			System.out.println("양수다");
		}
		//if (num<=0) {
		//	System.out.println("양수가 아니다");
		//}
		
	}
	
	public void method2() {
		//입력받은 정수가 짝수인지 홀수인지 출력
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		if (input%2==0) {
			System.out.println("짝수입니다.");
		} else {
			System.out.println("홀수입니다.");
		}
	}
	
	public void method3() {
		// 입력받은 월(달)에 맞는 계절 출력하기
		// 단, 1~12 사이의 값이 아니면 "해당하는 계절이 없습니다" 출력
		
		Scanner sc = new Scanner(System.in);
		System.out.print("월(달)을 입력해주세요: ");
		int input = sc.nextInt();
		String season; // 계속 System.out.~~ 이거 써주기 싫어서
		
		// 봄 : 3, 4, 5월
		if (input>=3&&input<=5) {
			season = "봄";
		// 여름 : 6, 7, 8월
		} else if (6<=input&&input<=8) {
			season = "여름";
		// 가을 : 9, 10, 11월
		} else if (9<=input&&input<=11) {
			season = "가을";
		// 겨울 : 12, 1, 2월
		} else if (input==12||input==1||input==2) {
			season = "겨울";
		} else {
			season = "해당하는 계절이 없습니다";
		}
		
		System.out.println(season);		
	}
	
	public void method4() {
		/*
		 * 점수를 하나 입력받아 등급을 나눠 점수와 등급을 출력해라
		 * 90점 이상은 A등급
		 * 90점 미만 80점 이상은 B등급
		 * 80점 미만 70점 이상은 C등급
		 * 70점 미만 60점 이상은 D등급
		 * 60점 미만은 F등급
		 */
		Scanner sc = new Scanner(System.in);
		System.out.print("점수 입력 : ");
		int score = sc.nextInt();
		char grade;
		
		if (90<=score) {
			grade = 'A';
		} else if (80<=score) {
			grade = 'B';
		} else if (70<=score) {
			grade = 'C';
		} else if (60<=score) {
			grade = 'D';
		} else { // 만약에 이게 else if로 하고 조건을 주고 끝내는 경우에는 에러발생 -> grade의 초기값 설정이 안된 것이기 때문에...!
			grade = 'F';
		}
		
		System.out.println("당신의 점수는 "+score+"점이고, "+"등급은 "+grade+"입니다.");
		
	}
	
	public void method5() {
		// 각 등급에서 다음의 경우에 '+' 구문이 추가로 출력될 수 있도록 하자
		
		// 95점 이상 A+
		// 85점 이상 B+
		// 75점 이상 C+
		// 65점 이상 D+
		
		/*
		 * 점수를 하나 입력받아 등급을 나눠 점수와 등급을 출력해라
		 * 90점 이상은 A등급
		 * 90점 미만 80점 이상은 B등급
		 * 80점 미만 70점 이상은 C등급
		 * 70점 미만 60점 이상은 D등급
		 * 60점 미만은 F등급
		 */
		Scanner sc = new Scanner(System.in);
		System.out.print("점수 입력 : ");
		int score = sc.nextInt();
		String grade;
		
		if (90<=score) {
			grade = "A";
			if(95<=score) {
				grade += "+"; // grade = "A+"로 굳이 안해도 A를 활용함
			}
		} else if (80<=score) {
			grade = "B";
			if(85<=score) {
				grade += "+";
			}
		} else if (70<=score) {
			grade = "C";
			if(75<=score) {
				grade += "+";
			}
		} else if (60<=score) {
			grade = "D";
			if(65<=score) {
				grade += "+";
			}
		} else {
			grade = "F";
		}
		
		System.out.println("당신의 점수는 "+score+"점이고, "+"등급은 "+grade+"입니다.");
		
	}
	

}
