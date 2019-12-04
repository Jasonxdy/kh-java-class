package model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.vo.Member;

public class MemberDAO {
	
	// 1_10. MemberDAO 기본 생성자 코드 작성
	/*
	 * DAO 클래스는 SQL 구문 실행 후 결과 반환 받는 클래스.
	 * 이전 프로젝트에서는 SQL 구문을 DAO에 직접 작성. (정적 코딩)
	 * 유지보수성의 증가를 위해서 SQL 구문을 별도의 .properties 파일에 작성하여
	 * DAO 객체가 생성 되어질 때마다 SQL 구문을 동적으로 읽어들이는 코드 작성 (동적 코딩)
	 */
	
	
	// 1_11. 기본생성자 작성 전에 먼저 SQL 구문이 작성될 query.properties 생성 후
	// 		 Properties 객체 선언
	private Properties prop = null;
	
	
	// 1_12. 기본 생성자 내부에 query.properties 파일을 동적으로 읽어들일 구문 작성
	public MemberDAO() throws Exception {
		prop = new Properties();
		prop.load(new FileReader("query.properties"));
	}
	
	// 1_14. 새로운 회원 정보 추가 DAO
	// Service에서 생성한 Connection 객체를 전달 받을 예정
	
	/**
	 * 새로운 회원 정보 추가용 DAO
	 * @param conn : Connection
	 * @param member : Member
	 * @return result : int
	 * @throws Exception
	 */
	public int insertMember(Connection conn, Member member) throws Exception {
		
		// SQL 수행, DB 자원 반환 (-- Service에서 나머지를 다 떼어감)
		
		// 1_15. SQL을 DB에 전달하고 결과를 반환 받을 PreparedStatement 변수 선언
		//		 + DB 처리 결과를 저장할 result 변수 선언
		PreparedStatement pstmt = null;
		int result = 0;
		
		// 1_16. query.properties 파일에 insertMember 작성 -> query.properties에서 작성
		
		// 1_17. query.properties에서 SQL 구문 얻어오기
		String query = prop.getProperty("insertMember");
		
		// 1_18. 전달 받은 Connection과, query를 DB 전달할 준비
		try {
			pstmt = conn.prepareStatement(query);
			
			// 1_19. 각 위치 홀더에 알맞은 값 대입
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			
			// PreparedStatememt.getChar() 메소드는 존재하지 않음
			// -> Java의 char 자료형을 DB에 전달할 경우 String으로 변환하여 전달할 것!
			pstmt.setString(4, member.getGender() + ""); // -- String으로 변환하기 위해 "" 추가
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setInt(8, member.getAge());
			
			
			// 1_20. SQL 구문 실행 후 결과를 반환받아 저장
			result = pstmt.executeUpdate();
		} finally { 		// -- 여기서 throws 사용하는데도 try-catch 사용하는 이유 finally를 사용하기 위해 
			// 1_21. SQL 수행에 사용된 자원 반환
			// -> JDBCTemplate에서 자원 반환 메소드 작성
			
			// JDBCTemplate static import 해준 후
			close(pstmt);
		}
		
		// 1_22. insert 결과 반환
		return result;
	}
	
	
	
	
	
	
	// 2_6. 모든 회원 정보 조회용 DAO
	public List<Member> selectAll (Connection conn) throws Exception {
		
		// 2_7. SQL 문을 DB에 전달하고 결과값을 반환받아 저장할 변수들 선언
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		
		
		// 2_8. query.properties 파일에 SQL 구문 작성 후 얻어오기
		String query = prop.getProperty("selectAll");
		
		//2_9. 전달받은 Connection과 query를 이용하여 DB로 SQL문 전달
		try {
			stmt = conn.createStatement();
			
			// 2_10. SQL문 수행 후 반환 받은 결과를 rset에 저장
			rset = stmt.executeQuery(query);
			
			// 2_11. 조회 결과를 저장할 ArrayList 객체 생성
			mList = new ArrayList<Member>();
			
			// 2_12. 조회 결과의 한 행의 값을 임시 저장할 Member 참조 변수 선언
			Member member = null;
			
			// 2_13. rset에 저장된 회원 정보를 한 행씩 읽어들여 mList에 추가
			while (rset.next()) {
				String memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				char gender = rset.getString("GENDER").charAt(0);
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				int age = rset.getInt("AGE");
				String address = rset.getString("ADDRESS");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				
				member = new Member(memberId, memberPwd, memberName, gender, email, phone, address, age, enrollDate);
				
				mList.add(member);
			}
			
		} finally {
			// 2_14. 사용한 DB 자원 반환
			close(rset);
			close(stmt);
		}
		
		// 2_15. 조회 결과를 저장한 mList 반환
		return mList;
	}
	
	
	// 3_11. 입력받은 성별의 회원 정보 조회용 DAO
	public List<Member> selectGender(Connection conn, char gen) throws Exception {
		
		// 3_12. SQL을 DB에 전달하고 결과를 반환 받을 PreparedStatement, + DB 조회결과 저장용 ResultSet, List 선언 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		
		// 3_13. query.properties에 SQL 작성 후 얻어오기
		String query = prop.getProperty("selectGender");
		
		// 3_14. 조회 결과 저장용 ArrayList 생성
		//		 + 한 행 임시 저장용 Member 참조 변수 선언
		mList = new ArrayList<Member>();
		Member member = null;
		
		// 3_15. 전달 받은 Connection과 SQL 구문을 DB로 전달할 준비
		try {
			pstmt = conn.prepareStatement(query);
			
			// 3_16. 위치 홀더에 알맞은 값 대입
			pstmt.setString(1, gen + "");
			
			// 3_17. SQL 구문 수행 후 반환 값 rset에 저장
			rset = pstmt.executeQuery();
			
			// 3_18. rset에 저장된 조회 정보를 mList에 추가
			while (rset.next()) {
				String memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				char gender = rset.getString("GENDER").charAt(0);
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				int age = rset.getInt("AGE");
				String address = rset.getString("ADDRESS");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				
				member = new Member(memberId, memberPwd, memberName, gender, email, phone, address, age, enrollDate);
				
				mList.add(member);
			}
		} finally {
			// 3_19. 사용한 DB 자원 반환
			close(rset);
			close(pstmt);
		}
		
		// 3_20. 조회 결과 (mList) 반환
		return mList;
	}
	
	
	
	
	
	public List<Member> selectMemberId(Connection conn, String id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		
		String query = prop.getProperty("selectMemberId");
		mList = new ArrayList<Member>();
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				char gender = rset.getString("GENDER").charAt(0);
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				int age = rset.getInt("AGE");
				String address = rset.getString("ADDRESS");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				
				member = new Member(memberId, memberPwd, memberName, gender, email, phone, address, age, enrollDate);
				
				mList.add(member);
			}
		} finally {
			close(pstmt);
			close(rset);
		}
		return mList;
	}

	
	
	
	
	public List<Member> selectAddress(Connection conn, String addr) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> mList = new ArrayList<Member>();
		String query = prop.getProperty("selectAddress");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, addr);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				char gender = rset.getString("GENDER").charAt(0);
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				int age = rset.getInt("AGE");
				String address = rset.getString("ADDRESS");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				
				mList.add(new Member(memberId, memberPwd, memberName, gender, email, phone, address, age, enrollDate));
			}
			
		} finally {
			close(pstmt);
			close(rset);
		}
		return mList;

	
	}
	
	
	
	
	
	
	
	
	
	// 4_8. 아이디가 일치하는 회원 존재여부 확인용 DAO
	public int checkMember(Connection conn, String memberId) throws Exception {
		
		// 4_9. SQL을 DB에 전달하고 결과를 반환 받을 변수 선언
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int check = 0;
		
		// 4_10. query.properties에 SQL 구문 작성 후 얻어오기
		String query = prop.getProperty("checkMember");
		
		// 4_11. SQL구문 DB 전달 준비
		try {
			pstmt = conn.prepareStatement(query);
			
			// 4_12. 위치 홀더에 알맞은 값 대입
			pstmt.setString(1, memberId);
			
			// 4_13. SQL 실행 결과를 rset에 저장
			rset = pstmt.executeQuery();
			
			// 4_14. 조회 결과를 check에 저장
			if(rset.next()) {
				check = rset.getInt(1);
				// 얻어올 값이 있는 컬럼 순서로 지정하는 것도 가능 
			}
		} finally {
			
			// 4_15. DB 사용 자원 반환
			close(pstmt);
			close(rset);
		}
		// 4_16. 조회 결과 반환
		return check;
	}
	
	
	
	
	
	
	// 4_33. 회원 정보 수정용 DAO
	public int updateMember(Connection conn, String updateQuery, String memberId, String Input)
	throws Exception {
		
		// 4_34. SQL DB 전달 및 결과 반환 받을 변수 선언
		PreparedStatement pstmt = null;
		int result = 0;
		
		
		// 4_35. query.properties에서 SQL 구문 작성 후 얻어오기
		String query = prop.getProperty(updateQuery);
		
		
		// 4_36. SQL DB 전달 준비
		try {
			pstmt = conn.prepareStatement(query);
			
			// 4_37. 위치 홀더에 알맞은 값 대입
			pstmt.setString(1, Input);
			pstmt.setString(2, memberId);
			
			// 4_38. SQL 구문 실행 후 결과를 반환 받아 저장
			result = pstmt.executeUpdate();
			
		} finally {
			// 4_39. DB 사용 자원 반환
			close(pstmt);
		}
		
		// 4_40. 수정 결과 반환
		return result;
	}
	
	
	

}
