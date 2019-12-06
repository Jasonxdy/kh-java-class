package view;

import java.util.List;
import java.util.Scanner;

import controller.BookController;
import model.vo.Board;
import model.vo.Book;
import model.vo.Member;

public class BookView {

	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		BookController bController = new BookController();
		int sel = 0;
		
		do {
			if(BookController.LoginMember == null) {

				System.out.println("\n *** E-Book 프로그램 *** \n");
				
				System.out.println("1. 로그인");
				System.out.println("0. 프로그램 종료");
				System.out.print("메뉴 선택 ==> ");
				sel = sc.nextInt();
				sc.nextLine(); 
				
				switch(sel) {
				case 1 : bController.login(); break;
				case 0 : System.out.println("프로그램을 종료합니다."); break;
				default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요."); 
				}
			} else {
				System.out.println();
				System.out.println("1. 로그아웃");
				System.out.println("2. 책 목록 조회");
				System.out.println("3. 책 번호 선택");
				System.out.println("4. My 도서관");
				System.out.println("5. CASH 잔액 조회 및 충전");
				System.out.println("0. 프로그램 종료");
				System.out.print("메뉴 선택 ==> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				case 1 : System.out.println("로그아웃 되었습니다.");
						 BookController.LoginMember = null;
						 break;
				
				// 책 목록 조회
				case 2 : 
					System.out.println();
					bController.selectAll(); break;
				// 책 조회 및 구매
				case 3 :
					System.out.println();
					bController.selectBook(); break;
				// 구매 목록 출력 및 책 읽기
				case 4 :
					System.out.println();
					bController.selectBuy(); break;
				// 캐시 조회 및 충전하기
				case 5 :
					System.out.println();
					bController.plusCash(); break;
				case 0 : System.out.println("프로그램을 종료합니다."); break;
				default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			}
		} while (sel != 0);
	}
	
	
	
	public Member inputLogin() {
		Member loginMember = new Member();
		
		System.out.println("----- 로그인 -----");
		System.out.print("ID : ");
		loginMember.setMemberId(sc.nextLine());
		System.out.print("PW : ");
		loginMember.setMemberPwd(sc.nextLine());
		return loginMember;
	}
	
	public void displayLoginSuccess() {
		System.out.println(BookController.LoginMember.getMemberName() + "님 환영합니다.");
	}

	public void displayLoginFail() {
		System.out.println("로그인 정보를 확인해주세요");
	}
	
	public void displayError(String msg, Exception e) {
		System.out.println("오류 발생 : " + msg);
		e.printStackTrace();
	}
	
	public void displaySuccess(String msg) {
		System.out.println("서비스 요청 성공 : " + msg);
	}
	
	public void displayFail(String msg) {
		System.out.println("서비스 요청 실패 : " + msg);
	}



	public void selectAll(List<Book> bList) {
		System.out.printf("%-5s %-15s %-15s %-15s\n", "책번호", "제목", "작가", "가격");
		System.out.println("------------------------------------------");
		for (Book b : bList) {
			System.out.printf("%-5d %-10s %-10s %-15s\n", b.getBookNo(), b.getTitle(), b.getAuthor(), b.getPrice());
		}
	}



	/**
	 * 책 번호 선택용 View
	 * @return bookNo : int
	 */
	public int selectBook() {
		System.out.print("책 번호 선택 : ");
		int bookNo = sc.nextInt();
		sc.nextLine();
		return bookNo;
	}



	/**
	 * 책 정보 조회용 View
	 * @param book
	 * @param state
	 */
	public void showBook(Book book, char state) {
		
		System.out.println("------------------------------------------");
		System.out.println("책 번호 : " + book.getBookNo());
		System.out.println("제목 : " + book.getTitle() + "\t\t작가 : " +  book.getAuthor());
		System.out.println("가격 : " + book.getPrice()+" CASH" + "\t\t구매 여부 : '" + state+ "'");
		System.out.println("SUMMARY : " + book.getSummary());
		System.out.println("------------------------------------------");
	}



	/**
	 * 책 구매 View
	 * @return select : char
	 */
	public char selectBuy(int price) {
		
		while(true) {
			System.out.println("현재 캐시 보유량 : " + BookController.LoginMember.getCash());
			System.out.print(price+ " CASH를 사용하여 해당 책을 구매하시겠습니까? (Y / N) : ");
			char select = sc.nextLine().toUpperCase().charAt(0);
			
			if (select == 'Y' || select == 'N') {
				return select;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
		} 
	}



	/**
	 * CASH 보유량 조회 및 충전 선택용 View
	 * @return
	 */
	public int selectCash() {
		System.out.println("------------------------------------------");
		System.out.println(BookController.LoginMember.getMemberName() + "님의 현재 CASH 보유량   :  " + BookController.LoginMember.getCash());
		System.out.println("------------------------------------------");
		System.out.println("1. 충전하기");
		System.out.println("2. 메인으로 돌아가기");
		System.out.print("메뉴 선택 >> ");
		int select = sc.nextInt();
		sc.nextLine();
		
		return select;
	}



	public int inputCash() {
		
		int input = 0;
		
		while(true) {
			System.out.print("충전할 CASH를 입력해주세요 : ");
			input = sc.nextInt();
			sc.nextLine();
			
			if(input > 0) {
				break;
			} else {
				System.out.println("충전할 CASH는 0보다 커야 합니다.");
			}
		}
		return input;

	}



	public char checkInputCash(int input) {
		System.out.print(input + " CASH를 충전하시겠습니까? (Y / N) : ");
		return sc.nextLine().toUpperCase().charAt(0);
	}



	public void displayContent(Book book) {
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("책 번호: " + book.getBookNo());
		System.out.println("제목    : " + book.getTitle());
		System.out.println("작가    : " + book.getAuthor());
		System.out.println("----------------  내용   ----------------");
		System.out.println(book.getContent());
	}



	public int selectBuyBook() {
		System.out.println();
		System.out.println("1. 구매한 책 읽기 ");
		System.out.println("0. 메인 메뉴로 돌아가기");
		System.out.print("메뉴 선택 >> ");
		int selectBook = sc.nextInt();
		sc.nextLine();
		return selectBook;
	}


	
	
	
	

	/**
	 * 서평 작성용 메뉴
	 */
	public void boardMenu(Book book) {
		BookController bController = new BookController();
		int select = 0;
		
		do {
			System.out.println("=== 서평 메뉴 ===");
			System.out.println("1. 서평 목록 조회");
			System.out.println("2. 서평 작성");
			System.out.println("3. 서평 삭제");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("메뉴 선택 ==> ");
			select = sc.nextInt();
			sc.nextLine();

			switch(select) {
			case 1: bController.selectAllBoard(book.getBookNo()); break;
//			case 2: bController.inputBoard(book.getBookNo()); break;
//			case 3: bController.deleteBoard(book.getBookNo()); break;
			case 0 : break;
			default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
			
		} while (select != 0);
	}
	
	
	
	
	
	
	
	// 서평 메뉴용 View
	
	// 조회
	public void selectAllBoard(List<Board> bList) {
		System.out.printf("%-3s %-15s %-10s %-15s\n",
							"BNO", "TITLE", "WRITER", "CREATE_DATE");
		System.out.println("------------------------------------------");
		for(Board b : bList) {
			System.out.printf("%-3d %-15s %-10s %-15s\n",
						b.getbNo(), b.getTitle(), b.getWriter(), b.getCreateDate());
		}
	}



	public int selectBookMenu() {
		int sel = 0;
		do {
			System.out.println("1. 책 읽기");
			System.out.println("2. 서평 메뉴");
			System.out.println("0. 나가기");
			System.out.print("메뉴 선택 >> ");
			sel = sc.nextInt();
			sc.nextLine();
		} while (sel != 0);
		return sel;
	}



	public Board insertBoard() {
		System.out.println("\n 게시글 작성 \n");
		System.out.print("글 제목 : ");
		String title = sc.nextLine();
		
		StringBuffer content = new StringBuffer();
		StringBuffer input = new StringBuffer();
		
		
		System.out.println(" ------ 내용 입력 (종료시 exit 입력) ------ ");
		while(true) {
			input.delete(0, input.capacity()); // -- 0~길이-1까지 비워냄
			input.append(sc.nextLine()); // -- Scanner에서 입력받아서 input에 저장
			if(input.toString().equals("exit")) break; //-- StringBuffer는 String이 아니므로 일단 toString으로 String 만들어주고 .equals()사용
			
			content.append(input);
			content.append("\n");
		}
		return new Board(title, content.toString());
	}
	
}



