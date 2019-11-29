package com.kh.chap2_set.practice.run;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Run {
	public static void main(String[] args) {
		
		// 1. 1 ~ 45 사이의 난수 6개를 중복이 되지 않게 저장 후 
		// Iterator를 이용하여 출력  
		Set<Integer> lotto = new HashSet<Integer>();
		
		int ran = 0;
		for(int i=0 ; i<6 ; ) {
			ran = (int)(Math.random()*45) + 1;
			if(lotto.add(ran)) {
				i++;
			}
		}
		
		Iterator<Integer> it = lotto.iterator();
		System.out.println("추첨된 로또 번호");
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		
		// 2. 저장된 수를 자동 오름차순 정렬되게 처리하는 
		// Set 계열 객체에 저장 후 
		// 향상된 for문을 이용하여 출력
		Set<Integer> tree = new TreeSet<Integer>(lotto);
		
		System.out.println("정렬된 로또 번호");
		for(int i : tree) {
			System.out.print(i + " ");
		}
		
	}
}
