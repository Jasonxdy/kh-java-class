package model.vo;

import java.sql.Date;

public class Board {
	
	private int bNo; // 글 번호
	private String title; // 글 제목
	private String content; // 글 내용
	private Date createDate; // 글 작성일
	private String writer; // 작성자
	private int bookNo;
	// -- 마지막꺼 (deleteYN)는 안함
	
	public Board() {
	}
	
	

	public Board(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}



	public Board(int bNo, String title, Date createDate, String writer, int bookNo) {
		super();
		this.bNo = bNo;
		this.title = title;
		this.createDate = createDate;
		this.writer = writer;
		this.bookNo = bookNo;
	}



	public Board(int bNo, String title, String content, Date createDate, String writer, int bookNo) {
		super();
		this.bNo = bNo;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.writer = writer;
		this.bookNo = bookNo;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	@Override
	public String toString() {
		return "Board [bNo=" + bNo + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", writer=" + writer + ", bookNo=" + bookNo + "]";
	}
	
	
	

}
