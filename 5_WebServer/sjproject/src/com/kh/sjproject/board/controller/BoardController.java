package com.kh.sjproject.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.sjproject.board.model.service.BoardService;
import com.kh.sjproject.board.model.vo.Board;
import com.kh.sjproject.board.model.vo.PageInfo;
import com.kh.sjproject.common.ExceptionForward;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		// -> /sjproject/board/list
		System.out.println(uri);
		
		String contextPath = request.getContextPath();
		System.out.println(contextPath);
		// -> /sjproject
		
		
		String command = uri.substring((contextPath+"/board").length());
		System.out.println(command);
		// -> /list
		
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		BoardService boardService = new BoardService();
		
		
		// 게시글 목록 조회용 Controller
		if(command.equals("/list")) {
			
			
			try {
				// 페이징 처리 (pagination)
				// 눈에 보이는 게시판에 일정 개수의 게시글만 노출되고
				// 나머지는 페이지로 구분하여 숫자 형태로 보여주게 하는 방법
				
				
				// 7개의 숫자가 필요하여 설정 및 vo 작성하여 객체 생성함
				
				// 현재 게시글 전체 수
				int listCount = boardService.getListCount();
				// Service는 순수 자바로 처리해주는 게 좋고 method 하나 당 트랜잭션 하나로 처리해주는 게 좋다!
				// 따라서 request를 넘기지 않는 게 좋음
				
				
				// 사용자 지정칸
				int limit = 5; // 한 페이지에 보여질 게시글의 수
				int pagingBarSize = 10; // 보여질 페이징바의 페이지 개수
				
				
				int currentPage = 0; // 현재 페이지 번호를 표시할 변수
				int maxPage = 0; // 전체 페이지의 수 (== 마지막 페이지)
				int startPage = 0; // 페이징바 시작 페이지 번호
				int endPage = 0; // 페이징바 끝 페이지 번호
				
				
				
				
				// currentPage - 현재 페이지 번호를 표시할 변수
				// 처음 게시판 목록으로 화면이 전환 되면  1페이지가 보여야 함
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
//					전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				
				// maxPage - 총 페이지 수 (== 마지막 페이지)
				// limit == 5일 경우
				// 게시글의 개수가 50개일 경우 필요 페이지 수 : 10 페이지
				// 게시글의 개수가 51개일 경우 필요 페이지 수 : 11 페이지
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				// Math.ceil(number) : 올림
				
				
				
				
				// startPage - 페이징바의 시작 페이지 번호
				// 페이징바에 수가 10개씩 보여질 경우 
				// 시작 번호는 1, 11, 21, 31, ... 
				
				startPage = (currentPage-1) / pagingBarSize * pagingBarSize  + 1;
				
				
				
				
				// endPage - 페이징바 끝페이지 번호
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				System.out.println(pInf);
				
				List<Board> bList = boardService.selectList(currentPage, limit);
				
				path = "/WEB-INF/views/board/boardList.jsp";
				request.setAttribute("bList", bList);
				request.setAttribute("pInf", pInf);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch (Exception e) {
				
				ExceptionForward.errorPage(request, response, "게시글 목록 조회", e);
				
			}
			
			
			
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
