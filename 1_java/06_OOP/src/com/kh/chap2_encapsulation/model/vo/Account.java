package com.kh.chap2_encapsulation.model.vo;

public class Account {

	// 클래스 구조
	/*
	 * 클래스 선언부 (==Class Signature) {
	 * 
	 * 		// 필드 (== 멤버 (클래스를 구성하는 구성원의 의미)변수)
	 * 
	 * 		// 생성자
	 * 
	 * 		// 메소드
	 * }
	 * 
	 */
	
	//필드
	private String name; // 예금주
	private String accountNumber; // 계좌번호
	private String password; // 비밀번호
	private int bankCode; // 은행코드
	private int balance; // 잔액
	
	//생성자 : 객체 생성 시 초기 필드값과 생성되자마자 수행할 기능을 작성하는 부분
	public Account() {}
	
	// 메소드
	
	// getter / setter : getter는 값을 얻는, setter는 값을 넣는 메소드
	// name에 대한 getter/setter
	
	public void setName(String name) {
		this.name = name;
		// 필드 name  전달받은 name
	}
	
	public String getName() {
		return name;
	}
	
	// accountNumber의 getter/setter
	/*
	 * < setter 작성 방법 >
	 * public void set필드명(필드의자료형 변수명(==필드명)){
	 * this.필수명 = 변수명;
	 * }
	 */
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	/*
	 * getter 작성 방법
	 * public 반환형 get필드명() {
	 * return 필드명;
	 * }
	 */
	
	public String getAcocuntNumber() {
		return accountNumber;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	
	public int getBankCode() {
		return bankCode;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getBalance() {
		return balance;
	}
	
	// 입금 기능 
	public void deposit(int money) {
						// 입금하고자 하는 금액이 int money로 전달되어 넘어옴
		balance += money;
		System.out.println(name+"님의 계좌에 "+ money + "원이 입금됨.");
	
	
	}
	
}
