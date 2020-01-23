package jquery.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import jquery.model.vo.User;

@WebServlet("/jqTest3.do")
public class JQueryAjaxServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JQueryAjaxServlet3() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 연습용 데이터
		// 회원 정보가 저장되어있는 List 생성
		ArrayList<User> list = new ArrayList<>();
		
		list.add(new User(1,"박철수", 30, '남'));
		list.add(new User(2,"김영희", 26, '여'));
		list.add(new User(3,"오영심", 32, '여'));
		list.add(new User(4,"이민기", 28, '남'));
		list.add(new User(5,"홍길동", 33, '남'));
		
		// 요청 받은 데이터 저장
		int input = Integer.parseInt(request.getParameter("input"));
		
		// JSONObject : JSON 형태의 데이터를 핸들링할 수 있는 객체
		// 내부에 "Key" : Value의 형태로 데이터를 취급함.
		
		JSONObject jsonUser = null;
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getNo() == input) {
				jsonUser = new JSONObject();
				
				jsonUser.put("no", list.get(i).getNo()); // "no":3 이런식으로 키밸류 형태로 저장됨
				jsonUser.put("name", list.get(i).getName());
				jsonUser.put("age", list.get(i).getAge());
				jsonUser.put("gender", list.get(i).getGender()+""); // JSON은 char 형태를 취급하지 못하므로 String 형태로 변환
				
			}
		}
		
		
		// 브라우저로 jsonUser 객체를 전송
		// * json 데이터를 전달하는 방법 (1)
//		response.setContentType("application/json; charset=UTF-8"); // json이라는 Mime-type 설정 (그냥 UTF-8로 작성하는 경우 문자열이 넘어감)
		
		// * json 데이터를 전달하는 방법(2)
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonUser);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
