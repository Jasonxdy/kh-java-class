package com.kh.chap2_ab_in.model.vo;

public abstract class Bird implements Animal {
	
	public static final int LEG = 2;
	
	private int wing;
	
	public Bird() {} // 기본생성자
	
	public Bird(int wing) {
		this.wing = wing;
	}

	public int getWing() {
		return wing;
	}

	public void setWing(int wing) {
		this.wing = wing;
	}
	
	public void flappingWing() {
		System.out.println("날개 퍼덕거리기");
	}
	
	// 추상 메소드
	public abstract void fly();

}
