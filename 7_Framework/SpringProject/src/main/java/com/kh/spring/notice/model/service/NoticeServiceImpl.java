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
	
	
//	------------------------ my version ------------------------------
//	/**
//	 * 공지사항 상세조회 (검색 포함)
//	 * @param noticeNo
//	 * @return notice
//	 * @throws Exception
//	 */
//	@Override
//	public Notice selectNotice(int noticeNo) throws Exception {
//		return noticeDAO.selectNotice(noticeNo);
//	}
//	
//	
//	
//	
//	/**
//	 * 공지사항 삭제
//	 * @param no
//	 * @return result
//	 * @throws Exception
//	 */
//	@Transactional(rollbackFor = Exception.class)
//	@Override
//	public int deleteNotice(int noticeNo) throws Exception {
//		return noticeDAO.deleteNotice(noticeNo);
//	}
//	
//	
//	
//	
//	
//	/**
//	 * 공지사항 수정용 Service
//	 * @param noticeNo
//	 * @return result
//	 * @throws Exception
//	 */
//	@Transactional(rollbackFor = Exception.class)
//	@Override
//	public int updateNotice(Notice notice) throws Exception {
//		return noticeDAO.updateNotice(notice);
//	}
//	
//	
//	/**
//	 * 공지사항 등록용 Service
//	 * @param notice
//	 * @return result
//	 * @throws Exception
//	 */
//	@Transactional(rollbackFor = Exception.class)
//	@Override
//	public int insertNotice(Notice notice) throws Exception {
//		
//		int noticeNo = noticeDAO.getNoticeNo();
//		
//		notice.setNoticeNo(noticeNo);
//		
//		int result = noticeDAO.insertNotice(notice);
//		
//		if(result > 0) result = noticeNo;
//		
//		return result;
//		
//	}

//	----------------------- teacher version -----------------------------
	
	/** 공지사항 상세조회용 Service
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Notice selectNotice(Integer no) throws Exception {
		// 공지사항 조회
		Notice notice = noticeDAO.selectNotice(no);
		
		if(notice != null) {
			// 조회수 증가
			int result = noticeDAO.increaseCount(no);
			
			if(result > 0) {
				notice.setNoticeCount(notice.getNoticeCount() + 1);
			}else {
				notice = null;
			}
		}
		
		return notice;
	}

	
	/** 공지사항 등록용 Service
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertNotice(Notice notice) throws Exception {
		
		int result = 0;
		// 공지사항 다음 글번호 얻어오기
		// -> 공지사항 등록 성공 시 해당 게시글로 돌아갈 수 있게하기 위함
		int no = noticeDAO.selectNextNo();
		System.out.println("no : " + no);
		
		if(no > 0) { // 글번호 얻어오기 성공 시 
			// 조회된 글번호 세팅
			notice.setNoticeNo(no);
			
			// DB 저장 시 개행문자를 <br>로 변경해줘야
			// 상세 조회시 줄바꿈이 유지됨.
			notice.setNoticeContent(
					notice.getNoticeContent().replace("\r\n", "<br>"));
			
			result = noticeDAO.insertNotice(notice);
			System.out.println("result : " + result);
			
			if(result > 0)	result = no;
		}
		
		return result;
	}

	/** 공지사항 수정 화면용 Service
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	@Override
	public Notice updateView(Integer no) throws Exception {
		Notice notice = noticeDAO.selectNotice(no);
		// 수정을 위해 textarea에 content를 출력할 경우
		// <br>로 되어있는 부분을 개행문자로 변경해줘야됨.
		notice.setNoticeContent(notice.getNoticeContent().replace("<br>", "\r\n"));
		notice.setNoticeNo(no);
		return notice;
	}

	
	/** 공지사항 수정용 Service
	 * @param notice
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatetNotice(Notice notice) throws Exception {
		notice.setNoticeContent(
				notice.getNoticeContent().replace("\r\n", "<br>"));
		return noticeDAO.updateNotice(notice);
	}

	
	/** 공지사항 삭제용 Service
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deletetNotice(Integer no) throws Exception {
		return  noticeDAO.deleteNotice(no);
	}	
}
