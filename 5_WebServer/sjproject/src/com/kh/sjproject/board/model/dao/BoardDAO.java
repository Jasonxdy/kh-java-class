package com.kh.sjproject.board.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.sjproject.board.model.vo.Board;
import com.kh.sjproject.notice.model.dao.NoticeDAO;

import sun.font.CreatedFontTracker;

import static com.kh.sjproject.common.JDBCTemplate.*;


public class BoardDAO {

	private Properties prop = null;
	
	public BoardDAO() throws Exception {

		String fileName = NoticeDAO.class.getResource("/com/kh/sjproject/sql/board/board-query.properties").getPath();

		prop = new Properties();

		prop.load(new FileReader(fileName));

	}
	
	/**
	 * 전체 게시글 수 조회용 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception{
		
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("getListCount");
		int listCount = 0;
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	/**
	 * 게시판 목록 조회용 DAO
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectList(Connection conn, int currentPage, int limit) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> bList = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			// 쿼리문 실행 시 between 조건에 사용될 값
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, 1);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			Board board = null;
			
			while(rset.next()) {
				board = new Board(rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getInt("BOARD_COUNT"),
						rset.getDate("BOARD_MODIFY_DT"),
						rset.getString("MEMBER_ID"),
						rset.getString("CATEGORY_NM"));
				
				bList.add(board);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return bList;
	}
	
	
	

}
