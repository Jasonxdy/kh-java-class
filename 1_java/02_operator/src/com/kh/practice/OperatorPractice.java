package com.kh.practice;

import java.util.Scanner;

public class OperatorPractice {
	/// 연산자 실습문제 ///
	
	
	// 실습문제 1번
	public void practice1() {
		/*
키보드로 입력 받은 하나의 정수가 양수이면 “양수다“, 양수가 아니면 “양수가 아니다“를 출력하세요.
ex.
정수 : -9
양수가 아니다
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int input = sc.nextInt();
		String result = input>0? "양수다" : "양수가 아니다";
		System.out.println(result);
	}
	
	public void practice2() {
		/*
		 * 키보드로 입력 받은 하나의 정수가 양수이면 “양수다“,
양수가 아닌 경우 중에서 0이면 “0이다“, 0이 아니면 “음수다”를 출력하세요.
ex.
정수 : -9
음수다
		 * */
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int input = sc.nextInt();
		String result = input>0? "양수다" : input==0? "0이다" : "음수다";
		System.out.println(result);
		
	}

	public void practice3() {
		/*
		 * 메소드 명 : public void practice3(){}
키보드로 입력 받은 하나의 정수가 짝수이면 “짝수다“, 짝수가 아니면 “홀수다“를 출력하세요.
ex.
정수 : 5
홀수다 
		 * */
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int input = sc.nextInt();
		String result = input%2==0? "짝수다" : "홀수다";
		System.out.println(result);
	}

	public void practice4() {
		/*
		 * 메소드 명 : public void practice4(){}
모든 사람이 사탕을 골고루 나눠가지려고 한다. 인원 수와 사탕 개수를 키보드로 입력 받고
1인당 동일하게 나눠가진 사탕 개수와 나눠주고 남은 사탕의 개수를 출력하세요.
ex.
인원 수 : 29
사탕 개수 : 100
1인당 사탕 개수 : 3
남는 사탕 개수 : 13
		 * */
		
		Scanner sc = new Scanner(System.in);
		System.out.print("인원 수 : ");
		int people = sc.nextInt();
		System.out.print("사탕 개수 : ");
		int candy = sc.nextInt();
		System.out.println("1인당 사탕 개수 : " + candy/people);
		System.out.println("남은 사탕 개수 : " + candy%people);
	}

	public void practice5() {
		/*
		 * 키보드로 입력 받은 값들을 변수에 기록하고 저장된 변수 값을 화면에 출력하여 확인하세요.
이 때 성별이 ‘M’이면 남학생, ‘M’이 아니면 여학생으로 출력 처리 하세요.
ex.
이름 : 홍길동
학년(숫자만) : 3
반(숫자만) : 4
번호(숫자만) : 15
성별(M/F) : M
성적(소수점 아래 둘째 자리까지) : 85.75
3학년 4반 15번 홍길동 남학생의 성적은 85.75이다.
		 * */
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("학년 (숫자만) : ");
		int grade = sc.nextInt();
		System.out.print("반 (숫자만) : ");
		int cls =sc.nextInt();
		System.out.print("번호 (숫자만) : ");
		int num = sc.nextInt();
		sc.nextLine();
		System.out.print("성별 (M/F): ");
		char sex = sc.nextLine().charAt(0);
		String sx = (sex=='M')? "남" : "여";
		System.out.print("성적(소수점 아래 둘째 자리까지 : ");
		double score = sc.nextDouble();
	
		System.out.println(grade+"학년 "+cls+"반 "+num+"번 "+name+" "+sx+"학생의 성적은 "+score+"이다.");
		
		
	}

	public void practice6() {
		/*
		 * 나이를 키보드로 입력 받아 어린이(13세 이하)인지, 청소년(13세 초과 ~ 19세 이하)인지,
성인(19세 초과)인지 출력하세요.
ex.
나이 : 19
청소년
		 * */
		
		Scanner sc = new Scanner(System.in);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		String result = age<=13? "어린이" : age<=19? "청소년" : "성인";
		System.out.println(result);
	}

	public void practice7() {
		/*
		 * 국어, 영어, 수학에 대한 점수를 키보드를 이용해 정수로 입력 받고,
세 과목에 대한 합계(국어+영어+수학)와 평균(합계/3.0)을 구하세요.
세 과목의 점수와 평균을 가지고 합격 여부를 처리하는데
세 과목 점수가 각각 40점 이상이면서 평균이 60점 이상일 때 합격, 아니라면 불합격을 출력하세요.
ex.
국어 : 60
영어 : 80
수학 : 40
합계 : 180
평균 : 60.0
합격

		 * */
		Scanner sc = new Scanner(System.in);
		System.out.print("국어 : ");
		int lang = sc.nextInt();
		System.out.print("영어 : ");
		int eng = sc.nextInt();
		System.out.print("수학 : ");
		int mat = sc.nextInt();
		
		int total = lang + eng + mat;
		float avg = (float)(total)/3;
		String result = (lang>=40&&eng>=40&&mat>=40)&&(avg>=60)? "합격":"불합격";
				
		System.out.println("합계 : " + total);
		System.out.println("평균 : " + avg);
		System.out.println("결과 : " + result);

		
	}

	public void practice8() {
		/*
		 * 주민번호를 이용하여 남자인지 여자인지 구분하여 출력하세요. (charAt() 활용)
ex.
주민번호를 입력하세요(- 포함) : 132456-2123456
여자
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("주민번호를 입력하세요 (- 포함) : ");
		char gender = sc.nextLine().charAt(7);
		String sex = gender=='1'||gender=='3'? "남자" : gender=='2'||gender=='4'? "여자" : "잘못 입력하셨습니다";
		System.out.println(sex);
		
		
	}

	public void practice9() {
		/*
		 * 키보드로 정수 두 개를 입력 받아 각각 변수(num1, num2)에 저장하세요.
그리고 또 다른 정수를 입력 받아 그 수가 num1 이하거나 num2 초과이면 true를 출력하고
아니면 false를 출력하세요.
(단, num1은 num2보다 작아야 함)
ex.
정수1 : 4
정수2 : 11
입력 : 13
true
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 1: ");
		int num1 = sc.nextInt();
		System.out.print("정수 2: ");
		int num2 = sc.nextInt();
		System.out.print("입력 : ");
		int num = sc.nextInt();
		
		boolean result = num<=num1||num2<num;
		System.out.println(result);
		
	}

	public void practice10() {
		/*
		 * 3개의 수를 키보드로 입력 받아 입력 받은 수가 모두 같으면 true, 아니면 false를 출력하세요.
ex.
입력1 : 5
입력2 : -8
입력3 : 5
false
		 * */
		
		Scanner sc = new Scanner(System.in);
		System.out.print("입력 1 : ");
		int num1 = sc.nextInt();
		System.out.print("입력 2 : ");
		int num2 = sc.nextInt();
		System.out.print("입력 3 : ");
		int num3 = sc.nextInt();
		
		boolean isTrue = num1==num2&&num1==num3;
		System.out.println(isTrue);
	}

	public void practice11() {
		/*
		 * A, B, C 사원의 연봉을 입력 받고 각 사원의 연봉과 인센티브를 포함한 연봉을 계산하여 출력하고
인센티브 포함 급여가 3000만원 이상이면 “3000 이상”, 미만이면 “3000 미만”을 출력하세요.
(A 사원의 인센티브는 0.4, B 사원의 인센티브는 없으며, C 사원의 인센티브는 0.15)
ex.
A사원의 연봉 : 2500
B사원의 연봉 : 2900
C사원의 연봉 : 2600
A사원 연봉/연봉+a : 2500/3500.0
3000 이상
B사원 연봉/연봉+a : 2900/2900.0
3000 미만
C사원 연봉/연봉+a : 2600/2989.9999999999995
3000 미만
		 * */
		
		Scanner sc = new Scanner(System.in);
		System.out.print("A사원의 연봉 : ");
		int aincome = sc.nextInt();
		System.out.print("B사원의 연봉 : ");
		int bincome = sc.nextInt();
		System.out.print("C사원의 연봉 : ");
		int cincome = sc.nextInt();
		
		double ic_a = 1.4;
		double ic_b = 1;
		double ic_c = 1.15;
		
		double a = aincome*ic_a; // aincome *=ic_a;
		double b = bincome*ic_b;
		double c = cincome*ic_c;
		
		String s1 = "3000만원 이상";
		String s2 = "3000만원 미만";
		
		
		String result1 = a>=3000? s1 : s2;
		String result2 = b>=3000? s1 : s2;
		String result3 = c>=3000? s1 : s2;
		
		/*
		System.out.printf("%c사원의 연봉/연봉+a : %d/%.1f\n%s", 'A',aincome,a,result1);
		System.out.printf("%c사원의 연봉/연봉+a : %d/%.1f\n%s", 'B',bincome,b,result2);
		System.out.printf("%c사원의 연봉/연봉+a : %d/%.1f\n%s", 'C',cincome,c,result3);
		*/
		
		System.out.println("A사원 연봉/연봉+a : "+aincome+"/"+a+"\n"+result1);
		System.out.println("B사원 연봉/연봉+a : "+bincome+"/"+b+"\n"+result2);
		System.out.println("C사원 연봉/연봉+a : "+cincome+"/"+c+"\n"+result3);
		
		
		
	}

}
