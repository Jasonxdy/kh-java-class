package com.kh.objectArray.controller;

import java.util.Scanner;

import com.kh.objectArray.model.vo.Book;

public class BookController {
	
	public void method1() {
		
		// Book 객체를 3개 만들어서 각각의 값을 알맞게 초기화를 한 후 
		// 책 제목을 입력받아 해당 책이 있으면 책 정보 출력, 없으면 "해당하는 책이 없습니다" 출력
		
		// 크기가 3인 Book배열을 선언 및 할당 
		Book books[] = new Book[3];
		Scanner sc = new Scanner(System.in);
		
		// Book 배열의 각 요소를 초기화
		books[0] = new Book("자바의정석", "남궁성", "도우출판", 30000);
		books[1] = new Book("C언어의정석", "조미현", "KH출판", 60000);
		books[2] = new Book("수학의정석", "홍성대", "성지출판", 60000);
		
		//Book 배열에 저장된 모든 책의 정보 출력
		/*
		System.out.println(books[0].inform());
		System.out.println(books[1].inform());
		System.out.println(books[2].inform());
		*/
		
		for (int i = 0; i<books.length;i++) {
			// books의 i번째 인덱스 요소가 가지고 있는 inform()이라는 메소드를 이용하여 해당 객체의 정보를 출력
			System.out.println(books[i].inform());
		}
		
		System.out.print("검색할 책 제목 : ");
		String title = sc.nextLine();
		
		
		//입력받은 값 검색
		boolean flag = true;
		for(int i =0; i<books.length; i++) {
			// books의 i번째 요소의 title 
			if (books[i].getTitle().equals(title)) {
				System.out.println(books[i].inform());
				flag = false;
				break; //메모리 아낌
			}
			
			// 검색의 기본 알고리즘은 무언가를 찾았으면 지표를 주고 못찾으면 그 지표를 통해서 결과없음을 알려줌
			}
		if(flag) {
			System.out.println("해당하는 책이 없습니다.");
		}
		
		
	}

}
