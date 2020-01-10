package com.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet2
 */
@WebServlet("/TestServlet2")
public class TestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		// POST 방식으로 데이터를 전달 받은 경우
		// HTTP Body에 요청하는 브라우저의 ContentType이 같이 전달됨.
		// 현재 개발 환경 문자인코딩 == UTF-8
		// 브라우저 문자인코딩(크롬) == ISO-8859-1
		// 둘의 문자 인코딩이 달라 문자가 깨짐

		// 요청 데이터 사용 전에 미리 request 객체의 문자 인코딩을 변경
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String height = request.getParameter("height");
		String[] foodArr = request.getParameterValues("food");

		System.out.println("입력받은 name : " + name);
		System.out.println("입력받은 gender : " + gender);
		System.out.println("입력받은 age : " + age);
		System.out.println("입력받은 city : " + city);
		System.out.println("입력받은 height : " + height);
		System.out.print("입력받은 food : " + Arrays.toString(foodArr));

		// 응답 화면 출력 준비
		response.setContentType("text/html; charset=UTF-8");

		// 문자열 (HTML 코드)을 사용자 응답 화면에 출력할 스트림을
		// HttpServletResponse 객체에서 얻어와 응답화면과
		// 해당 Servlet 연결
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"ko\">\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <title>개인 정보 출력 화면</title>\r\n" + 
				"    <style>\r\n" + 
				"        h2 {\r\n" + 
				"            color: red;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        span.name {\r\n" + 
				"            color: orange;\r\n" + 
				"            font-weight: bold;\r\n" + 
				"        }\r\n" + 
				"        span.gender {\r\n" + 
				"            color: yellow; background-color: black;\r\n" + 
				"            font-weight: bold;\r\n" + 
				"        }\r\n" + 
				"        span.age {\r\n" + 
				"            color: green;\r\n" + 
				"            font-weight: bold;\r\n" + 
				"        }\r\n" + 
				"        span.city {\r\n" + 
				"            color: blue;\r\n" + 
				"            font-weight: bold;\r\n" + 
				"        }\r\n" + 
				"        span.height {\r\n" + 
				"            color: navy;\r\n" + 
				"            font-weight: bold;\r\n" + 
				"        }\r\n" + 
				"        span.food {\r\n" + 
				"            color: purple;\r\n" + 
				"            font-weight: bold;\r\n" + 
				"        }\r\n" + 
				"    </style>\r\n" + 
				"\r\n" + 
				"</head>\r\n" + 
				"\r\n" + 
				"<body>\r\n" + 
				"    <h2>개인 정보 입력 결과 (POST)</h2>");
		
		out.printf("    <span class=\"name\">%s</span>님은\r\n" + 
				"    <span class=\"age\">%s</span>이시며,\r\n" + 
				"    <span class=\"city\">%s</span>에 사는\r\n" + 
				"    키 <span class=\"height\">%s</span>cm인\r\n" + 
				"    <span class=\"gender\">%s</span>입니다.<br>\r\n" + 
				"    좋아하는 음식은\r\n" + 
				"    <span class=\"food\">%s</span>입니다.\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>", name, age, city, height, gender, String.join(", ", foodArr));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
//		ㄴ 이런식으로 post로 넘어와도 doGet으로 바로 넘기기 때문에 doGet()에만 작성해주면 된다
	}

}
