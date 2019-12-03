package view;

import java.util.Scanner;

import controller.MemberController;
import model.vo.Member;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
			
			// MemberController 객체 생성
			MemberController mController = new MemberController();
			
			// 메뉴 선택값 저장용 변수 선언
			int sel = 0;
			
			do {
				System.out.println("\n *** 회원 관리 프로그램 *** \n");
				System.out.println("1. 새 회원 등록");
				System.out.println("2. 모든 회원 정보 조회");
				System.out.println("3. 특정 조건 회원 정보 조회");
				System.out.println("4. 회원 정보 수정");
				System.out.println("5. 회원 탈퇴");
				System.out.println("0. 프로그램 종료");
				System.out.println();
				System.out.print("메뉴 선택 >> ");
				sel = sc.nextInt();
				sc.nextLine(); // Scanner 버퍼에 남아있는 개행 문자 제거
				
				switch(sel) {
				case 1 : mController.insertMember(); break;
				case 2 : break;
				case 3 : break;
				case 4 : break;
				case 5 : break;
				case 0 : System.out.println("프로그램을 종료합니다."); break;
				default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
				}
			} while(sel != 0);
		}
	
	
	/** 회원 정보 입력용 View
	 * @return member : Member
	 */
	public Member insertMember() {
		
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String memberPwd = sc.nextLine();
		
		System.out.print("이름 : ");
		String memberName = sc.nextLine();
		
		System.out.print("성별(M/F) : ");
		char gender = sc.nextLine().toUpperCase().charAt(0);
		
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.print("전화번호(-포함) : ");
		String phone = sc.nextLine();
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine(); // 개행문자 제거
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		return new Member(memberId, memberPwd, memberName, gender, email, phone, address, age);
	}
	
	
	public void displaySuccess(String msg) {
		System.out.println("서비스 요청 성공 : " +msg);
	}
	
	public void displayFail(String msg) {
		System.out.println("서비스 요청 실패 : " +msg);
	}
	
	public void displayError(String msg, Exception e) {
		System.out.println("서비스 요청 중 오류 발생 : " + msg);
		e.printStackTrace();
	}
	
	
}
