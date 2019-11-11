package com.kh.chap2_set.practice.run;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		<실습문제>
//		클래스명 : com.kh.chap2_set.practice.run.Run.java >> main()메소드에 기능 구현
//		1~45 사이의 난수 6개를 중복이 되지않게 저장 후 Iterator를 이용하여 출력
//		저장된 수를 자동 오름차순 정렬되게 처리하는 Set 계열 객체에 저장 후 향상된 for문을 이용하여 출력

		Set<Integer> num = new HashSet<Integer>();
		while (num.size()!=6) {
			num.add(new Integer((int)((Math.random()*45+1))));
		}	
		Iterator<Integer> it = num.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		
		System.out.println();
		
		Set<Integer> list = new TreeSet<Integer>();
		list.addAll(num);
		
		for (Integer integer : list) {
			System.out.print(integer + " ");
		}
		
	}

}
