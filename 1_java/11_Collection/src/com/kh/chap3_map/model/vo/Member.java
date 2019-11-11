package com.kh.chap3_map.model.vo;

public class Member {
	
	
//	- password:String
//	- name:String
//	+ Member()
//	+ Member(password:String, name:String)
//	+ setter() / getter()
//	+ toString() : String
	
	private String password;
	private String name;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String password, String name) {
		super();
		this.password = password;
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Member [password=" + password + ", name=" + name + "]";
	}

}
