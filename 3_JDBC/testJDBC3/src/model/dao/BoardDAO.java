package model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import controller.BoardController;
import model.vo.Board;
import model.vo.Member;

/**
 * 게시판 프로그램 DAO
 * @author Jsn
 */
public class BoardDAO {
	
	private Properties prop = null;
	
	/**
	 * BoardDAO 객체 생성 시 query.properties 파일을 동적으로 읽어옴
	 * @throws Exception
	 */
	public BoardDAO() throws Exception {
		prop = new Properties();
		prop.load(new FileReader("query.properties"));
	}
	
	
	/**
	 * 로그인용 DAO
	 * @param conn : Connection
	 * @param inputLogin : Member
	 * @return member : Member
	 * @throws Exception
	 */
	public Member login(Connection conn, Member inputLogin) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String query = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, inputLogin.getMemberId());
			pstmt.setString(2, inputLogin.getMemberPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String memberId = rset.getString("MEMBER_ID");
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				char gender = rset.getString("MEMBER_PWD").charAt(0);
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				String address = rset.getString("ADDRESS");
				int age = rset.getInt("AGE");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				
				member = new Member(memberId, memberPwd, memberName, gender, email, phone, address, age, enrollDate);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}
	
	public List<Board> selectAll(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		List<Board> bList = null;
		
		String query = prop.getProperty("selectAll");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			bList = new ArrayList<Board>();
			Board board = null;
			
			while(rset.next()) {
				int bNo = rset.getInt("BNO");
				String title = rset.getString("TITLE");
//				String content = rset.getString("CONTENT");
				Date createDate = rset.getDate("CREATE_DATE");
				String writer = rset.getString("WRITER");
				
				board = new Board(bNo, title, createDate, writer);
				bList.add(board);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		return bList;
	}


	/**
	 * @param conn : Connection
	 * @param select : int
	 * @return board : Board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int select) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		String query = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, select);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				int bNo = rset.getInt("BNO");
				String title = rset.getString("TITLE");
				String content = rset.getString("CONTENT");
				Date createDate = rset.getDate("CREATE_DATE");
				String writer = rset.getString("WRITER");
				
				board = new Board(bNo, title, content, createDate, writer);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return board;
	}


	public int insertBoard(Connection conn, Board board) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, BoardController.LoginMember.getMemberId());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public Board updateBoard(Connection conn, int bNo) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		String query = prop.getProperty("updateBoard");
		
		try {
		} finally {
			// TODO: handle finally clause
		}
		
		return null;
	}


	public int updateTitle(Connection conn, int bNo, String title) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateTitle");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setInt(2, bNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int updateContent(Connection conn, int bNo, String content) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateContent");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, bNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public String selectWriter(Connection conn, int bNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String writer= null;
		
		String query = prop.getProperty("selectWriter");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				writer = rset.getString("WRITER");
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return writer;
	}


	/**
	 * 선생님 풀이용 DAO
	 * @param conn : Connection
	 * @param bNo : int
	 * @param updateString : String
	 * @param query : String
	 */
	public int updateBoard(Connection conn, int bNo, String updateString, String query) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		
		try {
			pstmt = conn.prepareStatement(prop.getProperty(query));
			pstmt.setString(1, updateString);
			pstmt.setInt(2, bNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
		
	}
	
	
	
}
