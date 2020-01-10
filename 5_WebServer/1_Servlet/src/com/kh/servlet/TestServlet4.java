package com.kh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet4
 */
@WebServlet("/TestServlet4")
public class TestServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet4() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String height = request.getParameter("height");
		String[] foodArr = request.getParameterValues("food");
		
		String gift = "";
		
		switch(age) {
		case "10대 미만": gift = "인형"; break;
		case "10대": gift = "텀블러"; break;
		case "20대": gift = "향수"; break;
		case "30대": gift = "명품"; break;
		case "40대": gift = "상품권"; break;
		case "50대": gift = "안마의자"; break;
		}
		
		// jsp에게 HTML 코드에 알맞은 값을 작성할 수 있도록 데이터 위임 준비
		// request 객체에 Key, Value 형태로 값을 저장해서 위임 가능
		request.setAttribute("name", name);
		request.setAttribute("gender", gender);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("foods", String.join(", ", foodArr));
		request.setAttribute("gift", gift);
		
		// 위임 객체 -> RequestDispatcher
		// Dispatcher : 필요한 정보를 제공하는 역할
		// RequestDispatcher : 현재 request 객체에 담긴 정보를 저장하고 있다가
		// 다음 지정된 페이지에서 해당 정보를 볼 수 있게 위임(제공)하는 역할
		RequestDispatcher view = request.getRequestDispatcher("views/testServlet4End.jsp");
		view.forward(request, response);
		
		// forward 방식 : 기존 파라미터(request, response)를 유지하며 페이지 전환
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
