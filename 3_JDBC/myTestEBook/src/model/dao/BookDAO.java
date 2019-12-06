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

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import controller.BookController;
import model.vo.Board;
import model.vo.Book;
import model.vo.Member;

public class BookDAO {
	
	private Properties prop = null;
	
	/**
	 * BoardDAO 객체 생성 시 query.properties 파일을 동적으로 읽어옴
	 * @throws Exception
	 */
	public BookDAO() throws Exception {
		prop = new Properties();
		prop.load(new FileReader("query.properties"));
	}
	
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
				int cash = rset.getInt("CASH");
				
				member = new Member(memberId, memberPwd, memberName, gender, email, phone, address, age, enrollDate, cash);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	/**
	 * 전체 책 목록 조회용 DAO
	 * @return bLlist : List
	 */
	public List<Book> selectAll(Connection conn) throws Exception {
		
		Statement stmt = null;
		ResultSet rset = null;
		List<Book> bList = null;
		
		String query = prop.getProperty("selectAll");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			bList = new ArrayList<Book>();
			Book book = null;
			
			while(rset.next()) {
				int bookNo = rset.getInt("BOOK_NO");
				String title = rset.getString("TITLE");
				String author = rset.getString("AUTHOR");
				int price = rset.getInt("PRICE");
				
				book = new Book(bookNo, title, author, price);
				bList.add(book);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		return bList;
	}

	
	
	
	
	public Book selectBook(Connection conn, int bookNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Book book = null;
		
		String query = prop.getProperty("selectBook");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String title = rset.getString("TITLE");
				String author = rset.getString("AUTHOR");
				int price = rset.getInt("PRICE");
				String summary = rset.getString("SUMMARY");
				
				book = new Book(bookNo, title, author, summary, price);
			}
		} finally {
			close(rset);
			close(pstmt);
			
		}
		return book;
	}
	
	
	
	
	
	public int buyState(Connection conn, int bookNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("buyState");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, BookController.LoginMember.getMemberId());
			pstmt.setInt(2, bookNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	
	
	
	
	
	
	
	public int insertBuy(Connection conn, int bookNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBuy");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, BookController.LoginMember.getMemberId());
			pstmt.setInt(2, bookNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	public int minusCash(Connection conn, int price) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("minusCash");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, price);
			pstmt.setString(2, BookController.LoginMember.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	public Member getMember(Connection conn, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String query = prop.getProperty("getMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String memberPwd = rset.getString("MEMBER_PWD");
				String memberName = rset.getString("MEMBER_NAME");
				char gender = rset.getString("MEMBER_PWD").charAt(0);
				String email = rset.getString("EMAIL");
				String phone = rset.getString("PHONE");
				String address = rset.getString("ADDRESS");
				int age = rset.getInt("AGE");
				Date enrollDate = rset.getDate("ENROLL_DATE");
				int cash = rset.getInt("CASH");
				
				member = new Member(memberId, memberPwd, memberName, gender, email, phone, address, age, enrollDate, cash);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;	
	}

	public int plusCash(Connection conn, int input) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("plusCash");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, input);
			pstmt.setString(2, BookController.LoginMember.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	public List<Book> selectBuy(Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Book> bList = new ArrayList<Book>();
		Book book = null;
		
		String query = prop.getProperty("selectBuy");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, BookController.LoginMember.getMemberId());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				int bookNo = rset.getInt("BOOK_NO");
				String title = rset.getString("TITLE");
				String author = rset.getString("AUTHOR");
				String content = rset.getString("CONTENT");
				int price = rset.getInt("PRICE");
				
				book = new Book(bookNo, title, author, price, content);
				bList.add(book);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		return bList;
	}

	
	
	
	
	
	
	
	// 서평 메뉴용 
	
	
	public List<Board> selectAllBoard(Connection conn, int bookNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> bList = null;
		
		String query = prop.getProperty("selectAllBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookNo);
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			Board board = null;
			
			while(rset.next()) {
				int bNo = rset.getInt("BNO");
				String title = rset.getString("TITLE");
				Date createDate = rset.getDate("CREATE_DATE");
				String writer = rset.getString("WRITER");
				
				board = new Board(bNo, title, createDate, writer, bookNo);
				bList.add(board);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return bList;
	}

	public int insertBoard(Connection conn, Board board) throws Exception{
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, BookController.LoginMember.getMemberId());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	
}
