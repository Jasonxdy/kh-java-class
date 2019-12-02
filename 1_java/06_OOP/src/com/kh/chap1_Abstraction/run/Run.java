package com.kh.chap1_Abstraction.run;

import com.kh.chap1_Abstraction.model.vo.Person;

public class Run {

	public static void main(String[] args) {
		
		/*
		 * 1. 객체 지향 언어 : 객체를 지향하는 언어로써 프로그램 내부 코드들이 객체로 이루어져 있고 이 객체들이 상호작용 하면서 프로그램을 구동시키게 하는 언어!
		 * 
		 * 2. 객체 (속성 + 기능)
		 * - 사전적 의미: 독립적으로 존재하는 인지할 수 있는 모든 것  (무형, 유형 따지지 않음)
		 * - Java에서의 의미 : 클래스에 정의된 내용을 new 연산자를 통해 Heap 영역에 생성(메모리 할당)하는 것
		 * 
		 * 3. 객체 지향 프로그래밍 (OOP)
		 * - 현실세계의 객체들 간의 상호작용을 객체지향 언어를 통해 프로그래밍하여 가상세계에 구현하는 과정
		 * 
		 * 4. Java에서 객체를 구현하기 위해 필요한 것
		 * - Class (추상화 + 캡슐화 반드시 포함)
		 * 
		 */
		
		/*
		 * 추상화
		 * 내가 구현하고자 하는 프로그램의 "목적"에 맞춰 공통적인 부분을 추출해내고 불필요한 부분을 제거해 나가는 과정
		 */
		
		// 객체 생성
		// class == 사용자 정의 자료형 -> Class 명이 자료형이 됨
		// person = 사람의 데이터를 저장할 수 있는 자료형이 됨
		Person person = new Person();
		//자료형   변수명
		
		person.pNo = "000101-1234567";
		person.name = "홍길동";
		person.gender = '남';
		person.address = "서울시 중구 남대문로 120 대일빌딩";
		person.phone = "010-2247-1339";
		person.age = 20;
		
		System.out.println("이름 : " + person.name);
		System.out.println("주민등록번호 : " + person.pNo);
		System.out.println("성별 : " + person.gender);
		System.out.println("나이 : " + person.age);
		System.out.println("전화번호 : " + person.phone);
		System.out.println("주소 : " + person.address);
	}

}
