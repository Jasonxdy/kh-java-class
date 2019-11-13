package com.kh.chap2_ab_in.practice.model.vo;

public class V40 extends SmartPhone implements NotePen {
	
	public V40() {
		super.setMaker("LG");
	}
	@Override
	public String makeCall() {
		// TODO Auto-generated method stub
		return "번호를 누르고 통화버튼을 누름";
	}
	@Override
	public String takeCall() {
		// TODO Auto-generated method stub
		return "수신 버튼을 누름";
	}
	@Override
	public String picture() {
		// TODO Auto-generated method stub
		return "1200, 1600만 트리플 카메라";
	}
	@Override
	public String charge() {
		// TODO Auto-generated method stub
		return "고속 충전, 고속 무선 충전";
	}
	@Override
	public String touch() {
		// TODO Auto-generated method stub
		return "정전식";
	}
	@Override
	public boolean bluetoothPen() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String printInformation() {
		// TODO Auto-generated method stub
		return "V40은 "+ super.getMaker()+"에서 만들어졌고 제원은 다음과 같다.\r\n" + 
		makeCall()+ "\\n" + 
		takeCall()+ "\n" + 
		picture() + "\n" + 
		charge() + "\n" + 
		touch() + "\n" + 
		"블루투스 펜 탑재 여부 : " + bluetoothPen();
				
	}
}
