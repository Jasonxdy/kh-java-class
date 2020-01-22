package com.kh.sjproject.board.model.service;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.sjproject.board.model.dao.BoardDAO;
import com.kh.sjproject.board.model.vo.Attachment;
import com.kh.sjproject.board.model.vo.Board;

import static com.kh.sjproject.common.JDBCTemplate.*;

public class BoardService {

	/**
	 * 전체 게시글 수 조회 Service
	 * 
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount() throws Exception {

		Connection conn = getConnection();

		int listCount = new BoardDAO().getListCount(conn);

		close(conn);
		return listCount;
	}

	/**
	 * 게시판 목록 조회용 Service
	 * 
	 * @param currentPage
	 * @param limit
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectList(int currentPage, int limit) throws Exception {

		Connection conn = getConnection();

		List<Board> bList = new BoardDAO().selectList(conn, currentPage, limit);

		close(conn);
		return bList;
	}

	/**
	 * 게시글 등록용 Service 
	 * @param board
	 * @param boardWriter
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Board board, int boardWriter, ArrayList<Attachment> fList) throws Exception {
		Connection conn = getConnection();
		BoardDAO boardDAO = new BoardDAO();
		
		int result = 0;
		
		// 1) 등록될 게시글의 번호를 얻어옴
		//		-> SEQ_BNO.NEXTVAL 값을 얻어와라! (CURRVAL NO)
		int boardNo = boardDAO.selectNextNo(conn);
		
		System.out.println("boardNo : " + boardNo);
		
		if(boardNo > 0) { // 게시글 번호를 얻어 온 경우
			
			// 2) 게시글(Board)를 DB에 먼저 삽입
			board.setBoardNo(boardNo);
			result = boardDAO.insertBoard(conn, board, boardWriter);
			
			if(result > 0) { // 게시글 삽입 성공시 
				result = 0; // 트랜잭션 처리를 위해 재활용
				
				// 3) fList의 데이터를 하나씩 DB에 삽입
				for (Attachment file : fList) {
					// 현재 게시글 번호 추가
					file.setBoardId(boardNo);
					
					result = boardDAO.insertAttachment(conn, file);
					
					// 삽입 실패 시
					if(result == 0) break; // 하나라도 실패 시 무조건 break해서 아무 작업도 안함
				}
				
				
				
				
			}
			
			// 4) 트랜잭션 처리
			if(result>0)	commit(conn);
			else {
				
				// 5) DB 삽입 실패 시 서버에 저장된 파일을 삭제
				// 	-> MultipartRequest 객체 생성 시 서버에 이미 저장되므로..
				
				for(Attachment file : fList) {
					String path = file.getFilePath();
					String saveFile = file.getFileChangeName();
					
					// java.io에 있는 파일임
					File failedFile = new File(path + saveFile);
					// -> 매개변수로 지정된 경로의 파일을 취급할 수 있음.
					System.out.println("path : " + path);
					System.out.println("saveFile : " + saveFile);
					failedFile.delete();
				}
				
				rollback(conn);
			}
		}

	return result;
}

	
	
	
	
	
	
	/**
	 * 썸네일 이미지 목록 조회 Service
	 * @param currentPage
	 * @param limit
	 * @return fList
	 * @throws Exception
	 */
	public List<Attachment> selectFileList(int currentPage, int limit) throws Exception {
		Connection conn = getConnection();
		ArrayList<Attachment> fList = new BoardDAO().selectFileList(conn, currentPage, limit);
		
		close(conn);
		return fList;
	}

}
