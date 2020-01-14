package com.kh.sjproject.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.sjproject.member.model.service.MemberService;
import com.kh.sjproject.member.model.vo.Member;

@WebServlet("/member/signUp.do")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. POST 방식 + 한글 데이터 포함 -> 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2. 전달된 파라미터를 변수에 저장 후 Member 객체를 생성하여 저장
		
		String memberId = request.getParameter("id");
		String memberPwd = request.getParameter("pwd1");
		String memberName = request.getParameter("name");
		
		// 전화번호를 '-'를 구분자로 하여 하나로 합침
		String memberPhone = request.getParameter("phone1") + "-"+ request.getParameter("phone2") + "-"+ request.getParameter("phone3");
		String memberEmail = request.getParameter("email");
		
		// 주소를 ','를 구분자로 하여 하나의 string으로 합침
		String memberAddress = request.getParameter("post") + "," + request.getParameter("address1") + "," + request.getParameter("address2");
		
		String[] interest = request.getParameterValues("memberInterest");
		
		// 관심분야 배열을 ","를 구분자로 하여 하나의 String으로 합침
		String memberInterest = null;
		if(interest != null) memberInterest = String.join(",", interest);
		
		
		// 3. 비즈니스 로직을 수행하여 DB에 회원정보 저장
		try {
			int result = new MemberService().signUp(new Member(memberId, memberPwd, memberName, memberPhone, memberEmail, memberAddress, memberInterest));
			
			// 4. result 값에 따라 경고창에 메세지 출력
			String msg = null;
			
			if(result > 0) msg = "가입 성공!";
			else		   msg = "가입 실패!";
			
			// session의 "msg" 속성에 msg 세팅
			request.getSession().setAttribute("msg", msg);
			
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			
			request.setAttribute("errorMsg", "회원 가입 과정에서 오류가 발생하였습니다.");
			
			e.printStackTrace();
			
			String path = "/WEB-INF/views/common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
