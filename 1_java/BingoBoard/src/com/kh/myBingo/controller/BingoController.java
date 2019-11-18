package com.kh.myBingo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BingoController {

	private Map<String, String> bingoBoard;
	private int bingoSize;
	public static int goal;
	
	public void setBingo(int bingoSize, int goal) {
		// 빙고판을 저장할 새로운 LinkedHashMap객체 선언
		bingoBoard = new LinkedHashMap<String, String>();
		
		this.bingoSize = bingoSize;
		this.goal = goal;
		
		createBingo();
		
	}
	
	public Map<String, String> getBingo() {
		return bingoBoard;
	}

	private void createBingo() {
		// bingoSize 만큼의 key 개수와 각 key에 해당하는 객체
		
		int random = 0;
		for (int i = 0; i < bingoSize * bingoSize;) {
			random = (int)(Math.random()*(bingoSize * bingoSize)+1);
			if(bingoBoard.put(""+random, ""+random) == null) {
				i++;
			}
		}
	}

	public int checkBingo(String input) {
		if(bingoBoard.containsKey(input)) {
			bingoBoard.replace(input, "X");
		}
		
		List<String> list = new ArrayList<String>(bingoBoard.values());
		boolean rowCheck = true;
		boolean colCheck = true;
		boolean diaCheck1 = true;
		boolean diaCheck2 = true;
		int bingoCount = 0;
		
		for (int i = 0; i < bingoSize; i++) {
			// 가로 빙고 체크
			rowCheck = true;
			for (int j = i*bingoSize; j < (i+1)*bingoSize; j++) {
				if(!list.get(j).equals("X")) {
					rowCheck = false;
					break;
				}
			}
			if(rowCheck) bingoCount++;
			
			// 세로 빙고 체크
			colCheck = true;
			if(!list.get(i*bingoSize).equals("X")) {
				colCheck = false;
			}
			if(colCheck) bingoCount++;
			
			for (int j = bingoSize*i + i; j < bingoSize; j++) {
				
			}
		}
		
		
		
		return bingoCount;
	}

}
