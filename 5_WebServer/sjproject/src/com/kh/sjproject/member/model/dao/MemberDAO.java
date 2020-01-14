package com.kh.sjproject.member.model.dao;

import static com.kh.sjproject.common.JDBCTemplate.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import com.kh.sjproject.member.model.vo.Member;

public class MemberDAO {

	private Properties prop = null;

	public MemberDAO() throws Exception {
		// member 관련 sql 구문을 관리할 properties 파일 생성
		String fileName = MemberDAO.class.getResource("/com/kh/sjproject/sql/member/member-query.properties").getPath();
		
		prop = new Properties();
		
		prop.load(new FileReader(fileName));

	}

	/**
	 * 로그인용 DAO
	 * @param conn
	 * @param member
	 * @return longinMember : Member
	 * @throws Exception
	 */
	public Member loginMember(Connection conn, Member member) throws Exception {
		
		// 토큰(?) 사용
		PreparedStatement pstmt = null;
		//select 구문의 결과 받음
		ResultSet rset = null;
		
		Member loginMember = null;
		
		String query = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				int memberNo = rset.getInt("MEMBER_NO");
				String memberId = rset.getString("MEMBER_ID");
				String memberName = rset.getString("MEMBER_NM");
				String memberPhone = rset.getString("MEMBER_PHONE");
				String memberEmail = rset.getString("MEMBER_EMAIL");
				String memberAddress = rset.getString("MEMBER_ADDR");
				String memberInterest = rset.getString("MEMBER_INTEREST");
				Date memberEnrollDate = rset.getDate("MEMBER_ENROLL_DATE");
				String memberStatus = rset.getString("MEMBER_STATUS");
				String memberGrade = rset.getString("MEMBER_GRADE");
				
				loginMember = new Member(memberNo, memberId, memberName, memberPhone, memberEmail, memberAddress, memberInterest, memberEnrollDate, memberStatus, memberGrade);
			}
			
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginMember;
	}

	/**
	 * 아이디 중복체크 확인용 DAO
	 * @param conn : Connection
	 * @param id : String
	 * @return result : int
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String id) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("idDupCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
//				1번째 열의 값을 가져오기
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	/**
	 * 회원 가입용 DAO
	 * @param conn : Connection
	 * @param member : Member
	 * @return result : int
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("signUp");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberAddress());
			pstmt.setString(7, member.getMemberInterest());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 회원 정보 조회용 DAO
	 * @param conn : Connection
	 * @param memberId : String
	 * @return selectMebmer : Member
	 * @throws Exception
	 */
	public Member selectMember(Connection conn, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member selectMember = null;
		
		String query = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				selectMember = 	new Member(rset.getInt("MEMBER_NO"),
								rset.getString("MEMBER_ID"),
								rset.getString("MEMBER_NM"),
								rset.getString("MEMBER_PHONE"),
								rset.getString("MEMBER_EMAIL"),
								rset.getString("MEMBER_ADDR"),
								rset.getString("MEMBER_INTEREST"));
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return selectMember;
	}

	/**
	 * 회원 정보 수정용 DAO
	 * @param conn : Connection
	 * @param member : Member
	 * @return result : int
	 */
	public int updateMember(Connection conn, Member member) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPhone());
			pstmt.setString(2, member.getMemberEmail());
			pstmt.setString(3, member.getMemberAddress());
			pstmt.setString(4, member.getMemberInterest());
			pstmt.setString(5, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
