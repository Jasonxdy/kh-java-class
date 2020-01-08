package com.kh.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet1
 */
public class TestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 객체의 직렬화와 연관이 있음 (통로는 작고 객체는 클때 객체를 잘라서 지나가게 하는데 객체 전후를 비교하게 해줌: 그냥 참고만)
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet1() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// Servlet 초기화 메소드
		System.out.println("init() 메소드 실행 !!");
		
		// init() 메소드가 없을 경우 자동적으로 다음 순서인 service() 메소드 실행
		
	}


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 메소드 실행!!");
		doPost(request, response);
		// service() 메소드가 없어도 전송된 방식(GET, POST)를 알아서 따져
		// doGet() 또는 doPost() 호출함.
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// HttpServletRequest : 웹 브라우저에서 사용자가 요청한 내용을 받아주는 용도 
		// + 각종 클라이언트 정보
		
		// HttpServletResponse : HttpServletRequest 처리한 결과를 다시 클라이언트 웹 브라우저에 보여주기 위한 용도로 사용
		
		String name = request.getParameter("name");
				// input 태그 중 name 속성이 "name"인 요소의 값을 얻어옴.
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String height = request.getParameter("height");
		
		// 체크박스 등 복수 개의 정보를 전달 받을 때는 배열로 받아야 함
		String[] foodArr = request.getParameterValues("food");
		
		System.out.println("입력받은 name : " + name);
		System.out.println("입력받은 gender : " + gender);
		System.out.println("입력받은 age : " + age);
		System.out.println("입력받은 city : " + city);
		System.out.println("입력받은 height : " + height);
		System.out.print("입력받은 food : " + Arrays.toString(foodArr));
//		for (String s : foodArr) {
//			System.out.print(s + ", ");
//		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		
	}

}
