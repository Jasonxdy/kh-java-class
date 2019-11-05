package com.kh.dimension;

import java.util.Arrays;
import java.util.Scanner;

public class DimensionPractice {
	
	public void practice1() {
		/*
		3행 3열짜리 문자열 배열을 선언 및 할당하고
		인덱스 0행 0열부터 2행 2열까지 차례대로 접근하여 “(0, 0)”과 같은 형식으로 저장 후 출력하세요.
		ex.
		(0, 0)(0, 1)(0, 2)
		(1, 0)(1, 1)(1, 2)
		(2, 0)(2, 1)(2, 2)
		*/
		
		String[][] arr = new String[3][3];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = "("+i+", "+j+")";
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	
	public void practice2() {
		/*
		4행 4열짜리 정수형 배열을 선언 및 할당하고
		1) 1 ~ 16까지 값을 차례대로 저장하세요.
		2) 저장된 값들을 차례대로 출력하세요.
		ex.
		1 2 3 4
		5 6 7 8
		9 10 11 12
		13 14 15 16
		*/
		
		int num =1;
		
		int[][] arr = new int[4][4];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = num++;
				System.out.printf("%2d ",arr[i][j]);
			}
			System.out.println();
		}
	}
	
	public void practice3() {
		/*
		4행 4열짜리 정수형 배열을 선언 및 할당하고
		1) 16 ~ 1과 같이 값을 거꾸로 저장하세요.
		2) 저장된 값들을 차례대로 출력하세요.
		ex.
		16 15 14 13
		12 11 10 9
		8 7 6 5
		4 3 2 1
		*/
		
		int num = 16;

		int[][] arr = new int[4][4];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = num--;
				System.out.printf("%2d ", arr[i][j]);
			}
			System.out.println();

		}
	}
	
	public void practice4() {
		/*
		4행 4열 2차원 배열을 생성하여 0행 0열부터 2행 2열까지는 1~10까지의 임의의 정수 값 저장 후
		아래의 내용처럼 처리하세요.
		ex.
		2 4 3 9
		10 2 2 14
		7 3 8 18
		19 9 13 62
		*/
		
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[4][4];
		
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				arr[i][3] = 0;
				for (int j = 0; j < arr[i].length - 1; j++) {
					System.out.print(i + "행 " + j + "열 값 입력 (1~10사이 정수) : ");
					arr[i][j] = sc.nextInt();
					arr[i][3] += arr[i][j];
				}
			} else {
				for (int j = 0; j < arr.length; j++) {
					for (int k = 0; k < arr.length - 1; k++) {
						arr[i][j] += arr[k][j];
					}
				}
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
			System.out.println();
		}
	}
	
	public void practice5() {
		/*
		2차원 배열의 행과 열의 크기를 사용자에게 직접 입력받되, 1~10사이 숫자가 아니면
		“반드시 1~10 사이의 정수를 입력해야 합니다.” 출력 후 다시 정수를 받게 하세요.
		크기가 정해진 이차원 배열 안에는 영어 대문자가 랜덤으로 들어가게 한 뒤 출력하세요.
		(char형은 숫자를 더해서 문자를 표현할 수 있고 65는 A를 나타냄, 알파벳은 총 26글자)
		ex.
		행 크기 : 5
		열 크기 : 4
		T P M B
		U I H S
		Q M B H
		H B I X
		G F X I
		*/
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("행 크기 (1~10사이) : ");
			int row = sc.nextInt();
			System.out.print("열 크기 (1~10사이) : ");
			int clm = sc.nextInt();
			char[][] arr = new char[row][clm];

			if (1 <= row && row <= 10 && 1 <= clm && clm <= 10) {
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < clm; j++) {
						arr[i][j] = (char)((int) (Math.random() * 27 + 65));
					}
				}
			} else {
				System.out.println("반드시 1~10 사이의 정수를 입력해야 합니다.");
				continue;
			}
			for (int i = 0; i < arr.length; i++) {
				System.out.print(Arrays.toString(arr[i]));
				System.out.println();
			}
			break;
		}
		
		
		
	}
	
}
