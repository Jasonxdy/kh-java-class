package com.kh.array;

import java.util.Scanner;

public class A_Array {
	
	// 배열
	// - 같은 자료형의 변수를 하나의 묶음으로 다루는 것.
	// - 저장된 값마다 인덱스가 지정된다.
	// (index는 0부터 시작된다)
	
	public void method1() {
		// 배열을 사용하지 않고 int 형 변수 5개를 각각 만들어 값을 초기화하고 출력하기
		/*
		int num1 = 1;
		int num2 = 2;
		int num3 = 3;
		int num4 = 4;
		int num5 = 5; // 즉 위의 것들은 stack에 각각 할당 및 저장됨
		
		System.out.println(num1);
		System.out.println(num2);
		System.out.println(num3);
		System.out.println(num4);
		System.out.println(num5);
		*/
		// 너무 코드가 많고 힘듬
		
		// 1단계 : 배열 선언
		// 자료형 [] 배열명; or 자료형 배열명 [];
		int[] arr;
		
		// 2단계 : 배열 할당
		// - 실제 값을 저장할 수 있는 배열 공간 할당 (==생성)
		// 배열명 = new 자료형 [배열크기];
		
		arr = new int [5];
		
		for (int i = 0; i<arr.length; i++) { //length : 길이를 return
			arr[i]=i+1;// i는 index의 i였음...
		}
		
		for (int i =0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	
		
		/*
		// 변수처럼 각 인덱스 요소에 접근하여 값 저장 가능.
		arr[0] = 1;
		arr[1] = 2;
		arr[2] = 3;
		arr[3] = 4;
		arr[4] = 5;
		
		// 변수처럼 각 배열요소에 접근하여 출력도 가능
		System.out.println("=====배열 출력=====");
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		System.out.println(arr[3]);
		System.out.println(arr[4]);
		*/// 이건 또 배열의 장점을 살리지 못함
		
	}
	
	public void method2() {
		// 사용자가 지정한 크기만큼의 배열을 할당하고 그 배열에 1부터 지정한 크기만큼의 숫자를 저장 및 출력하기
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		// 입력받은 크기 만큼의 배열 선언 및 할당
		int[] arr = new int[input];
		for (int i=0; i<arr.length;i++) {
			arr[i] = i+1;
			System.out.println(arr[i]);
		}
		
		
	}

	public void method3() {
		
		// 사과, 바나나, 오렌지, 파인애플, 키위
		// 다섯개의 과일이 저장되어 있는 배열이 있다.
		// 인덱스를 입력받아 해당 인덱스에 저장된 과일을 출력.
		
		Scanner sc = new Scanner(System.in);
		// String 배열을 선언과 동시에 초기화 -> 컴파일러가 알아서 배열의 크기를 할당하고, 각 요소에 값을 대입함.
		String[] fruit = {"사과", "바나나","오렌지","파인애플","키위"};
		System.out.print("인덱스 입력 : ");
		int index = sc.nextInt();
		if (0<=index&&index<fruit.length) {
			System.out.print(fruit[index]);
		} else {
			System.out.println("잘못된 인덱스입니다");
		}
	}
	
	public void method4() {
		// 사과, 바나나, 오렌지, 파인애플, 키위
		// 다섯개의 과일이 저장되어 있는 배열이 있다.
		// 과일 명을 입력받아 해당 과일이 몇번째 인덱스에 저장되어 있는지 출력

		Scanner sc = new Scanner(System.in);
		String fruit[] = { "사과", "바나나", "오렌지", "파인애플", "키위" };
		System.out.print("과일명 입력 : ");
		String input = sc.nextLine();

		boolean isTrue = true;
		for (int i = 0; i < fruit.length; i++) {
			// 현재 접근한 배열 요소의 값이 입력한 값과 같은 경우
			if (fruit[i].equals(input)) {
				System.out.println("해당 과일은 " + i + "번째 인덱스입니다");
				isTrue = false;
				break; // 목적 달성 시 break는 항상 사용해 줄것
			}
			}
		if (isTrue) {
			System.out.println("해당 과일이 저장된 인덱스는 없습니다");
		}
	}
	// 배열을 이용한 검색 방법 ---> 어려웠음.. 다시 볼 것
	
	public void method5() {
		// 문자열 3개를 저장할 수 있는 배열을 선언 및 할당하고 문자열 3개를 입력받아 배열에 순서대로 저장 후
		// 검색할 문자열을 입력받아 배열에 몇번째 인덱스에 저장되어 있는지 출력
		
		Scanner sc = new Scanner(System.in);
		String[] arr = new String[3];
		for (int i = 0; i < arr.length; i++) {
			System.out.print(i + "번째 인덱스에 할당할 문자열 : ");
			arr[i] = sc.nextLine();
		}

		System.out.print("검색할 문자열을 입력하세요 : ");
		String input = sc.nextLine();

		boolean isTrue = true;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(input)) {
				System.out.println("해당 문자열은 " + i + "번째 인덱스에 저장되어 있습니다");
				isTrue = false;
				break;
			}
		}
		if (isTrue) {
			System.out.println("해당 문자열은 저장되어 있지 않습니다");
		}
	}
	
	public void method6() {
		// 5명의 키를 입력받아 배열에 저장 후 5명의 평균 키 출력
		Scanner sc = new Scanner(System.in);
		double height[] = new double[5];
		//실수형 배열 height 선언 및 5칸 할당
		double sum = 0;
		
		for(int i =0; i<height.length;i++) {
			System.out.print(i+1+"번째 사람의 키 : ");
			height[i]=sc.nextDouble();
			sum+=height[i];
		}
		System.out.println(height.length+"명의 평균 키 : "+sum/height.length+"cm입니다");
	}
	
	
	
	
	
	
	
}
