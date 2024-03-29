package com.kh.semiproject.seeBoard.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semiproject.board.model.service.BoardService;
import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.board.model.vo.Attachment;
import com.kh.semiproject.board.model.vo.BoardHJ;
import com.kh.semiproject.board.model.vo.PageInfo;
import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.common.MyFileRenamePolicy;
import com.kh.semiproject.findBoard.model.service.FindBoardService;
import com.kh.semiproject.findBoard.model.vo.FindBoard;
import com.kh.semiproject.member.model.service.MemberService;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.seeBoard.model.service.SeeBoardService;
import com.kh.semiproject.seeBoard.model.vo.SeeBoard;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/seeBoard/*")
public class seeBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public seeBoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/seeBoard").length());
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		BoardService boardService = new BoardService();
		SeeBoardService seeBoardService = new SeeBoardService();
		MemberService memberService = new MemberService();
		
		if(command.equals("/boardList")) {
			try {
				int boardType = 2;
				int listCount = boardService.getListCount(boardType);
				
				int limit = 1;
				int pagingBarSize = 10;
				
				int currentPage = 0;	
				int maxPage = 0;		
				int startPage = 0;		
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( ( (double)listCount / limit ) );
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize +1;
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInfo = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				List<BoardHJ> bList = boardService.selectFList(currentPage, limit, boardType);
				
				List<Attachment> aList = boardService.selectAList(currentPage, limit, boardType);
				
				List<Animal> animalList = boardService.selectAnimalList(currentPage, limit, boardType);
				
				List<SeeBoard> sList = seeBoardService.selectSeeList(currentPage, limit, boardType);
				
				path = "/WEB-INF/views/seeBoard/seeBoardList.jsp";
				request.setAttribute("pInf", pInfo);
				request.setAttribute("bList", bList);
				request.setAttribute("aList", aList);
				request.setAttribute("animalList", animalList);
				request.setAttribute("sList", sList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "봤어요 게시판 조회", e);
			}
		}
		
		else if(command.equals("/insertForm")) {
			path = "/WEB-INF/views/seeBoard/seeBoardInsert.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		else if(command.equals("/insert")) {
			try {
				if(ServletFileUpload.isMultipartContent(request)) {
					int maxSize = 10 * 1024 * 1024;
					
					String root = request.getSession().getServletContext().getRealPath("/");
				
					String savePath =  root + "resources/uploadImages/";
					
					MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
					ArrayList<String> saveFiles = new ArrayList<String>();
					
					ArrayList<String> originFiles = new ArrayList<String>();
					
					Enumeration<String> files = multiRequest.getFileNames();
					
					while(files.hasMoreElements()) {
						
						// 업로드된 파일은 역순으로 전달됨
						String name = files.nextElement();
						
						if(multiRequest.getFilesystemName(name) != null) {
							// getFilesystemName(key) : rename된 파일명 얻어오기
							saveFiles.add(multiRequest.getFilesystemName(name));
							
							originFiles.add(multiRequest.getOriginalFileName(name));
						}
					}
					
					Member loginMember = (Member)request.getSession().getAttribute("loginMember");
					
					String boardTitle = multiRequest.getParameter("title");
					String boardContent = multiRequest.getParameter("content");
					String boardURL = multiRequest.getParameter("videoURL");
					String boardWriter = loginMember.getMemberId();
					int boardCode = 2;
					
					BoardHJ board = new BoardHJ(boardTitle, boardContent, boardURL, boardWriter, boardCode);
					
					String sBoardLocation = multiRequest.getParameter("place1")
											+ "," + multiRequest.getParameter("place2")
											+ "," + multiRequest.getParameter("place3");
					String sBoardPhone = multiRequest.getParameter("phone1")
										+ "-" + multiRequest.getParameter("phone2")
										+ "-" + multiRequest.getParameter("phone3");
					String sBoardDate = multiRequest.getParameter("missingDate");
					
					String temp[] = sBoardDate.split("-");
					
					Date date = new Date(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2]));
					
					String sBoardMap = multiRequest.getParameter("spot");
					
					String sBoardLocationTell = multiRequest.getParameter("locationTell");
					String sBoardBreedTell = multiRequest.getParameter("breedTell");
					String sBoardCommentTell = multiRequest.getParameter("commentTell");
					
					SeeBoard seeBoard = new SeeBoard(sBoardLocation, sBoardPhone, date, sBoardMap, sBoardLocationTell, sBoardBreedTell, sBoardCommentTell);

					String animalGender = multiRequest.getParameter("gender");
					String animalType = multiRequest.getParameter("breed1");
					String animalBreed = multiRequest.getParameter("breed2");
					
					Animal animal = new Animal(animalGender, animalType, animalBreed);
					
					
					ArrayList<Attachment> fList = new ArrayList<Attachment>();
					
					for(int i = originFiles.size()-1 ; i >=0 ; i--) {
						Attachment file = new Attachment();
						file.setFilePath(savePath);
						file.setFileOriginName(originFiles.get(i));
						file.setFileChangeName(saveFiles.get(i));
						
						// 썸네일 이미지는 fileLevel 0으로 나머지 이미지에는 fileLevel 1 부여
						if( (i == originFiles.size()-1) && multiRequest.getFilesystemName("img1") != null) {
							file.setFileLevel(0);
						} else {
							file.setFileLevel(1);
						}
						
						fList.add(file);
					}
					
					int result = SeeBoardService.insertSeeBoard(board,seeBoard,animal,fList);
					
					if(result>0) {
						result = new SeeBoardService().sendSeeAlram(board.getBoardTitle(), seeBoard.getsBoardLocation(), animal.getAnimalBreed());
						msg = "게시글 등록 성공";
					}
					else		 msg = "게시글 등록 실패";
					
					request.getSession().setAttribute("msg", msg);
					response.sendRedirect("boardList");
				}
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 등록", e);
			}
			
		}
		else if(command.contentEquals("/detail")) {
			int boardNo = Integer.parseInt(request.getParameter("no"));
			
			try {
				BoardHJ board = boardService.selectBoard(boardNo);
				
				if(board != null) {
					List<Attachment> files = boardService.selectFiles(boardNo);
					
					SeeBoard seeBoard = seeBoardService.selectSeeBoard(boardNo);
					
					int animalCode = seeBoard.getAnimalCode();
					
					Animal animal = boardService.selectAnimal(animalCode);
					
					String memberId = board.getBoardWriter();
					
					Member member = memberService.selectMember(memberId);
					
					if(!files.isEmpty()) {
						request.setAttribute("files", files);
					}
					
					request.setAttribute("seeBoard", seeBoard);
					request.setAttribute("animal", animal);
					request.setAttribute("board", board);
					request.setAttribute("member", member);
					
					path = "/WEB-INF/views/seeBoard/seeBoardDetail.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					request.getSession().setAttribute("msg", "게시글 상세 조회 실패");
					response.sendRedirect("list");
				}
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 등록", e);
			}
		}
		
		else if(command.equals("/updateForm")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			try {
				BoardHJ board = boardService.updateForm(no);
				
				if(board != null) {
					List<Attachment> files = boardService.selectFiles(no);
					
					SeeBoard seeBoard = seeBoardService.selectSeeBoard(no);
					
					int animalCode = seeBoard.getAnimalCode();
					
					Animal animal = boardService.selectAnimal(animalCode);
					
					String memberId = board.getBoardWriter();
					
					Member member = memberService.selectMember(memberId);
					
					if(!files.isEmpty()) {
						request.setAttribute("files", files);
					}
					request.setAttribute("seeBoard", seeBoard);
					request.setAttribute("animal", animal);
					request.setAttribute("board", board);
					request.setAttribute("member", member);
					
					path = "/WEB-INF/views/seeBoard/seeBoardUpdate.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					request.getSession().setAttribute("msg", "게시판 수정 화면 출력");
				}
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 등록", e);
			}
		}
		
		else if(command.contentEquals("/update")) {
			try {
				if(ServletFileUpload.isMultipartContent(request)) {
					int maxSize = 10 * 1024 * 1024;
					
					String root = request.getSession().getServletContext().getRealPath("/");
				
					String savePath =  root + "resources/uploadImages/";
					
					MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
					ArrayList<String> saveFiles = new ArrayList<String>();
					
					ArrayList<String> originFiles = new ArrayList<String>();
					
					Enumeration<String> files = multiRequest.getFileNames();
					
					while(files.hasMoreElements()) {
						
						// 업로드된 파일은 역순으로 전달됨
						String name = files.nextElement();
						
						if(multiRequest.getFilesystemName(name) != null) {
							// getFilesystemName(key) : rename된 파일명 얻어오기
							saveFiles.add(multiRequest.getFilesystemName(name));
							
							originFiles.add(multiRequest.getOriginalFileName(name));
						}
					}
					
					int boardNo = Integer.parseInt(request.getParameter("no"));
					String boardTitle = multiRequest.getParameter("title");
					String boardContent = multiRequest.getParameter("content");
					String boardURL = multiRequest.getParameter("videoURL");
					
					BoardHJ board = new BoardHJ(boardNo, boardTitle, boardContent, boardURL);
					
					String sBoardLocation = multiRequest.getParameter("place1")
							+ "," + multiRequest.getParameter("place2")
							+ "," + multiRequest.getParameter("place3");
					String sBoardPhone = multiRequest.getParameter("phone1")
										+ "-" + multiRequest.getParameter("phone2")
										+ "-" + multiRequest.getParameter("phone3");
					String sBoardDate = multiRequest.getParameter("missingDate");
					
					String temp[] = sBoardDate.split("-");
					
					Date date = new Date(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2]));
					
					String sBoardMap = multiRequest.getParameter("spot");
					
					String sBoardLocationTell = multiRequest.getParameter("locationTell");
					String sBoardBreedTell = multiRequest.getParameter("breedTell");
					String sBoardCommentTell = multiRequest.getParameter("commentTell");
					
					SeeBoard seeBoard = new SeeBoard(sBoardLocation, sBoardPhone, date, sBoardMap, sBoardLocationTell, sBoardBreedTell, sBoardCommentTell);
					
					String animalGender = multiRequest.getParameter("gender");
					String animalType = multiRequest.getParameter("breed1");
					String animalBreed = multiRequest.getParameter("breed2");
					
					Animal animal = new Animal(animalGender, animalType, animalBreed);
					
					ArrayList<Attachment> fList = new ArrayList<Attachment>();
					
					for(int i = originFiles.size()-1 ; i >=0 ; i--) {
						Attachment file = new Attachment();
						file.setFilePath(savePath);
						file.setFileOriginName(originFiles.get(i));
						file.setFileChangeName(saveFiles.get(i));
						
						// 썸네일 이미지는 fileLevel 0으로 나머지 이미지에는 fileLevel 1 부여
						if( (i == originFiles.size()-1) && multiRequest.getFilesystemName("img1") != null) {
							file.setFileLevel(0);
						} else {
							file.setFileLevel(1);
						}
						
						fList.add(file);
					}
					
					int result = SeeBoardService.updateSeeBoard(board,seeBoard,animal,fList);
					
					if(result>0) {
						msg = "게시글 수정 성공";
						result = new SeeBoardService().sendSeeAlram(board.getBoardTitle(), seeBoard.getsBoardLocation(), animal.getAnimalBreed());
					}
					else		 msg = "게시글 수정 실패";
					
					request.getSession().setAttribute("msg", msg);
					response.sendRedirect("boardList");
				}
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 수정", e);
			}
		}
		
		else if(command.equals("/delete")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			try {
				int result = seeBoardService.deleteSeeBoard(no);
				
				if(result>0) {
					msg = "게시글이 삭제되었습니다.";
					path = "boardList";
				}
				else {
					msg = "게시글 삭제 실패";
					path = "detail?no="+no;
				}
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 수정", e);
			}
		}
		
		else if(command.contentEquals("/searchList")) {
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");

			String condition = null;
			
			searchValue = "'%' || '" + searchValue + "' || '%' ";
			
			switch(searchKey) {
			case "title": condition =  " BOARD_TITLE LIKE " + searchValue; break;
			case "content": condition =  " BOARD_CONTENT LIKE " + searchValue; break;
			case "titcont": condition =  " (BOARD_CONTENT LIKE" + searchValue + " OR BOARD_TITLE LIKE " + searchValue +")"; break;
			case "writer" : condition = " MEM_NAME LIKE " + searchValue; break;
			}
			try {
				int boardType = 2;
				int listCount = boardService.getSearchListCount(condition, boardType);
				
				int limit = 1;
				int pagingBarSize = 10;
				
				int currentPage = 0;	
				int maxPage = 0;		
				int startPage = 0;		
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				int startRow = (currentPage -1) * limit + 1;
				int endRow = startRow + limit -1;
				
				maxPage = (int)Math.ceil( ( (double)listCount / limit ) );
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize +1;
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInfo = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				List<BoardHJ> bList = boardService.searchBoardList(startRow, endRow, boardType, condition);
				
				List<Attachment> aList = boardService.searchAList(startRow, endRow, boardType, condition);
				
				List<Animal> animalList = boardService.searchAnimalList(startRow, endRow, boardType, condition);
				
				List<SeeBoard> sList = seeBoardService.searchSeeList(startRow, endRow, boardType, condition);
				
				System.out.println(bList);
				System.out.println(aList);
				
				path = "/WEB-INF/views/seeBoard/seeBoardSearchList.jsp";
				request.setAttribute("pInf", pInfo);
				request.setAttribute("bList", bList);
				request.setAttribute("aList", aList);
				request.setAttribute("animalList", animalList);
				request.setAttribute("sList", sList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 검색", e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
