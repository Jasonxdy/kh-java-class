package com.kh.sjproject.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		if(conn == null) {
			
			try {
				Properties prop = new Properties();
				
				String fileName
					= JDBCTemplate.class.getResource("/com/kh/sjproject/sql/driver.properties").getPath();
				// JDBCTemplate.class 파일에서 하위에 있는 /com~~.properties를 찾아서 걔의 경로를 찾아라
				prop.load(new FileReader(fileName));
				
				// ojdbc6 라이브러리를 WebContent/WEB-INF/lib 폴더에 추가
				

				Class.forName(prop.getProperty("driver"));
				
				conn = DriverManager.getConnection(prop.getProperty("url"),
						prop.getProperty("user"), prop.getProperty("password"));
				
				conn.setAutoCommit(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void close(Statement stmt) {
		
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void close(ResultSet rset) {
		
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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
