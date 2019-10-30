package com.kh.chap3.practice;

import java.util.Scanner;

public class BranchPractice {

	public void practice1() {

		/*
		 * 사용자로부터 한 개의 값을 입력 받아 1부터 그 숫자까지의 숫자들을 모두 출력하세요. 단, 입력한 수는 1보다 크거나 같아야 합니다. 만일
		 * 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요“를 출력하세요. 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를
		 * 입력해주세요”가 출력되면서 다시 사용자가 값을 입력하도록 하세요. ex. 1이상의 숫자를 입력하세요 : 4 1이상의 숫자를 입력하세요 :
		 * 0 1 2 3 4 1 이상의 숫자를 입력해주세요. 1이상의 숫자를 입력하세요 : 8 1 2 3 4 5 6 7 8
		 */

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("1이상의 숫자를 입력하세요 : ");
			int input = sc.nextInt();
			if (input < 1) {
				System.out.println("1 이상의 숫자를 입력하세요~~");
				continue;
			}
			for (int i = 0; i <= input; i++) {
				System.out.printf("%d ", i);
			}
			System.out.println();
			break; // while break
		}
	}

	public void practice2() {
		/*
		 * 사용자로부터 한 개의 값을 입력 받아 1부터 그 숫자까지의 모든 숫자를 거꾸로 출력하세요. 단, 입력한 수는 1보다 크거나 같아야 합니다.
		 * 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요”가 출력되면서 다시 사용자가 값을 입력하도록 하세요. ex. 1이상의 숫자를
		 * 입력하세요 : 4 1이상의 숫자를 입력하세요 : 0 4 3 2 1 1 이상의 숫자를 입력해주세요. 1이상의 숫자를 입력하세요 : 8 8 7
		 * 6 5 4 3 2 1
		 */

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("1이상의 숫자를 입력하세요 : ");
			int input = sc.nextInt();
			if (input < 1) {
				System.out.println("1 이상의 숫자를 입력하세요~~");
				continue;
			}
			for (int i = input; i >= 1; i--) {
				System.out.printf("%d ", i);
			}
			System.out.println();
		}

	}

	public void practice3() {

		/*
		 * 사용자로부터 두 개의 값을 입력 받아 그 사이의 숫자를 모두 출력하세요. 만일 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를
		 * 입력해주세요“를 출력하세요. 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요”가 출력되면서 다시 사용자가 값을 입력하도록
		 * 하세요. ex. 첫 번째 숫자 : 8 첫 번째 숫자 : 4 첫 번째 숫자 : 9 두 번째 숫자 : 4 두 번째 숫자 : 8 두 번째 숫자
		 * : 0 4 5 6 7 8 4 5 6 7 8 1 이상의 숫자를 입력해주세요. 첫 번째 숫자 : 6 두 번째 숫자 : 2 2 3 4 5 6
		 */

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("첫번재 숫자 : ");
			int num1 = sc.nextInt();
			System.out.print("두번재 숫자 : ");
			int num2 = sc.nextInt();
			if (num1 < 1 || num2 < 1) {
				System.out.println("1 이상의 숫자를 입력해주세요.");
				continue;
			}
			if (num1 > num2) {
				int temp = num1;
				num1 = num2;
				num2 = temp;
			}
			for (int i = num1; i <= num2; i++) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	public void practice4() {
		/*
		 * 사용자로부터 입력 받은 숫자의 단부터 9단까지 출력하세요. 단, 9를 초과하는 숫자가 들어오면 “9 이하의 숫자만 입력해주세요”를
		 * 출력하세요. 9를 초과하는 숫자가 입력됐다면 “ 9 이하의 숫자를 입력해주세요”가 출력되면서 다시 사용자가 값을 입력하도록 하세요. 숫자
		 * : 4 숫자 : 10 ===== 4단 ===== 9 이하의 숫자만 입력해주세요. ===== 5단 ===== 숫자 : 8 ===== 6단
		 * ===== ===== 8단 ===== ===== 7단 ===== ===== 9단 ===== ===== 8단 ===== ===== 9단
		 * ===== (해당 단의 내용들은 길이 상 생략)
		 */

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("숫자 : ");
			int input = sc.nextInt();
			if (input > 9) {
				System.out.println("9 이하의 숫자를 입력해주세요~");
				continue;
			}
			for (int i = input; i <= 9; i++) {
				System.out.printf("=====%d단=====\n", i);
				for (int j = 1; j <= 9; j++) {
					System.out.printf("%d X %d = %2d\n", i, j, i * j);
				}
				System.out.println();
			}

		}

	}

	public void practice5() {
		/*
		 * 사용자로부터 시작 숫자와 공차를 입력 받아 일정한 값으로 숫자가 커지거나 작아지는 프로그램을 구현하세요. 단, 출력되는 숫자는 총
		 * 10개입니다. ‘공차’는 숫자들 사이에서 일정한 숫자의 차가 존재하는 것을 말한다. ex) 2, 7, 12, 17, 22 … 5 5 5 5
		 * => 여기서 공차는 5 ex. 시작 숫자 : 4 공차 : 3 4 7 10 13 16 19 22 25 28 31
		 */

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("시작 숫자 : ");
			int sNum = sc.nextInt();
			System.out.print("공차 : ");
			int dif = sc.nextInt();
			int count = 0;
			for (int i = sNum;; i += dif) {
				System.out.print(i + " ");
				count++;
				if (count == 10) {
					break;
				}
			}
			/*
			 * for (int i =sNum, count =0; count<=10; i+=num,count++){
			 * 	System.out.print(i + " ");
			 * }  -> 코드 자체는 적은데 너무 가독성이 안좋아서 잘 안쓰임.. for문 안에서는 변수 하나 조건 하나
			 */
			System.out.println();
		}
	}

	public void practice6() {
		/*
		 * 다음 실행 화면처럼 작업을 수행하는 프로그램을 구현하시오. [실행화면] 연산자(+, -, *, /, %) : + 정수1 : 10 정수2 :
		 * 4 10 + 4 = 14 연산자(+, -, *, /, %) : / 연산자(+, -, *, /, %) : / 정수1 : 10 정수1 : 10
		 * 정수2 : 4 정수2 : 0 10 / 4 = 2 0으로 나눌 수 없습니다. 다시 입력해주세요. 연산자(+, -, *, /, %) : ^
		 * 연산자(+, -, *, /, %) : exit 정수1 : 10 프로그램을 종료합니다. 정수2 : 4 없는 연산자입니다. 다시 입력해주세요.
		 */

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("연산자(+, -, *, /, %) : ");
			String op = sc.nextLine();
			if (op.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			System.out.print("정수 1 : ");
			int num1 = sc.nextInt();
			System.out.print("정수 2 : ");
			int num2 = sc.nextInt();
			sc.nextLine(); // 버퍼에 남아있는 줄바꿈 문자 제

			switch (op) {
			case "+": System.out.printf("%d + %d = %d", num1, num2, num1 + num2);break;
			case "-": System.out.printf("%d - %d = %d", num1, num2, num1 - num2);break;
			case "*": System.out.printf("%d * %d = %d", num1, num2, num1 * num2);break;
			case "/":
				if (num2 != 0)
					System.out.printf("%d / %d = %d", num1, num2, num1 / num2);
				else
					System.out.print("0으로 나눌 수 없습니다."); break;
			case "%": System.out.print(num1 + " + " + num2 + " = " + num1 % num2);break;
			default: System.out.println("없는 연산자입니다. 다시 입력해주세요."); continue;
			}
			System.out.println();
		}

	}
}
