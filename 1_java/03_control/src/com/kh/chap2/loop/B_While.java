package com.kh.chap2.loop;

import java.util.Scanner;

public class B_While {
	
	//1. 단독 while문
	
	public void method1() {
		int i = 1; // 초기식
		while(i<=10) { // 조건식
			System.out.printf("%2d 출력\n",i);
			i++;// 증감식
		}
		
		for (int j=1; j<=10; j++) {
			System.out.printf("%2d 출력\n",j);
		}
	}
	
	public void method2() {
		int i = 10;
		while (i>=1) {
			System.out.printf("%2d 출력\n", i);
			i--;
		}
		
		for (int j=10; 1<=j;j--) {
			System.out.printf("%2d 출력\n", j);
		}
	}
	
	public void method3() {
		
		// 정수 0이 입력될 때까지 입력받은 모든 값을 더하여 결과 출력 --> 이런식으로 종료 조건만 아는 경우
		
		Scanner sc = new Scanner(System.in);
		int input = -1; // 입력받은 정수를 저장할 변수 : -1을 하는 경우는 0일 경우 while문 진입을 못함
		int sum = 0; // 정수의 합을 저장할 변수
		
		while(input!=0) {
			System.out.print("입력 : ");
			input = sc.nextInt(); // input에 -1이 있엇는데 이 문장으로 인해 input값 변경
			sum+=input; // 이 때 이 부분은 0이 입력 되고도 한번 더 실행되니까 코드적으로 효율적이지 않다는것을 알아둘 것
		}
		System.out.println("총합 : " + sum);
	}
	
	public void method4() {
		
		Scanner sc = new Scanner(System.in);
		int input=0; //입력값을 저장할 변수
		int sum=0;//정수들의 합을 저장할 변수
		boolean check = true; // 반복문의 반복 여부를 지정할 논리형 변수!
		
		while(/*조건식 : 결과가 논리형이라는 뜻*/check) {
			System.out.print("입력 : ");
			input = sc.nextInt();
			if (input==0) { // 입력값이 0이면
				check = false; // 반복문 종료를 위해 check 값을 false로 변경
			} else {
				sum += input;
			}			
		}
		System.out.println("총합 : " + sum);		
	}
	
	// 중첩 while문
	// 주의 사항!!
	// - while문 하나 만으로도 무한 루프의 가능성이 다분함
	// - while문을 중첩해서 사용하는 경우 무한 루프의 위험성이 늘어남
	// ---> 종료 조건이 확실히 나타날 수 있는 경우에만 사용
	
	public void method5() {
		// method3 do~while 구문으로 변경
		
		Scanner sc = new Scanner (System.in);
		int input = -1;
		int sum = 0;
		
		do {
		System.out.print("입력 : ");
		input = sc.nextInt(); // input에 -1이 있엇는데 이 문장으로 인해 input값 변경
		sum+=input;
		} while(input!=0); // do~while문은 while이 끝나는 시점에 세미콜론 (;) 반드시 추가
	}
	
	public void method6() {
		// 키보드로 입력한 문자열을 모두 한 문장으로 만들어 출력
		// 단, exit 입력 시 종료
		
		Scanner sc = new Scanner(System.in);
		String str = null; // 입력         cf> == String str = ""; (둘 다 비어있는 문자열 만드는 방법)
		String sum = ""; // 문장 저장
		
		do {
			System.out.print("문자열 입력 : ");
			str = sc.nextLine();
			if (!str.equals("exit")) {
			sum +=str;
			}
		} while(!str.equals("exit"));
		
		System.out.println(sum);
	}
	
	public void method7() {
		//do~while을 이용하여 메뉴화면 구성하기
		Scanner sc = new Scanner(System.in);
		int input = 0;
		do {
			System.out.println("1. 메소드1 실행");
			System.out.println("2. 메소드2 실행");
			System.out.println("3. 메소드3 실행");
			System.out.println("4. 메소드4 실행");
			System.out.println("5. 메소드5 실행");
			System.out.println("6. 메소드6 실행");
			System.out.println("0. 종료");
			System.out.print("메뉴 선택 ==> ");
			input = sc.nextInt();
			
			switch(input) {
			case 1: method1(); break;
			case 2: method2(); break;
			case 3: method3(); break;
			case 4: method4(); break;
			case 5: method5(); break;
			case 6: method6(); break;
			case 0: System.out.println("프로그램을 종료합니다"); break;
			default: System.out.println("잘못 입력하셨습니다. 다시 입력하세요");
			}
		} while (input!=0);
		
	}
		
}

