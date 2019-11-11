package com.kh.chap1_poly.practice.view;

import java.util.Scanner;

import com.kh.chap1_poly.model.vo.Member;
import com.kh.chap1_poly.practice.controller.LibraryController;

public class LibraryMenu {
	
//	- lc : LibraryController // 초기화 생성
//	- sc : Scanner // 초기화 생성
//	+ mainMenu() : void
//	+ selectAll() : void
//	+ searchBook() : void
//	+ rentBook() : void
	
	private LibraryController lc = new LibraryController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		
//		// 이름, 나이, 성별을 키보드로 입력 받은 후 Member 객체 생성
//		// LibraryController의 insertMember() 메소드에 전달
//		==== 메뉴 ==== // 무한 반복 실행
//		1. 마이페이지 // LibraryController의 myInfo() 호출하여 출력
//		2. 도서 전체 조회 // LibraryMenu의 selectAll() 호출
//		3. 도서 검색 // LibraryMenu의 searchBook() 호출
//		4. 도서 대여하기 // LibraryMenu의 rentBook() 호출
//		9. 프로그램 종료하기
//		메뉴 번호 :
		
//		이름 : 백동현
//		나이 : 20
//		성별 : M
		
//		// 이름, 나이, 성별을 키보드로 입력 받은 후 Member 객체 생성
//		// LibraryController의 insertMember() 메소드에 전달
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("성별(M/F) : ");
		char gender = sc.nextLine().charAt(0);
		lc.insertMember(new Member(name, age, gender, 0));
		
//		==== 메뉴 ==== // 무한 반복 실행
//		1. 마이페이지 // LibraryController의 myInfo() 호출하여 출력
//		2. 도서 전체 조회 // LibraryMenu의 selectAll() 호출
//		3. 도서 검색 // LibraryMenu의 searchBook() 호출
//		4. 도서 대여하기 // LibraryMenu의 rentBook() 호출
//		9. 프로그램 종료하기
		int sel = 0;
		do {
			System.out.println(
					"==== 메뉴 ====\r\n" + 
					"1. 마이페이지\r\n" + 
					"2. 도서 전체 조회\r\n" + 
					"3. 도서 검색\r\n" + 
					"4. 도서 대여하기\r\n" + 
					"9. 프로그램 종료하기");
			System.out.print("메뉴 번호 : ");
			sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
			case 1 : System.out.println(lc.myInfo()); break;
			case 2 : selectAll(); break;
			case 3 : searchBook(); break;
			case 4 : rentBook(); break;
			case 9 : System.out.println("프로그램을 종료합니다"); break;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		} while (sel != 9);
	}
	
	public void selectAll() {
		for (int i = 0; i < 5; i++) {
			System.out.println(lc.selectAll()[i]);
		}
	}
	
	public void searchBook() {
		
	}
	
	public void rentBook() {
		
	}
			

}
