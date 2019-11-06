package com.kh.chap1_Object.controller;

import com.kh.chap1_Object.model.vo.Student;

public class ObjectController {
	// API (Application Programming Interface)
	// 응용 프로그램을 만들 때 프로그래밍 언어가 제공하는 기능들을 제어할 수 있는 인터페이스를 제공하는 것
	
	
	// Object 클래스 
	/*
	 * 필드가 존재하지 않고, 11개의 메소드만 존재.
	 * - 모든 클래스의 최상위 부모이기 때문에 어떤 클래스에서든 바로 사용 가능
	 */
	
	public void method1() {
		Student std1 = new Student("홍길동", "A강의장",20, 80);
		System.out.println(std1); // System.out.println(std1.toString()); 실제로는 이런형태인 것
		// ㄴ 실행결과 : com.kh.chap1_Object.model.vo.Student@15db9742 //15db9742 16진수로 암호화된 위치?
		Student std2 = new Student("홍길동", "A강의장",20, 80);
	
		
		// 동등객체 (hashCode()) / 동일객체 (equals())
		// 동등객체 : 완전히 같은 객체
		// 동일객체 : 값이 완전히 같은 객체
		System.out.println("둘이 동등한 객체?" + std1.equals(std2)); //equals : true/false나옴
		
	}



}
