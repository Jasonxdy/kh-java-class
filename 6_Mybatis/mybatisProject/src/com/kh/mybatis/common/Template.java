package com.kh.mybatis.common;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	
	public static SqlSession getSqlSession() {
		// SqlSession 객체를 반환하기 위한 변수 선언
		SqlSession session = null;
		
		// JDBCTemplate에서 public static Connection getConnection()과 같은 개념의 메소드로
		// 기존에는 DB 접속 정보가 들어있는 properties파일을 불러와
		// 이 메소드 안에서 DBMS와 연결을 하였다.
		
		// 하지만 우리가 초반에 mybatis-config.xml 파일을 통해서 DB접속 관련한 정보를 작성했었다.
		// 그러면 그 mybatis-config.xml 파일을 불러와야 된다.
		
		// 현재 클래스 패스와 다른 위치에 있는 자원을 로드하는 것을 좀 더 쉽게 해주는
		// 마이바티스 라이브러리에서 제공하는 util성 클래스인
		// Resources라는 클래스를 가지고 파일을 불러와보도록 하자.
		try {
			InputStream stream = Resources.getResourceAsStream("/mybatis-config.xml");
			// -> source folder로 지정해 두어야지만 매개변수로 작성한 경로로 검색됨
			// SqlSession을 만들기 위해서 SqlSessionFactory에서 openSession이라는 것을 해줘야 되는데
			// SqlSessionFactory는 SqlSessionFactoryBuilder라는 클래스의 build()를 통해서 생성이된다.
			// openSession(false)의 의미는 자동 커밋을 하지 않게 설정하는 것이다.
			session = new SqlSessionFactoryBuilder().build(stream).openSession(false);
		}catch(Exception e) {
			System.out.println("Template Exception catch");
			e.printStackTrace();
		}
		return session;
	}
	
	// * commit(), rollback(), close() 함수는 안만드는 이유
	
	// SqlSession 클래스가 commit, rollback, close 함수를 제공해주고
	// Statement관련, ResultSet 관련한 close부분은 작성하지 않는데,
	// Dao쪽에서  마이바티스를 적용하면서 더 편리하게  sql문을 활용할 수 있게 되었기 때문에
	// Statement 관련, ResultSet 관련 내용을 사용하지 않게 된다. 
	
}
