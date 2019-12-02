package com.kh.bingo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BingoController {
	
	private Map<String, String> bingoBoard;
	private int bingoSize;
	public static int goal; 
	
	
	// 빙고판 크기 지정 및  승리 목표 지정
	public void setBingo(int bingoSize, int goal) {
		
		// 새로운 빙고판을 저장할 LinkedHashMap 객체 선언
		// * LinkedHashMap : 추가되는 순서를 유지하는 Map
		bingoBoard = new LinkedHashMap<String, String>();
		
		// 매개변수로 빙고판의 크기
		this.bingoSize = bingoSize;
		this.goal = goal;
		
		// 빙고판 생성 메소드 호출
		createBingo();
	}
	
	public Map<String, String> getBingo(){
		// 빙고판 정보 리턴
		return bingoBoard;
	}
	
	// 빙고판 생성
	public void createBingo() {
		int random = 0;
		for(int i=0 ; i<bingoSize * bingoSize; ) {
			
			random = (int)(Math.random() * (bingoSize * bingoSize) + 1);
			if(bingoBoard.put(""+ random, ""+ random) == null){
				i++;
			}
		}
	}
	
	// 빙고판에 입력된 값을 체크
	// 성립된 빙고 개수 반환
	public int checkBingo(String input) {
		
		// 입력된 값이 빙고판에 있을 경우 "X"로 변경
		if(bingoBoard.containsKey(input)) {
			bingoBoard.replace(input, "X");
		}
		
		// Map에 저장되 value 값들만을 ArrayList객체로 변환
		List<String> list = new ArrayList<String>(bingoBoard.values());
		
		boolean rowCheck = true; // 가로 빙고 여부 판단
		boolean colCheck = true; // 세로 빙고 여부 판단
		int bingoCount = 0;
		
		boolean diaCheck1 = true; //좌상 우하(\) 대각선 빙고 여부 판단
		boolean diaCheck2 = true; //우상 좌하(/) 대각선 빙고 여부 판단
		
		for(int i=0 ; i<bingoSize; i++) {
			// 가로 빙고 체크
			rowCheck = true;
			for(int j=i*bingoSize; j<(i+1)*bingoSize ; j++) {
				if(!list.get(j).equals("X")) {
					rowCheck = false;
					break;
				}
			}
			if(rowCheck) bingoCount++;

			
			
			// 세로 빙고 체크
			colCheck = true;
			for(int k=i; k<list.size() ; k+=bingoSize) {
				if(!list.get(k).equals("X")) {
					colCheck = false;
					break;
				}
			}
			if(colCheck) bingoCount++;
						
			
			// 좌상 우하(\) 대각선 빙고 체크
			if(!list.get(i + (bingoSize * i) ).equals("X")) {
				diaCheck1 = false;
			}
			
			// 우상 좌하(/) 대각선 빙고 체크
			if(!list.get(bingoSize * i + (bingoSize - 1 - i)).equals("X")) {
				diaCheck2 = false;
			}
		}
		
		if(diaCheck1) bingoCount++;
		if(diaCheck2) bingoCount++;
		
		return bingoCount;
	}
}
