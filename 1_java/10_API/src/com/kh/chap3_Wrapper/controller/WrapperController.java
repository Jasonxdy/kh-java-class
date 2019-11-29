package com.kh.chap3_Wrapper.controller;

public class WrapperController {
	
	// Wrapper Class
	/*
	 * - Wrapper : 포장지
	 * - 무엇을 포장하는 클래스? -> 기본 자료형을 객체로 포장하는 클래스
	 * - 왜 포장하는가?
	 * 1) 기본 자료형으로는 수행할 수 없는 기능을 추가하여 사용하기 위해서 ex) 자료형의 최대, 최소값 출력 / String 문자열 ("123123") -> 각 타입 (int면 123123)에 맞게 변환 등등..
	 * 2) 기본 자료형을 객체로 다뤄야만 되는 경우가 있어서 ex) 매개변수 또는 반환값이 객체를 요구하는 경우 / 기본 자료형이 아닌 객체로 값을 저장하는 경우 / 객체간의 비교가 필요한 경우
	 */

	public void method1() {
		// int의 Wrapper Class -> Integer (변수는 1개, 그리고 상수들이 존재:ex -> int의 최대, 최소값 / int 크기 등등)
		System.out.println("int의 byte 크기 : " + Integer.BYTES); // static으로 상수 저장되어있음 public static final
		System.out.println("int의 bit 크기 : " + Integer.SIZE);
		System.out.println("int의 최대값 : " + Integer.MAX_VALUE);
		System.out.println("int의 최소값 : " + Integer.MIN_VALUE);
		System.out.println("Integer의 기본 자료형 : " + Integer.TYPE);
		
		// int의 최소값~최대값 100씩 증가하는 코드 출력
		/*
		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i+=100) {
			System.out.println(i);
		}
		*/
		
		// String형을 기본 자료형으로 변환
		String num1 = "10";
		int num2 = 20;
		System.out.println(Integer.parseInt(num1) + num2); //parse : String 자료형을 기본 자료형으로 바꾸는 것..?
						  // String형 숫자를 int형 숫자로 변환시켜주는 메소드
		
		byte bNum = Byte.parseByte("100"); // parse 메소드는 static에 저장되어 있음... 항상 쓸 수 있도록
		short sNum = Short.parseShort("1000");
		int iNum = Integer.parseInt("123456789");
		long lNum = Long.parseLong("123456789123456789");
		float fNum = Float.parseFloat("3.14");
		double dNum = Double.parseDouble("3.141592123456");
		boolean bool = Boolean.parseBoolean("true");
		// Character 클래스는 parse기능 없음.. 문자열을 아무리 받아도 1개만 받을 수 있어서
		char ch = "Hello".charAt(0);
		
		// 기본 자료형 -> String으로 변환
		String s1 = Integer.valueOf(123).toString(); // valueOf : 필드로 저장하는 메소드
		String s2 = 123 + "";
		
		
		// Boxing : 기본 자료형을 Wrapper Class 객체로 변경하는 과정 -> 기본 자료형을 박스로 포장
		Integer it1 = new Integer(123);
		
		// UnBoxing : Wrapper Class를 기본 자료형으로 변환
		int i = it1.intValue(); // it1에 저장되어 있는 int값을 get해라
		int i2 = (int)it1; // 강제 형변환이 적용 가능...!! -> Integer 객체 내부에 int값 반환
		
		
		// AutoBoxing & UnBoxing : 포장하고 꺼내고를 자동으로!
		Integer it2 = 100; // Auto Boxing
		int i3 = it2; // Auto UnBoxing
		
		
		
		
		
	}
	

}
