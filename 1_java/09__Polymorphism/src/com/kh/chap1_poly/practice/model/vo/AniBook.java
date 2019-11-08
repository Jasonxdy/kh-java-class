package com.kh.chap1_poly.practice.model.vo;

public class AniBook extends Book {
	
//	- accessAge : int // 제한 나이
//	+ AniBook()
//	+ AniBook(title:String, author:String,
//	publisher:String, accessAge:int)
//	+ toString() : String

	private int accessAge;
	
	public AniBook() {
		// TODO Auto-generated constructor stub
	}

	public AniBook(int accessAge) {
		super();
		this.accessAge = accessAge;
	}

	@Override
	public String toString() {
		return "AniBook [accessAge=" + accessAge + "]";
	}
	
	
}
