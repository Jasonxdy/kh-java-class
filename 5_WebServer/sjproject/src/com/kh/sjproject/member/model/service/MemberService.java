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

}
