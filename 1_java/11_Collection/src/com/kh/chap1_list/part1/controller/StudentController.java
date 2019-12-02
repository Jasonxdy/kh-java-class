package com.kh.chap1_list.part1.controller;

import java.util.ArrayList;

import com.kh.chap1_list.part1.model.vo.Student;

public class StudentController {
	// Collection 중 ArrayList를 이용하여 Student 객체 관리
	
	// 학생 객체를 저장할 ArrayList 생성
	ArrayList<Student> list = new ArrayList<Student>();
	// <Student> --> Generics(제네릭) : 뭐가 들어올지 불확실하기 때문에 E로 표현하는 것
	// 해당 컬렉션에 저장할 객체의 타입을 제한
	
	public void method() {
		// 제네릭 타입 제한 테스트
		ArrayList list1 = new ArrayList();
		ArrayList<Student> list2 = new ArrayList<Student>();
		
		// Student 객체 추가
		list1.add(new Student());
		list2.add(new Student());
		
		// String 객체 추가
		list1.add(new String("aaa"));
		// list2.add(new String("aaa")); -> list2는 저장할 객체 타입이 지정되어 있기 때문에
		
		// 타입제한 왜 하는가?
		// 타입 제한을 안한 상태에서 Student 객체의 setName() 메소드 호출하기
		// list1.get(0).setName("이름") : 이게 안됨...왜? -> get이 반환하는 것은 부모 객체인 Object이기 때문에 (.get 치고 반환형 보면.. 이게 타입 제한을 안해서 이런듯),
		if (list1.get(0) instanceof Student) { // 0번째 객체가 Student 타입인지 확인까지 해줘야하고
			((Student) list1.get(0)).setName("학생이름1"); // 이런식으로 DownCasting 해줘야 함 -> object를 반환하기 때문에
		}
		
		// 타입제한이 된 리스트에서 Student 객체의 setName() 메소드 호출하기
		list2.get(0).setName("학생이름1");
	}
	
	// ArrayList에 학생 객체 추가 메소드
	public void insertStudent(Student std) {
		list.add(std);
	}
	
	// ArrayList에 저장되어 있는 모든 데이터 출력
	public void printStudent() {
		for (int i = 0; i < list.size(); i++) { // .size : 현재 객체의 요소 개수 반환 Returns the number of elements in this list.
			System.out.println(list.get(i));
			// 이때 객체 배열과는 다르게 list.size로 정해줬기 때문에 List가 기본적으로 10칸을 배정한다고 해도 null이 출력되진 않음
		}
		
	}

	
	// 매개변수로 전달받은 index에 해당하는 요소 삭제
	public void removeStudent(int index) {
		// TODO Auto-generated method stub
		list.remove(index);
		
	}
	
	

}
