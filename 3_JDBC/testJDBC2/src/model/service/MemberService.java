package model.service;

// static import 작성
import static common.JDBCTemplate.*;

import java.sql.Connection;

import model.dao.MemberDAO;
import model.vo.Member;


public class MemberService {
	
	/*
	 * Service 클래스는 데이터에 대한 비즈니스 로직 (가공) 수행 및 
	 * DB와의 연결 정보를 가지고 있는 Connection 객체를 생성하고, 
	 * 여러 DAO를 호출하여 하나의 요청에 대한 여러번의 DB 접근/갱신을 진행하며 
	 * 그 것을 하나의 트랜잭션으로 묶어 처리하는 클래스. 
	 */
	
	/*
	 * Service 클래스에서 메소드 작성하는 방법
	 * 1) Controller에서 데이터를 전달 받음 (by 매개변수)
	 * 2) Connection 객체 생성
	 * 3) DAO 객체 생성
	 * 4) Connection 객체와 Controller에서 전달받은 데이터를 알맞은 DAO 메소드로 전달 (=호출)
	 * 5) DAO 수행 결과를 가지고 필요 시 데이터 가공 및 트랜잭션 처리 진행
	 */
	
	// 1_6. 새로운 회원 정보 추가용 Service
	// 매개변수로 Controller로부터 데이터를 전달 받음
	
	public int insertMember(Member member) throws Exception {
		
		// 1_7. DB 연결을 위한 Connection 객체 생성 구문의 중복되는 코드를 JDBCTemplate에 작성
		
		
		// 1_8. Connection 객체 생성 (얻어오기)
		// Connection conn = JDBCTemplate().getConnection();
		
		// * static import 사용 (위에 import란 볼 것)
		// -> static 필드 또는 메소드를 한 클래스에서 지속적으로 호출해야되는 경우,
		// 	    해당 static 호출에 필요한 클래스 명을 생략할 수 있게 해주는 기능.
		Connection conn = getConnection();
		
		
		// 1_9. DBMS에 접속하여 데이터를 전송하고 결과값을 전달 받을 MemberDAO 객체 생성 및 클래스 작성.
		MemberDAO memberDAO = new MemberDAO();
		
		
		// 1_13. 새로운 회원 정보 삽입을 위한 MemberDAO.insertMember(conn, member) 메소드 작성
		
		
		// 1_23. 매개 변수로 connection 객체와 Controller에서 전달받은 값을  DAO로 전달하고 
		// 		  회원 정보 삽입 결과를 반환받아 저장
		int result = memberDAO.insertMember(conn, member);
		
		// 1_24. DB 처리 결과에 따라 트랜잭션 처리
		
		if (result > 0) { // 삽입 성공 시
			commit(conn);
		} else { // 삽입 실패 시
			rollback(conn);
		}
		
		
		// 1_25. DB 처리 결과를 Controller로 반환
		return result;
	}
	
	
	
	

}
