package com.kh.sjproject.member.model.service;

import static com.kh.sjproject.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.sjproject.member.model.dao.MemberDAO;
import com.kh.sjproject.member.model.vo.Member;

public class MemberService {

	/**
	 * 로그인용 Service
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Member member) throws Exception {
		
		Connection conn = getConnection();
		Member loginMember = new MemberDAO().loginMember(conn, member);
		
		return loginMember;
	}

	/**
	 * 아이디 중복 확인용 Service
	 * @param id : String
	 * @return result : int
	 * @throws Exception
	 */
	public int idDupCheck(String id) throws Exception {
		
		Connection conn = getConnection();
		return new MemberDAO().idDupCheck(conn, id);
	}

	/**
	 * 회원 가입용 Service
	 * @param member : Member
	 * @return : int
	 */
	public int signUp(Member member) throws Exception {
		Connection conn = getConnection();
		int result =  new MemberDAO().signUp(conn, member);
		
		// DML 구문이기 때문에 트랜잭션 처리
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		return result;
	}

	/**
	 * 회원 정보 조회용 서비스
	 * @param memberId : String
	 * @return 
	 * @throws Exception
	 */
	public Member selectMember(String memberId) throws Exception {
		Connection conn = getConnection();
		return new MemberDAO().selectMember(conn, memberId);
	}

	/**
	 * 회원 정보 수정용 서비스
	 * @param member : Member
	 * @return result : int
	 */
	public int updateMember(Member member) throws Exception {
		Connection conn = getConnection();
		int result = new MemberDAO().updateMember(conn, member);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		return result;
	}

}
