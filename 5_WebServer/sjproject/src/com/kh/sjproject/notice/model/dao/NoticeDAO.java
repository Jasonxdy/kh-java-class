package com.kh.sjproject.notice.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.kh.sjproject.common.JDBCTemplate.*;

import com.kh.sjproject.notice.model.vo.Notice;

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

	/**
	 * 공지사항 상세 조회용 DAO
	 * @param conn : Connection
	 * @param no : int
	 * @return notice
	 * @throws Exception
	 */
	public Notice selectNotice(Connection conn, int no) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		
		String query = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice (no,
						rset.getString("NOTICE_TITLE"),
						rset.getString("NOTICE_CONTENT"),
						rset.getString("NOTICE_WRITER"),
						rset.getInt("NOTICE_COUNT"),
						rset.getDate("NOTICE_MODIFY_DT")
						);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}

	public int increaseCount(Connection conn, int no) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	// 내꺼1
//	/**
//	 * 공지사항 등록용 DAO
//	 * @param conn : Connection
//	 * @param notice : Notice
//	 * @return result : int
//	 */
//	public int insertNotice(Connection conn, Notice notice) throws Exception {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		
//		String query = prop.getProperty("insertNotice");
//		
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, notice.getNoticeTitle());
//			pstmt.setString(2, notice.getNoticeContent());
//			pstmt.setString(3, notice.getNoticeWriter());
//			
//			result = pstmt.executeUpdate();
//			
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}

	// 내꺼2
//	/**
//	 * 공지사항 번호 조회용 DAO
//	 * @param conn : Connection
//	 * @return noticeNo : int
//	 */
//	public int getNoticeNo(Connection conn) throws Exception {
//		Statement stmt = null;
//		ResultSet rset = null;
//		int noticeNo = 0;
//		String query = prop.getProperty("getNoticeNo");
//		
//		try {
//			stmt = conn.createStatement();
//			rset = stmt.executeQuery(query);
//			
//			if(rset.next()) {
//				noticeNo = rset.getInt(1);
//			}
//			
//		} finally {
//			close(rset);
//			close(stmt);
//		}
//		
//		return noticeNo;
//	}

	/**
	 * 공지사항 글번호 생성용 dao
	 * @param conn
	 * @return no
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		int no = 0;
		
		String query = prop.getProperty("selectNextNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				no = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		return no;
	}

	/**
	 * 공지사항 등록용 Dao
	 * @param conn
	 * @param notice
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice(Connection conn, Notice notice, int no) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, notice.getNoticeTitle());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setString(4, notice.getNoticeWriter());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	/** 공지사항 수정용 DAO
	 * @param conn : Connection 
	 * @param notice : Notice
	 * @return result : int
	 * @throws Exception
	 */
	public int updateNotice(Connection conn, Notice notice) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	/**
	 * 공지사항 삭제용 DAO 
	 * @param conn
	 * @param no
	 * @return
	 * @throws Exception
	 */
	public int deleteNotice(Connection conn, int no) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	
	
	
	/**
	 * 공지사항 검색용 DAO
	 * @param conn
	 * @param condition
	 * @return list
	 * @throws Exception
	 */
	public List<Notice> searchNotice(Connection conn, String condition) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		List<Notice> list = null;
		
		String query1 = prop.getProperty("searchNotice1");
		String query2 = prop.getProperty("searchNotice2");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query1+condition+query2);
			
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
