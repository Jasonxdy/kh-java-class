package com.kh.sjproject.notice.model.service;

import java.sql.Connection;
import java.util.List;
import static com.kh.sjproject.common.JDBCTemplate.*;

import com.kh.sjproject.notice.model.dao.NoticeDAO;
import com.kh.sjproject.notice.model.vo.Notice;

public class NoticeService {

	/**
	 * 공지사항 목록 조회용 서비스
	 * @return list : List<Notice>
	 * @throws : Exception
	 */
	public List<Notice> selectList() throws Exception{
		
		Connection conn = getConnection();
		
		List<Notice> list = new NoticeDAO().selectList(conn);
		
		close(conn);
		
		// 조회이기 때문에 트랜잭션 처리 X
		return list;
	}

	/**
	 * 공지사항 상세 조회용 Service
	 * @param no 
	 * @return notice
	 * @throws Exception
	 */
	public Notice selectNotice(int no) throws Exception {
		Connection conn = getConnection();
		
		NoticeDAO noticeDAO =  new NoticeDAO();
		
		// 공지사항 상세조회
		Notice notice = noticeDAO.selectNotice(conn, no);
		
		// 공지사항 상세조회 성공 시 조회수 증가
		
		if(notice != null) {
			int result = noticeDAO.increaseCount(conn, no);
			
			if(result >0) {
				commit(conn);
				
				// 반환되는 notice는 조회수 증가가 되어있지 않으므로
				// return시 조회수를 +1 시켜줌
				notice.setNoticeCount(notice.getNoticeCount() + 1);
			} else {
				rollback(conn);
				notice = null; // 조회수 증가 실패 시 조회되지 않게 만듬
			}
		}
		
		close(conn);
		return notice;
	}

	public int insertNotice(Notice notice) throws Exception {
		Connection conn = getConnection();
		
		NoticeDAO noticeDAO = new NoticeDAO();
		
		// 등록될 공지사항의 글 번호 생성
		// -> 공지사항 등록 성공 시 해당 글 번호 상세조회를 위해서
		//  + 파일 업로드 시 현재 등록된 글 번호를 외래키로 사용하기 위해서
		
		// 실무에서는 이런식으로 nextval해서 sequence의 값을 꺼내와서 
		// currval은 안써도 사용 가능하게 함.. 왜냐면 currval을 사용하는 경우 다른 요청(다른사람이 nextval 사용)이 들어와서
		// currval이 변경되었을 가능성이 있기 때문...! 
		
		int no = noticeDAO.selectNextNo(conn);
		
		int result = 0;
		
		
		if(no > 0) { // 다음 번호가 생성이 됐을 경우
			
			// DB 저장 시 개행문자 \r\n을 <br>로 변경해야
			// 상세 조회 시 줄바꿈이 유지됨.
			notice.setNoticeContent(
					notice.getNoticeContent().replace("\r\n", "<br>")
					);
			
			
			result = noticeDAO.insertNotice(conn, notice, no);
			if(result >0) { // 공지사항 dB 삽입 성공
				commit(conn);
				
				result = no;
				// 다음 번호 조회 + 공지사항 등록 성공 시 등록 완료 후 해당 작성 페이지로 이동하기 위해서
				// 조회된 번호를 반환시킴
				// -> 이런식으로 확인용 변수는 원하는 변수로 대체해버리기도 한다..(알고리즘 적으로 생각할 것)
				
			}
			else		rollback(conn);
		}
		
		
		return result;
		
		// 성공시 0보다 큰값, 실패시 0 (위에서 선언했고 안바꼈으므로)
	}

	
	

	
	// 내꺼
//	/**
//	 * 공지사항 등록용 Service
//	 * @param notice : Notice
//	 * @return result : int
//	 */
//	public int[] insertNotice(Notice notice) throws Exception {
//		Connection conn = getConnection();
//		
//		int[] result = new int[2];
//		
//		result[0] = new NoticeDAO().insertNotice(conn, notice);
//		
//		if(result[0] > 0) {
//			commit(conn);
//			result[1] = new NoticeDAO().getNoticeNo(conn);
//		}
//		else			rollback(conn);
//		
//		return result;
//	}

	
	
	
	
	/** 공지사항 수정 화면용 Service
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	public Notice updateForm(int no) throws Exception {
		
		Connection conn = getConnection();
		
		// 공지사항 상세조회
		Notice notice = new NoticeDAO().selectNotice(conn, no);
		
		// DB에 저장된 내용을 textarea에 출력할 경우 
		// <br>로 저장되어 있는 부분을 \r\n으로 변경해야 함.
		notice.setNoticeContent(notice.getNoticeContent().replace("<br>", "\r\n"));
		
		close(conn);
		
		return notice;
	}

	
	
	
	/**
	 * 공지사항 수정용 Service
	 * @param notice : Notice
	 * @return result : int
	 * @throws Exception
	 */
	public int updateNotice(Notice notice) throws Exception {
		
		Connection conn = getConnection();
		
		// 수정된 내용이 DB에 저장될 경우 개행문자 변경 필요
		notice.setNoticeContent(notice.getNoticeContent().replace("\r\n", "<br>"));
		
		int result = new NoticeDAO().updateNotice(conn, notice);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
	/**
	 * 공지사항 삭제용 Service
	 * @param no : int
	 * @return result : int
	 * @throws Exception
	 */
	public int deleteNotice(int no) throws Exception {
		
		Connection conn = getConnection();
		
		int result = new NoticeDAO().deleteNotice(conn, no);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		
		close(conn);
		return result;
	}

	
	
	
	
	
	/**
	 * 공지사항 검색용 Service
	 * @param searchKey 
	 * @param searchValue
	 * @return list
	 * @throws Exception
	 */

	public List<Notice> searchNotice(String searchKey, String searchValue) throws Exception {
		Connection conn = getConnection();
		
		String condition = null;
		
		
		// SQL 하기 전에 Service단에서 query문을 가공해줌
		searchValue = "'%' || '" + searchValue + "' || '%'";
				// like '%공지%'
		
		
		switch(searchKey) {
			case "title" : condition = " NOTICE_TITLE LIKE " + searchValue; break; 
			case "content" : condition = " NOTICE_CONTENT LIKE " + searchValue; break;
			case "titcont" : condition = " (NOTICE_TITLE LIKE " + searchValue 
					+ " OR NOTICE_CONTENT LIKE " + searchValue + ")"; break;
		}
		
		List<Notice> list = new NoticeDAO().searchNotice(conn, condition);
		
		close(conn);
		
		return list;
	}
}
