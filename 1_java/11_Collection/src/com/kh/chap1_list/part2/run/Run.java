package com.kh.chap1_list.part2.run;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.kh.chap1_list.part2.model.vo.Food;

public class Run {

	public static void main(String[] args) {
//		Food만을 저장할 수 있는 List 객체 생성

		List<Food> list = new ArrayList<Food>(); // 이 한줄 안에 다형성, 인터페이스, 객체, 추상화, 캡슐화의 모든 것이 들어있음...
//		ArrayList<Food> aList = new ArrayList<Food>();
//		LinkedList<Food> lList = new LinkedList<Food>();
//		
//		// aList = lList; -> 불가능.. 형제관계(?)라서.. 따라서 (성능에 따라서) 언제든지 변환할 수 있게 부모 객체인 List로 해놓고 작성하는게 좋다
//		
//		list = aList;
//		list = lList;
//		// List는 인터페이스라서 객체 생성은 불가능하나 참조형 변수로는 사용할 수 있다는 특징이 드러남
		

		// 1. add(E e) : 리시트의 끝에 인스턴스 데이터를 추가하는 메소드
		list.add(new Food("갈비찜", 15000));
		list.add(new Food("쌀국수", 9000));
		list.add(new Food("냉면", 13000));
		System.out.println(list); // 질문.. 인터페이스는 객체 못 만드는거 아니었음??
		
		// 2. add(int index, E e)
		// 인덱스를 지정하여 해당 인덱스에 인스턴스를 추가하는 메소드
		list.add(1, new Food("라면",3000));
		System.out.println(list);
		
		// 3. set(int index, E e)
		// 해당 인덱스의 값을 새로운 인스턴스로 변경
		list.set(0, new Food("갈비찜",12000));
		System.out.println(list);
		
		// 4. size()
		// 리스트 내에 있는 객체의 개수 반환
		System.out.println("size : " + list.size());
		
		// 5. get(int index)
		// 해당 인덱스에 저장된 인스턴스 반환
		System.out.println(list.get(2));
		
		// 6. remove(int index)
		// 해당 인덱스에 저장된 인스턴스 제거 후 빈 공백을 뒤에서 당겨서 메꿈
		list.remove(3);
		System.out.println(list);
		
		// 7. subList(int index1, int index2)
		// 리스트의 index1 이상 index2 미만의 객체를 추출하여 별도의 리스트 생성
		List<Food> sub = list.subList(1, 3);
		System.out.println(sub);
		
		// 8. clear()
		// 리스트 내 내용 전부 삭제
		list.clear();
		System.out.println(list);
		
		// 9. isEmpty()
		// 리스트가 비어있는지 확인
		System.out.println(list.isEmpty());
		
		
	}
	

}
