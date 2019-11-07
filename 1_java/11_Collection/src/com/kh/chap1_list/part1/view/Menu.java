package com.kh.chap1_list.part1.view;

import java.util.Scanner;

import com.kh.chap1_list.part1.controller.ObjectArrayController;
import com.kh.chap1_list.part1.controller.StudentController;
import com.kh.chap1_list.part1.model.vo.Student;

public class Menu {
	
	private Scanner sc = new Scanner(System.in); // 스캐너를 필드로 선언 -> 이때 캡슐화의 원칙을 적용해야 해서 private으로 접근제한자 적용
	//private ObjectArrayController con = new ObjectArrayController();
	private StudentController con = new StudentController(); 

	public void display() {
		int sel = 0; // 입력받은 메뉴 번호를 저장할 변수 선언
		
		do {
			System.out.println("==== 학생 관리 프로그램 ====");
			System.out.println("1. 학생추가");
			System.out.println("2. 전체 조회");
			System.out.println("3. 학생 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴 선택 --> ");
			sel = sc.nextInt();
			sc.nextLine(); // 입력 버퍼에 남아있는 줄바꿈 문자 제거
			
			switch(sel) {
			case 1 : insertStudent(); break;
			case 2 : printStudent(); break;
			case 3 : removeStudent(); break;
			case 0 : System.out.println("프로그램 종료"); break;
			default : System.out.println("잘못 입력하셨습니다.");
			}
			
		} while(sel != 0);
	}
	
	
	// Sub 메뉴들은 무조건 private으로 접근제한자 써줘야 함
	
	private void insertStudent() { // DB쪽에서 데이터를 추가하는건 insert 사용
		System.out.println("[학생 추가]");
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		System.out.print("점수 : ");
		int score = sc.nextInt();
		sc.nextLine();
		
		// 학생 객체를 생성하자마자 해당 객체의 주소를 ObjectArrayController의 insertStudent()로 전달
		con.insertStudent(new Student(name,score)); // 객체 변수 선언 안하고 그냥 바로 넘김
	}
	
	private void printStudent() {
		System.out.println("[전체 학생 정보 출력]");
		con.printStudent();
	}

	
	// 학생 삭제 메소드
	// 입력받은 인덱스에 해당하는 학생 객체 삭제
	private void removeStudent() {
		System.out.println("[학생 삭제]");
		System.out.print("인덱스 입력 : ");
		int index = sc.nextInt();
		sc.nextLine();
		
		con.removeStudent(index);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
