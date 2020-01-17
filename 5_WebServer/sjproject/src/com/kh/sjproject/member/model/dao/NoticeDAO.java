package com.kh.sjproject.member.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.kh.sjproject.common.JDBCTemplate.*;

import com.kh.sjproject.member.model.vo.Notice;

public class NoticeDAO {

	private Properties prop = null;

	public NoticeDAO() throws Exception {

		String fileName = NoticeDAO.class.getResource("/com/kh/sjproject/sql/notice/notice-query.properties").getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));

	}

	/**
	 * 공지사항 목록 조회용 DAO
	 * @param conn : Connection
	 * @return list : List<Notice>
	 * @throws Exception
	 */
	public List<Notice> selectList(Connection conn) throws Exception {

		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectList");
		
		List<Notice> list = null;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Notice>();
			
			Notice notice = null;
			
			while(rset.next()) {
				
				notice = new Notice(rset.getInt("NOTICE_NO"),
						rset.getString("NOTICE_TITLE"),
						rset.getString("NOTICE_WRITER"),
						rset.getInt("NOTICE_COUNT"),
						rset.getDate("NOTICE_MODIFY_DT"));
				
				list.add(notice);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

}
