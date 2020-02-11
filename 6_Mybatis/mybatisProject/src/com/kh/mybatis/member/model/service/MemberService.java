package com.kh.mybatis.member.model.service;

import com.kh.mybatis.member.model.dao.MemberDAO;
import com.kh.mybatis.member.model.vo.Member;
import static com.kh.mybatis.common.Template.*;

import org.apache.ibatis.session.SqlSession;

public class MemberService {
	
	/**
	 * 로그인 서비스
	 * @param member
	 * @return member : Member
	 * @throws Exception
	 */
	public Member loginMember(Member member) throws Exception{
		
		SqlSession session = getSqlSession();
		
		return new MemberDAO().loginMember(session, member);
	}

}
