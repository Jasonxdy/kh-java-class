package model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import model.dao.BoardDAO;
import model.vo.Board;
import model.vo.Member;

/**
 * 게시판 프로그램  Service
 * @author Jsn
 */
public class BoardService {
	

	/** 로그인용 Service
	 * @param inputLogin : Member
	 * @return member : Member
	 * @throws Exception
	 */
	public Member login(Member inputLogin) throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		
		return boardDAO.login(conn, inputLogin);
	}
	
	/**
	 * 게시글 목록 조회용 Service
	 * @return bList : Board List
	 * @throws Exception
	 */
	public List<Board> selectAll() throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		return boardDAO.selectAll(conn);
	}

	public Board selectBoard(int select) throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		return boardDAO.selectBoard(conn, select);
	}

	public int insertBoard(Board board) throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		
		int result = boardDAO.insertBoard(conn, board);
		
		if(result>0) commit(conn);
		else 		 rollback(conn);
		
		return result;
	}

	public Board updateBoard(int bNo) throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		return boardDAO.updateBoard(conn, bNo);
	}

	public int updateTitle(int bNo, String title) throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.updateTitle(conn, bNo, title);
		if(result >0) commit(conn);
		else		  rollback(conn);
		return result;
	}

	public int updateContent(int bNo, String content) throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.updateContent(conn, bNo, content);
		if(result >0) commit(conn);
		else		  rollback(conn);
		return result;
	}

	public String selectWriter(int bNo) throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		return boardDAO.selectWriter(conn, bNo);
	}

	public int updateBoard(int bNo, String updateString, String updateQuery) throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.updateBoard(conn, bNo, updateString, "update" + updateQuery);
		if(result >0) commit(conn);
		else		  rollback(conn);
		return result;
	}
	

}
