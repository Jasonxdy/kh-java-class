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
			
			stmt = conn.createStatement();
			String query = "SELECT * FROM EMP";
			rset = stmt.executeQuery(query);
			
			empList = new ArrayList<EMP>();
			EMP emp = null;
			
			while (rset.next()) {
				int empNo = rset.getInt("EMPNO");
				String eName = rset.getString("ENAME");
				String job = rset.getString("JOB");
				int mgr = rset.getInt("MGR");
				Date hireDate = rset.getDate("HIREDATE");
				int sal = rset.getInt("SAL");
				int comm = rset.getInt("COMM");
				int deptNo = rset.getInt("DEPTNO");
				
				emp = new EMP(empNo, eName, job, mgr, hireDate, sal, comm, deptNo);
				
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
		
	}
	
	
	
	

}
