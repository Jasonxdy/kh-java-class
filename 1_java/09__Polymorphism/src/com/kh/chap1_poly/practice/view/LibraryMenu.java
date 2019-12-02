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
		while(sel != 9) {
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
			case 9 : System.out.println("프로그램을 종료합니다"); return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	public void selectAll() {
		// LibraryController의 selectAll()메소드 호출하여 결과 값 Book[] 자료형 bList에 담기
		// for문 이용하여 bList의 모든 도서 목록 출력
		// 단, i를 이용하여 인덱스도 같이 출력 대여할 때 도서번호를 알기 위해
//		ex ) 0번도서 : 백종원의 집밥 / 백종원 / tvN / true
		
		
		for (int i = 0; i < lc.selectAll().length; i++) {
			System.out.println(i + "번 도서 : " + lc.selectAll()[i]);
		}
	}
	
	public void searchBook() {
		while(true) {
			System.out.print("검색할 제목 키워드 : ");
			String keyword = sc.nextLine();
			if(lc.searchBook(keyword) == null) {
				System.out.println("검색한 키워드를 포함하는 제목은 없습니다.");
				return;
			} else {
				for (int i = 0; i < lc.searchBook(keyword).length; i++) {
					if(lc.searchBook(keyword)[i] != null) {
						System.out.println(lc.searchBook(keyword)[i]);
					}
				}
				return;
			}
		}
	}
	
	public void rentBook() {
			selectAll();
			System.out.print("대여할 도서 번호 선택 : ");
			int index = sc.nextInt();
			sc.nextLine();
			switch(lc.rentBook(index)) {
			case 0 : System.out.println("성공적으로 대여되었습니다"); break;
			case 1 : System.out.println("나이제한으로 대여 불가능입니다"); break;
			case 2 : System.out.println("성공적으로 대여되었습니다. 요리학원 쿠폰이 발급되었으니 마이페이지에서 확인하세요"); break;
			default : System.out.println("해당 도서는 없습니다"); 
			}
		}
}
