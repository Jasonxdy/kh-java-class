package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.EMP;

public class EmpDAO {
	
	// 1_3. 사원 정보 전체 조회
	public ArrayList<EMP> selectAll(){
		
		// JDBC 객체 선언(java.sql)
		Connection conn = null;
		// - DB의 연결 정보를 담은 객체
		// - JDBC드라이버 DB 사이를 연결해주는 일종의 통로
		// - 직접 객체 생성 불가 
		//	  -> DriverManager.getConnection() 이용하여 생성
		
		Statement stmt = null;
		// - Connection 객체를 이용해서 DB에 SQL문을 전달하고
		//   전달한 SQL문의 실행 결과를 반환 받는 역할을 하는 객체
		// - Connection.createStatement() 이용해서 객체 생성
		
		ResultSet rset = null;
		// - SELECT문을 사용한 SQL 성공 시 반환되는 값을 저장할 객체
		// - SELECT의 결과로 생성된 테이블을 담고 있으며
		//   커서(CURSOR)라는 테이블의 한 행씩 접근하는 DB객체를 이용하여
		//   특정 행에 대한 참조를 조작함.
		
		ArrayList<EMP> empList = null;
		// DB에서 조회한 결과를 저장할 ArrayList
		
		
		// 1_4. 해당 DB에 대한 라이브러리(JDBC 드라이버) 등록 작업
		// -> JDBC 드라이버의 클래스를 메모리에 로드
		// Class.forName("클래스명") 를 이용하여 메모리에 로드
		// --> ClassNotFoundException 발생할 가능성이 있음
		//     --> 예외처리 필요(try - catch)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 1_5. DBMS 연결 작업
			/*
			 * 연결 정보를 담을 Connection
			 * 연결 처리를 위한 DriverManager
			 * 
			 * - jdbc:oracle:thin -> JDBC드라이버 thin 타입 의미
			 * - @127.0.0.1  
			 * 		-> 접속하려는 오라클이 설치된 컴퓨터 ip 입력
			 * 		   127.0.0.1 은 자신의 컴퓨터 ip를 의미
			 * 			(@localhost 로 대체 가능)
			 * - 1521 : 오라클 Listener 포트 번호
			 * - xe   : 접속할 오라클 DB명 (Express -> xe)
			 * - SCOTT : DB 접속 계정
			 * - TIGER : 접속 비밀번호 
			 * */
			
			conn = DriverManager
					.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "SCOTT", "TIGER");                 
			
			// 1_6. DB 접속 성공 확인
			//      접속 성공 시 conn을 출력하면 DB 정보가 출력됨.
			//      실패 시 null, SQLException 발생 가능성이 있음.
			//System.out.println(conn);
			
			// 1_8. DB에 SQL문을 전달하고, 실행시킨 후
			//      그 결과를 반환 받아올 Statement 객체 생성
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM EMP" ;
			
			// 1_9. SQL문을 Statement 객체를 이용하여 
			//       DB에 전달 및 실행 시킨 후
			//       반환된 결과를 ResultSet rset에 저장
			rset = stmt.executeQuery(query);
			
			// executeQuery() 
			//  -> DB로 SELECT문을 전달하여 실행하고, 결과를 반환 받음
			// executeUpdate()
			//  -> DML(INSERT, UPDATE, DELETE) 전달 및 실행 후 결과 반환 받음
			
			
			// 1_10. rset에 있는 결과를 ArrayList에 담아주기
			
			empList = new ArrayList<EMP>(); // 결과를 저장할 ArrayList 생성
			EMP emp = null; // 조회 결과의 한 행(row) 값을 저장할 임시 변수 선언
			
			while(rset.next()) {
				// ResultSet.next() 
				// 반환 받은 조회 결과(테이블)에
				// 커서(CURSOR)를 이용하여 한 행씩 접근
				// 이 때, 행이 존재하면 true, 없으면 false 반환
				
				// get[Type]("컬럼명") : 해당 컬럼의 값을 가져옴.
				// [Type]은 가져올 값의 자료형
				
				int empNo = rset.getInt("EMPNO");
				String eName = rset.getString("ENAME");
				String job = rset.getString("JOB");
				int mgr = rset.getInt("MGR");
				Date hireDate = rset.getDate("HIREDATE");
				int sal = rset.getInt("SAL");
				int comm = rset.getInt("COMM");
				int deptNo = rset.getInt("DEPTNO");
				
				// 조회 결과를 매개변수로 하여 EMP 객체 생성
				emp = new EMP(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);
				
				// 생성된 EMP 객체를 empList에 추가
				empList.add(emp);
			}
			
			//System.out.println(empList);
			
			// 조회 결과를 모두 empList에 저장했다면
			// DB 연결에 사용되었던 모든 객체 반환
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 1_11. DB 연결 끊기
			// DB 연결에 사용된 객체는 사용 후 반드시 반환할 것
			try {
				// 반환 순서 : 마지막에 생성된 DB 관련 객체부터 반환
				rset.close();
				stmt.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 1_12. 조회된 사원 정보가 담긴 empList를 반환
		return empList;
	}
	
	// 2_6. 매개변수로 전달된 사번으로 직원 정보 조회
	public EMP selectEmp(int empNo) {
		
		// 2_7. JDBC 관련 객체 선언
		
		Connection conn = null; // DB 연결 정보 (일종의 연결 통로)
		//Statement stmt = null; // SQL문 DB 전달 및 결과 반환
		ResultSet rset = null; // DB 조회 결과 저장

		// 2_19.  PreparedStatement 선언
		PreparedStatement pstmt = null;
		/* PreparedStatement
		 * - Statement와 같은 SQL문을 전달하고 실행하여
		 *   결과를 반환 받는 객체
		 * 
		 * - 차이점은 실행 시간동안 인수값을 위한 공간을 확보할 수 있다.
		 * 
		 * - SQL문에서 인수가 필요한 각 부분에
		 *   위치 홀더(?)를 작성하여 SQL문을 정의할 수 있음.
		 *   
		 * -  ? (위치홀더) : SQL 문장에 나타나는 토큰
		 * 				SQL 구문이 실행되기 전에만 실제 값으로 대체하면됨.
		 * */
		
		EMP emp = null; // 조회 결과 임시 저장 변수
		
		
		
		// 2_8. JDBC 드라이버 등록 작업
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager
					.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			
			//---------------------------------------------------------
			// 2_9. Statement를 이용하여 정보 조회
			// 해당 사번의 사원 정보를 조회하는 쿼리문 작성
			//String query = "SELECT * FROM EMP WHERE EMPNO = " + empNo;
			
			//stmt = conn.createStatement(); // Statement 생성
			//rset = stmt.executeQuery(query); // DB 조회 결과 저장
			//---------------------------------------------------------
			
			// 2_20. PreparedStatement 를 이용하여 조회
			String query = "SELECT * FROM EMP WHERE EMPNO = ?";
			
			pstmt = conn.prepareStatement(query);
			// query를 PreparedStatement 객체 위에 올려두고 
			// 홀더 (?)를 채울 준비를 함
			
			// 홀더에 값 세팅 방법
			// pstmt.set[Type](?의 순번, 적용할 값);
			pstmt.setInt(1, empNo);
			// SQL문의 1번 홀더 자리에 empNo 값을 대입
			
			// SQL문 완성 후 DB에서 실행 후 결과를 반환 받음
			rset = pstmt.executeQuery();
			
			
			// 2_10. 조회된 결과를 EMP 객체에 저장
			if(rset.next()) {
				// 조회 결과가 한 행 이므로 if문을 이용하여 next()를 1회만 진행
				
				// empNo 매개변수를 재활용 
				String eName = rset.getString("ENAME");
				String job = rset.getString("JOB");
				int mgr = rset.getInt("MGR");
				Date hireDate = rset.getDate("HIREDATE");
				int sal = rset.getInt("SAL");
				int comm = rset.getInt("COMM");
				int deptNo = rset.getInt("DEPTNO");
				
				emp = new EMP(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			// 2_11. DB 연결 자원 반환
			try {
				rset.close();
				//stmt.close();
				pstmt.close(); // 2_21. pstmt 반환
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 2_12. 조회 정보를 저장한 emp 반환
		return emp;
	}

	
	
	
	
	
	
	public ArrayList<EMP> salRange(int[] range) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<EMP> empList = null;
		EMP emp = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			
			String query = "SELECT * FROM EMP WHERE SAL BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, range[0]); 
			pstmt.setInt(2, range[1]); 
			
			rset = pstmt.executeQuery();
			
			empList = new ArrayList<EMP>();
			
			while(rset.next()) {
				int empNo = rset.getInt("EMPNO");
				String eName = rset.getString("ENAME");
				String job = rset.getString("JOB");
				int mgr = rset.getInt("MGR");
				Date hireDate = rset.getDate("HIREDATE");
				int sal = rset.getInt("SAL");
				int comm = rset.getInt("COMM");
				int deptNo = rset.getInt("DEPTNO");
				
				emp = new EMP(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);
				
				empList.add(emp);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close(); 
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return empList;
	}
	
	
	
	
	
}






