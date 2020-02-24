package com.kh.spring.notice.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.common.vo.PageInfo;
import com.kh.spring.notice.model.dao.NoticeDAO;
import com.kh.spring.notice.model.vo.Notice;

@Service("noticeService") // Bean에 이름을 등록함. 안할경우 원래는 자동적으로 앞글자만 소문자로 되어 이름 지정됨 (똑같지만 그냥 명시해줌)
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	
	/**
	 * 공지사항 게시글 수 조회 서비스 (검색 포함)
	 * @param map
	 * @return listCount
	 * @throws Exception
	 */
	@Override
	public int getListCount(Map<String, String> map) throws Exception {
		return noticeDAO.getListCount(map);
	}
	
	
	
	
	
	
	/**
	 * 공지사항 목록 조회 서비스 (검색 포함)
	 * @param map
	 * @param pInf
	 * @return list
	 * @throws Exception
	 */
	@Override
	public List<Notice> selectList(Map<String, String> map, PageInfo pInf) throws Exception {
		return noticeDAO.selectList(map, pInf);
	}
	
	
	
	/**
	 * 공지사항 상세조회 (검색 포함)
	 * @param noticeNo
	 * @return notice
	 * @throws Exception
	 */
	@Override
	public Notice selectNotice(int noticeNo) throws Exception {
		return noticeDAO.selectNotice(noticeNo);
	}
	

	
	
	/**
	 * 공지사항 삭제
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteNotice(int noticeNo) throws Exception {
		return noticeDAO.deleteNotice(noticeNo);
	}
	
	
	
	
	
	/**
	 * 공지사항 수정용 Service
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateNotice(Notice notice) throws Exception {
		return noticeDAO.updateNotice(notice);
	}
	
	
	/**
	 * 공지사항 등록용 Service
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertNotice(Notice notice) throws Exception {
		
		int noticeNo = noticeDAO.getNoticeNo();
		
		notice.setNoticeNo(noticeNo);
		
		int result = noticeDAO.insertNotice(notice);
		
		if(result > 0) result = noticeNo;
		
		return result;
		
	}

}
