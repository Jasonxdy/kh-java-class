package com.kh.sjproject.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.sjproject.member.model.service.MemberService;
import com.kh.sjproject.member.model.vo.Member;

// (눈에는 보이지 않지만) 서버 구동 시 web.xml에 현재 Servlet 클래스 명으로
// <servlet>이 등록되고, 매개변수에 작성된 url로 <servlet-mapping>이 작성됨.
@WebServlet("/member/login.do")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// POST 방식 전송 시 문자 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		// 요청 데이터를 변수에 기록
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		// Member 객체에 id, pwd를 저장
		Member member = new Member(memberId, memberPwd);
		
		try {
			// 비즈니스 로직 처리를 위한 MemberService 클래스 생성
			// login 기능을 수행할 메소드 호출 + 결과값 반환 받기
			Member loginMember = new MemberService().loginMember(member);
			
			// 로그인 동작 여부 확인 Test
//			System.out.println("loginMember : " + loginMember);
			
			// 응답 데이터 문자 인코딩 처리
			response.setContentType("text/html; charset=UTF-8");
			
			// 서비스 요청에 해당하는 결과를 가지고 성공/실패에 대한 View(화면) 처리
			
			// 1) 서비스 요청 성공 시
			// 세션 (Session) 객체를 생성하여 로그인된 정보를 담음.
			// Session은 브라우저에 저장되며 클라이언트측에서 가지고 있는 것 (서버는 모른다)
			
			HttpSession session = request.getSession();
			if(loginMember != null) {
				
				session.setMaxInactiveInterval(600); // 10분 (초단위로 작성)
				// 10분 뒤 session 만료
				
				// 로그인이 성공한 경우
				session.setAttribute("loginMember", loginMember);
			} else {
				// 2) 로그인 서비스 요청 실패
				// 메인 페이지 (index.jsp)로 이동하여 경고창에 "로그인 정보가 유효하지 않습니다." 출력
				session.setAttribute("msg", "로그인 정보가 유효하지 않습니다.");
				
			}
			
			
			// 메인 페이지로 이동
			// sendRedirect() <-> forward()
			// sendRedirect()는 주소가 지정한 url로 변환되고
			// --> 갱신이 되었다는 의미
			// --> 갱신이 되면 request, response 객체 새로 생성됨.
			response.sendRedirect(request.getContextPath());
			
		} catch (Exception e) {
			request.setAttribute("errorMsg", "로그인 과정에서 오류가 발생하였습니다.");
			e.printStackTrace();
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
//			WEB-INF는 원래 브라우저에서 직접 접근은 안되는데 servlet은 WAS로 요청하기 때문에
//			또한 / : 상대경로 표시하면 됨
			
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			// request.sendRedirect() vs RequestDispatcher.forward()
			/*
			 * sendRedirect() : URL --> new URL로 이동 (갱신)
			 * forward() : URL이 그대로 갱신 (request, response가 같이 넘어가기 때문에 갱신됨)
			 * 
			 */
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
