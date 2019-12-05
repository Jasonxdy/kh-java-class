package view;

import java.util.List;
import java.util.Scanner;

import controller.BoardController;
import model.vo.Board;
import model.vo.Member;

/**
 * 게시판 프로그램 View
 * @author Jsn
 */
public class BoardView {
	
	private Scanner sc = new Scanner(System.in);
	
	
	
	/**
	 * 게시판 프로그램 메인 메뉴
	 */
	public void mainMenu() {
		BoardController bController = new BoardController();
		int sel = 0;
		
		do {
			if(BoardController.LoginMember == null) {
				// 프로그램 시작 시 로그인 체크
				System.out.println("\n *** 게시판 프로그램 *** \n");
				
				System.out.println("1. 로그인");
				System.out.println("0. 프로그램 종료");
				System.out.print("메뉴 선택 ==> ");
				sel = sc.nextInt();
				sc.nextLine(); // 개행문자 제거
				
				switch(sel) {
				case 1 : bController.login(); break;
				case 0 : System.out.println("프로그램을 종료합니다."); break;
				default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요."); 
				}
			} else {
				System.out.println("1. 로그아웃");
				System.out.println("2. 글 목록 조회");
				System.out.println("3. 게시글 조회 (글번호)");
				System.out.println("4. 글 쓰기");
				System.out.println("5. 글 수정");
				System.out.println("6. 글 삭제");
				System.out.println("0. 프로그램 종료");
				System.out.print("메뉴 선택 ==> ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch(sel) {
				// 로그아웃
				case 1 : System.out.println("로그아웃 되었습니다.");
						 BoardController.LoginMember = null;
						 break;
						 
				// 글 목록 조회
				case 2 : bController.selectAll(); break;
				
				// 게시글 조회
				case 3 : bController.selectBoard(); break;
				
				// 글 쓰기
				case 4 : bController.insertBoard(); break;
				
				// 제목, 내용 수정
				case 5 : bController.updateBoard(); break;
				case 6 : break;
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
		System.out.println(BoardController.LoginMember.getMemberName() + "님 환영합니다.");
	}





	public void displayLoginFail() {
		System.out.println("로그인 정보를 확인해주세요");
	}





	public void displayError(String msg, Exception e) {
		System.out.println("오류 발생 : " + msg);
		e.printStackTrace();
	}





	public void selectAll(List<Board> bList) {
		System.out.printf("%-3s %-15s %-10s %-15s\n",
							"BNO", "TITLE", "WRITER", "CREATE_DATE");
		System.out.println("------------------------------------------");
		for(Board b : bList) {
			System.out.printf("%-3d %-15s %-10s %-15s\n",
						b.getbNo(), b.getTitle(), b.getWriter(), b.getCreateDate());
		}
	}





	public void displaySuccess(String msg) {
		System.out.println("서비스 요청 성공 : " + msg);
	}





	public int selectBoard() {
		System.out.print("글 번호 입력 : ");
		int select = sc.nextInt();
		sc.nextLine();
		return select;
	}





	public void displaySelectBoard(Board board) {
		System.out.println("------------------------------------------");
		System.out.println("글번호 : " + board.getbNo());
		System.out.println("제목 : " + board.getTitle());
		System.out.println("작성자 : " + board.getWriter() + "\t작성일 : " + board.getCreateDate());
		System.out.println("------------------------------------------");
		System.out.println(board.getContent());
		System.out.println("------------------------------------------");
		System.out.println();
	}





	public void displayFail(String msg) {
		System.out.println("서비스 요청 실패 : " + msg);
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





	public int selectbNo() {
		System.out.print("수정할 게시글 번호 : ");
		int bNo = sc.nextInt();
		sc.nextLine();
		return bNo;
	}





	public int updateBoard() {
		System.out.println("수정할 항목을 선택해주세요\n"
				+ "1. 게시글 제목\n"
				+ "2. 게시글 내용\n"
				+ "0. 메인 메뉴로 돌아가기");
		System.out.print("메뉴 선택 ==> ");
		int select = sc.nextInt();
		sc.nextLine();
		return select;
	}





	public String updateTitle() {
		System.out.println("게시글 제목을 수정합니다.");
		System.out.print("수정할 제목을 입력해주세요 : ");
		return sc.nextLine();
	}





	public String updateContent() {
		System.out.println("게시글 내용을 수정합니다.");
		System.out.print("수정할 내용을 입력해주세요 : ");
		return sc.nextLine();
	}
	
	
	
	
	
	
	
	
	
	
	
}
