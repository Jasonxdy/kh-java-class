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
		
		// Mybatis에서는 JDBC에서 사용하던 Connection과
		// Connection을 통해서 DB와 데이터 교환, 트랜잭션 처리를 하던 부분을 SqlSession이라는 객체 하나로 대체할 수 있다
		SqlSession session = getSqlSession();
		
		Member loginMember = new MemberDAO().loginMember(session, member);
		
		session.close();
		
		return loginMember;
	}

	
	
	
	
	/**
	 * 아이디 중복검사 서비스
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int idDupCheck(String id) throws Exception {
		SqlSession session = getSqlSession();
		
		int result = new MemberDAO().idDupCheck(session, id);
		
		session.close();
		
		return result;
	}
	
	
	
	
	/**
	 * 회원가입 서비스
	 * @param member
	 * @return result : int
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception {
		
		SqlSession session = getSqlSession();
		
		int result = new MemberDAO().signUp(session, member);
		
		if(result > 0) session.commit();
		else			session.rollback();
		
		session.close();
		
		return result;
	}





	
	/**
	 * 회원 정보 조회용 서비스
	 * @param memberId
	 * @return member
	 * @throws Exception
	 */
	public Member selectMember(String memberId) throws Exception {
		
		SqlSession session = getSqlSession();
		Member selectMember = new MemberDAO().selectMember(session, memberId);
		session.close();
		return selectMember;
	}





	
	/**
	 * 회원 정보 수정용 Service
	 * @param member
	 * @return result : int
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception {
		
		SqlSession session = getSqlSession();
		
		int result = new MemberDAO().updateMember(session, member);
		
		if(result>0) session.commit();
		else			session.rollback();
		
		session.close();
		
		return result;
	}





	
	
	/**
	 * 회원 삭제 서비스
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int deleteMember(Member loginMember) throws Exception {
		SqlSession session = getSqlSession();
		
		MemberDAO memberDAO = new MemberDAO();
		
		int result = 0;
		
		if(memberDAO.checkPwd(session, loginMember) > 0) {
			result = memberDAO.deleteMember(session, loginMember);
				if(result >0) session.commit();
				else			session.rollback();
		} else {
			result = -1;
		}
		
		return result;
	}



}
