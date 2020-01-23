package jquery.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jquery.model.vo.User;

@WebServlet("/jqTest4.do")
public class JQueryAjaxServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public JQueryAjaxServlet4 () {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 정보가 저장되어있는 List 생성
		ArrayList<User> list = new ArrayList<>();
		
		list.add(new User(1,"박철수", 30, '남'));
		list.add(new User(2,"김영희", 26, '여'));
		list.add(new User(3,"오영심", 32, '여'));
		list.add(new User(4,"이민기", 28, '남'));
		list.add(new User(5,"홍길동", 33, '남'));
		
		// 요청 받은 성별
		char gender = request.getParameter("gender").charAt(0);
		System.out.println("선택한 성별 : " + gender);
		
		// JSONArray : JSONObject를 배열 형태로 나타내어 인덱스별로 나누어 저장할 수 있는 객체
		// 기본 : [ {"k":v, "k":v, ....}, {"k":v, "k":v, ....}, {"k":v, "k":v, ....}  ]
		
		// JSONArray 생성
		JSONArray jArr = new JSONArray();
		
		// 선택한 성별을 가진 회원의 정보를 담아 JSONArray에 추가한 JSONObject 생성
		JSONObject jsonUser = null;

		// for each문
		for(User user : list) {
			if(user.getGender() == gender) {
				jsonUser = new JSONObject();
				jsonUser.put("no", user.getNo());
				jsonUser.put("name", user.getName());
				jsonUser.put("age", user.getAge());
				jsonUser.put("gender", user.getGender()+"");
				
				// JSONObject를 JSONArray에 추가
				jArr.add(jsonUser);
			}
		}
		
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().print(jArr);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}