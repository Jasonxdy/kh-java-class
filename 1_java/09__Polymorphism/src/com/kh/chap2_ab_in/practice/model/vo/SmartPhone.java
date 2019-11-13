package com.kh.chap2_ab_in.practice.model.vo;

public abstract class SmartPhone implements CellPhone, TouchDisplay {

//	- maker : String // 제조사 정보
//	+ SmartPhone()
//	+ printInformation() : String
//	+ setMaker(maker : String) : void
//	+ getMaker() : String
	
	private String maker;
	
	public SmartPhone() {
		// TODO Auto-generated constructor stub
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}
	
	public abstract String printInformation();
}
