package com.kh.chap4_constructor.run;

import com.kh.chap4_constructor.model.vo.User;

public class Run {
	public static void main(String[] args) {
		
		// User 객체 생성
		/*
		 * User user1 = new User();
		
		user1.setUserId("admin");
		user1.setUserPwd("1234");
		user1.setUserName("관리자");
		user1.setAge(40);
		user1.setGender('남');
		
		System.out.println(user1.inform());*/
		
		
		// 매개변수 있는 생성자를 이용하여 User 객체 생성
		User user2 = new User("user02", "pass02", "사용자2", 20, '여');
		System.out.println(user2.inform());
		
		//
		User user3 = new User();  //--> 이 경우에 에러가 나는 이유는 User클래스의 생성자가 매개변수가 있기 때문에 JVM이 자동으로 생성자 만들어주지 않음
		
	}
}
