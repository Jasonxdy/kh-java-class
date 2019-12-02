package com.kh.array;

import java.util.Arrays;
import java.util.Scanner;

public class B_ArraySort {
	
	// 오름차순 : 1 2 3 4 5
	// 내림차순 : 5 4 3 2 1
	// temp를 이용한 교환방법이 여러번 반복된다고 생각하면 됨
	
	public void method1() {
		//배열 요소들 위치 바꾸기
		int arr[] = {2, 1, 3};
		
		
		/*
		// 1, 2, 3으로 바꾸기
		int temp = arr[0];
		arr[0] = arr[1];
		arr[1] = temp;
		*/
		
		// 3 2 1 (내림차순)
		int temp = arr[2];
		arr[2] = arr[1];
		arr[1] = arr[0];
		arr[0] = temp;
		
		for(int i =0; i<arr.length;i++) {
			System.out.print(arr[i] + " ");
		}
	}
		

	// 삽입 정렬 (Insertion Sort) --> 결과적으로 하나씩 삽입된 형식으로 정렬되므로
	// 정렬 알고리즘에서 가장 간단하고 기본이 되는 알고리즘.
	// ex) 카드게임 패 순서대로 정리
	// 3 4 2 1 5
	// 배열의 n번째 인덱스 값을 (n은 1부터 시작) 기준으로 하여 0 ~ n-1번 인덱스까지 비교하여 정렬 (데이터 바꿈)
	public void method2() {
		
		int arr[] = {2,5,4,1,3};
		int count = 0;
		// 삽입 정렬 (오름차순)
		
		// 1. 비교 주체를 정하는 for문 생성
		for (int i=1;i<arr.length;i++) {
			// 2. 비교 대상을 정하는 for문 생성
			for (int j =0; j<i; j++) {
				// 비교를 통해서 오름차순 정렬 : 비교 주체가 비교 대상보다 작을 경우 값을 교환
				if(arr[i]<arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					count++;
				}
				
			}
			
		}
		
		for(int i = 0; i<arr.length;i++) {
			System.out.print(arr[i]+ " ");
		}
		System.out.println();
		System.out.println(count);
	}

	public void method3() {
		//오름차순 again
		
		int arr[] = { 7, 1, 5, 3, 4, 2, 6 };
		int count = 0;
		for (int i=1;i<arr.length;i++) {
			for(int j=0;j<i;j++) {
				if(arr[i]<arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					count++;
				}
			}
		}
		for(int i=0; i<arr.length;i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println(count);
	}
	
	public void method4() {
		
		// 삽입 정렬 (내림차순)
		int arr[] = { 2, 5, 4, 1, 3 };
		
		// 1. 비교 주체를 정하는 for 문 생성
		for (int i = 1; i<arr.length;i++) {
			// 2. 비교 대상을 정하는 for 문 생성
			for (int j=0;j<i;j++) {
				if(arr[i]>arr[j]) {
					// 내림차순은 오름차순과 비교 연산자 방향만 다르게 반대로!
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		for(int i =0; i<arr.length;i++) {
			System.out.print(arr[i]+ " ");
		}
	}
	
	public void method5() {
		// 정수 5개를 입력받아 배열에 저장 후 내림차순 정렬(삽입정렬)하여 출력
		
		Scanner sc = new Scanner(System.in);
		int arr[] = new int[5];
		/*
		for(int i=0;i<arr.length;i++){
			System.out.print(i+1+"번째 정수 입력 : ");
			arr[i] = sc.nextInt();
		}
		*/
		System.out.print("1번째 정수 입력 : ");
		arr[0] = sc.nextInt();
		for(int i=1;i<arr.length;i++) {
			System.out.print(i+1+"번째 정수 입력 : ");
			arr[i] = sc.nextInt();
			for(int j =0; j<i; j++) {
				if(arr[i]>arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.print("내림차순 정렬 결과 :");
		for(int i = 0; i<arr.length;i++) {
			System.out.print(" "+arr[i]);
		}
	}
	
	// 버블 정렬 (Bubble sort)
	// 인접한 두 개의 요소를 검사하여 정렬하는 방법
	// 뒤에서부터 정렬이 되어지는 것이 특징
	// 장점 : 구현이 쉽다, 이미 정렬된 데이터를 다시 정렬할때 가장 빠르다.
	// 단점 : 다른 정렬에 비해 정렬 속도가 느림.
	
	public void method6() {
		
		int arr[] = { 2, 5, 4, 1, 3 };
		int count = 0;
		
		for (int i=arr.length-1;i>0;i--) {
			// 1. 한 회차당 내부에 있는 for문이 비교를 반복할 회수 지정
			for (int j = 0; j<i;j++) {
				// 2. 지정된 횟수만큼 반복하며 j번째 index 요소와 j+1번째 요소를 비교하여 정렬
				if(arr[j]>arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					count++;
				}
			}
		}
		for(int i =0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		System.out.println(count);
	}
	
	public void method7() {
		// 버블 정렬 (오름차순)
		int arr[] = { 7, 1, 5, 3, 4, 2, 6 };
		int count = 0;
		for(int i = arr.length-1; i>0;i--) {
			for(int j = 0; j<i;j++) {
				if(arr[j]>arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					count++;
				}
			}
		}
		for (int i=0; i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		System.out.println(count);
		
		
	}
	
	public void method8() {
		// 버블 정렬 (내림차순)
		int arr[] = { 7, 1, 5, 3, 4, 2, 6 };
		for(int i = arr.length-1; i>0;i--) {
			for(int j = 0; j<i;j++) {
				if(arr[j]<arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		/*
		for (int i=0; i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		*/
		System.out.println(Arrays.toString(arr));
	}
	
}
