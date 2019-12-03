package model.dao;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import static common.JDBCTemplate.*;
import model.vo.Member;

public class MemberDAO {
	
	private Properties prop = null;
	
	public MemberDAO() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream("query.properties"));
	}
	
	public int insertMember(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
//		Properties prop = new Properties();
//		prop.load(new FileInputStream("query.properties"));
		int result = 0;
		String query = prop.getProperty("insertMember");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender() + "");
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getAddress());
			pstmt.setInt(8, member.getAge());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
