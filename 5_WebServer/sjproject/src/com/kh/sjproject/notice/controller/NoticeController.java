package com.kh.sjproject.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.sjproject.common.ExceptionForward;
import com.kh.sjproject.member.model.vo.Member;
import com.kh.sjproject.notice.model.service.NoticeService;
import com.kh.sjproject.notice.model.vo.Notice;

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
		String msg = null;
		
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
		
		
		
		
		// 공지사항 상세조회 Controller
		else if(command.equals("/detail")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			try {
				Notice notice = noticeService.selectNotice(no);
				
				if(notice != null) { // 상세조회 성공 시
					path = "/WEB-INF/views/notice/noticeDetail.jsp";
					request.setAttribute("notice", notice);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} else { // 상세조회 실패 시
					request.getSession().setAttribute("msg", "공지사항 상세 조회 실패");
					response.sendRedirect("list"); // 공지사항 목록으로 돌아가기
				}
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "공지사항 상세 조회", e);
				// errorPage로 넘기는 클래스를 common폴더의 static으로 선언해서 이용해줌
				
			}
			
		}
		
		
		
		// 공지사항 등록 화면 Controller
		else if (command.equals("/insertForm")) {
			path = "/WEB-INF/views/notice/noticeInsert.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		
		
		// 공지사항 등록 Controller
		else if (command.equals("/insert")) {
			
			// 글제목, 글내용, 작성자
			
			// 작성자 얻어오기
			HttpSession session = request.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			String noticeWriter = loginMember.getMemberId();
			
			// 글제목 얻어오기
			String noticeTitle = request.getParameter("title");
			// 글제목 얻어오기
			String noticeContent = request.getParameter("content");
			
			Notice notice = new Notice(noticeTitle, noticeContent, noticeWriter);
			
			try {
				// 1) 입력된 notice를 DB에 저장
//				int[] result = new int[2];
				
				int result = new NoticeService().insertNotice(notice);
				
				// 2) DB 저장 결과에 따라 트랜잭션 처리 
				// ->service에서 완료
				
				// 3) DB 저장 성공 시 : "공지사항이 등록되었습니다"
				// 		-> 작성한 공지글 상세조회 페이지로 redirect
				
				if(result >0) {
					
					msg = "공지사항이 등록되었습니다.";
					path = "detail?no=" + result;
				}
				
				// 4) DB 저장 실패 시 : "공지사항 등록 실패"
				//		-> 공지사항 목록 조회로 redirect
				else {
					msg = "공지사항 등록 실패";
					path ="list"; // 공지사항 목록으로 돌아가기
				}
				
				session.setAttribute("msg", msg);
				response.sendRedirect(path);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "공지사항 등록", e);
				
			}
		}
		
		
		
		
		// 공지사항 수정 화면 Controller
		else if(command.equals("/updateForm")) {
			
			// DB에서 해당 글을 조회하여 화면으로 전달
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			try {
				Notice notice = noticeService.updateForm(no);
				
				if(notice != null) {
					path = "/WEB-INF/views/notice/noticeUpdate.jsp";
					request.setAttribute("notice", notice);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				} else {
					request.getSession().setAttribute("msg", "공지사항 수정 화면으로 전환하는 ");
				}
				 
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "공지사항 수정 화면 출력", e);
			}
		}
		
		
		
		
		// 공지사항 수정 Controller
		else if (command.equals("/update")) {
			int noticeNo = Integer.parseInt(request.getParameter("no"));
			String noticeTitle = request.getParameter("title");
			String noticeContent = request.getParameter("content");
			
			Notice notice = new Notice(noticeNo, noticeTitle, noticeContent);
			
			try {
				int result = noticeService.updateNotice(notice);
				
				if(result > 0) msg = "공지사항이 수정되었습니다.";
				else			msg = "공지사항 수정 실패";
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect("detail?no=" + noticeNo);
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "공지사항 수정", e);
			}
		
		}
		
		
		
		
		//공지사항 삭제 Controller
		else if (command.equals("/delete")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			try {
				int result = noticeService.deleteNotice(no);
				
				if(result > 0) {
					msg = "공지사항이 삭제되었습니다.";
					path = "list";
				}
				else {
					msg = "공지사항 삭제 실패";
					path = "detail?no=" + no;
				}
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
			} catch (Exception e) {
				
				ExceptionForward.errorPage(request, response, "공지사항 삭제", e);
			}
		}
		
		
		// 공지사항 검색용 Controller
		else if(command.equals("/search")) {
			
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			
			try {
				List<Notice> list = noticeService.searchNotice(searchKey, searchValue);
				
				path = "/WEB-INF/views/notice/noticeList.jsp";
				
				request.setAttribute("list", list);
				view = request.getRequestDispatcher(path);
				
				view.forward(request, response);
				// /list로 돌아가서 해당 request, response 사용 가능 (자세한건 noticeList.jsp의 스크립틀릿 확인할 것)
				
			} catch (Exception e) {
				
				ExceptionForward.errorPage(request, response, "공지사항 검색", e);
			}
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
