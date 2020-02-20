package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository("memberDAO") // bean 등록 어노테이션의 매개변수  == 등록되는 bean의 이름
						 //  --> 미작성 시 클래스명 중 제일 앞글자만 소문자로 변경되어 bean이 등록됨..!
public class MemberDAO {

	@Autowired // 
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 회원 로그인용 DAO
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member selectMember(Member member) throws Exception{
		return sqlSession.selectOne("memberMapper.loginMember", member);
	}

	
	/**
	 * 회원 가입용 DAO
	 * @param signUpMember
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member signUpMember) throws Exception {
		return sqlSession.insert("memberMapper.signUp", signUpMember);
	}


	/**
	 * 아이디 중복 체크용 DAO
	 * @param memberId
	 * @return result
	 */
	public int idDupCheck(String memberId) throws Exception{
		return sqlSession.selectOne("memberMapper.idDupCheck", memberId);
	}
	
	

}
