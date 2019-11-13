package com.kh.chap1_poly.model.vo;

public class Member {
	
//	- name : String // 회원명
//	- age : int // 회원나이
//	- gender : char // 성별
//	- couponCount : int // 요리학원쿠폰개수 = 0
//	+ Member()
//	+ Member(name:String, age:int, gender:char)
//	+ toString() : String
	
	private String name;
	private int age;
	private char gender;
	private int couponCount;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String name, int age, char gender, int couponCount) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.couponCount = couponCount;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + ", gender=" + gender + ", couponCount=" + couponCount + "]";
	}
	
	

}
