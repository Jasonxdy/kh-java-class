package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.EmpController;
import model.vo.EMP;

public class EmpView {
	
	// View 전체에서 공용으로 사용될 Scanner 객체 생성
	private Scanner sc = new Scanner(System.in);
	
	
	// 메인 메뉴
	public void mainMenu() {
		
		// 클라이언트 요청을 DAO에 알맞은 메소드로 전달하고 DB 
		// 처리 결과를 반환받는 역할을 할 Controller 객체 생성
		EmpController controller = new EmpController();
		
		int select = 0; // 메뉴 선택을 위한 변수
		
		do {
			System.out.println("=================================");
			System.out.println("[Main Menu]");
			System.out.println("1. 전체 사원 정보 조회");
			System.out.println("2. 사번으로 사원 정보 조회");
			System.out.println("3. 새로운 사원 정보 추가");
			System.out.println("4. 사번으로 사원 정보 수정");
			System.out.println("5. 사번으로 사원 정보 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.println("=================================");
			
			System.out.print("메뉴 선택>> ");
			select = sc.nextInt();
			sc.nextLine(); // 스캐너 버퍼에 남아있는 개행문자 제거
			
			switch(select) {
			case 1 : controller.selectAll(); break;
			case 2 : break;
			case 3 : break;
			case 4 : break;
			case 5 : break;
			case 0 : System.out.println("프로그램을 종료합니다."); break;
			default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요."); 
			}
			System.out.println();
			
		} while (select != 0);
	}


	public void selectAll(ArrayList<EMP> empList) {
		
		System.out.println("사번 \t 이름 ");
		
		
		
		
	}
}