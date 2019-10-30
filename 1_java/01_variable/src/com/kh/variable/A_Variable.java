package com.kh.variable;

public class A_Variable {
	
	// 변수를 사용하지 않는 경우
	public void noVariable () {
		
		System.out.println("====== 변수 사용 X ======");
		System.out.println(2 * 3.141592653589793 * 10);
		System.out.println(3.141592653589793 * 10 * 10);
		System.out.println(3.141592653589793 * 10 * 10 * 20);
		System.out.println(4 * 3.141592653589793 * 10 * 10);
		
	}
	
	// 변수를 사용한 경우
	public void usingVariable() {
		
		double pi = 3.141592653589793; // 원주율
		int r = 10; // 반지름
		int h = 20; // 높이
		
		System.out.println("===== 변수 사용 O =====");
		System.out.println(2 * pi * r); // 원의 둘레
		System.out.println(pi * r * r); // 원의 넓이
		System.out.println(pi * r * r * h); // 원기둥의 부피
		System.out.println(4 * pi * r * r); // 구의 겉넓이
		
	}	

	// 변수 선언
	public void declareVariable() {
		
		// 1. 논리형
		boolean isTrue; // 1byte 할당 - true / false
		
		// 2. 정수형
		byte bNum; // 1byte == 8bit == 2^8개
		short sNum; // 2byte == 16bit == 2^16개
		int iNum; // 4byte == 32bit == 2^32개 -> 정수형 기본 자료형
		long lNum; // 8byte == 64bit == 2^64개
		
		// 3. 실수형 -> 부동 소수점 방식
		float fNum; // 4byte
		double dNum; // 8byte -> 실수형 기본 자료형

		// 4. 문자형
		char ch; // 2byte 유니코드 (숫자당 매칭되는 글자 존재)
				 // 실제 저장되는 값은 정수지만, 매칭되는 문자가 출력됨
		
		// 5. 문자열 (참조형)
		String str; //
		
		// 논리형 값 대입
		isTrue = true;
		// 논리형의 리터럴은 true 또는 false 2개밖에 없음
		
		// 정수형 값 대입
		iNum = 100;
		// 일반 정수는 int의 리터럴 표현법 (뒤에 표시 없는 것들)
		
		lNum = 200l;
		// long의 리터럴은 정수 뒤에 l 또는 L을 표시
		
		bNum = 127;
		sNum = 32767;
		// byte, short 자료형은 옛날 코드와의 호환을 위해 남아있음 (예전에 메모리 딸렸을 때)
		// -> 따로 리터럴 표현식이 없어서 int 표현식을 같이 사용
		
		fNum = 1.1234567f;
		// float의 리터럴은 실수 뒤에 f 또는 F를 표시
		// float는 소수점 아래 8번째 자리에서 반올림함.
		
		dNum = 1.123456789012345;
		// double은 소수점 아래 16번째 자리에서 반올림함.
		// double에도 리터럴에 d 붙혀줘도 상관없는데 기준이 됨으로써 안씀
		// 일반 실수는 double의 리터럴 표현법(뒤에 표시 없는 것들)
		
		// 문자형 값 대입
		ch = 'A'; //char의 리터럴은 '' (홀따옴표) 내부 한 글자
		
		// 문자열 값 대입
		str = "집에 보내줘";// String의 리터럴은 " " 내부 문자열
		
		// 변수에 저장된 값 출력
		System.out.println("isTrue : " + isTrue);
		System.out.println("bNum : " + bNum);
		System.out.println("sNum : " + sNum);
		System.out.println("iNum : " + iNum);
		System.out.println("lNum : " + lNum);
		
		System.out.println("fNum : " + fNum);
		System.out.println("dNum : " + dNum);
		
		System.out.println("ch : "+ ch);
		System.out.println("str : " + str);
		
	}

	// 변수 선언과 동시에 초기화
	public void initVariable() {
		boolean isTrue = false;
		byte bNum = -128; // 1byte
		short sNum = 500; // 2byte
		int iNum = 100000; // 4byte
		long lNum = 12345678901L; // 8byte
		
		float fNum = 1.1234567F; // 4byte
		double dNum = 1.123456789012345; // 8byte
		
		char ch = 'A'; // 2byte
		String str = "Hello World"; // 4byte (참조형은 모두 4byte)
	}

}
