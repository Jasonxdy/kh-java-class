package com.kh.bingo.view;

import java.util.Map;
import java.util.Scanner;

import com.kh.bingo.controller.BingoController;

public class BingoMenu {
	private Scanner sc = new Scanner(System.in);
	private BingoController bc = new BingoController();
	
	public void mainMenu() {
		int sel= 0;
		do {
			System.out.println("========== 빙고 게임 ==========");
			System.out.println("1. 게임 시작");
			System.out.println("0. 종료");
			
			System.out.print("메뉴 선택 : ");
			sel = sc.nextInt();
			sc.nextLine();
			
			switch(sel) {
			case 1 : initGame(); break;
			case 2 : System.out.println("게임 종료"); break;
			default : System.out.println("다시 입력해 주세요.");
			}
			
			System.out.println();
		}while(sel != 0);
	}

	// 게임 시작 전 설정 메소드
	private void initGame() {
		
		// 빙고 크기 입력
		System.out.print("빙고 크기 입력 ( ex. 5X5 빙고 --> 5) : ");
		int bingoSize = sc.nextInt();
		
		// 빙고 승리 목표 입력
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
	
	// 빙고판 출력 메소드
	private void printBingo(Map<String,String> bingoBoard) {
		
		int i=1;
		System.out.println();
		for(String s : bingoBoard.keySet()) {
			
			System.out.printf("%4s",bingoBoard.get(s));
			if(i % Math.sqrt(bingoBoard.size()) == 0){
				System.out.println();
			}
			i++;
		}
	}
	
	
	// 게임 시작 메소드
	private void startGame() {
		String input = null;
		
		while(true) {
			System.out.println();
			System.out.print("숫자를 입력하세요 : ");
			input = sc.nextLine();
			
			// 입력 받은 값에 해당 하는 부분을 "X"로 변경하고 빙고 개수 반환 받기
			int bingoCount = bc.checkBingo(input);
			for(int i=0; i<10 ; i++) System.out.println();
			printBingo(bc.getBingo());
			
			// 빙고 개수 출력
			System.out.println();
			System.out.println("현재 빙고 수 : " + bingoCount);
			
			// 빙고 개수와 승리 목표의 개수 같으면 "Bingo!!" 문구를 띄우고 메인으로 이동
			if(bingoCount == BingoController.goal) {
				System.out.println("Bingo!!");
				break;
			}
		}
	}
}	
