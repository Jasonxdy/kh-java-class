package com.kh.chap1.practice;

import java.util.Scanner;

public class ControlPractice {
	
	public void practice1() {
		/*
		 * 아래 예시와 같이 메뉴를 출력하고 메뉴 번호를 누르면 “OO메뉴입니다“를,
종료 번호를 누르면 “프로그램이 종료됩니다.”를 출력하세요.
ex.
1. 입력
2. 수정
3. 조회
4. 삭제
7. 종료
메뉴 번호를 입력하세요 : 3
조회 메뉴입니다.
		 * 
		 * */
		Scanner sc = new Scanner (System.in);
		System.out.println("1. 입력");
		System.out.println("2. 수정");
		System.out.println("3. 조회");
		System.out.println("4. 삭제");
		System.out.println("7. 종료");
		System.out.print("메뉴 번호를 입력하세요 : ");
		int num = sc.nextInt();
		switch (num) {
		case 1 : System.out.println("입력 메뉴입니다"); break;
		case 2 : System.out.println("수정 메뉴입니다"); break;
		case 3 : System.out.println("조회 메뉴입니다"); break;
		case 4 : System.out.println("삭제 메뉴입니다"); break;
		case 7 : System.out.println("프로그램이 종료됩니다"); break;
		default : System.out.println("잘못 입력하셨습니다");
		}
	}
	
	public void practice2() {
		/*
		 * 키보드로 정수를 입력 받은 정수가 양수이면서 짝수일 때만 “짝수다”를 출력하고
짝수가 아니면 “홀수다“를 출력하세요.
양수가 아니면 “양수만 입력해주세요.”를 출력하세요.
ex.
숫자를 한 개 입력하세요 : -8
양수만 입력해주세요
*/
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 한 개 입력하세요 : ");
		int num =sc.nextInt();
		if (num>0) {
			switch (num%2) {
			case 0 : System.out.println("짝수다"); break;
			case 1 : System.out.println("홀수다"); break;
			}
		} else {
			System.out.println("양수만 입력해주세요.");
		}		
	}

	public void practice3() {
		/*
		 * 국어, 영어, 수학 세 과목의 점수를 키보드로 입력 받고 합계와 평균을 계산하고
합계와 평균을 이용하여 합격 / 불합격 처리하는 기능을 구현하세요.
(합격 조건 : 세 과목의 점수가 각각 40점 이상이면서 평균이 60점 이상일 경우)
합격 했을 경우 과목 별 점수와 합계, 평균, “축하합니다, 합격입니다!”를 출력하고
불합격인 경우에는 “불합격입니다.”를 출력하세요.
		 * */
		Scanner sc = new Scanner(System.in);
		System.out.print("국어점수 : ");
		int lan = sc.nextInt();
		System.out.print("수학점수 : ");
		int mat = sc.nextInt();
		System.out.print("영어점수 : ");
		int eng = sc.nextInt();
		int tot = lan + mat + eng;
		double avg = tot/3;
		
		if (lan>=40&&mat>=40&&eng>=40&&avg>=60) {
			System.out.println("국어 : " + lan);
			System.out.println("수학 : " + mat);
			System.out.println("영어 : " + eng);
			System.out.println("합계 : " + tot);
			System.out.println("평균 : " + avg);
			System.out.println("축하합니다, 합격입니다");
		} else {
			System.out.println("불합격입니다.");
		}
	}
	
	public void practice4() {
		/*
		 * 수업 자료(7page)에서 if문으로 되어있는 봄, 여름, 가을, 겨울 예제를 switch문으로 바꿔서 출력하세요.
ex 1.
1~12 사이의 정수 입력 : 8
8월은 여름입니다.
ex 2.
1~12 사이의 정수 입력 : 99
99월은 잘못 입력된 달입니다.
		 * */
		Scanner sc = new Scanner(System.in);
		System.out.print("1~12 사이의 정수 입력 : ");
		int mth = sc.nextInt();
		switch(mth) {
		case 3: case 4 : case 5: System.out.println(mth+"월은 봄입니다"); break;
		case 6: case 7 : case 8: System.out.println(mth+"월은 여름입니다"); break;
		case 9: case 10 : case 11: System.out.println(mth+"월은 가을입니다"); break;
		case 12: case 1 : case 2: System.out.println(mth+"월은 겨울입니다"); break;
		default : System.out.println(mth+"월은 잘못 입력된 달입니다.");
		}	
	}
	
	public void practice5() {
		/*
		 * 아이디, 비밀번호를 정해두고 로그인 기능을 작성하세요.
로그인 성공 시 “로그인 성공”,
아이디가 틀렸을 시 “아이디가 틀렸습니다.“,
비밀번호가 틀렸을 시 “비밀번호가 틀렸습니다.”를 출력하세요.
		 * */
		Scanner sc = new Scanner(System.in);
		// 스트링은 참조형이므로 비교가 불가능하다!!
		/* 문자열(String)끼리의 값 비교 방법
		 * 
		 * String 자료형은 == (비교연산자)로는 값이 같은지 비교 불가
		 * 
		 * ** equals()라는 String 제공 메소드를 사용해야 함.
		 * 
		 * ex)
		 * String str = "abc";
		 * 
		 * str == "abc"  -> false (비교 불가)
		 * 
		 * str.equals("abc")  -> true (비교 가능)
		 * 
		 * 
		 * */
		
		System.out.print("아이디 : ");
		String inputId =sc.nextLine();	
		
		System.out.print("비밀번호 : ");
		String inputPwd =sc.nextLine();
		
		/*
		if (inputId.equals("myId")&&inputPwd.equals("myPassword")==true) {
			System.out.println("로그인 성공");
		} else if (inputId.equals("myId")||inputPwd.equals("myPassword")==false) {
			System.out.println("아이디 및 비밀번호가 틀렸습니다");
		} else {
			if (inputId.equals("myId")==false) {
				System.out.println("아이디가 틀렸습니다");
			} else {
				System.out.println("비밀번호가 틀렸습니다");
			}
			
		}
		*/
		
		switch (inputId) {
		case "myId" :
			switch(inputPwd) {
			case "myPassword" : System.out.println("로그인 성공"); break;
			default : System.out.println("비밀번호가 틀렸습니다.");
			} break;
		default :
			switch(inputPwd) {
			case "myPassword" : System.out.println("아이디가 틀렸습니다"); break;
			default : System.out.println("아이디 및 비밀번호가 틀렸습니다.");
		}
		
		}
	}
	
	public void practice6() {
		/*
		 * 사용자에게 관리자, 회원, 비회원 중 하나를 입력 받아 각 등급이 행할 수 있는 권한을 출력하세요.
단, 관리자는 회원관리, 게시글 관리, 게시글 작성, 게시글 조회, 댓글 작성이 가능하고
회원은 게시글 작성, 게시글 조회, 댓글 작성이 가능하고
비회원은 게시글 조회만 가능합니다.
ex.
권한을 확인하고자 하는 회원 등급 : 관리자
회원관리, 게시글 관리 게시글 작성, 댓글 작성 게시글 조회
		 * */
		Scanner sc = new Scanner (System.in);
		System.out.print("권한을 확인하고자 하는 회원 등급 : ");
		String grade = sc.nextLine();
		switch (grade) {
		case "관리자" : System.out.print("회원관리, 게시글 관리,");
		case "회원" : System.out.print (" 게시글 작성, 댓글 작성,");
		case "비회원" : System.out.print (" 게시글 조회"); break;
		default : System.out.println("잘못 입력하셨습니다");
		}		
	}
	
	public void practice7() {
		/*
키, 몸무게를 double로 입력 받고 BMI지수를 계산하여 계산 결과에 따라
저체중/정상체중/과체중/비만을 출력하세요.
BMI = 몸무게 / (키(m) * 키(m))
BMI가 18.5미만일 경우 저체중 / 18.5이상 23미만일 경우 정상체중
BMI가 23이상 25미만일 경우 과체중 / 25이상 30미만일 경우 비만
BMI가 30이상일 경우 고도 비만
ex.
키(m)를 입력해 주세요 : 1.65
몸무게(kg)를 입력해 주세요 : 58.4
BMI 지수 : 21.45087235996327
정상체중
*/
		Scanner sc = new Scanner(System.in);
		System.out.print("키(m)를 입력해 주세요 : ");
		double height = sc.nextDouble();
		System.out.print("몸무게 (kg)를 입력해 주세요 : ");
		double weight = sc.nextDouble();
		double bmi = weight/(height*height);
		String result;
		if (30<=bmi) {
			result = "고도 비만";
		} else if (25<=bmi) {
			result = "비만";
		} else if (23<=bmi) {
			result = "과체중";
		} else if (18.5<=bmi) {
			result = "정상 체중";
		} else {
			result = "저체중";
		}
		
		System.out.println("BMI 지수 : " + bmi);
		System.out.println(result);
		
	}
	
	public void practice8() {
		/*
		 * 키보드로 두 개의 정수와 연산 기호를 입력 받아 연산 기호에 맞춰 연산 결과를 출력하세요.
(단, 두 개의 정수 모두 양수일 때만 작동하며 없는 연산 기호를 입력 했을 시
“잘못 입력하셨습니다. 프로그램을 종료합니다.” 출력)
ex.
피연산자1 입력 : 15
피연산자2 입력 : 4
연산자를 입력(+,-,*,/,%) : /
15 / 4 = 3.750000
		 */
		
		Scanner sc = new Scanner(System.in);
		System.out.print("피연산자1 입력 : ");
		int num1 = sc.nextInt();
		System.out.print("피연산자2 입력 : ");
		int num2 = sc.nextInt();
		sc.nextLine();
		System.out.print("연산자를 입력(+,-,*,/,%) : ");
		char op  = sc.nextLine().charAt(0);
		if (num1>0&&num2>0) {
			switch(op) {
			case '+' : System.out.println(num1 + " + " + num2 + " = " + num1+num2); break;
			case '-' : System.out.println(num1 + " - " + num2 + " = " + (num1-num2)); break;
			case '*' : System.out.println(num1 + " * " + num2 + " = " + num1*num2); break;
			case '/' : System.out.println(num1 + " / " + num2 + " = " + num1/num2); break;
			case '%' : System.out.println(num1 + " % " + num2 + " = " + num1%num2); break;
			default : System.out.println("잘못 입력하셨습니다. 프로그램을 종료합니다.");
			}
			
		} else {
			System.out.println("잘못 입력하셨습니다. 프로그램을 종료합니다.");
		}
		
		/*
		if (num1>0&&num2>0&&((op=='+')||(op=='-')||(op=='*')||(op=='/')||(op=='%')) {
			
		} else {
			System.out.println("잘못 입력하셨습니다. 프로그램을 종료합니다.")
		}
		*/
		
		
	}
	
	public void practice9() {
	/*	중간고사, 기말고사, 과제점수, 출석회수를 입력하고 Pass 또는 Fail을 출력하세요.
		평가 비율은 중간고사 20%, 기말고사 30%, 과제 30%, 출석 20%로 이루어져 있고
		이 때, 출석 비율은 출석 회수는 총 강의 회수 20회 중에서 출석한 날만 따진 값으로 계산하세요.
		70점 이상일 경우 Pass, 70점 미만이거나 전체 강의에 30% 이상 결석 시 Fail을 출력하세요.
	*/
		Scanner sc = new Scanner(System.in);
		System.out.print("중간 고사 점수 : ");
		double mid = sc.nextInt()*0.2;
		System.out.print("기말 고사 점수 : ");
		double fin = sc.nextInt()*0.3;
		System.out.print("과제 점수 : ");
		double hw = sc.nextInt()*0.3;
		System.out.print("출석 횟수 : ");
		int atd = sc.nextInt();
		double atdp = atd;
		double sum = mid+fin+hw+atd;
		
		if (atd>14) { // 출석을 먼저 따져줌... 이런거 정하는게 실력인듯하다 (뭐 먼저 조건문으로 거를지)
			if(sum>=70) {
				System.out.println("========= 결과 =========");
				System.out.println("중간 고사 점수 (20) : " + mid);
				System.out.println("기말 고사 점수 (30) : " + fin);
				System.out.println("과제 점수 (30) : " + hw);
				System.out.println("출석 점수 (20) : " + atdp);
				System.out.println("총점 : " + sum);
				System.out.println("PASS");
			} else {
				System.out.println("FAIL [점수 미달]");
			}
		} else {
			System.out.println("FAIL [출석 회수 부족 ("+atd+"/20)]");
		}
	}
	
	public void practice10() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. 메뉴 출력");
		System.out.println("2. 짝수/홀수");
		System.out.println("3. 합격/불합격");
		System.out.println("4. 계절");
		System.out.println("5. 로그인");
		System.out.println("6. 권한 확인");
		System.out.println("7. BMI");
		System.out.println("8. 계산기");
		System.out.println("9. P/F");
		System.out.print("선택 : ");
		int input = sc.nextInt();
		
		switch(input) {
		case 1 : practice1(); break;
		case 2 : practice2(); break;
		case 3 : practice3(); break;
		case 4 : practice4(); break;
		case 5 : practice5(); break;
		case 6 : practice6(); break;
		case 7 : practice7(); break;
		case 8 : practice8(); break;
		case 9 : practice9(); break;
		default : System.out.println("잘못 입력하셨습니다");
		}
	}
	
	
	
	
	
	
	
}
