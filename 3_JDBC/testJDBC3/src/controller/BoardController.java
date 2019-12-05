package controller;


import model.service.BoardService;
import model.vo.Board;
import model.vo.Member;
import view.BoardView;
import java.util.List;

/**
 * 게시판 프로그램 Controller
 * @author Jsn
 */
public class BoardController {
	
	private BoardService bService = new BoardService();
	private BoardView view = new BoardView();
	
	// 로그인 정보를 유지할 Member 참조 변수 선언 (나중에 배울 Session의 역할)
	public static Member LoginMember = null;
	
	
	/**
	 * 회원 로그인
	 */
	public void login() {
		
		// id, pwd 입력 -- Member객체를 이용해서 가지고 오면 될듯
		Member inputLogin = view.inputLogin();
		try {
			LoginMember = bService.login(inputLogin);
			if(LoginMember != null) {
				view.displayLoginSuccess();
			} else {
				view.displayLoginFail();
			}
			
		} catch (Exception e) {
			view.displayError("로그인 과정 중 오류 발생", e);
		}
	}
	
	
	public void selectAll() {
		
		try {
			List<Board> bList = bService.selectAll();
			if(!bList.isEmpty()) {
				view.selectAll(bList);
			} else {
				view.displaySuccess("조회 결과가 없습니다.");
			}
		} catch (Exception e) {
			view.displayError("게시글 목록 조회 과정 중 오류 발생", e);
			e.printStackTrace();
		}
	}
	
	
	public void selectBoard() {
		int select = view.selectBoard();
		try {
			Board board = bService.selectBoard(select);
			if(board != null) {
				view.displaySelectBoard(board);
			} else {
				view.displayFail("해당 글이 존재하지 않습니다.");
			}
		} catch (Exception e) {
			view.displayError("게시글 조회 과정 중 오류 발생", e);
			e.printStackTrace();
		}
	}
	
	
	
	
	public void insertBoard() {
		
		// 글 입력용 View
		Board board = view.insertBoard();
		
		try {
			int result = bService.insertBoard(board);
			
			if(result > 0) {
				view.displaySuccess("게시글이 등록되었습니다.");
			} else {
				view.displayFail("게시글이 등록되지 않았습니다.");
			}
		} catch (Exception e) {
			view.displayError("게시글 등록 과정 중 오류 발생", e);
		}
	}
	
	
	
	
	
	/* 수정하기
	 * 1) 수정할 글 번호 입력
	 * 2) 수정할 글의 작성자와 로그인 아이디가 같은지 검사
	 * 3) 같을 경우 제목, 내용 수정 선택
	 * 4) 수정 내용 DB에 반영
	 */
	
	
	// 내 풀이
//	public void updateBoard() {
//		int bNo = view.selectbNo();
//		
//		// bNo에 대한 writer의 정보를 Service로 전달 후 해당 번호의 Board 객체 전달받음
//		try {
//			String writer = bService.selectWriter(bNo);
//			int select = 0;
//			if(writer != null) {
//				if(writer.equals(LoginMember.getMemberId())) {
//					do {
//						select = view.updateBoard();
////						if(select == 0) view.displaySuccess("메인 메뉴로 돌아갑니다."); return;
//						
//						switch(select) {
//						case 1 : 
//							String title = view.updateTitle();
//							int resultTitle = bService.updateTitle(bNo, title);
//							if(resultTitle > 0) view.displaySuccess("제목을 수정하였습니다.");
//							else				view.displayFail("제목이 수정되지 않았습니다.");
//							return;
//						case 2 :
//							String content = view.updateContent();
//							int resultContent = bService.updateContent(bNo, content);
//							if(resultContent > 0) view.displaySuccess("게시글 내용을 수정하였습니다.");
//							else				  view.displayFail("게시글 내용이 수정되지 않았습니다.");
//							return;
//						case 0 :
//							view.displaySuccess("메인 메뉴로 돌아갑니다."); return;
//						default : view.displayFail("잘못 입력하셨습니다. 다시 입력해주세요.");
//						}
//					} while (select != 0);
//				} else {
//					view.displayFail("해당 글의 작성자가 아닙니다.");
//				}
//			} else {
//				view.displayFail("해당 글이 존재하지 않습니다.");
//			}
//		} catch (Exception e) {
//			view.displayError("게시글 수정 과정 중 오류 발생", e);
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	public void updateBoard() {
		int bNo = view.selectbNo();
		
		// bNo에 대한 writer의 정보를 Service로 전달 후 해당 번호의 Board 객체 전달받음
		try {
			String writer = bService.selectWriter(bNo);
			int select = 0;
			if(writer != null) {
				if(writer.equals(LoginMember.getMemberId())) {

					String updateString = null;
					String updateQuery = null;
					
					while(true) {
						select = view.updateBoard();
//						if(select == 0) view.displaySuccess("메인 메뉴로 돌아갑니다."); return;

						
						switch(select) {
						case 1 : 
							updateString = view.updateTitle();
							updateQuery = "Title";
							break;
						case 2 :
							updateString = view.updateContent();
							updateQuery = "Content";
							break;
						case 0 :
							view.displaySuccess("메인 메뉴로 돌아갑니다."); return;
						default : view.displayFail("잘못 입력하셨습니다. 다시 입력해주세요."); continue;
						}
						
						int result = bService.updateBoard(bNo, updateString, updateQuery);
						if(result > 0) 		  view.displaySuccess("내용을 수정하였습니다.");
						else				  view.displayFail("내용이 수정되지 않았습니다.");
						return;
					}
				} else {
					view.displayFail("해당 글의 작성자가 아닙니다.");
				}
			} else {
				view.displayFail("해당 글이 존재하지 않습니다.");
			}
		} catch (Exception e) {
			view.displayError("게시글 수정 과정 중 오류 발생", e);
		}
	}
















}
