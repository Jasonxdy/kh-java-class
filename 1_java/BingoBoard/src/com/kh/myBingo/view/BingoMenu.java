package com.kh.myBingo.view;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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

	private void startGame() {
		// TODO Auto-generated method stub
		String input = null;
		while(true) {
			System.out.println();
			System.out.print("숫자를 입력하세요 : ");
			input = sc.nextLine();
			int bingoCount = bc.checkBingo(input);
			for (int i = 0; i < 10; i++) System.out.println();
			printBingo(bc.getBingo());
			System.out.println();
			System.out.println("현재 빙고 수 : " + bingoCount);
			
			if(bingoCount == BingoController.goal) {
				System.out.println("BINGO!");
				break;
			}
		}
	}

	private void printBingo(Map<String, String> bingoBoard) {
		Set<String> bingoSet = bingoBoard.keySet();
		Iterator<String> it = bingoSet.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
	}
}
