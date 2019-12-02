package com.kh.chap2_ab_in.model.vo;

public class Chicken extends Bird implements Animal {
	// 추상클래스는 상속해서 사용하며, 인터페이스는 정해진 그대로 
	private double crestLength;
	
	public Chicken() {
		// TODO Auto-generated constructor stub
	}
	
	public Chicken(int wing, double crestLength) {
		super(wing);
		this.crestLength = crestLength;
	}
	
	public double getCrestLength() {
		return crestLength;
	}

	public void setCrestLength(double crestLength) {
		this.crestLength = crestLength;
	}

	@Override
	public void fly() {
		System.out.println("날지 못함");
	}
	
	@Override
	public void breathing() {
		System.out.println("폐 호흡");
	}
	
	@Override
	public void move() {
		System.out.println("두발로 걸어다님");
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
	
}
