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
import com.kh.sjproject.board.model.vo.Reply;
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

	
	
	
	
	
	
	
	/**
	 * 게시글 상세 조회용 DAO
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int boardNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		
		String query = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				board = new Board(boardNo, rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getInt("BOARD_COUNT"),
						rset.getDate("BOARD_MODIFY_DT"),
						rset.getString("MEMBER_ID"),
						rset.getString("CATEGORY_NM"));
			}
			
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		return board;
	}

	
	
	
	
	/**
	 * 게시글 이미지 파일 조회용 DAO
	 * @param conn
	 * @param boardNo
	 * @return files
	 */
	public List<Attachment> selectFiles(Connection conn, int boardNo) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> files = null;
		
		String query = prop.getProperty("selectFiles");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			files = new ArrayList<Attachment>();
			Attachment file = null;
			
			while(rset.next()) {
				
				file = new Attachment(rset.getInt(1),
									rset.getInt(2),
									rset.getString(3), 
									rset.getString(4), 
									rset.getString(5), 
									rset.getDate(6), 
									rset.getInt(7), 
									rset.getInt(8));
				
				
				files.add(file);
				
			}
					
		} finally {
			close(rset);
			close(pstmt);
		}
		return files;
	}
	
	
	
	
	
	
	/** 파일 다운로드용 Dao
	 * @param conn
	 * @param fNo
	 * @return file
	 * @throws Exception
	 */
	public Attachment selectFile(Connection conn, int fNo) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment file = null;
		String query = prop.getProperty("selectFile");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, fNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				file = new Attachment(rset.getString("FILE_ORIGIN_NAME"), 
									  rset.getString("FILE_CHANGE_NAME"), 
									  rset.getString("FILE_PATH"));
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return file;
	}

	
	
	
	
	
	/**
	 * 댓글 등록용 DAO
	 * @param conn
	 * @param reply
	 * @param replyWriter
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Connection conn, Reply reply, int replyWriter) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getBoardId());
			pstmt.setInt(3, replyWriter);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	
	/**
	 * 댓글 조회용 DAO
	 * @param conn
	 * @param boardId
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(Connection conn, int boardId) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reply> rList = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardId);
			
			rset = pstmt.executeQuery();
			
			rList = new ArrayList<Reply>();
			Reply reply = null;
			
			while (rset.next()) {
				
				reply = new Reply(rset.getInt("REPLY_NO"),
						rset.getString("REPLY_CONTENT"),
						rset.getInt("BOARD_ID"),
						rset.getString("MEMBER_ID"),
						rset.getTimestamp("REPLY_MODIFY_DT"));
				
				rList.add(reply);
			}
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}
	
	
	
	

}
