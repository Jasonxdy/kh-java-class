package com.kh.array.practice;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayPractice {
	
	public void practice1() {
		/*
		길이가 10인 배열을 선언하고 1부터 10까지의 값을 반복문을 이용하여
		순서대로 배열 인덱스에 넣은 후 그 값을 출력하세요.
		ex.
		1 2 3 4 5 6 7 8 9 10
		*/
		
		int[] arr = new int[10];
		for(int i=0; i<arr.length;i++) {
			arr[i] = i+1;
		}
		System.out.print(Arrays.toString(arr));
	}
	
	public void practice5() {
		/*
		문자열을 입력 받아 문자 하나하나를 배열에 넣고 검색할 문자가 문자열에 몇 개 들어가 있는지
		개수와 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요.
		ex.
		문자열 : application
		문자 : i
		application에 i가 존재하는 위치(인덱스) : 4 8
		i 개수 : 2
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 : ");
		String str = sc.nextLine();
		System.out.print("문자 : ");
		char ch = sc.nextLine().charAt(0);
		//charAt(index): 문자열의 해당 index에 있는 문자 하나를 추출
		
		//2. 사용자가 입력한 문자열에서 문자 하나하나를 char 배열에 저장하기
		//2-1. 문자열의 길이만큼의 char 배열을 선언 및 할당
		char[] arr= new char[str.length()];
		// 스트링.length() : 메소드
		// 그림 떠올리기!!
		
		//2-1. 반복문을 통해 str.charAt(i)값을 arr[i]에 저장
		for(int i = 0; i<arr.length;i++) { //배열.length : 변수 개념 (메소드X)
			arr[i] = str.charAt(i);
		}
		
		//3. 검색할 문자가 배열 내에 몇개가 있는지 검색 + 검색된 인덱스 출력
		
		int count = 0;
		// 반복문 실행 전 구문 출력
		System.out.print(str + "에" + ch + "가 존재하는 위치 (인덱스) : ");
		
		// 반복문을 이용하여 배열의 각 요소에 접근
		for (int i = 0; i<arr.length;i++) {
			if(arr[i]==ch) { // 해당 인덱스의 요소가 검색될 문자와 같을 경우
				System.out.print(i + " ");
				count++;
			}
		}
		
		System.out.println(); //줄바꿈
		
		// 결과 출력
		System.out.println(ch + "개수 : "+count);
		
		
	}

	public void practice8() {
		
		/*
		3이상인 홀수 자연수를 입력 받아 배열의 중간까지는 1부터 1씩 증가하여 오름차순으로 값을 넣고,
		중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어 출력하세요.
		단, 입력한 정수가 홀수가 아니거나 3 미만일 경우 “다시 입력하세요”를 출력하고
		다시 정수를 받도록 하세요.
		ex.
		정수 : 4
		다시 입력하세요.
		정수 : -6
		다시 입력하세요.
		정수 : 5
		1, 2, 3, 2, 1
		*/
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("정수 : ");
			int input = sc.nextInt();
			if (input % 2 == 1 && input >= 3) {
				// 입력받은 크기만큼의 int형 배열 선언 및 할당
				int[] arr = new int[input];

				// 배열 요소에 저장할 값을 만들 변수 생성
				int value = 0;
				// 배열의 중간까지는 value++, 배열 중간 이후부터는 value--

				// 반복문을 이용하여 배열 요소에 알맞은 값 대입
				for (int i = 0; i < arr.length; i++) {
					if (i <= arr.length / 2) {
						value++;
					} else {
						value--;
					}
					// 현재 배열 요소에 value 대입
					arr[i] = value;
					System.out.print(arr[i] + " ");
				} // for end.
				break;
			} else {
				System.out.println("다시 입력하세요.");
			}
		}
	}
	
	public void practice15() {
		/*
		문자열을 입력 받아 문자열에 어떤 문자가 들어갔는지 배열에 저장하고
		문자의 개수와 함께 출력하세요.
		ex.
		문자열 : application
		문자열에 있는 문자 : a, p, l, i, c, t, o, n
		문자 개수 : 8
		*/
		// 1. 사용자에게 문자열 입력받기
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자열 : ");
		String str = sc.nextLine();
		
		// 2. 입력받은 문자열을 char 배열에 저장
		char[] arr = new char[str.length()];
		for (int i = 0; i<arr.length;i++) {
			arr[i] = str.charAt(i);
		}
		System.out.print("문자열에 있는 문자 : ");
		
		// 3. 반복문을 이용하여 char 배열에서 중복값이 존재하는 경우 출력 X
		
		// 비교 기준 설정
		int count = 0;
		for (int i = 0; i<arr.length; i++) {
			boolean flag = true; // 깃발을 올리거나 내리거나 하는 방식
			// 중복 체크용 boolean
			
			for(int j = 0; j<i; j++) { // 비교 대상 선정
				if(arr[i]==arr[j]) {
					// 기준과 비교 대상이 같을 경우 == 중복 발생 시
					flag = false;
					break;
				}
			}
			
			//중복이 발생하지 않은 경우 출력 및 count 증가
			if(flag) {
				if(i==0) {
					System.out.print(arr[i]);
				} else {
					System.out.print(", " + arr[i]);
				}
				count++;
			}
		}
		System.out.println();
		System.out.println("문자 개수 : " + count);
	}
}
