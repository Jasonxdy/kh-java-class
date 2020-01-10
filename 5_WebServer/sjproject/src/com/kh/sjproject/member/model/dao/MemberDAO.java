package com.kh.sjproject.member.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;
import static com.kh.sjproject.common.JDBCTemplate.*;

import com.kh.sjproject.common.JDBCTemplate;
import com.kh.sjproject.member.model.vo.Member;

public class MemberDAO {

	private Properties prop = null;

	public MemberDAO() throws Exception {
		// member 관련 sql 구문을 관리할 properties 파일 생성
		String fileName = MemberDAO.class.getResource("/com/kh/sjproject/sql/member/member-query.properties").getPath();
		
		prop = new Properties();
		
		prop.load(new FileReader(fileName));

	}

	public Member loginMember(Connection conn, Member member) {

		return null;
	}

}
