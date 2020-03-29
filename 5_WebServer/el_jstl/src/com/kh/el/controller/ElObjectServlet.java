package com.kh.el.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/01_el/elObject.do")
public class ElObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ElObjectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 쿠키 생성
		Cookie cookie = new Cookie("saveId", "user01");
		cookie.setMaxAge(24*60*60); // 하루
		cookie.setPath("/"); // 최상단 주소 -> 하위에 있는 모든 곳에서 사용 가능
		
		response.addCookie(cookie); // 서버에서 생성한 쿠키를 클라이언트에게 보내서 저장시킴
		
		RequestDispatcher view = request.getRequestDispatcher("/views/01_el/03_elObject.jsp");
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
