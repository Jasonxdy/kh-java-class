package sjprojectTestVersion.com.kh.sjprojectTest.common;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;

public class JDBCTemplate {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Properties prop = new Properties();
				String fileName = JDBCTemplate.class.getResource("/com/kh/sjproject/sql/driver.properties").getPath();
				prop.load(new FileReader(fileName));
				
				Class.forName(prop.getProperty(""));
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
