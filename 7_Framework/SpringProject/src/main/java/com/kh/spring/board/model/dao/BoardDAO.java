package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.vo.PageInfo;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession; // root-context.xml에 bean으로 등록되어 있음

	/**
	 * 전체 게시글 수 조회용 DAO
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Map<String, Object> map) throws Exception{
		return sqlSession.selectOne("boardMapper.getListCount", map);
	}

	/**
	 * 게시글 목록 조회용 DAO
	 * @param map
	 * @param pInf
	 * @return list
	 * @throws Exception
	 */
	public List<Board> selectList(Map<String, Object> map, PageInfo pInf) throws Exception{
		RowBounds rowBounds = new RowBounds((pInf.getCurrentPage()-1)*pInf.getLimit(), pInf.getLimit());
		return sqlSession.selectList("boardMapper.selectList", map, rowBounds);
	}

	/**
	 * 다음 게시글 번호 조회용 DAO
	 * @return boardNo
	 * @throws Exception
	 */
	public int selectNextNo() throws Exception{
		return sqlSession.selectOne("boardMapper.selectNextNo");
	}

	
	
	/**
	 * 게시글 등록용 DAO
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Board board) throws Exception {
		return sqlSession.insert("boardMapper.insertBoard", board);
	}

	/**
	 * 이미지 삽입용 DAO
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int insertAttachment(Attachment at) throws Exception {
		return sqlSession.insert("boardMapper.insertAttachment", at);
	}
	
	
	
	/** 썸네일 목록 조회용 DAO
	 * @param list
	 * @return thList
	 * @throws Exception
	 */
	public List<Attachment> selectThumbnailList(List<Board> list) throws Exception {
		return sqlSession.selectList("boardMapper.selectThumbnailList", list);
	}

	/** 게시글 조회용 DAO
	 * @param no
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(Integer no) throws Exception {
		return sqlSession.selectOne("boardMapper.selectBoard", no);
	}

	/** 게시글 이미지 조회용 DAO
	 * @param no
	 * @return files
	 * @throws Exception
	 */
	public List<Attachment> selectFiles(Integer no) throws Exception {
		return sqlSession.selectList("boardMapper.selectFiles", no);
	}

	/** 게시글 조회수 증가용 DAO
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int increaseCount(Integer no) throws Exception {
		return sqlSession.update("boardMapper.increaseCount", no);
	}

	/**
	 * 게시글 수정용 DAO
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Board board) throws Exception{
		return sqlSession.update("boardMapper.updateBoard", board);
	}

	/**
	 * 파일 수정용 DAO
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int updateAttachment(Attachment at) throws Exception{
		return sqlSession.update("boardMapper.updateAttachment", at);
	}
	
	
	
}
