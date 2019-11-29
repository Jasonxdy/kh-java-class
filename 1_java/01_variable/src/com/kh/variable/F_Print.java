package com.kh.variable;

import java.util.Scanner;

public class F_Print {
	
	public void printMethod() {
		
		System.out.println("A"); // A (출력하고 줄바꿈)

		System.out.printf("%c\n" , 'A');
		//				     출력형식   , 변수 or 값
		
		// %c : 문자 하나를 나타내는 형식
		// \n (이스케이프 문자) : 줄바꿈 (new line)
		
		// A B (출력하고 줄바꿈)
		
		System.out.println("A B");
		System.out.printf("%c %c\n%c %c\n",'A','B', 'C', 'D');
		System.out.printf("%s\n", "A B");
		
		int iNum1 = 100;
		//100(줄바꿈)
		System.out.println(iNum1);
		System.out.printf("%d\n" , iNum1);
		//%d : 10진수 정수 형식
		
		double dNum = 1.123456;
		// 1.123456 (줄바꿈)
		System.out.println(dNum);
		System.out.printf("%f\n", dNum);
		
		//1.12 (줄바꿈) (단, dNum사용) -> printf를 사용하는 이유
		System.out.println( ((int)(dNum * 100)) / 100.0);
		// 위에가 너무 복잡하므로 printf 사용해줌
		System.out.printf("%.2f \n", dNum);
		
		// %f : 10진수 실수 형식
		// %.2f :  소수점 아래 2째 자리까지 표현
		//			---> 소수점 아래 3째 자리에서 반올림 한다는 뜻
		
		Scanner sc = new Scanner(System.in);
		
		// 10칸을 주고 정수를 입력받아 오른쪽 정렬하여 출력
		
		System.out.print("정수 입력 (10자리까지): ");
		int input = sc.nextInt();
		System.out.printf("%10d", input);
		// %10d : 10칸의 정수형 공간, 오른쪽 정렬 출력
		
	}

}
