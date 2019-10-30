package com.kh.chap3.branch;

public class B_Continue {
	
	public void method1() {
		
		//1부터 100 까지의 정수 중 4의 배수를 제외한 정수의 합 구하기
		
		int sum =0;
		
		for (int i=1; i<=100; i++) { //범위가 정해져 있으므로 while보다는 for문 사용하는 것이 좋음
			if (i%4==0) {
				continue;
			}
			sum+=i;
		}
		System.out.println("총합 : " + sum);
	}
	
	public void method2() {
		//2~9의 구구단 중 홀수 단만 출력하기
		
		for (int i=2; i<=9;i++) {
			 if (i%2==0) {
				 continue;
			 }
			 
			 System.out.printf("====%d단====\n",i);
			 for(int j=1; j<=9; j++) {
				 System.out.printf("%d X %d = %2d \t", i,j,i*j);
				 
			 }
			 System.out.println();
		}
	}

}
