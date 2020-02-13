package com.kh.mybatis.board.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Attachment;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.PageInfo;

import static com.kh.mybatis.common.Template.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author user1
 *
 */
public class BoardService {

	
	
	/**
	 * 전체 게시글 수 조회용 Service
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount() throws Exception {
		
		SqlSession session = getSqlSession();
		int result =  new BoardDao().getListCount(session);
		
		session.close();
		
		return result;
	}

	
	
	/**
	 * 게시글 목록 조회용 서비스
	 * @param pInf
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectList(PageInfo pInf) throws Exception {
		
		SqlSession session = getSqlSession();
		List<Board> bList = new BoardDao().selectList(session, pInf);
		
		session.close();
		return bList;
	}



	
	/**
	 * 게시글 등록용 서비스
	 * @param board
	 * @param boardWriter
	 * @param fList
	 * @return result : int
	 * @throws Exception
	 */
	public int insertBoard(Board board, int boardWriter, ArrayList<Attachment> fList) throws Exception {
		
		SqlSession session = getSqlSession();
		
		BoardDao boardDao = new BoardDao();
		
		int result = 0;
		
		
		// 1) 게시글 번호 얻어오기
		int boardNo = boardDao.selectNextNo(session);
			
		// 2) 게시글만 등록하기
		if(boardNo > 0) {
			board.setBoardContent(board.getBoardContent().replace("\r\n", "<br>"));
			board.setBoardNo(boardNo);
			board.setBoardWriter(boardWriter+""); // boardWriter는 int인데 ""추가해서 String형태로 전환
			
			result = boardDao.insertBoard(session, board);
			
			// 3) 파일 등록 -> 안함
			// 4) 트랜잭션 처리
			if(result > 0) {
				session.commit();
				result = boardNo; // 게시글 등록 성공 시 바로 작성한 글로 redirect하기 위해 result에 boardNo 대입
			}
			else {
				session.rollback();
				// 5) 등록 실패 시 서버에 저장된 파일 제거 -> 안함
			}
		}
			
		session.close();
		return result;
	}


	
	/**
	 * 게시글 상세 조회용 service
	 * @param boardNo
	 * @return Board
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo) throws Exception{
		
		SqlSession session = getSqlSession();
		BoardDao boardDao = new BoardDao();
		
		Board board = boardDao.selectBoard(session, boardNo);
		
		if(board != null) {
			int result = boardDao.increaseCount(session, boardNo);
			
			if(result>0) {
				session.commit();
				board.setBoardCount(board.getBoardCount()+1);
			} else {
				session.rollback();
			}
		}
		
		session.close();
		return board;
	}



	/**
	 * 검색된 게시글 총 개수 조회용 서비스
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public int getSearchListCount(Map<String, String> map) throws Exception {
		SqlSession session = getSqlSession();
		int searchListCount = new BoardDao().getSearchListCount(session, map); 
		session.close();
		return searchListCount;
	}



	/**
	 * 검색된 게시글 조회용 서비스
	 * @param map
	 * @param pInf
	 * @return searchList
	 * @throws Exception
	 */
	public List<Board> selectSearchList(Map<String, String> map, PageInfo pInf) throws Exception {
		SqlSession session = getSqlSession();
		
		List<Board> searchList = new BoardDao().selectSearchList(session, map, pInf);
		
		session.close();
		return searchList;
	}

}
