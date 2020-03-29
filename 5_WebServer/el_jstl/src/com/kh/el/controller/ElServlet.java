package com.kh.el.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.el.model.vo.Person;

@WebServlet("/01_el/el.do")
public class ElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ElServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// page, request, session, application
		
		// request scope
		request.setAttribute("classRoom", "A강의장");
		request.setAttribute("person", new Person("홍길동", 20, '남'));
		
		
		// session scope
		HttpSession session = request.getSession();
		session.setAttribute("academyName", "KH정보교육원");
		
		
		// request, session, application 같은 속성명
		request.setAttribute("scope", "request 범위");
		session.setAttribute("scope", "session 범위");
		
		ServletContext application = request.getServletContext();
		application.setAttribute("scope", "application 범위");
		
		
		RequestDispatcher view = request.getRequestDispatcher("/views/01_el/01_el.jsp");
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
