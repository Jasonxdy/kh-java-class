package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mybatis.member.model.service.MemberService;
import com.kh.mybatis.member.model.vo.Member;

//@WebServlet("/member/login.do")
@WebServlet(name="LoginServlet", urlPatterns="/member/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		Member member = new Member(memberId, memberPwd);
		
		try {
			Member loginMember = new MemberService().loginMember(member);
			
			// DB 조회 결과 확인
			System.out.println("loginMember : " + loginMember);
			
			HttpSession session = request.getSession(); // 괄호에 true 써도됨
			
			if(loginMember != null) { // 성공일 경우
				// session.setMaxInactiveInterval(600); 	// 10분 뒤 자동 로그아웃
				session.setAttribute("loginMember", loginMember); 
				
				// 아아디 저장 체크박스 값 가져오기
				String save = request.getParameter("save");
				System.out.println("save : " + save); // 체크 시 on 이라는 문자열이 넘어옴
				
				// javax.servlet.http.Cookie를 이용하여 쿠기 생성
				// 새로운 쿠키 객체 생성
				Cookie cookie = new Cookie("saveId", memberId);
				
				// 아이디 저장이 체크된 경우
				if(save != null) {
					// 쿠키가 유지될 수 있는 유효기간을 설정
					cookie.setMaxAge(7 * 24 * 60 * 60); // 7일로 설정
				}else { // 아이디 저장이 체크되지 않은 경우 -> 쿠키를 만료시켜 삭제시키기!
					cookie.setMaxAge(0);
				}
				
				// 쿠키가 사용될 수 있는 유효한 디렉토리를 설정
				cookie.setPath("/"); // 해당 도메인 전역에서 사용 가능
				
				// response 객체에 쿠키를 담아서 웹브라우저에 전송. -> 이후 쿠키는 브라우저가 관리한다.
				response.addCookie(cookie);
				
			}else {
				System.out.println("로그인 실패");
				session.setAttribute("msg", "로그인 정보가 유효하지 않습니다."); 
			}
			
			// 메인 페이지로 이동
			//response.sendRedirect(request.getContextPath());
			
			// referer : 사이트 방문 흔적
			// request.getHeader("referer") :  이전페이지 uri가 담겨있음.
			response.sendRedirect(request.getHeader("referer"));
			
			
		}catch (Exception e) {
			request.setAttribute("errorMsg", "로그인 과정에서 오류가 발생했습니다.");
			e.printStackTrace();
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
