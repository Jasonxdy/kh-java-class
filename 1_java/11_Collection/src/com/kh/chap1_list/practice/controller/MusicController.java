package com.kh.chap1_list.practice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kh.chap1_list.practice.model.vo.Music;

public class MusicController {
	// ArrayList 객체 생성
	
//	- list : List = new ArrayList<Music>()
//			+ addList(music:Music):int
//			+ addAtZero(music:Music):int
//			+ printAll():int
//			+ searchMusic(title:String):Music
//			+ removeMusic(title:String):Music
//			+ setMusic(title:String, music:Music):Music
//			+ ascTitle():int
//			+ descSinger():int
	
	private List<Music> list = new ArrayList<Music>();
	Scanner sc = new Scanner(System.in);
//	1. 마지막 위치에 곡 추가 // addList() 실행
//	2. 첫 위치에 곡 추가 // addAtZero()
//	3. 전체 곡 목록 출력 // printAll()
//	4. 특정 곡 검색 // searchMusic()
//	5. 특정 곡 삭제 // removeMusic()
//	6. 특정 곡 정보 수정 // setMusic()
//	7. 곡명 오름차순 정렬 // ascTitle()
//	8. 가수명 내림차순 정렬 // descSinger()
//	9. 종료 // “종료” 출력 후 main()으로 리턴
//	메뉴 번호 선택 : >> 입력 받음
//	// 메뉴 화면 반복 실행 처리
	
	public boolean addList(Music music) {
	// 마지막 리스트에 객체 저장, 1 리턴
		return list.add(music);
	}
	
	public int addAtZero(Music music) {
	// 첫 번째 리스트에 객체 저장, 1 리턴
		list.add(0, music);

		// list 내부에 전달받은 music 객체가 포함되어 있는 경우
		if(list.contains(music)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public List<Music> printAll() {
	// 모든 객체 콘솔 창에 출력, 1 리턴
		return list;
	}
	
	public Music searchMusic(String title) {
	// 곡 명으로 객체 검색, 객체가 있으면 객체 정보 리턴, 없으면 null 리턴
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getTitle().equals(title)) {
				return list.get(i);
			} 			
		}
		return null;
	}
	
	public Music removeMusic(String title) {
	// 곡 명으로 객체 검색, 객체가 있으면 객체 정보 삭제, 없으면 null 리턴
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTitle().equals(title)) {
				return list.remove(i);
			}
		}
		return null;
	}
	
	public Music setMusic(String title) {
	// 곡 명으로 객체 검색, 객체가 있으면 객체 정보 수정, 없으면 null 리턴
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getTitle().equals(title)) {
				System.out.print("수정할 곡 명 : ");
				String newTitle = sc.nextLine();
				System.out.print("수정할 가수 명 : ");
				String newName = sc.nextLine();
				list.get(i).setTitle(newTitle);
				list.get(i).setSinger(newName);
				return list.get(i);
			}
		}
		return null;
	}
	
	public int ascTitle() {
	// 리스트 곡 명 오름차순 정렬, 제목이 같으면 가수 명으로 오름차순 정렬, 1 리턴
		return 0;
	}
	
	public int descSinger() {
	// 리스트 가수 명 내림차순 정렬, 1 리턴
		return 0;
	}
	
	

}
