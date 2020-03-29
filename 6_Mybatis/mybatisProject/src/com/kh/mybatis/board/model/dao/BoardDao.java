package com.kh.mybatis.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.PageInfo;

public class BoardDao {

	/**
	 * 전체 게시글 수 조회용 DAO
	 * 
	 * @param session
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(SqlSession session) throws Exception {
		return session.selectOne("boardMapper.getListCount");
		// mapper의 namespace.tag_id

	}

	/**
	 * 게시글 목록 조회용 dao
	 * 
	 * @param session
	 * @param pInf
	 * @return
	 * @throws Exception
	 */
	public List<Board> selectList(SqlSession session, PageInfo pInf) throws Exception {

		List<Board> bList = null;

		// RowBounds 객체
		// - 페이징 처리를 위해 제공하는 Mybatis 객체

		// * offset : 몇 개의 게시글을 건너 뛰고 조회를 시작할 것인지를 지정
		int offset = (pInf.getCurrentPage() - 1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());

		bList = (List) session.selectList("boardMapper.selectList", null, rowBounds);
		// 첫번째 인자 : namespace.tag_id
		// 두번째 인자 : 전달할 Parameter, 없으면 null
		// 세번째 인자 : RowBounds 객체

		return bList;
	}

	/**
	 * 다음 게시글 번호 조회용 DAO
	 * 
	 * @param session
	 * @return boardNo : int
	 * @throws Exception
	 */
	public int selectNextNo(SqlSession session) throws Exception {
		return session.selectOne("boardMapper.selectNextNo");
	}

	/**
	 * 게시글 등록용 DAO
	 * 
	 * @param session
	 * @param board
	 * @return result : int
	 * @throws Exception
	 */
	public int insertBoard(SqlSession session, Board board) throws Exception {
		return session.insert("boardMapper.insertBoard", board);
	}

	/**
	 * 게시글 상세 조회용 DAO
	 * 
	 * @param session
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public Board selectBoard(SqlSession session, int boardNo) throws Exception {
		return session.selectOne("boardMapper.selectBoard", boardNo);
	}

	/**
	 * 조회수 증가 DAO
	 * 
	 * @param session
	 * @param boardNo
	 * @return result : int
	 * 
	 */
	public int increaseCount(SqlSession session, int boardNo) {
		return session.update("boardMapper.increaseCount", boardNo);
	}

	/**
	 * 검색된 게시글 총 개수 조회용 DAO
	 * 
	 * @param session
	 * @param map
	 * @return searchListCount
	 * @throws Exception
	 */
	public int getSearchListCount(SqlSession session, Map<String, String> map) throws Exception {
		return session.selectOne("boardMapper.getSearchListCount", map);
	}

	/**
	 * 검색된 게시글 목록 조회용 DAO
	 * 
	 * @param session
	 * @param map
	 * @param pInf
	 * @return searchList
	 * @throws Exception
	 */
	public List<Board> selectSearchList(SqlSession session, Map<String, String> map, PageInfo pInf) throws Exception {
		return session.selectList("boardMapper.selectSearchList", map,
				new RowBounds((pInf.getCurrentPage() - 1) * pInf.getLimit(), pInf.getLimit()));
	}

	/**
	 * 다중 조건 검색 총 게시글 수 조회용 Dao
	 * 
	 * @param session
	 * @param map
	 * @return searchListCount
	 * @throws Exception
	 */
	public int getSearchListCount2(SqlSession session, Map<String, Object> map) throws Exception {
		return session.selectOne("boardMapper.getSearchListCount2", map);
	}

	
	
	
	/**
	 * 다중 조건 게시글 검색용 DAO
	 * @param session
	 * @param map
	 * @param pInf
	 * @return searchList
	 * @throws Exception
	 */
	public List<Board> selectSearchList2(SqlSession session, Map<String, Object> map, PageInfo pInf) throws Exception {
		return session.selectList("boardMapper.searchList2", map, new RowBounds((pInf.getCurrentPage()-1)*pInf.getLimit(), pInf.getLimit()));
	}

}
