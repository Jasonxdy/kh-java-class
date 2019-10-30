package com.kh.variable;

public class D_Cast {
	
	/* 형변환에는 두 종류가 있음
	 * 
	 * 1. 자동 형변환
	 *  - 서로 다른 자료형 연산 시 컴파일러가 값의 범위가 작은 자료형을 큰 자료형으로 변환하는 것
	 * 
	 * 2. 강제 형변환 
	 *  - 값의 범위가 큰 자료형을 작은 자료형으로 강제적으로 변환시키는 것.
	 *  - 강제 형변환 시 데이터의 손실이 발생할 수 있으므로 이를 감안하고 사용.
	 *  - 컴파일러가 하는것이 아닌 사람이 하는 것
	 *  
	 * */
	
	public void rule1() { // 자동 형변환 테스트
		
		int a = 12;
		double d = 3.3;
		double result = a + d;
		// a(int) --> a(double)으로 자동 형변환 됨
		
		System.out.println("result1 : " + result);
		
		//int -> long
		int iNum = 2147483647; // int가 가질 수 있는 가장 큰 수
		long lNum = 1000000000L;
		long result2 = iNum + lNum;
		
		System.out.println("result2 : " + result2);
		
		// long -> float
		// long형이 byte는 더 크지만 float가 값의 범위가 더 크므로 자동 형변환 가능
		long lNum2 = 1234567890123456789L;
		float fNum = 10000000000000000000F;
		float result3 = lNum2 + fNum;
		
		System.out.println("result3 : " + result3);
		
		// char -> int
		// char는 실제 문자가 아닌 정수가 저장되고 저장된 수에 매칭되는 문자가 출력되는 것
		// 그러므로 char는 실제로는 정수형이다.
		
		char ch = 'ㅄ';
		int iNum3 = ch; // =는 대입 연산자
		
		System.out.println("iNum3 : " + iNum3);
		
		char ch2 = 65; // char는 실제로는 정수형이기 때문에 정수로 선언 및 초기화도 가능
		
		System.out.println("ch2 : " + ch2);
		
	}
	
	
	public void rule2() { // 강제 형변환 테스트
		int iNum = 10;
		double dNum = 5.89;
		
		// iNum + dNum --> 실수형 결과 (자동 형변환)
		double result = iNum + dNum;
		
		//But, iNum + dNum --> 정수형 결과 (강제 형변환)
		//int result2 = iNum + (int)dNum;
		int result2 = (int)(iNum + dNum);
		
		// 자동 형변환 결과
		System.out.println("result : " + result);
		// 강제 형변환 결과
		System.out.println("result2 : " + result2);
		
		//byte, short 연산 시 주의사항
		//
		byte bNum1 = 1;
		byte bNum2 = 10;
		
		byte result3 = (byte)(bNum1 + bNum2);
		
	}
	
	
	// 데이터 손실 확인
	public void dataloss() {
		int iNum = 290;
		System.out.println("iNum : " + iNum); // 290
		
		// 강제 형변환 시 데이터 손실 테스트
		byte bNum = (byte)(iNum);
		System.out.println("bNum : " + bNum);
	}
}
