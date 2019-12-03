package common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	/* 기존 DB 처리 작업 시 //(ex 쿼리문을 날릴 시)//마다
	 * 새로운 Connection 객체를 생성하여 DB 연결을 진행함.
	 * 이렇게 하면 한 사용자가 여러 개의 커넥션을 생성하게 됨.
	 * --> 메모리 낭비 증가, 객체 생성/소멸에 따른 시간 소비!
	 * 
	 * 이를 해결하기 위해서 
	 * 프로그램 전체에서 생성할 수 있는 Connection 객체를 한개로 제한 (수강신청 대기가 있는 이유)
	 * -> 메모리 낭비 및 DB 연결 객체 수 오버를 방지
	 * --> 싱글톤(SingleTone) 패턴
	 * 
	 * 
	 * 
	 * 싱글톤 패턴이란?
	 * - 프로그램 구동 시 메모리에 객체를 단 하나만 기록되게 하는 디자인 패턴 //(design = 설계)
	 * - 한 클래스의 모든 필드, 메소드를 static으로 선언하여 static 영역 자체에
	 *   해당 클래스 객체를 만드는 형태로 작성
	 * 
	 */
	
	// 한개의 공용 Connection 객체를 저장할 참조 변수 선언
	// 단, 외부에서 직접 접근할 수 없도록 private으로 선언
	private static Connection conn = null;
	
	// -- 해당 객체에서만 사용하지만 싱글톤이어야 하므로 static
	
	// DB 연결을 위한 Connection 객체 요청 메소드 작성
	public static Connection getConnection() {
		
		// 프로그램 실행 후 한번도 getConnection() 메소드가 호출되지 않았을 때
		// --> Connection 객체 생성
		if(conn == null) {
			/* 이전 프로젝트에서 Connection 생성 과정
			 * - JDBC 드라이버 로드
			 * - DB 연결을 위한 정보 (url, id, pwd)
			 * 이러한 내용들을 직접 작성함. (정적 코딩 : 직접 작성해서 일일히 입력)
			 * -> 추후 DB 정보 변경되는 경우 코드 자체를 수정해서 다시 컴파일, 배포해야 함 (유지보수에 불편...)
			 * 
			 * 이를 해결하기 위해 Properties 파일을 사용..!
			 * 프로그램 실행 시 동적으로 Properties 파일에서 DB 연결 정보를 읽어오도록 코딩. (동적 코딩)
			 * 
			 * -> driver.properties 파일 작성 (프로젝트 클릭 -> new -> file -> 이름 입력)
			 */
			
			// 외부에서 DB 연결 정보를 읽어올 Properties 객체 생성
			
			try {
				Properties prop = new Properties();
				
				// driver.properties 파일에서 정보를 읽어옴
				prop.load(new FileReader("driver.properties"));
				// -> IOException 발생 가능성이 있음.
				
				// driver.properties에서 읽어들인 정보를 이용해 DB와 연결할 Connection 객체 생성
				
				// jdbc 드라이버 로드
				Class.forName(prop.getProperty("driver"));
				
				// Connection 객체 생성
				conn = DriverManager.getConnection(prop.getProperty("url"),
						prop.getProperty("user"), prop.getProperty("password"));
				
				// Auto Commit 비활성화 --하는 이유 : 동적 코딩으로 변경되었기때문에 DB 버전등의 차이로 인해 AutoCommit될 가능성이 있음.. 그래서 꺼둠
				conn.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	// DB 연결 관련 자원 반환 메소드 close() 작성
	public static void close(Statement stmt) {
		// PreparedStatement는 Statement의 자식클래스 -> 상속관계 -> 다형성 적용 -> 매개변수로 부모 타입 사용 가능
		
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void close(ResultSet rset) {
		// PreparedStatement는 Statement의 자식클래스 -> 상속관계 -> 다형성 적용 -> 매개변수로 부모 타입 사용 가능
		
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void close(Connection conn) {
		// PreparedStatement는 Statement의 자식클래스 -> 상속관계 -> 다형성 적용 -> 매개변수로 부모 타입 사용 가능
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	// 처리 결과에 따른 트랜잭션 처리도 공통적인 업무임.
	// --> static으로 선언하여 코드 길이 감소, 재사용성 증가 도모
	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// rollback도 만듬
	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
