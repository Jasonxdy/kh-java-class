package com.kh.sjproject.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.sjproject.member.model.vo.Notice;
import com.kh.sjproject.model.service.NoticeService;

// 클라이언트의 요청 중 /notice 디렉토리 하위로 요청 시 해당 서블릿(컨트롤러)으로 집중을 시킴
@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Front Controller 
		// - 클라이언트의 요청을 한 곳으로 집중시켜서  개발 및 유지보수의 효율성을 증가시키는 패턴
		// - 요청에 따른 servlet을 따로 생성하지 않고 오직 하나의 servlet에서 제어함.
		
		String uri = request.getRequestURI();
//		System.out.println(uri);
		
		String contextPath = request.getContextPath();
//		System.out.println("contextPath : " + contextPath);
		
		String command = uri.substring((contextPath+ "/notice").length());
		// --> 결과 = 앞부분 다 떼어내고 /list,  /~~ 이런식으로 들어옴
		
		String path = null;
		RequestDispatcher view =null;
		
		NoticeService noticeService = new NoticeService();
		
		// 요청 주소가 "/list" (공지사항 목록조회)인 경우
		if(command.equals("/list")) {
			try {
//				List로 한 이유 : 일단 다형성 적용시켜서 다른 ArrayList등으로 바꿨을 때 속도 차이를 볼 수 있게함(유지보수 용이)
				List<Notice> list = noticeService.selectList();
				request.setAttribute("list", list);
				// 비어있던 아니던 그대로 전달해서 화면에서 처리함
				
				path = "/WEB-INF/views/notice/noticeList.jsp";
				
				
				
			} catch (Exception e) {
				
				path = "/WEB-INF/views/common/errorPage.jsp";
				request.setAttribute("errorMsg", "공지사항 목록조회 과정에서 오류가 발생하였습니다.");
				e.printStackTrace();
				
			} finally {
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
