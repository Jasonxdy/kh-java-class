package controller;

import java.util.List;

import model.service.BookService;
import model.vo.Board;
import model.vo.Book;
import model.vo.Member;
import view.BookView;

public class BookController {
	
	private BookService bService = new BookService();
	private BookView view = new BookView();
	
	public static Member LoginMember = null;
	
	
	/**
	 * 로그인용 Controller
	 */
	public void login() {
		
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
	
	/**
	 * 책 목록 조회용 Controller
	 */
	public void selectAll() {
		try {
			view.selectAll(bService.selectAll());
		} catch (Exception e) {
			view.displayError("책 목록 조회 과정 중 오류 발생", e);
		}
	}
	
	/**
	 * 책 선택 및 구매용 Controller
	 */
	public void selectBook() {
		
		int bookNo = view.selectBook();

		try {
			Book book = bService.selectBook(bookNo);
			if(book != null) {
				
				char state = ' ';
				
				if(bService.buyState(bookNo) == 1) {

					state = 'Y';
					view.showBook(book, state);
					
				} else {
					
					state = 'N';
					view.showBook(book, state);
					
					if(view.selectBuy(book.getPrice()) == 'Y') {
						
						if(LoginMember.getCash() >= book.getPrice()) {
							
							// 1. 구매 목록에 해당 결과 추가 --> 반환 int
							if(bService.insertBuy(bookNo) > 0) {
								
								// 2. cash 줄임
								if(bService.minusCash(book.getPrice()) > 0) {
									LoginMember = bService.getMember(LoginMember.getMemberId()); 
									
									// 3. 알맞은 view 출력 및 잔액 조회
									view.displaySuccess("구매 완료 하였습니다 (잔액 : " + LoginMember.getCash() + " CASH)");
								}
								
							} else {
								view.displayFail("구매목록 추가 실패");
							}
							
						
						} else {
							view.displayFail("CASH가 부족합니다. CASH 충전 후 다시 시도해주세요.");;
						}
					} 
				}
				
				
			} else {
				view.displayFail("해당 번호의 책이 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			view.displayError("책 선택 과정 중 오류 발생", e);
		}
	}
	
	
	
	
	
	
	/**
	 * 구매한 책 조회 및 내용 읽기용 Controller
	 */
	public void selectBuy() {
		
		try {
			List<Book> bList = bService.selectBuy();
			
			if(!bList.isEmpty()) {
				
				view.selectAll(bList);
				
				int selectMenu = view.selectBuyBook();
				if(selectMenu == 0) return;
				
				int select = view.selectBook();
				Book book = null;
			
				for(Book b : bList) {
					if(select == b.getBookNo()) {
						book = b;
						break;
					}
				}
				if(book == null) {
					view.displayFail("해당 책 번호는 존재하지 않습니다.");
				} else {
					view.displayContent(book);
//					
//					int sel = view.selectBookMenu();
//					switch (sel) {
//					case 1: view.displayContent(book); break;
//					case 2: 
//						// 서평 메뉴 시작
//						view.boardMenu(book);
					}
			} else {
				view.displayFail("구매하신 책이 없습니다.");
			}
		} catch (Exception e) {
			view.displayError("구매 도서 조회 중 오류 발생", e);
	}
	
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 캐시 조회 및 충전용 Controller 
	 */
	public void plusCash() {
		try {
			switch (view.selectCash()) {
			case 1:
				int input = view.inputCash();
				if (view.checkInputCash(input) == 'Y') {
					if (bService.plusCash(input) > 0) {
						LoginMember = bService.getMember(LoginMember.getMemberId());
						view.displaySuccess(input + " CASH를 충전하였습니다. (현재 잔액 : " + LoginMember.getCash() + " CASH)");
					} else {
						view.displayFail("CASH 충전을 실패하였습니다.");
					}

				} else {
					view.displaySuccess("CASH 충전을 취소하였습니다");
					return;
				}
				break;
			case 0:
				return;
			}
		} catch (Exception e) {
			view.displayError("CASH 충전 과정 중 오류 발생", e);
		}
	}

	
	
	
	
	
	
	
	
	
	
	// 서평 메뉴용 Controller
	
	public void selectAllBoard(int bookNo) {
		try {
			List<Board> bList = bService.selectAllBoard(bookNo);
			if(!bList.isEmpty()) {
				view.selectAllBoard(bList);
			} else {
				view.displaySuccess("조회 결과가 없습니다.");
			}
		} catch (Exception e) {
			view.displayError("게시글 목록 조회 과정 중 오류 발생", e);
			e.printStackTrace();
		}
	}

//	public void inputBoard(int bookNo) {
//		Board board = view.insertBoard();
//		
//		try {
//			int result = bService.insertBoard(board);
//			
//			if(result > 0) {
//				view.displaySuccess("게시글이 등록되었습니다.");
//			} else {
//				view.displayFail("게시글이 등록되지 않았습니다.");
//			}
//		} catch (Exception e) {
//			view.displayError("게시글 등록 과정 중 오류 발생", e);
//		}
//	}

}
