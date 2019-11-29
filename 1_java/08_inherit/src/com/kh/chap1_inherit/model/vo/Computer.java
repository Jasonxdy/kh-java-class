package com.kh.chap1_inherit.model.vo;

	  //상속 불가 클래스
public final class Computer extends Product {
					//확장하다
	// 부모클래스의 멤버를 가져와서 자식 클래스의 멤버에 포함시켜 자식의 크기를 확장 -> 상속
	
	private int hdd;
	private int ram;
	private String cpu;
	
	public Computer() {
		super();
	} // 기본생성자
	
	// 매개변수 있는 생성자
	// Computer 자신의 필드 + 상속받은 필드 모두 초기화
	public Computer (int hdd, int ram, String cpu, String brand, String name, String pCode, int price) {
		
		super(brand, name, pCode, price);
		
		this.hdd = hdd;
		this.ram = ram;
		this.cpu = cpu;
		
	}
	
	public void setHdd(int hdd) {
		this.hdd = hdd;
	}
	
	public int getHdd() {
		return hdd;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	
	
	@Override
	public String inform() {
		
		return  super.inform()+ ", " + cpu + ", " + hdd + ", " + ram;
	}

	@Override
	public void print() {
		System.out.println("오버라이딩 했는데?");	
	}
	
	
	

}
