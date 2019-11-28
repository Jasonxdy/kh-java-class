package com.kh.chap01_file.controller;

import java.io.File;
import java.io.IOException;

public class FileController {
	
	public void method1() {
		
		File f1 = new File("test.txt"); // 메모리 상에 일단 가상으로 지정해놓은 상태
		try {
			// 1. 현재 프로젝트에 test.txt 파일 생성하기
			f1.createNewFile(); 
			
			// 2. 기존에 존재하는 폴더에 파일을 생성
			File f2 = new File("C:\\dev\\test.txt"); // 이때 \는 String인줄 알고 2개써야함
			f2.createNewFile();
			
			// 3. 기존에 없는 폴더에 파일 생성
			// File f3 = new File("C:\\dev\\temp\\test.text");
			// f3.createNewFile();
			// -> 없는 경로에는 파일 생성 불가능 (IOException 발생)
			
			// 폴더 생성
			File f3 = new File("c:\\dev\\temp");
			f3.mkdir(); // 폴더 생성 메소드
			
			File f4 = new File("c:\\dev\\temp\\test.txt");
			f4.createNewFile();
			
			File f5 = new File("c:\\dev\\temp2\\test");
			f5.mkdirs(); // 경로상에 없는 모든 폴더 생성
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	public void method2() {
		// c:\\dev\\temp3 폴더가 있는지 검사 후 없으면 생성하고
		// temp3 폴더에 person.txt를 생성
		// --> 생성 성공 시 성공 메세지 출력
		
		File folder = new File("c:\\dev\\temp3");
		if(!folder.exists()) { // temp3 폴더가 존재하지 않으면 
			folder.mkdir(); // 폴더 생성
			System.out.println(folder.getName() + " 폴더를 생성하였습니다.");
		}
		
		try {
			File file = new File("c:\\dev\\temp3\\person.txt");
			if(file.createNewFile()) { // person.txt 생성 성공 시
				System.out.println(file.getName() + " 파일을 생성하였습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
