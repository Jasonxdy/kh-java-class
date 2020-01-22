package com.kh.sjproject.board.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.sjproject.board.model.vo.Attachment;
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

	
	
	
	
	
	/**
	 * 다음 게시글 번호 반환용 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception{
		
		Statement stmt = null;
		ResultSet rset = null;
		int boardNo = 0;
		
		String query = prop.getProperty("selectNextNo");
		// 일단 NEXTVAL값 가져옴
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				boardNo = rset.getInt(1); 
			}
			
		} finally {
			
			close(rset);
			close(stmt);
		}
		
		return boardNo;
	}
	
	
	
	
	

	/**
	 * 게시글 등록용 DAO
	 * @param conn
	 * @param board
	 * @param boardWriter
	 * @return result : int
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, Board board, int boardWriter) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, boardWriter);
			pstmt.setString(5, board.getBoardCategory());
			pstmt.setInt(6, 1); // 현재 게시판 번호 작성

			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	
	/**
	 * 게시글 파일(이미지) 정보 등록용 DAO
	 * @param conn
	 * @param file
	 * @return result : int
	 * @throws Exception
	 */
	public int insertAttachment(Connection conn, Attachment file) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, file.getBoardId());
			pstmt.setString(2, file.getFileOriginName());
			pstmt.setString(3, file.getFileChangeName());
			pstmt.setString(4, file.getFilePath());
			pstmt.setInt(5, file.getFileLevel());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	/** 썸네일 이미지 목록 조회용 DAO
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return fList : ArrayList<Attachment>
	 * @throws Exception
	 */
	public ArrayList<Attachment> selectFileList(Connection conn, int currentPage, int limit) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> fList = null;
		
		String query = prop.getProperty("selectFileList");

		
		try {
			// 쿼리문 실행 시 between 조건에 사용될 값
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, 1);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<Attachment>();
			Attachment file = null;
			
			while(rset.next()) {
				
				file = new Attachment();
				file.setFileNo(rset.getInt("FILE_NO"));
				file.setBoardId(rset.getInt("BOARD_ID"));
				file.setFileChangeName(rset.getString("FILE_CHANGE_NAME"));
				
				fList.add(file);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return fList;
	}
	
	
	

}
