package com.kh.chap2_encapsulation.run;

import com.kh.chap2_encapsulation.model.vo.Account;
import com.kh.chap3_field.model.vo.Korean;

public class AccountRun {
	
	public static void main(String[] args) {
		//캡슐화
		/*
		 * 추상화를 통해 정의된 속성, 기능을 하나로 묶어 관리하는 기법
		 * 이 때 클래스 내부의 데이터 (필드 or 멤버변수)에 직접 접근을 제한하는 것이 원칙이므로 private 접근 제한자를 사용.
		 * 
		 * 직접 접근을 대신하여 내부 데이터에 접근을 위한 간접 접근방법 (기능 == 메소드)을 클래스 내부에 작성.
		 * == getter / setter 메소드
		 * 
		 */
		
		Account acc = new Account();
		//생성자 : 객체 생성 시 초기 필드값과 생성되자마자 수행할 기능을 작성하는 부분
		
		// acc.name = "홍길동";
		// --> Account 객체 내부의 name 필드가 private이므로 외부 직접 접근 불가
		// 따라서 , 간접 접근 방법 사용해야 함
		acc.setName("홍길동");
		acc.setAccountNumber("01022471339");
		acc.setPassword("비밀번호");
		acc.setBankCode(10);
		acc.setBalance(100000000);
		
		System.out.println(Korean.nCode);
		
		System.out.println("계좌 정보");
		System.out.println(acc.getName());
		System.out.println(acc.getAcocuntNumber());
		System.out.println(acc.getPassword());
		System.out.println(acc.getBankCode());
		System.out.println(acc.getBalance());
		
		acc.deposit(100000000);
		System.out.println(acc.getBalance());
	}

}
