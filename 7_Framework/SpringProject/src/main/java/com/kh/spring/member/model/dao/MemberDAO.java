package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

/**
 * @author user1
 *
 */
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


	public Member selectMember(int memberNo) throws Exception{
		return sqlSession.selectOne("memberMapper.selectMember", memberNo);
	}


	
	/**
	 * 회원 정보 수정 DAO
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception{
		return sqlSession.update("memberMapper.updateMember", member);
	}


	/**
	 * 비밀번호 확인용 DAO
	 * @param memberNo
	 * @return memberPwd
	 * @throws Exception
	 */
	public String checkCurrent(int memberNo) throws Exception{
		return sqlSession.selectOne("memberMapper.checkCurrent", memberNo);
	}


	/**
	 * 비밀번호 변경 DAO
	 * @param member
	 * @return result
	 * @throws Excepiton
	 */
	public int updatePwd(Member member) throws Exception{
		return sqlSession.update("memberMapper.updatePwd", member);
	}


	/**
	 * 회원 탈퇴 DAO
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteMember(int memberNo) throws Exception{
		return sqlSession.update("memberMapper.deleteMember", memberNo);
	}
	
	

}
