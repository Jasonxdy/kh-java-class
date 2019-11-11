package com.kh.chap1_list.practice.view;

import java.util.Scanner;

import com.kh.chap1_list.practice.controller.MusicController;
import com.kh.chap1_list.practice.model.vo.Music;

public class MusicView {
	
//	- sc:Scanner = new Scanner(System.in)
//			- mc:MusicController = new MusicController();
//			+ mainMenu() : void
//			+ addList() : void
//			+ addAtZero() : void
//			+ printAll() : void
//			+ searchMusic() : void
//			+ removeMusic() : void
//			+ setMusic() : void
//			+ ascTitle() : void
//			+ descSinger() : void

	Scanner sc = new Scanner(System.in);
	private MusicController mc = new MusicController();
	
	// 스캐너 객체 생성
	// MusicController 객체 생성
	public void mainMenu () {
//	******* 메인 메뉴 *******
//	1. 마지막 위치에 곡 추가 // addList() 실행
//	2. 첫 위치에 곡 추가 // addAtZero()
//	3. 전체 곡 목록 출력 // printAll()
//	4. 특정 곡 검색 // searchMusic()
//	5. 특정 곡 삭제 // removeMusic()
//	6. 특정 곡 정보 수정 // setMusic()
//	7. 곡명 오름차순 정렬 // ascTitle()
//	8. 가수명 내림차순 정렬 // descSinger()
//	9. 종료 // “종료” 출력 후 main()으로 리턴
//	메뉴 번호 선택 : >> 입력 받음
//	// 메뉴 화면 반복 실행 처리
		
		int sel = 0;
		
		do {
			System.out.println(
					"=====***** 메인 메뉴 *****=====\n" + 
					"1. 마지막 위치에 곡 추가\n" + 
					"2. 첫 위치에 곡 추가\n" + 
					"3. 전체 곡 목록 출력\n" + 
					"4. 특정 곡 검색\n" + 
					"5. 특정 곡 삭제\n" + 
					"6. 특정 곡 수정\n" + 
					"7. 곡 명 오름차순 정렬\n" + 
					"8. 가수 명 내림차순 정렬\n" + 
					"9. 종료\n");
			System.out.println();
			System.out.print("메뉴 번호 입력 : ");
			sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
			case 1 : addList(); break;
			case 2 : addAtZero(); break;
			case 3 : printAll(); break;
			case 4 : searchMusic(); break;
			case 5 : removeMusic(); break;
			case 6 : setMusic(); break;
			case 7 : ascTitle(); break;
			case 8 : descSinger(); break;
			case 9 : System.out.println("프로그램 종료"); break;
			default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}
			
		} while (sel != 9);
		
		
		
		
	}
	public void addList() {
//	****** 마지막 위치에 곡 추가 ******
//	// 곡 명과 가수 명을 사용자에게 입력 받는다.
//	// MusicController에 addList()를 이용해서 입력한 정보를 넘기고
//	// 추가 성공 시 “추가 성공”, 추가 실패 시 “추가 실패” 콘솔창에 출력
		
		System.out.println("****** 마지막 위치에 곡 추가 ******");
		System.out.print("곡 명 : ");
		String title = sc.nextLine();
		
		System.out.print("가수 명 : ");
		String singer = sc.nextLine();
		
		boolean result = mc.addList(new Music(title, singer));
		if(result) {
			System.out.println("추가 성공");
		} else {
			System.out.println("추가 실패");
		}
		
	}
	public void addAtZero() {
//	****** 첫 위치에 곡 추가 ******
//	// 곡 명과 가수 명을 사용자에게 입력 받는다.
//	// MusicController에 addAtZero()를 이용해서 입력한 정보를 넘기고
//	// 추가 성공 시 “추가 성공”, 추가 실패 시 “추가 실패” 콘솔 창에 출력
		System.out.println("****** 첫 위치에 곡 추가 ******");
		
		System.out.print("곡 명 : ");
		String title = sc.nextLine();
		
		System.out.print("가수 명 : ");
		String singer = sc.nextLine();
		
		if(mc.addAtZero(new Music(title, singer))==1) {
			System.out.println("추가 성공");
		} else {
			System.out.println("추가 실패");
		}
	}
	
	public void printAll() {
//		****** 전체 곡 목록 출력 ******
//		// MusicController에 printAll()의 값에 따라 (성공을 1로 둔다)
//		// 성공 시 “출력 성공”, 실패 시 “출력 실패” 콘솔 창에 출력
		
		System.out.println("****** 전체 곡 목록 출력 ******");
		System.out.println(mc.printAll());
	}

	public void searchMusic() {
//		****** 특정 곡 검색 ******
//		// 사용자에게 곡 이름을 받고 MusicController에 있는 searchMusic으로 값을 넘긴다.
//		// searchMusic()의 반환 값에 따라 반환 값이 없으면 “검색한 곡이 없습니다.”
//		// 반환 값이 있으면 “검색한 곡은 000(곡 명, 가수 명) 입니다.” 콘솔 창에 출력
		System.out.println("****** 특정 곡 검색 ******");
		System.out.print("검색할 곡 명 : ");
		String title = sc.nextLine();
		if(mc.searchMusic(title)!=null) {
			System.out.println("검색한 곡은 ("+ mc.searchMusic(title).getTitle()+", "+ mc.searchMusic(title).getSinger()+")입니다");
		} else {
			System.out.println("검색한 곡이 없습니다");
		}
		
	}

	public void removeMusic() {
//		****** 특정 곡 삭제 ******
//		// 사용자에게 삭제할 곡의 이름을 직접 받고 MusicController에 removeMusic으로
//		// 값을 넘긴다. removeMusic()의 반환 값에 따라 반환 값이 없으면 “ 삭제할 곡이 없습니다.”
//		// 반환 값이 있으면 “000(곡 명, 가수 명)을 삭제 했습니다” 콘솔 창에 출력
		
		System.out.println("****** 특정 곡 삭제 ******");
		System.out.print("삭제할 곡 명 : ");
		String title = sc.nextLine();
		
		Music save = mc.removeMusic(title);
		
		if (save!=null) {
			System.out.println("("+save.getTitle()+", "+save.getSinger()+")을 삭제했습니다.");
		} else {
			System.out.println(title + "라는 곡이 없습니다");
		}
	}

	public void setMusic() {
//		****** 특정 곡 정보 수정 ******
//		// 사용자에게 수정할 곡을 입력 하여 MusicController에 setMusic으로 검색 할 수 있게
//		// 값을 넘기며 수정할 곡 명과 가수 명을 받아 setMusic으로 값을 넘긴다.
//		// 검색 결과 값이 없으면 “수정할 곡이 없습니다.”, 검색 결과 값이 있고
//		// 수정 했으면 “000(곡 명, 가수 명)의 값이 변경 되었습니다.” 콘솔 창에 출력
		System.out.println("****** 특정 곡 정보 수정 ******");
		System.out.print("검색할 곡 명 : ");
		String searchTitle = sc.nextLine();
		
		System.out.println("수정할 곡 명 : ");
		String title = sc.nextLine();
		System.out.println("수정할 가수명 : ");
		String singer = sc.nextLine();
		
		Music m = mc.setMusic(searchTitle, new Music(title,singer));
		
		if(m != null) {
			System.out.println(m + "가 값이 변경되었습니다");
		} else {
			System.out.println("수정할 곡이 없습니다.");
		}
		//if(mc.setMusic(title, music))
		
		
	}

	public void ascTitle() {
//		****** 곡 명 오름차순 정렬 ******
//		// MusicController에 ascTitle()의 값에 따라 성공 시 “정렬 성공”, 실패 시 “정렬 실패”
		if(mc.ascTitle()==1) {
			System.out.println("정렬 성공");
		} else {
			System.out.println("정렬 실패");
		}
	}

	public void descSinger() {
//		****** 가수 명 내림차순 정렬 ******
//		// musicController에 descSinger()의 값에 따라 성공 시 “정렬 성공”, 실패 시 “정렬 실패”
		if (mc.descSinger()==1) {
			System.out.println("정렬 성공");
		} else {
			System.out.println("정렬 실패");
		}
	}

}
