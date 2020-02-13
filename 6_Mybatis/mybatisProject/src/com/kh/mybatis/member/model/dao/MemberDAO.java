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
	public Member loginMember(SqlSession session, Member member) throws Exception {
		
		return session.selectOne("memberMapper.loginMember", member); // 리턴용 객체
		
	}

	
	
	
	/**
	 * 아이디 중복검사 dao
	 * @param session
	 * @param id
	 * @return result : int
	 * @throws Exception
	 */
	public int idDupCheck(SqlSession session, String id) throws Exception {
		
		return session.selectOne("memberMapper.idDupCheck", id);
		
	}




	/**
	 * 회원 가입 dao
	 * @param session
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(SqlSession session, Member member) throws Exception {
		
		return session.insert("memberMapper.signUp", member);
		
	}




	/**
	 * 회원 정보 조회용 DAO
	 * @param session
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public Member selectMember(SqlSession session, String memberId) throws Exception {
		
		return session.selectOne("memberMapper.selectMember", memberId);
	}




	
	
	
	/**
	 * 회원 정보 수정용 DAO
	 * @param session
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(SqlSession session, Member member) throws Exception {
		
		return session.update("memberMapper.updateMember", member);
	}




	
	/**
	 * 비밀번호 일치 확인용 DAO
	 * @param session
	 * @param loginMember
	 * @return result : int
	 * @throws Exception
	 */
	public int checkPwd(SqlSession session, Member loginMember) throws Exception {
		return session.selectOne("memberMapper.checkPwd", loginMember);
	}




	
	/**
	 * 회원 삭제용 DAO
	 * @param session
	 * @param loginMember
	 * @return result : int
	 * @throws Exception
	 */
	public int deleteMember(SqlSession session, Member loginMember) throws Exception {
		return session.update("memberMapper.deleteMember", loginMember);
	}

}
