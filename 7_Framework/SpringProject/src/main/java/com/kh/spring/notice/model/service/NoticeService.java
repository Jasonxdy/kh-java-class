package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.common.vo.PageInfo;
import com.kh.spring.notice.model.vo.Notice;

public interface NoticeService {

	/**
	 * 공지사항 게시글 수 조회 서비스 (검색 포함)
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	public abstract int getListCount(Map<String, String> map) throws Exception;

	
	
	/**
	 * 공지사항 목록 조회 서비스 (검색 포함)
	 * @param map
	 * @param pInf
	 * @return list
	 * @throws Exception
	 */
	public abstract List<Notice> selectList(Map<String, String> map, PageInfo pInf) throws Exception;


//	-------------------------- my version --------------------------------
//	/**
//	 * 공지사항 상세조회 (검색 포함)
//	 * @param noticeNo
//	 * @return notice
//	 * @throws Exception
//	 */
//	public abstract Notice selectNotice(Integer noticeNo) throws Exception;
//
//
//
//	/**
//	 * 공지사항 삭제
//	 * @param no
//	 * @return result
//	 * @throws Exception
//	 */
//	public abstract int deleteNotice(int noticeNo) throws Exception;
//
//
//
//	/**
//	 * 공지사항 수정용 Service
//	 * @param notice
//	 * @return result
//	 * @throws Exception
//	 */
//	public abstract int updateNotice(Notice notice) throws Exception;
//
//
//
//	/**
//	 * 공지사항 등록용 Service
//	 * @param notice
//	 * @return result
//	 * @throws Exception
//	 */
//	public abstract int insertNotice(Notice notice) throws Exception;
	
	
	
//	--------------------- teacher version ------------------------
	/** 공지사항 상세조회용 Service
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	public abstract Notice selectNotice(Integer no) throws Exception;

	
	/** 공지사항 등록용 Service
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	public abstract int insertNotice(Notice notice) throws Exception;

	
	/** 공지사항 수정 화면용 Service
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	public abstract Notice updateView(Integer no) throws Exception;

	
	/** 공지사항 수정용 Service
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	public abstract int updatetNotice(Notice notice) throws Exception;

	
	/** 공지사항 삭제용 Service
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public abstract int deletetNotice(Integer no) throws Exception;
	
	
	

}
