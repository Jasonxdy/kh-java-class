package js.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsAjax1.do")
//				쿼리스트링은 값일 뿐이지 주소는 아님! 따라서 안적어도 된다
public class JsAjaxServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JsAjaxServlet1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터로 전달된 값 변수에 저장
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		System.out.println("name : " + name);
		System.out.println("age : " + age);
		
		// 응답 데이터 문자 인코딩 처리
//		response.setContentType("text/html; charset=UTF-8");
		// -> 출력될 화면의 Mime Type이 이미 지정되어 있기 때문에
		// 		문자에 대한 인코딩 처리만 하면 됨
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		out.append("서버에서 전송한 응답 데이터<br>");
		out.append("이름은 "+ name + "이고 나이는 " + age + "입니다.");
		// -> javascriptAjax.jsp의 .responseText;에 반환된다!
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
