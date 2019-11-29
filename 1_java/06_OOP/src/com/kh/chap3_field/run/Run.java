package com.kh.chap3_field.run;

import com.kh.chap1_Abstraction.model.vo.Person;
import com.kh.chap3_field.model.vo.Korean;

public class Run {
	
	public static void main(String[] args) {
		
		// 필드 (Field)
		// C언어에서 말하는 '전역변수'와 비슷한 개념으로 해당 클래스 내부 어디서든 접근 가능한 변수
		// + 접근 제한자 또는 예약어(ex: static)에 따라 접근 범위 확장
		
		// 지역변수
		// 메소드, 생성자, 제어문 등의 블럭({ }) 내부에서 선언된 변수로 해당 선언된 블록 내에서만 사용 가능
		// 생명 주기 (life cycle) : 생성된 시점 (그 코드가 실행된 시점) ~ 해당 메소드가 종료되는 시점 

		
		// 필드의 종류
		/*
		 * 1. 멤버 변수 (== 인스턴스 변수 : 인스턴스 안에 생성되는 변수이기 때문)
		 *  생성 : new를 통해 인스턴스 (객체) 생성 시 메모리에 할당
		 *  소멸 : 인스턴스가 소멸시 소멸
		 * 
		 * 2. 클래스 변수(== static 변수 ) : 어떤 클래스에서든지 사용할 수 있기때문에 이렇게 부름 ;;
		 * --> static 예약어가 붙은 변수
		 *  생성 : 프로그램 실행 시 static 영역에 메모리 할당
		 *  소멸 : 프로그램 종료 시 소멸
		 */
		
		// 프로그램 시작 시 static 영역에 메모리가 할당 되는지 확인
		System.out.println(Korean.nCode);
		// 패키지 내 아무곳에서나 불러낼 수 있음... static 변수이므로 프로그램 시작하면 일단 싹 긁어서 static영역에 붙은거 업로드
		
		// 동일 객체간의 static 변수 공유 확인
		Korean k1 = new Korean();
		Korean k2 = new Korean();
		System.out.println("k1의 정보");
		System.out.println("age : "+ k1.getAge());
		System.out.println("nCode : "+ k1.nCode);
		System.out.println("k2의 정보");
		System.out.println("age : "+ k2.getAge());
		System.out.println("nCode : "+ k2.nCode);
		
		k1.setAge(10);
		k2.setAge(20);
		
		System.out.println("k1의 정보");
		System.out.println("age : "+ k1.getAge());
		System.out.println("nCode : "+ k1.nCode);
		System.out.println("k2의 정보");
		System.out.println("age : "+ k2.getAge());
		System.out.println("nCode : "+ k2.nCode);
		
		// Static 영역에 저장된 Korean.nCode 값 직접 변경
		Korean.nCode = 1;
		System.out.println("k1의 정보");
		System.out.println("age : "+ k1.getAge());
		System.out.println("nCode : "+ k1.nCode);
		System.out.println("k2의 정보");
		System.out.println("age : "+ k2.getAge());
		System.out.println("nCode : "+ k2.nCode);
		
		//k1.nCode 값 변경 시 공유 확인
		k1.nCode = 2;
		System.out.println("k1의 정보");
		System.out.println("age : "+ k1.getAge());
		System.out.println("nCode : "+ k1.nCode);
		System.out.println("k2의 정보");
		System.out.println("age : "+ k2.getAge());
		System.out.println("nCode : "+ k2.nCode);
		

		
	}

}
