package com.kh.chap2.practice;

import java.util.Scanner;

public class LoopPractice1 {
	
	public void practice1() {
		/*
		사용자로부터 한 개의 값을 입력 받아 1부터 그 숫자까지의 숫자들을 모두 출력하세요.
		단, 입력한 수는 1보다 크거나 같아야 합니다.
		만일 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요“를 출력하세요.
		ex.
		1이상의 숫자를 입력하세요 : 4 1이상의 숫자를 입력하세요 : 0
		1 2 3 4 1 이상의 숫자를 입력해주세요.
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("1 이상의 숫자를 입력하세요 : ");
		int input = sc.nextInt();
		
		if (input>=1) {
			for (int i = 1; i<=input;i++) {
				System.out.printf("%d ",i);
			}
		} else {
			System.out.println("1 이상의 숫자를 입력해주세요");
		}
		
		
		
	}
	
	public void practice2() {
		/*
		사용자로부터 한 개의 값을 입력 받아 1부터 그 숫자까지의 모든 숫자를 거꾸로 출력하세요.
		단, 입력한 수는 1보다 크거나 같아야 합니다.
		ex.
		1이상의 숫자를 입력하세요 : 4 1이상의 숫자를 입력하세요 : 0
		4 3 2 1 1 이상의 숫자를 입력해주세요.
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("1 이상의 숫자를 입력하세요 : ");
		int input = sc.nextInt();
		
		if (input>=1) {
			for (int i=input; i>=1; i--) {
				System.out.printf("%d ",i);
			}
		} else {
			System.out.println("1 이상의 숫자를 입력해주세요.");
		}
	}

	public void practice3() {
		/*
		1부터 사용자에게 입력 받은 수까지의 정수들의 합을 for문을 이용하여 출력하세요.
		ex.
		정수를 하나 입력하세요 : 8
		1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 = 36
		*/
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 하나 입력하세요 : ");
		int input = sc.nextInt();
		int sum = 0;
		
		for (int i = 1; i<input; i++) {
			System.out.printf("%d + ", i);
			sum += i;
		}
		sum += input;
		System.out.print(+input+" = "+sum);		
	}

	public void practice4() {
		/*
		사용자로부터 두 개의 값을 입력 받아 그 사이의 숫자를 모두 출력하세요.
		만일 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요“를 출력하세요.
		ex.
		첫 번째 숫자 : 8 첫 번째 숫자 : 4 첫 번째 숫자 : 9
		두 번째 숫자 : 4 두 번째 숫자 : 8 두 번째 숫자 : 0
		4 5 6 7 8 4 5 6 7 8 1 이상의 숫자를 입력해주세요.
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("첫 번째 숫자 : ");
		int num1 = sc.nextInt();
		System.out.print("두 번째 숫자 : ");
		int num2 = sc.nextInt();
		
		if (num1>num2) {
			int temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
		if (num1>=1&&num2>=1) {
			for (int i=num1; i<=num2; i++) {
				System.out.printf("%d ", i);
			}
		} else {
			System.out.println("1 이상의 숫자를 입력해주세요~");
		}
		
	}

	public void practice5() {
		/*
		사용자로부터 입력 받은 숫자의 단을 출력하세요.
		ex.
		숫자 : 4
		===== 4단 =====
		4 * 1 = 4
		4 * 2 = 8
		4 * 3 = 12
		4 * 4 = 16
		4 * 5 = 20
		4 * 6 = 24
		4 * 7 = 28
		4 * 8 = 32
		4 * 9 = 36
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자 : ");
		int input = sc.nextInt();
		
		for (int i = 1; i<=9 ; i++) {
			System.out.printf("%d X %d = %2d\n",input,i,input*i);
		}
	}
	
	public void practice6() {
		
		/*
		사용자로부터 입력 받은 숫자의 단부터 9단까지 출력하세요.
		단, 9를 초과하는 숫자가 들어오면 “9 이하의 숫자만 입력해주세요”를 출력하세요.
		숫자 : 4 숫자 : 10
		===== 4단 ===== 9 이하의 숫자만 입력해주세요.
		===== 5단 =====
		===== 6단 =====
		===== 7단 =====
		===== 8단 =====
		===== 9단 =====
		(해당 단의 내용들은 길이 상 생략)
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자 : ");
		int input = sc.nextInt();
	
		if (input<=9) {
			for (int i =input; i<=9; i++) {
				System.out.printf("========== %d단 =========\n", i);
				for (int j=1; j<=9;j++) {
					System.out.printf("%d X %d = %d\n",i,j,i*j);
				}
			}
		} else {
			System.out.println("9 이하의 숫자만 입력해주세요");
		}
		
		
		
	}
	
	public void practice7() {
	
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
		for (int i=1; i<=input;i++) {
			for(int j =1; j<=i; j++) {
				System.out.print('*');
			}
			System.out.println();
		}
		
	}
	
	public void practice8() {
		
		/*
		다음과 같은 실행 예제를 구현하세요.
		ex.
		정수 입력 : 4
		****
		***
		**
		*
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		for (int i = input; i>=1; i--) {
			for (int j = i; j>=1; j--) {
				System.out.print('*');
			}
			System.out.println();
		}
	}	
}
