package com.kh.myBingo.view;

import java.util.Scanner;

import com.kh.myBingo.controller.BingoController;

public class BingoMenu {
	
	private Scanner sc = new Scanner(System.in);
	private BingoController bc =  new BingoController();
	
	public void mainMenu() {
		int sel = 0;
		do {
			System.out.print("========== 빙고 게임 ==========\n" + 
					"1. 게임 시작\n" + 
					"0. 종료\n" + 
					"메뉴 선택 : ");
			sel = sc.nextInt();
			sc.nextLine();
			switch(sel) {
			case 1 : initGame(); break;
			case 0 : System.out.print("게임을 종료합니다"); return;
			default : System.out.println("다시 입력해주세요");
			}
			System.out.println();
		} while(sel != 0);
	}
	
	private void initGame() {
		// 빙고 크기 입력
		System.out.print("빙고 크기 입력 (ex. 5X5 빙고 --> 5) : ");
		int bingoSize = sc.nextInt();
		sc.nextLine();
		
		// 빙고 승리 조건 입력
		System.out.print("승리 목표 (빙고 개수) : ");
		int goal = sc.nextInt();
		sc.nextLine();
		
		// 빙고판 생성
		bc.setBingo(bingoSize, goal);
		
		// 빙고판 출력
		printBingo(bc.getBingo());
		
		// 게임 시작 메소드 출력
		startGame();
	}
}
