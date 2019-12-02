package com.kh.chap3_field.model.vo;

public class Korean {
	
	private int age;
	public static int nCode;
	// static으로 선언된 변수는(==필드는) 접근 제한자를 public으로 하는 것이 관례
	// (static 영역에 누구든 접근할 수 있기 때문)
	
	// 공유 + 상수 : 모두가 공유하되 변경할 수는 없게 만들기
	public static final double PI = 3.141592;
	
	
	// 인스턴스 초기화 블럭
	// 인스턴스 변수를 초기화 시키는 블럭
	// 객체 생성시 마다 초기화
	{
		age = 1;
	}
	
	// static 초기화 블럭
	// 클래스 변수를 초기화시키는 블럭
	// 프로그램 실행 시 딱 한번 초기화 진행 
	static {
		nCode = 82;
	}

	
	// 생성자 : 무조건 써야함
	public Korean() {}
	
	// 메소드
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	
	
	
}
