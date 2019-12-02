package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.EMP;

public class EmpDAO {
	
	
	public ArrayList<EMP> selectAll() {
		ArrayList<EMP> empList = null; // resultSet 담을 객체
		ResultSet rset = null; // query문의 결과를 받아올 객체
		Statement stmt = null; // DB와 java를 소통하게 해주는 객체
		Connection conn = null; // DB에 java를 연결해주는 객체
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ojdbc 드라이버 로드
			conn = DriverManager
					.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","SCOTT","TIGER");
			
			stmt = conn.createStatement(); // 그냥 Statement인 경우는 createStatement 메소드 사용하여 객체 생성해야함
			String query = "SELECT * FROM EMP";
			rset = stmt.executeQuery(query);  // executeQuery(String query)로 쿼리문 날리면 결과값이 ResultSet
			
			empList = new ArrayList<EMP>(); // ArrayList 객체 생성
			
			while (rset.next()) { // ResultSet.next() -> 반환값은 boolean, cursor는 다음것으로 이동

				int empNo = rset.getInt("EMPNO");
				String eName = rset.getString("ENAME");
				String job = rset.getString("JOB");
				int mgr = rset.getInt("MGR");
				Date hireDate = rset.getDate("HIREDATE");
				int sal = rset.getInt("SAL");
				int comm = rset.getInt("COMM");
				int deptNo = rset.getInt("DEPTNO");
				
				EMP emp = new EMP(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);
				empList.add(emp);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // DB 객체 사용 후 반환처리
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return empList;
		
	}
	
	
	
	

}
