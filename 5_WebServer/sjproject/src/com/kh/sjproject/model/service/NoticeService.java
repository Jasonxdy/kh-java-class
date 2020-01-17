package com.kh.sjproject.model.service;

import java.sql.Connection;
import java.util.List;
import static com.kh.sjproject.common.JDBCTemplate.*;

import com.kh.sjproject.member.model.dao.NoticeDAO;
import com.kh.sjproject.member.model.vo.Notice;

public class NoticeService {

	/**
	 * 공지사항 목록 조회용 서비스
	 * @return list : List<Notice>
	 * @throws : Exception
	 */
	public List<Notice> selectList() throws Exception{
		
		Connection conn = getConnection();
		
		// 조회이기 때문에 트랜잭션 처리 X
		return new NoticeDAO().selectList(conn);
	}

}
