package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

/**
 * @author user1
 *
 */
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

	
	
	/**
	 * 회원 가입을 위한 Service
	 * @param signUpMember
	 * @return result
	 * @throws Exception
	 */
	public abstract int signUp(Member signUpMember) throws Exception;


	
	/**
	 * 아이디 중복 검사 Service
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public abstract int idDupCheck(String memberId) throws Exception;



	
	
	/**
	 * 회원 정보 조회 Service
	 * @param memberNo
	 * @return member
	 * @throws Exception
	 */
	public abstract Member selectMember(int memberNo) throws Exception;



	/**
	 * 회원 정보 수정용 Service
	 * @param updateMember
	 * @return result
	 * @throws Exception
	 */
	public abstract int updateMember(Member member) throws Exception;



	/**
	 * 비밀번호 변경용 service
	 * @param member
	 * @param newPwd1
	 * @return result
	 * @throws Exception
	 */
	public abstract int updatePwd(Member member, String newPwd1) throws Exception;



	/**
	 * 회원 탈퇴 service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public abstract int deleteMember(Member member) throws Exception;
	
	

}
