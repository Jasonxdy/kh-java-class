package model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import static common.JDBCTemplate.*;

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
		} finally {
			// 1_21. SQL 수행에 사용된 자원 반환
			// -> JDBCTemplate에서 자원 반환 메소드 작성
			
			// JDBCTemplate static import 해준 후
			close(pstmt);
		}
		
		// 1_22. insert 결과 반환
		return result;
	}

}
