package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.mybatis.board.model.service.BoardService;
import com.kh.mybatis.board.model.vo.Attachment;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.model.vo.PageInfo;
import com.kh.mybatis.common.ExceptionForward;
import com.kh.mybatis.common.MyFileRenamePolicy;
import com.kh.mybatis.common.Pagination;
import com.kh.mybatis.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI(); // /board/list
		String contextPath = request.getContextPath(); // /mybatisProject
		String command = uri.substring((contextPath + "/board").length());

		String path = null;
		String msg = null;
		RequestDispatcher view = null;
		BoardService boardService = new BoardService();

		if (command.equals("/list")) {

			try {
				// ------------- 페이징 처리 -------------

				// 전체 게시글 수 조회
				int listCount = boardService.getListCount();

				// 현재 페이지
				int currentPage = 0;
				if (request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}

				int limit = 5;
				int pagingBarSize = 10;
//				int maxPage = listCount/limit + (listCount%limit == 0? 0 : 1); 

				PageInfo pInf = Pagination.getPageInfo(limit, pagingBarSize, currentPage, listCount);

				List<Board> bList = boardService.selectList(pInf);

				path = "/WEB-INF/views/board/boardList.jsp";

				request.setAttribute("pInf", pInf);
				request.setAttribute("bList", bList);

				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 목록 조회", e);
			}

		}

		// 게시글 작성 화면 출력용 Controller
		else if (command.equals("/insertForm")) {

			path = "/WEB-INF/views/board/boardInsert.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

		// 게시글 등록용 Controller
		else if (command.equals("/insert")) {
			// form 전송 시 multipart/form-data로 전송하는 경우
			// 기존처럼 request.getParameter()를 이용하여 입력값을 전달 받을 수 없음!
			// -> MultipartRequest를 사용해야 함
			// -> cos.jar 라이브러리 추가 필요
			// http://www.servlets.com/

			try {
				// 요청(request)이 multipart/form-data가 포함이 되어있는지 대한 조건문
				if (ServletFileUpload.isMultipartContent(request)) {

					// 1_1. 전송 파일의 용량 제한 : 10MB로 제한
					// B -> KB -> MB -> GB -> TB
					int maxSize = 10 * 1024 * 1024; // 10바이트 -> 10메가바이트

					// 1_2. 웹 서버 컨테이너 경로 추출
					// -> WebContent/ 경로 추출
					String root = request.getSession().getServletContext().getRealPath("/");

					// 1_3. 업로드 된 파일이 저장될 경로 지정
					String savePath = root + "resources/uploadImages/";
					// root : WebContent/까지

					// 2. 파일명 변환 작업
					// 왜? -> logo.jpg를 누가 올렸을때 다른 사람이 logo.jpg를 똑같은 이름으로 올림 -> 덮어씌워짐...
					// 즉, 파일명 중복으로 인한 데이터 손실 예방
					// + 파일명이 한글 또는 특수문자가 포함되면 서버 종류에 따라 문제가 발생할 수 있어서

					// cos.jar -> DefaultFileRenamePolicy 클래스를 제공해주지만
					// 나만의 파일명 규칙을 적용하기 위해서 별도의 MyFileRenamePolicy 작성하러 감

					// 3. MultipartRequest 객체 생성
					// -> 객체가 생성되는 순간에
					// -> request, 파일경로 , 최대 파일 크기 지정 (maxSize)
					// 문자 인코딩 지정
					// -> * 변경된 파일명으로 지정된 경로로 파일이 저장됨.
					MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
							new MyFileRenamePolicy());

					// 4. 서버에 저장된 파일의 원래 파일명, 이전 파일명을 DB에 전달하기 위한 ArrayList 생성

					// 저장된 파일(변경된 파일명)
					ArrayList<String> saveFiles = new ArrayList<String>();

					// 원본 파일명
					ArrayList<String> originFiles = new ArrayList<String>();

					// 전달된 요청(multiRequest)에서 파일 리스트 정보를 얻어와
					// 저장된 파일명, 원래 파일명을 각 ArrayList에 추가함
					Enumeration<String> files = multiRequest.getFileNames();
					// Enumeration : Iterator의 옛날 버전 (Collection에 순차적으로 반복 접근하는 기능)
					// cos.jar이 옛날꺼라서 그런듯

					while (files.hasMoreElements()) { // Iterator의 hasNext() 같은 기능

						// 업로드된 파일은 역순으로 전달됨. (즉, 1,2,3,4 이렇게 올리면 0번 인덱스부터 4,3,2,1 이렇게 저장)
						String name = files.nextElement();

						if (multiRequest.getFilesystemName(name) != null) {
							// getFilesystemName(key) : rename된 파일명 얻어오기
							saveFiles.add(multiRequest.getFilesystemName(name));

							originFiles.add(multiRequest.getOriginalFileName(name));

						}
					}

					// 5. 파일 외에 나머지 게시글 입력값 얻어오기
					String boardTitle = multiRequest.getParameter("title");
					// -> 여기는 그냥 request사용 못하므로...!
					String boardContent = multiRequest.getParameter("content");
					String boardCategory = multiRequest.getParameter("category");
					// 이때 전달되는 값은 String형태의 숫자들 -> 이때 형변환 안해도 Oracle에서 알아서 숫자로 변환되어 저장되므로 parseInt
					// 같은거 안해줘도 됨

					Board board = new Board(boardTitle, boardContent, boardCategory);

					// 회원 번호를 session에서 얻어옴
					// session은 request에서만 얻어올 수 있다.

					Member loginMember = (Member) request.getSession().getAttribute("loginMember");

					int boardWriter = loginMember.getMemberNo();

					// 6. Attachment VO를 생성한 후 Attachment를 저장할 List를 생성하여
					// 파일 경로, 파일 원본명, 변경될 파일명을 세팅
					ArrayList<Attachment> fList = new ArrayList<Attachment>();

					// 파일 정보는 역순으로 전달되므로, 반복문을 역으로 수행하여 원래 순서대로 저장
					for (int i = originFiles.size() - 1; i >= 0; i--) {

						Attachment file = new Attachment();
						file.setFilePath(savePath);
						file.setFileOriginName(originFiles.get(i));
						file.setFileChangeName(saveFiles.get(i));

						// 썸네일 이미지는 fileLevel을 0으로, 나머지 이미지에는 fileLevel을 1 부여
						if ((i == originFiles.size() - 1) && multiRequest.getFilesystemName("img1") != null) {
							// 파일이 역순으로 저장되므로 마지막에 저장된 것이 첫번째인 썸네일 이미지임
							file.setFileLevel(0);
						} else {
							file.setFileLevel(1);
						}

						fList.add(file);
					}

					int result = boardService.insertBoard(board, boardWriter, fList);

					if (result > 0) {
						msg = "게시글 등록 성공";
						path = "detail?no=" + result + "&currentPage=1"; // 게시글 등록 성공시 해당 글 조회페이지로 넘어감
					} else {
						msg = "게시글 등록 실패";
						path = "list";
					}

					request.getSession().setAttribute("msg", msg);

					response.sendRedirect(path);
				}

			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 등록", e);
			}
		}

		
		
		
		// 게시글 상세조회 Controller
		else if (command.equals("/detail")) {

			int boardNo = Integer.parseInt(request.getParameter("no"));

			try {

				Board board = boardService.selectBoard(boardNo);

				if (board != null) {

//					List<Attachment> files = boardService.selectFiles(boardNo);
//
//					if (!files.isEmpty()) {
//						request.setAttribute("files", files);
//					}

					path = "/WEB-INF/views/board/boardDetail.jsp";
					request.setAttribute("board", board);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					request.getSession().setAttribute("msg", "게시글 상세 조회 실패");
					response.sendRedirect("list");
				}

			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 상세 조회", e);
			}

		}
		
		
		
		else if (command.equals("/search")) {
			
			
			try {
				String searchKey = request.getParameter("searchKey");
				String searchValue = request.getParameter("searchValue");
				
				Map<String, String> map = new HashMap<String, String>();
				
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
				
				// 검색된 결과 목록에 대한 페이징 처리 준비
				
				// 검색 결과 전체 페이지 수 조회
				int listCount = new BoardService().getSearchListCount(map);
				
				System.out.println("검색된 개수 : " + listCount);
				
				int currentPage = 0;
				if (request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				int limit = 5;
				int pagingBarSize = 10;

				PageInfo pInf = Pagination.getPageInfo(limit, pagingBarSize, currentPage, listCount);
				
				List<Board> searchList = boardService.selectSearchList(map, pInf);
				
				request.setAttribute("searchList", searchList);
				request.setAttribute("pInf", pInf);
				
				path = "/WEB-INF/views/board/searchList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 검색", e);
				
			}
			
			
			
			
			
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
