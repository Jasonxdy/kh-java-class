package com.kh.objectArray.model.vo;

public class Book {
	
	// 필드
	private String title; // 제목
	private String author; // 저자
	private String publisher; // 출판사
	private int price; // 가격
	
	//생성자
	public Book() {}
	
	//매개변수 생성자
	public Book(String title, String author, String publisher, int price) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}
	
	// 메소드
	// getter/ setter 작성
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String inform() {
		return title + ", " + author + ", " + publisher + ", " + price;
	}
	
	
	
}
