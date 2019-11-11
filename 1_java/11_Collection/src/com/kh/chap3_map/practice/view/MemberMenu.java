package com.kh.chap3_map.practice.view;

import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.kh.chap3_map.controller.MemberController;
import com.kh.chap3_map.model.vo.Member;

public class MemberMenu {

//	- sc:Scanner = new Scanner(System.in)
//			- mc : MemberController
//			= new MemberController()
//			+ mainMenu() : void
//			+ memberMenu() : void
//			+ joinMembership() : void
//			+ logIn() : void
//			+ changePassword() : void
//			+ changeName() : void
//			+ sameName() : void
	
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
//		System.out.println("========== KH 사이트 ==========");
//		******* 메인 메뉴 *******
//		1. 회원가입 // joinMembership() 실행
//		2. 로그인 // logIn() 실행 후 memberMenu() 실행
//		3. 같은 이름 회원 찾기 // sameName()
//		9. 종료 // “프로그램 종료.” 출력 후 main()으로 리턴
//		메뉴 번호 선택 : >> 입력 받음
//		// 메뉴 화면 반복 실행 처리
//		// 잘 못 입력 하였을 경우 "잘못 입력하였습니다. 다시 입력해주세요" 출력 후 반복
		
		System.out.println("========== KH 사이트 ==========");
		int sel = 0;
		do {
			System.out.println(
					"=====***** 메인 메뉴 *****=====\r\n" + 
							"1. 회원가입\r\n" + 
							"2. 로그인\r\n" + 
							"3. 같은 이름 회원 찾기\r\n" + 
					"9. 종료");
			System.out.println();
			System.out.print("메뉴 번호 입력 : ");
			sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
			case 1 : joinMembership(); break;
			case 2 : logIn(); break;
			case 3 : sameName(); break;
			case 9 : System.out.println("프로그램 종료."); return;
			default : System.out.println("잘못 입력하셨습니다");
			}
		} while (sel != 9);
	}
		public void memberMenu() {
//		******* 회원 메뉴 *******
//		1. 비밀번호 바꾸기 // changePassword() 실행
//		2. 이름 바꾸기 // changeName()
//		3. 로그아웃 // “로그아웃 되었습니다.” 출력 후 mainMenu()로 리턴
//		메뉴 번호 선택 : >> 입력 받음
//		// 메뉴 화면 반복 실행 처리
//		// 잘 못 입력 하였을 경우 "잘못 입력하였습니다. 다시 입력해주세요" 출력 후 반복
			int sel = 0;
			do {
				System.out.println(
						"=====***** 회원 메뉴 *****=====\r\n" + 
								"1. 비밀번호 바꾸기\r\n" + 
								"2. 이름 바꾸기\r\n" + 
						"3. 로그아웃");
				System.out.print("메뉴 번호 선택 : ");
				sel = sc.nextInt();
				sc.nextLine();
				
				switch (sel) {
				case 1 : changePassword(); break;
				case 2 : changeName(); break;
				case 3 : System.out.println("로그아웃 되었습니다."); break;
				default : System.out.println("잘못 입력하였습니다. 다시 입력해주세요"); continue;
				}
			} while(sel != 3);
			
			
		}
		public void joinMembership() {
//		회원가입하기 위해 아이디, 비밀번호, 이름을 받고 비밀번호와 이름은
//		Member객체에 담고 id와 객체는 MemberController의 joinMembership()로 보냄.
//		받은 결과에 따라 true면 “성공적으로 회원가입 완료하였습니다.”,
//		false면 “중복된 아이디입니다. 다시 입력해주세요.” 출력
			
			while(true) {
				System.out.print("아이디 : ");
				String id = sc.nextLine();
				System.out.print("비밀번호 : ");
				String password = sc.nextLine();
				System.out.print("이름 : ");
				String name = sc.nextLine();
				if(mc.joinMembership(id, new Member(password, name))) {
					System.out.println("성공적으로 회원가입 완료하였습니다");
					break;
				} else {
					System.out.println("중복된 아이디입니다. 다시 입력해주세요");
				}
			}
		}
		
		
		public void logIn() {
//		아이디와 비밀번호를 사용자에게 받아 MemberController의 logIn()메소드로 넘겨 줌.
//		반환 값 있으면 “OOO님, 환영합니다!” 출력 후 로그인 된 화면으로(memberMenu()),
//		없으면 “틀린 아이디 또는 비밀번호입니다. 다시 입력해주세요.” 출력 후 반복
			
			while(true) {
				System.out.print("아이디 : ");
				String id = sc.nextLine();
				System.out.print("비밀번호 : ");
				String password = sc.nextLine();
				if(mc.logIn(id, password) != null) {
					System.out.println(mc.logIn(id, password) +"님, 환영합니다");
					memberMenu(); break;
				} else {
					System.out.println("틀린 아이디 또는 비밀번호입니다. 다시 입력해주세요.");
				}
			}
		}
		
		public void changePassword() {
//		아이디와 비밀번호, 변경할 비밀번호를 받아 MemberController의
//		changePassword()로 보냄.
//		받은 결과 값이 true면 “비밀번호 변경에 성공했습니다.”,
//		false면 “비밀번호 변경에 실패했습니다. 다시 입력해주세요.” 출력 후 반복
			
			while(true) {
				System.out.print("아이디 : ");
				String id = sc.nextLine();
				System.out.print("비밀번호 : ");
				String oldPw = sc.nextLine();
				System.out.print("변경할 비밀번호 : ");
				String newPw = sc.nextLine();
				
				if(mc.changePassword(id, oldPw, newPw) == true) {
					System.out.println("비밀번호 변경에 성공했습니다.");
					break;
				} else {
					System.out.println("비밀번호 변경에 실패했습니다. 다시 입력해주세요.");
				}
			}
		}
		
		public void changeName() {
//		아이디와 비밀번호를 받아 MemberController의 logIn()으로 넘겨
//		현재 저장되어 있는 이름을 받고
//		사용자에게 현재 저장되어 있는 이름을 출력하여 보여줌.
//		변경할 이름을 받아 MemberController의 chageName()로 id와 함께 넘기고
//		“이름 변경에 성공하였습니다.” 출력
//		만일 logIn()로부터 저장되어 있는 이름을 받지 못 했다면
//		“이름 변경에 실패했습니다. 다시 입력해주세요” 출력 후 반복
			while(true) {
				System.out.print("아이디 : ");
				String id = sc.nextLine();
				System.out.print("비밀번호 : ");
				String password = sc.nextLine();
				if(mc.logIn(id, password) != null) {
					System.out.println("현재 설정된 이름 : " + mc.logIn(id, password));
					System.out.print("변경할 이름 : ");
					String newName = sc.nextLine();
					mc.changeName(id, newName);
					break;
				} else {
					System.out.println("이름 변경에 실패했습니다. 다시 입력해주세요");
				}
			}
		}
		public void sameName(){
//		검색할 이름을 받고 mc의 sameName()메소드로 넘김.
//		반환 값을 가지고 entrySet()을 이용하여 ‘이름-아이디’ 형식으로 출력
			while(true) {
				System.out.print("검색할 이름 : ");
				String name = sc.nextLine();
				
				if(mc.sameName(name) != null) {
					Set<Entry<String, Member>> entrySet = mc.sameName(name).entrySet();
					Iterator<Entry<String, Member>> it = entrySet.iterator();
					Entry<String, Member> entry = it.next();
					System.out.println(entry.getKey() + " - " +entry.getValue().getName());
					break;
				} else {
					System.out.println("검색하신 이름은 없습니다.");
				}
			}
		}
	
	
}
