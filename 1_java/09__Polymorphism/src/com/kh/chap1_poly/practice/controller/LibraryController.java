package com.kh.chap1_poly.practice.controller;

import com.kh.chap1_poly.model.vo.Member;
import com.kh.chap1_poly.practice.model.vo.AniBook;
import com.kh.chap1_poly.practice.model.vo.Book;
import com.kh.chap1_poly.practice.model.vo.CookBook;

public class LibraryController {

//	- mem : Member // null로 명시초기화
//	- bList : Book[] // 크기 5 할당
//	+ insertMember(mem:Member) : void
//	+ myInfo() : Member
//	+ selectAll() : Book[]
//	+ searchBook(keyword:String) : Book[]
//	+ rentBook(index:int) : int
	
	private Member mem = null;
	private Book[] bList = new Book[5];
	
	{
		bList[0] = new CookBook("백종원의 집밥", "백종원", "tvN", true);
		bList[1] = new AniBook("한번 더 해요", "미티", "원모어", 19);
		bList[2] = new AniBook("루피의 원피스", "루피", "japan", 12);
		bList[3] = new CookBook("이혜정의 얼마나 맛있게요", "이혜정", "문학", false);
		bList[4] = new CookBook("최현석 날 따라해봐", "최현석", "소금책", true);
	}
	
	public void insertMember(Member mem) {
		this.mem = mem;
	}
	
	public Member myInfo() {
		return mem;
	}
	
	public Book[] selectAll() {
		return bList;
	}
	
	public Book[] searchBook(String keyword) {
		Book[] result = new Book[5];
		
		int count = 0;
		
		for (int i = 0; i < bList.length; i++) {
			Book b = bList[i];
			if (b.getTitle().contains(keyword)) {
				result[count] = bList[i];
				count++;
			}
		}
		if (count == 0) {
			return null;
		} else {
			return result;
		}
	}
	
	
	public int rentBook(int index) {
		int result = 0;
		if (bList[index] instanceof AniBook) {
			if (mem.getAge() < ((AniBook) (bList[index])).getAccessAge()) {
				result = 1;
			}
		} else if (bList[index] instanceof CookBook) {
			if (((CookBook) (bList[index])).isCoupon()) {
				mem.setCouponCount(mem.getCouponCount()+1);
				result = 2;
			}
		}
		return result;
	}
	
}
