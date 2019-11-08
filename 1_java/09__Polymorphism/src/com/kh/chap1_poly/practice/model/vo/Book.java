package com.kh.chap1_poly.practice.model.vo;

public class Book {
	
//	- title : String // 도서명
//	- author : String // 저자명
//	- publisher : String // 출판사명
//	+ Book()
//	+ Book(title:String, author:String,
//	publisher:String)
//	+ toString() : String
	
	private String title;
	private String author;
	private String publisher;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String title, String author, String publisher) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher=" + publisher + "]";
	}
	
	

}
