package com.kh.chap1_list.part1.controller;

import com.kh.chap1_list.part1.model.vo.Student;

public class ObjectArrayController {
	// 객체 배열을 이용하여 Student 객체 관리
	
	// 학생 객체 배열 생성
	private Student[] arr = new Student[3];
	
	// 객체 배열의 인덱스 및 현재 저장된 요소 개수 관리용 변수
	private int size = 0;
	
	// 객체 배열에 전달받은 Student 객체 추가 메소드
	public void insertStudent(Student std) {
		
		if(size >= arr.length) {
			// 기존 크기보다 두배 큰 배열을 만들어서 기존 배열 내용을 복사 후 새로 생성된 배열의 주소를 arr이 참조하게 만들기
			Student[] newArr = new Student[arr.length * 2];
			System.arraycopy(arr, 0, newArr, 0, arr.length);
			arr = newArr; // 크기가 증가된 배열의 주소를 기존 배열 참조형 변수로 참조하게 만듬 (얕은 복사)
		}
		arr[size] = std;
		size++;
	}
	
	// 객체 배열에 저장되어 있는 모든 데이터 출력 
	public void printStudent() {
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == null) break;
			System.out.println(arr[i]); // == System.out.println(arr[i].toString()); 으로 컴파일러가 자동으로 생성해줌..왜냐면 Student의 toString 오버라이딩 해놔서 
			}
	}

	
	// 매개변수로 전달된 인덱스에 있는 요소를 삭제하고 중간에 공백이 없도록 당겨주기
	public void removeStudent(int index) {
		// TODO Auto-generated method stub --> Menu에서 메소드 써놓고 에러나는 부분 만들어서 create~ 눌러서 자동생성
		
		// 현재 접근한 배열 요소에 다음 배열 요소를 대입(덮어쓰기)
		// 버블정렬 느낌으로 끝에있는거 한칸씩 땡기기 -> 그리고 끝에를 null로 바꿈
		for (int i = index; i < arr.length-1; i++) {
			arr[i] = arr[i+1];
		}
		
		// 마지막 자리를 null로 변경
		arr[arr.length-1] = null;
		size--; // 추가할때마다 size가 하나씩 추가됐으므로... 다음꺼 입력을 위해 size 줄임
		
	}
	


	// 이런식으로 굉장히 복잡하기 때문에 Collection 사용
	
}
