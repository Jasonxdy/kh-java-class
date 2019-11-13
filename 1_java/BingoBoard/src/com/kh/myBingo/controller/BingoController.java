package com.kh.myBingo.controller;

import java.util.Iterator;
import java.util.LinkedHashMap;
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
		for (int i = 0; i < bingoSize * bingoSize; i++) {
			random = (int)(Math.random()*(bingoSize * bingoSize)+1);
			if(bingoBoard.put(""+random, ""+random) == null) {
				
			}
		}
		
	}

}
