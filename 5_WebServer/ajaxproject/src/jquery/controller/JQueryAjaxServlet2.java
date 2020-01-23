package jquery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jqTest2.do")
public class JQueryAjaxServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JQueryAjaxServlet2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST 방식으로 데이터 얻어오므로 데이터 인코딩
		request.setCharacterEncoding("UTF-8");
		
		String input = request.getParameter("input");
		
		System.out.println("입력 값 : " + input);
		
		// 응답 데이터에 한글이 있을 경우 문자 인코딩
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().print("입력된 값 : " + input + ", 길이 : " + input.length());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
