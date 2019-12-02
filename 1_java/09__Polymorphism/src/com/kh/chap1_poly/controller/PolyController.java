package com.kh.chap1_poly.controller;

import com.kh.chap1_poly.model.vo.Child1;
import com.kh.chap1_poly.model.vo.Child2;
import com.kh.chap1_poly.model.vo.Parent;

public class PolyController {
	
	// 다형성
	// 부모 클래스 타입의 참조변수로 상속 관계에 있는 여러 타입의 자식 객체를 참조할 수 있는 기술
	
	public void method1() {
		// 1. 부모 타입 레퍼런스 변수로 부모 객체 다루기
		System.out.println("1. 부모 타입 레퍼런스 변수로 부모 객체 다루기");
		Parent p1 = new Parent();
	//    ㄴ부모타입 레퍼런스  	    ㄴ부모 타입 객체
		p1.print();
		
		// 2. 자식 타입 레퍼런스 변수로 자식 객체 다루기
		System.out.println("2. 자식 타입 레퍼런스 변수로 자식 객체 다루기");
		Child1 c1 = new Child1();
    //	 ㄴ 자식타입 레퍼런스	   ㄴ 자식타입 객체
		c1.printChild();// 자식만의 메소드 접근 가능
		c1.print(); // 자식이 상속받은 부모 메소드 접근 가능
		
		// 3. 부모 타입의 레퍼런스 변수로 자식 객체 다루기 (다형성)
		System.out.println();
		System.out.println("3. 부모 타입의 레퍼런스 변수로 자식 객체 다루기");
		Parent p2 = new Child1();
	//   ㄴ부모타입 레퍼런스        ㄴ 자식타입 객체
		// 부모 타입 참조형 변수로 자식 객체를 참조 시 자동으로 업캐스팅(Up-Casting)이 일어난다.  --> 어짜피 객체또한 자료형이므로 자동형변환과 같은 개념임
		
		p2.print();
		// p2.printChild();
		// 부모 타입의 참조형 변수로는 자식 객체 전체 중 부모 객체 부분만 보이므로 자식의 멤버에는 접근할 수 없음!!
		
		// 다운 캐스팅
		// 부모 타입 참조형 변수가 자식 객체의 멤버를 참조할 수 있도록 참조형 변수의 타입을 자식으로 강제 형변환하는 것.
		
		((Child1)p2).printChild(); //괄호의 이유 : 점연산자(.)가 형변환연산자보다 우선순위가 높으므로 괄호로 묶어줘야 함
		
		
		// 4. 자식 타입의 레퍼런스 변수로 형제 타입 객체 다루기
		System.out.println();
		System.out.println("4. 자식 타입의 레퍼런스 변수로 형제 타입 객체 다루기");
		//Child2 c2 = new Child1();
		Child2 c2 = (Child2)p1;
		// 컴파일 에러는 발생하지 않음. 그러나 실행 중 형변환 관련 문제가 발생함. (--> RunTime error라고 불림)
	}
}
