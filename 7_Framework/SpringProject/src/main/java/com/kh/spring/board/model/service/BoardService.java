package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.vo.PageInfo;

public interface BoardService {

	/**
	 * 전체 게시글 수 조회용 Service
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public abstract int getListCount(Map<String, Object> map) throws Exception;

	/**
	 * 게시글 목록 조회용 Service
	 * @param map
	 * @param pInf
	 * @return list : List<Board>
	 * @throws Exception
	 */
	public abstract List<Board> selectList(Map<String, Object> map, PageInfo pInf) throws Exception;

	/**
	 * 게시글 등록용 Service
	 * @param board
	 * @param files
	 * @return result
	 * @throws Exception
	 */
	public abstract int insertBoard(Board board, List<Attachment> files) throws Exception;

}
