package com.kh.chap2_set.part1.run;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.kh.chap2_set.part1.model.vo.Student;


public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Set --> 순서 유지 X, 중복 저장 X
		// index로 접근 불가 (일반 for문 사용 불가)
		
		Set<String> testSet = new HashSet<String>(); // HashSet은 Set의 후손클래스 (다형성)
		
		testSet.add(new String("점심 30분 전"));
		testSet.add(new String("뭐 드시러 가세요?"));
		testSet.add(new String("지금 너무 배고파요"));
		testSet.add(new String("소고기 먹고싶다."));
		
		System.out.println(testSet.toString());
		// 순서 유지 X 확인
		
		testSet.add(new String("점심 30분 전"));
		System.out.println(testSet);
		// 중복 제거 확인
		// String은 이미 hashCode(), equals()가 오버라이딩이 되어있음 -> HashSet 저장 시 중복이 제거됨
		
		//Set<Student> hs = new HashSet<Student>();
		Set<Student> hs = new LinkedHashSet<Student>();
		// 순서가 유지되는 HashSet
		
		hs.add(new Student("서진웅", 27, 100));
		hs.add(new Student("윤희빈", 27, 80));
		hs.add(new Student("문영준", 30, 60));
		hs.add(new Student("서진웅", 27, 100));
		
		System.out.println(hs);
		// 1) 오버라이딩 전 --> 중복 제거가 되지 않음
		// 2) equals() 오버라이딩 후 --> 중복제거가 되지 않음
		// why? HashSet은 먼저 hashCode()값이 같은지부터 판별을 하기 때문에 equals() 결과가 true여도 hashCode() 값이 다르면 서로 다른 객체로 인식함
		// 3) hashCode() 오버라이딩
		
		// Set에 저장된 객체를 하나씩 접근하는 방법
		
		// 1. Set -> List에 담아 준 후 접근하기
		List<Student> list = new ArrayList<Student>(hs);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("=========================================");
		
		// 2. 반복자 Iterator 사용하여 접근
		Iterator<Student> it = hs.iterator();
		// 무작위로 나열되어 있던 HashSet을 iterator를 사용하여 반복할 수 있는 형태로 만든 후 it라는 변수로 참조
		
		while(it.hasNext()/*다음을 가지고 있냐 없냐 판별하는 기능*/) {
			// Iterator에서 현재 가리키고 있는 위치 다음 부분에 값이 있을경우 true, 없으면 false
			System.out.println(it.next()/*다음것을 가져오고*/);
			//즉, 다음것이 잇는지 보고 있으면 가져오기 반복
		}
		
		System.out.println("=========================================");
		
		// 3. 향상된 for문 (for-each문) --> 이걸 아마 가장 많이 사용할듯..
		
		// for(참조형 : 배열 또는 Collection) {}
		// ---> 참조형이 배열 또는 Collection의 요소를 하나씩 순차적으로 참조하는 형태의 반복문
		for (Student s : hs) {
			// for문 한번 돌때마다 s가 하나씩 hs를 참조함
			System.out.println(s);
		}
		
	}
}
