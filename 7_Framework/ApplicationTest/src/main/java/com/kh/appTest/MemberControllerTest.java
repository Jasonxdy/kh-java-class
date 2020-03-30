package com.kh.appTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // web.xml을 읽어들임
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class MemberControllerTest {

	@Autowired
	private WebApplicationContext wac;
	// 현재 실행중인 애플리케이션의 구성을 제공하는 인터페이스

	private MockMvc mockMvc;
	// client 요청 내용을 controller에서 받아 처리하는 것과 같은 테스트를 진행할 수 있는 클래스.

	@Before // JUnit 테스트 진행 전 먼저 실행하는 것을 지정하는 어노테이션
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); // 작성한 코드를 빌드해서 mockMvc에 넣어줌 (이제 mockMvc가
	}

	@Test // 테스트용 메소드임을 명시하는 어노테이션
	public void testMemberLogin() throws Exception {
		try {
			mockMvc.perform(post("/beginSpring/memberRegister.action")
					.param("userid", "admin")
					.param("passwd","1234")
					.param("name", "관리자")
					.param("email", "admin@kh.or.kr")
					.param("tel", "01012341234"))
					.andDo(print())
					.andExpect(status().isOk());
			
		} catch (Exception e) {
			
		}
	}

}
