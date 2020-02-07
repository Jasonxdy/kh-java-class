package algorithm.practice03;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int maxNum = 0; // 최댓값 지정
		int numCount = 1; // 숫자 종류
		
		int []arr = new int[10];
		
		for(int i = 0; i<arr.length; i++) {
			System.out.print(i + "번째 숫자 입력 : ");
			arr[i] = sc.nextInt();
			sc.nextLine();
			if(i<0) {
				for(int j=0; j< i; j++) {
					if(arr[i] != arr[j]) {
						numCount++;
					}
				}
			}
			
		}
		
		System.out.println("입력한 배열 : " + Arrays.toString(arr));
		
		
		int countNext = 0; // 카운트 수
		int countBefore = 0; // 이전 카운트 수 저장
		int num = 0;
		
		for(int j =0; j<=maxNum; j++) {
			countNext = 0;
			for(int i = 0; i < arr.length; i++) {
				if(arr[i] == j) {
					countNext++;
				}
			}
			
			if(countNext >= countBefore) {
				countBefore = countNext;
				num = j;
			}
			
		}
		
		System.out.println("정답 : " + num + " (" + countBefore + "회)");
		
	}

}
