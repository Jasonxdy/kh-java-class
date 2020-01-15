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

@WebServlet("/member/updateMember.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateMemberServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. POST 방식 + 한글 포함 시 문자 인코딩 처리
		request.setCharacterEncoding("UTF-8");

		// 2. 전달 받은 파라미터를 변수에 저장
//				+ 로그인 ID를 얻어와 Member 객체에 저장

		// session에 저장된 ID 얻어오기
		String memberId = ((Member) request.getSession().getAttribute("loginMember")).getMemberId();

		// 전화번호를 '-'를 구분자로 하여 하나로 합침

		// 파라미터 변수에 저장
		String memberPhone = request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
				+ request.getParameter("phone3");
		String memberEmail = request.getParameter("email");

		// 주소를 ','를 구분자로 하여 하나의 string으로 합침
		String memberAddress = request.getParameter("post") + "," + request.getParameter("address1") + ","
				+ request.getParameter("address2");

		String[] interest = request.getParameterValues("memberInterest");

		// 관심분야 배열을 ","를 구분자로 하여 하나의 String으로 합침
		String memberInterest = null;
		if (interest != null)
			memberInterest = String.join(",", interest);

		// 3. 비즈니스 로직을 수행할 서비스 메소드 호출 후 결과 값 반환 받기
		try {
			int result = new MemberService()
					.updateMember(new Member(memberId, memberPhone, memberEmail, memberAddress, memberInterest));

			// 4. result 값에 따라 경고창에 메세지 출력
			String msg = null;

			if (result > 0)
				msg = "회원 정보가 수정되었습니다";
			else
				msg = "회원 정보 수정 실패!";

//					String path = "/WEB-INF/views/member/mypage.jsp";
//					RequestDispatcher view = request.getRequestDispatcher(path);
//					
//					view.forward(request, response);

			// session의 "msg" 속성에 msg 세팅
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect("mypage.do");

		} catch (Exception e) {

			request.setAttribute("errorMsg", "회원 정보 수정 과정에서 오류가 발생하였습니다.");

			e.printStackTrace();

			String path = "/WEB-INF/views/common/errorPage.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
