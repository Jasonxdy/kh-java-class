package com.kh.chap1_poly.practice.model.vo;

public class AniBook extends Book {
	
//	- accessAge : int // ���� ����
//	+ AniBook()
//	+ AniBook(title:String, author:String,
//	publisher:String, accessAge:int)
//	+ toString() : String

	private int accessAge;
	
	public AniBook() {
		// TODO Auto-generated constructor stub
	}

	public AniBook(String title, String author, String publisher, int accessAge) {
		super(title, author, publisher);
		this.accessAge = accessAge;
	}

	@Override
	public String toString() {
		return super.toString() + "AniBook [accessAge=" + accessAge + "]";
	}

	
	
	
}