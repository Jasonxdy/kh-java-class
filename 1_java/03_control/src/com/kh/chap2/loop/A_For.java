package com.kh.chap2.loop;

import java.util.Scanner;

public class A_For {
	
	// 1. For문(단일 for문)
	/*
	 *  for (초기식; 조건식; 증감식) {
	 *  	실행될 구문;
	 *  }
	 *  
	 *  1회전 (처음 for문이 실행되는 경우)
	 *  초기식 -> 조건식 Test -> (조건식이 T인 경우) 내부 구문 실행 -> 증감식 수행 : 4단계 구성
	 *  
	 *  2회전 이후
	 *  조건식 Test -> (조건식이 T인 경우) 내부 구문 실행 -> 증감식 수행 : 3단계 구성
	 * 
	 * */
	
	//~부터 ~까지 ~~~하기 : 무조건 반복문 (이때 범위가 정해진 경우는 보통 while보다는 for문 사용할 것)
	
	public void method1() {
		
		// 1부터 5까지 1씩 증가하며 출력하기
		for(int i = 1; i <= 5; i++) {
			System.out.println(i);
		}
		
		// 0부터 10까지 1씩 증가
		System.out.println();
		System.out.println();
		System.out.println();
		
		for (int i = 0; i <= 10; i++) {
			System.out.println(i+"번째 반복");
		}
	}
	
	public void method2() {
		
		// 1부터 10 사이의 홀수만 출력
		
		for(int i = 1; i<=10; i+=2) {
			System.out.print(i + " ");
		}	
	}
	
	public void method3() {
		
		// 10부터 1까지 짝수 출력
		
		for (int i = 10; 1<=i; i-=2) {
			System.out.print(i + " ");
		}	
	}
	
	public void method4() {
	
		// 2에서 9사이의 정수를 입력받아 해당 단의 구구단 출력하기
		// 단, 2-9 사이 값이 아니면 "잘못 입력하셨습니다" 출력
		
		Scanner sc = new Scanner(System.in);
		System.out.print("단 입력(2~9) : ");
		int input = sc.nextInt();
		
		if (2<=input&&input<=9) {
			for (int i=1; i<=9; i++) {
				System.out.printf("%d X %d = %2d\n", input, i, input*i);
			}
		} else {
			System.out.println("잘못 입력하셨습니다");
		}
	}
	
	public void method5() {
		
		// 1부터 10 사이 임의의 수 5개의 합계 구하기
		//		  (난수==random)

		int sum = 0; // 합계를 저장할 변수

		for (int i = 1; i <= 5; i++) { //5회 반복
			int ran = (int)(Math.random()*10+1); // 지역변수
			// 0 <= math.random() < 1의 실수  
			// 0 <= math.random()*10 < 10의 실수 (0~9.99)
			// 1 <= math.random()*10 + 1 < 11의 실수 (0~10.99) ==> 1,2,3,4,5,6,7,8,9,10
			System.out.println(ran);
			sum += ran;
		}
		System.out.println("난수의 합 : " + sum);
	}

	public void method6() {
		// 키보드로 두 수를 입력받아 작은 수부터 큰 수까지의 모든 정수의 합 구하기
		Scanner sc = new Scanner(System.in);
		System.out.print("첫번재 정수 : ");
		int num1 = sc.nextInt();
		System.out.print("두번재 정수 : ");
		int num2 = sc.nextInt();
		int sum = 0;
		
		if (num1>num2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;				
			}
		// 변수 교환하는 방법 (중요)		
		
		
		for(int i = num1; i<=num2; i++) {
			sum += i;
		}
		
		System.out.println("총 합은 " +sum+"입니다");
	
		
	}
	
	
	
	// ==========================================================================================================================================
	// 2. 중첩 for 문
	
	
	public void method7() {
		// 1부터 5까지 출력되는 문장을 5줄 출력하기 
		for (int i = 1; i<=5; i++) {
			for (int j = 1; j<=6; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}
	
	public void method8() {
		// 0시 0분부터 23시 59분까지 출력하기
		// ex) 0시 0분
		//     0시 1분...
		
		for (int hour = 0; hour<24; hour++) {
			for (int min = 0; min<60; min++) {
				//System.out.println(hour+"시 "+ min +"분");
				System.out.printf("%d시 %d분\n",hour,min);
			}
		}
		
	}
	
	public void method9() {
		//2단부터 9단까지 모두 출력
		
		for (int i = 2; i<=9; i++) {
			for(int j = 1; j<=9; j++) {
				System.out.printf("%-1d X %1d = %2d  ", i, j, i*j);
			}
			System.out.println();
		}
	}
	
	public void method10() {
		
		// 입력한 수 만큼 문장을 출력하는데 한 문장당 '*' 표시를 5개씩 출력하시오
		// ex)
		// 입력 : 3
		// *****
		// *****
		// *****
		
		Scanner sc = new Scanner(System.in);
		System.out.print("입력 : ");
		int input = sc.nextInt();
		
		for (int i = 1; i<=input;i++) {
			for (int j = 1; j<=5; j++) {
				System.out.print('*');
			}
			System.out.println();
		}
		
		
	}

	public void method11() {
		
		// 정수를 입력받아 그 수 만큼 '*'을 출력하여 사각형 모양 만들기
		// 단, 줄 수와 칸 수가 일치하는 부분은 해당 줄 번호 출력
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력: ");
		int input = sc.nextInt();
		
		for (int i = 1; i<=input; i++) {
			for(int j = 1; j<=input; j++) {
				if (i==j) {
					System.out.print(i);
				} else {
					System.out.print('*');
				}	
			}
			System.out.println();
		}
		
	}

	public void method12() {
		
	}







}
