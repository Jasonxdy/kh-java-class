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
	
	
	/**
	 * 비밀번호 수정용 서비스
	 * @param loginMember : Member
	 * @param newPwd : String
	 * @return result : int
	 * @throws Exception
	 */
	public int updatePwd(Member loginMember, String newPwd) throws Exception {
		
		// 서비스 : 비즈니스 로직을 처리. 이때 DB 왔다갔다 하는 것도 비즈니스 로직이기 때문에 몇번 왔다갔다 할지 설계도 service에서 해줌
		
		Connection conn = getConnection();
		MemberDAO memberDao = new MemberDAO();
		
		// 현재 비밀번호 일치 여부 확인
		// SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PWD=?
		int result = memberDao.checkPwd(conn, loginMember);
		
		if(result>0) { // 현재 비밀번호가 일치할 경우
			
			// 비밀번호 변경 진행
			loginMember.setMemberPwd(newPwd);
			result = memberDao.updatePwd(conn, loginMember);
			
			if(result >0) commit(conn);
			else		  rollback(conn);
			
			return result;
			
		} else { // 현재 비밀번호가 일치하지 않을 경우
			return -1;
		}
		
	}

	/**
	 * 회원 탈퇴용 서비스
	 * @param loginMember : Member
	 * @return result : int
	 */
	public int deleteMember(Member loginMember) throws Exception {
		
		Connection conn = getConnection();
		MemberDAO memberDao = new MemberDAO();
		int result = 0;
		
		if(memberDao.checkPwd(conn, loginMember) > 0) { // 비밀번호가 일치하는 경우
			result = memberDao.deleteMember(conn, loginMember.getMemberId());
//			매개변수는 최대한 작게 넘기는게 좋음.. (큰 객체를 계속 넘기면 메모리 손실)
			
			if(result >0) commit(conn);
			else		  rollback(conn);
			
			return result;
			
		} else { // 비밀번호 불일치
			
			return -1;
		}
	}

}
