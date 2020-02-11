package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDAO {

	
	/**
	 * 로그인용 DAO
	 * @param session
	 * @param member
	 * @return member : Member
	 * @throws Exception
	 */
	public Member loginMember(SqlSession session, Member member) throws Exception{
		Member loginMember = null; // 리턴용 객체
		
		try {
			loginMember = session.selectOne("memberMapper.loginMember", member);
		} finally {
			session.close();
		}
		
		return loginMember;
	}

}
