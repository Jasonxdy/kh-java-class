package model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import model.dao.MemberDAO;
import model.vo.Member;

public class MemberService {

	public int insertMember(Member member) throws Exception {
		MemberDAO memberDAO = new MemberDAO();
		Connection conn = getConnection();
		
		// MemberDAO.insertMember(conn, member) 생성
		int result = memberDAO.insertMember(conn, member);
		if(result >0) {
			commit(conn);
		} else {
			rollBack(conn);
		}
		return result;
	}
}
