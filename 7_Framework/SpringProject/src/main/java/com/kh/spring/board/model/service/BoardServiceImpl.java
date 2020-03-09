package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDAO;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService { // @Service("bean의 이름")을 안할 경우 자동적으로 interface의 구현체의 앞글자 소문자로
														// 변경된 것의 이름으로 bean 생성됨

	@Autowired
	private BoardDAO boardDAO;

	
	/**
	 * 전체 게시글 수 조회용 Service
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	@Override
	public int getListCount(Map<String, Object> map) throws Exception {
		return boardDAO.getListCount(map);
	}

	
	/**
	 * 게시글 목록 조회용 Service
	 * @param map
	 * @param pInf
	 * @return list : List<Board>
	 * @throws Exception
	 */
	@Override
	public List<Board> selectList(Map<String, Object> map, PageInfo pInf) throws Exception {
		return boardDAO.selectList(map, pInf);
	}
	
	
	/**
	 * 게시글 등록용 Service
	 * @param board
	 * @param files
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertBoard(Board board, List<Attachment> files) throws Exception {
		
		int result = 0;
		
		// 1) 다음 SEQ_BNO 얻어오기
		int boardNo = boardDAO.selectNextNo(); // select 실패 시 0 또는 null값 나옴
	
		// 2) 게시글 (board) DB 삽입
		if(boardNo > 0) {
			// DB에 Content 저장 시 개행 문자를 <br>로 변경
			board.setBoardContent(board.getBoardContent().replace("\r\n", "<br>"));
			
			// 조회한 boardNo를 board에 set
			board.setBoardNo(boardNo);
			
			// title, content, writer, boardNo 존재 --> DB에 저장
			// 게시글 DB 등록
			result = boardDAO.insertBoard(board);
			
			// 3) 이미지 (attachment) DB 삽입
			if(result > 0 && !files.isEmpty()) {
				
				result = 0; // result 재활용
				
				// files의 데이터를 하나씩 반복 접근하여 DB에 삽입
				for (Attachment at : files) {
					at.setBoardId(boardNo); // 게시글 번호 set
					result = boardDAO.insertAttachment(at);

					if(result == 0) {
						throw new Exception(); // 파일 삽입이 제대로 되지 않은 것이므로 transaction 처리 시 rollback되도록 예외 발생시킴
					}
				}
			}
			
		}		
		
		if(result > 0) {
			// result에 글번호 저장
			result = boardNo;
		}
		
		return result;
	}

}
