package com.kh.operator;

public class F_Compound {
	
	// 복합 대입 연산자
	/*
	 * 다른 연산자와 대입 연산자를 함께 사용하는 연산자
	 * 
	 * 산술 대입 연산자 : += -= *= /= %= (5종류 존재)
	 * 
	 * 복합 대입 연산자를 사용하는 이유 : 연산처리 속도가 단순 대입연산에 비해 훨씬 빠르므로 사용 권장.
	 * 
	 * */
	
	public void method1() {
		
		int num = 12;
		
		// num을 3 증가시키기
		num = num + 3; // 복합대입연산 사용 X
		System.out.println("num = num + 3 : " + num); //15
	 	
		// num을 또 3 증가시키기 
		num +=3;
		System.out.println("num += 3 : " +num); //18
		
		// num을 5 감소시키기 
		num -=5;
		System.out.println("num -= 5 : " + num); //13
		
		// num을 6배 증가시키기 
		num *=6;
		System.out.println("num *= 6 : " +num); //78
		
		// num을 1/2로 감소시키기
		num /=2;
		System.out.println("num /= 2 : " + num); //39
		
		// num을 4로 나눴을 때 나머지
		num %=4;
		System.out.println("num %= 4 : " + num); // 3

	}

}
