package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	
	// Service Interface를 사용하는 이유
	/*
	 * 1. 프로젝트에 규칙성을 부여하기 위해서
	 * 
	 * 2. 클래스간의 결합도를 약화시키기 위함.
	 * --> 유지 보수성 향상
	 * 
	 * 3. Spring AOP를 사용하기 위함(이였다...)
	 * --> 최근에는 필요는 없지만 이전 버전 프로젝트와의 호환을 위해 사용
	 */
	
	
	
	/**
	 * 회원 로그인을 위한 Service
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public abstract Member loginMember (Member member) throws Exception;
		// 인터페이스 메소드는 묵시적으로 public abstract
		// 인터페이스의 필드는 묵시적으로 public static final
		// public protected (default) private
	
	

}
