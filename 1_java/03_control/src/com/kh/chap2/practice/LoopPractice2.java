package com.kh.chap2.practice;

import java.util.Scanner;

public class LoopPractice2 {
	
	public void practice11() {
		/*
		1부터 사용자에게 입력 받은 수까지 중에서
		1) 2와 3의 배수를 모두 출력하고
		2) 2와 3의 공배수의 개수를 출력하세요.
		* ‘공배수’는 둘 이상의 수의 공통인 배수라는 뜻으로 어떤 수를 해당 수들로 나눴을 때
		모두 나머지가 0이 나오는 수
		ex.
		자연수 하나를 입력하세요 : 15
		2 3 4 6 8 9 10 12 14 15
		count : 2
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("자연수 하나를 입력하세요 : ");
		int input = sc.nextInt();
		int count = 0;
		
		for (int i = 1; i<=input; i++) {
			if(i%2==0&&i%3==0) {
				++count;
				System.out.printf("%d ", i);
			} else if (i%2==0||i%3==0) {
				System.out.printf("%d ", i);
			}
		}
		System.out.println();
		System.out.println("count : " +count);
	}

	public void practice12() {
		
		/*
		다음과 같은 실행 예제를 구현하세요.
		ex.
		정수 입력 : 4
		   *
		  **
		 ***
		****
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		for(int i = input; 1<=i; i--) {
			for (int j = 1; j<=input; j++) {
				if (i<=j) {
					System.out.print('*');
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
	}
	// 다시 풀어보기 (실습문제 10번)
	
	public void practice13() {
		
		/*
		다음과 같은 실행 예제를 구현하세요.
		ex.
		정수 입력 : 3
		*
		**
		***
		**
		*
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		for (int i = 1;i<=input*2-1;i++) {
			if (i<=input) {
				for (int j=1; j<=i;j++) {
					System.out.print('*');
				}
				System.out.println();
			} else {
				for (int j =2*input-i; j>=1; j--) {
					System.out.print('*');
				}
				System.out.println();
			}
			
		}
		
		
	}
}
