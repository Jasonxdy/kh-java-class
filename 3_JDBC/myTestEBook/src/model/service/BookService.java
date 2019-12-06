package model.service;

import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import model.dao.BookDAO;
import model.vo.Board;
import model.vo.Book;
import model.vo.Member;

public class BookService {

	public Member login(Member inputLogin) throws Exception {
		Connection conn = getConnection();
		BookDAO bookDAO = new BookDAO();
		
		return bookDAO.login(conn, inputLogin);
	}

	public List<Book> selectAll() throws Exception {
		Connection conn = getConnection();
		BookDAO bookDAO = new BookDAO();
		return bookDAO.selectAll(conn);
	}

	/**
	 * 책 선택 및 해당 책 정보 호출용 Service
	 * @param bookNo
	 * @return Book
	 * @throws Exception
	 */
	public Book selectBook(int bookNo) throws Exception {
		Connection conn = getConnection();
		BookDAO bookDAO = new BookDAO();
		return bookDAO.selectBook(conn, bookNo);
	}

	/**
	 * 해당 책 구매 여부 확인용 Service
	 * @param bookNo
	 * @return int
	 * @throws Exception
	 */
	public int buyState(int bookNo) throws Exception {
		Connection conn = getConnection();
		return new BookDAO().buyState(conn, bookNo);
	}

	public int insertBuy(int bookNo) throws Exception {
		Connection conn = getConnection();
		BookDAO bookDAO = new BookDAO();
		
		int result = bookDAO.insertBuy(conn, bookNo);
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		return result;
	}

	public int minusCash(int price) throws Exception {
		Connection conn = getConnection();
		BookDAO bookDAO = new BookDAO();
		
		int result = bookDAO.minusCash(conn, price);
		
		if(result >0) 	commit(conn);
		else			rollback(conn);
		
		return result;
	}

	public Member getMember(String memberId) throws Exception {
		Connection conn = getConnection();
		BookDAO bookDAO = new BookDAO();
		
		return bookDAO.getMember(conn, memberId);
	}

	public int plusCash(int input) throws Exception {
		Connection conn = getConnection();
		BookDAO bookDAO = new BookDAO();
		
		int result = bookDAO.plusCash(conn, input);
		
		if(result >0) 	commit(conn);
		else			rollback(conn);
		
		return result;
	}

	public List<Book> selectBuy() throws Exception {
		Connection conn = getConnection();
		BookDAO bookDAO = new BookDAO();
		return bookDAO.selectBuy(conn);
	}

	
	
	
	
	
	
	
	
	
	
	// 서평 메뉴용 Service
	public List<Board> selectAllBoard(int bookNo) throws Exception {
		Connection conn = getConnection();
		BookDAO bookDAO = new BookDAO();
		return bookDAO.selectAllBoard(conn, bookNo);
	}

//	public int insertBoard(Board board) {
//		Connection conn = getConnection();
//		BookDAO boardDAO = new BookDAO();
//		
//		int result = boardDAO.insertBoard(conn, board);
//		
//		if(result>0) commit(conn);
//		else 		 rollback(conn);
//		
//		return result;
//	}
//	
	
	
	
	
	
	
	
	
	
}
