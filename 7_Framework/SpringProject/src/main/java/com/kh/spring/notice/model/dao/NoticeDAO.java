package com.kh.spring.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.common.vo.PageInfo;
import com.kh.spring.notice.model.vo.Notice;

@Repository("noticeDAO")
public class NoticeDAO {
	
	// Bean으로 등록된 것들중 아래와 같은 클래스가 있으면 알아서 의존성 주입해라 (단, Bean-scanning이 되어야 함)
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	
	
	/**
	 * 공지사항 게시글 수 조회 dao (검색 포함)
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Map<String, String> map) throws Exception{
		return sqlSession.selectOne("noticeMapper.getListCount", map);
	}




	/**
	 * 공지사항 목록 조회 dao
	 * @param map
	 * @param pInf
	 * @return list
	 * @throws Exception
	 */
	public List<Notice> selectList(Map<String, String> map, PageInfo pInf) throws Exception {
		
		int offset = (pInf.getCurrentPage()-1) * pInf.getLimit();
		RowBounds rowBounds = new RowBounds(offset, pInf.getLimit());
		
		return sqlSession.selectList("noticeMapper.selectList", map, rowBounds);
		
	}



// ------------------------ My version -----------------------------
//	/**
//	 * 공지사항 상세조회 dao
//	 * @param noticeNo
//	 * @return notice
//	 * @throws Exception
//	 */
//	public Notice selectNotice(int noticeNo) throws Exception {
//		return sqlSession.selectOne("noticeMapper.selectNotice", noticeNo);
//	}
//	
////	---------- teacher version ----------
//	/** 공지사항 조회수 증가용 DAO
//	 * @param no
//	 * @return result
//	 * @throws Exception
//	 */
//	public int increaseCount(Integer no) throws Exception{
//		return sqlSession.update("noticeMapper.increaseCount", no);
//	}
//
//
//
//
//	/**
//	 * 공지사항 삭제용 dao
//	 * @param no
//	 * @return result
//	 * @throws Exception
//	 */
//	public int deleteNotice(int noticeNo) throws Exception{
//		return sqlSession.update("noticeMapper.deleteNotice", noticeNo);
//	}
//
//
//
//
//	/**
//	 * 공지사항 수정용 DAO
//	 * @param noticeNo
//	 * @return result
//	 * @throws Exception
//	 */
//	public int updateNotice(Notice notice) throws Exception{
//		return sqlSession.update("noticeMapper.updateNotice", notice);
//	}
//
//
//
//
//	/**
//	 * 공지사항 등록용 DAO
//	 * @param notice
//	 * @return result
//	 * @throws Exception
//	 */
//	public int insertNotice(Notice notice) throws Exception {
//		return sqlSession.insert("noticeMapper.insertNotice", notice);
//	}
//
//
//
//
//	/**
//	 * 다음 공지사항 번호 얻어오기
//	 * @return noticeNo
//	 */
//	public int getNoticeNo() {
//		return sqlSession.selectOne("noticeMapper.getNoticeNo");
//	}
	
	
//	--------------------- Teacher version ----------------------------
	/** 공지사항 상세조회용 DAO
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	public Notice selectNotice(Integer no) throws Exception{
		return sqlSession.selectOne("noticeMapper.selectNotice", no);
	}


	/** 공지사항 조회수 증가용 DAO
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int increaseCount(Integer no) throws Exception{
		return sqlSession.update("noticeMapper.increaseCount", no);
	}

	
	/** 공지사항 다음 글번호 조회용 DAO
	 * @return no
	 */
	public int selectNextNo() {
		return sqlSession.selectOne("noticeMapper.selectNextNo");
	}

	/** 공지사항 등록용 DAO
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice(Notice notice) throws Exception{
		return sqlSession.insert("noticeMapper.insertNotice", notice);
	}


	/** 공지사항 수정용 DAO
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	public int updateNotice(Notice notice) {
		return sqlSession.update("noticeMapper.updateNotice", notice);
	}


	/** 공지사항 삭제용 DAO
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int deleteNotice(Integer no) {
		return sqlSession.update("noticeMapper.deleteNotice", no);
	}	

	

}
