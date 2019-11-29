package com.kh.chap2_ab_in.model.vo;

public interface Animal extends AnimalParent1, AnimalParent2 {
	
	//인터페이스 끼리의 상속은 extends 사용
	// --> 인터페이스 내부에 강제로 구현 시켜야될 기능이 증가하므로 인터페이스의 크기가 확장됨
	
	// 클래스가 인터페이스 상속 시 implements 사용
	// --> 인터페이스가 강제로 구현하라고 명령한 기능을 구현해야 하기 때문에 implements
	
	// 인터페이스는 다중 상속이 가능!
	
	
//	public static final int LIFE = 1;
	int LIFE = 1;
	// 인터페이스에서 필드 선언 시 모든 변수는 묵시적으로 public static final임
	
	public abstract void breathing();

	void move();
	// 인터페이스에서 모든 메소드는 묵시적으로 public abstract이다.
	
	
}
