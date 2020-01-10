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
 * Servlet implementation class TestServlet3
 */
@WebServlet("/TestServlet3")
public class TestServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet3() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// POST 방식 요청 데이터 문자 인코딩 변환
		request.setCharacterEncoding("UTF-8");
		
		
		// 요청 데이터를 모두 얻어와 변수에 저장
		
		// 나이대에 따라서 추천할 선물 지정
		// 10대 미만 : 인형
		// 10대 : 텀블러
		// 20대 : 향수
		// 30대 : 명품
		// 40대 : 상품권
		// 40대 : 안마의자
		
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

		System.out.println("입력받은 name : " + name);
		System.out.println("입력받은 gender : " + gender);
		System.out.println("입력받은 age : " + age);
		System.out.println("입력받은 city : " + city);
		System.out.println("입력받은 height : " + height);
		System.out.print("입력받은 food : " + Arrays.toString(foodArr));
		
		
		response.setContentType("text/html; charset=UTF-8");
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
				"    <h2>개인 정보 입력 결과 (비즈니스 로직)</h2>");
		
		out.printf("    <span class=\"name\">%s</span>님은\r\n" + 
				"    <span class=\"age\">%s</span>이시며,\r\n" + 
				"    <span class=\"city\">%s</span>에 사는\r\n" + 
				"    키 <span class=\"height\">%s</span>cm인\r\n" + 
				"    <span class=\"gender\">%s</span>입니다.<br>\r\n" + 
				"    좋아하는 음식은\r\n" + 
				"    <span class=\"food\">%s</span>입니다.\r\n" +
				"<h3>%s에 맞는 선물 추천</h3>" + 
				"'%s' 선물은 어떠신가요?" +
				"</body>\r\n" + 
				"\r\n" + 
				"</html>", name, age, city, height, gender, String.join(", ", foodArr), age, gift);

		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
