package com.kh.spring.board.model.vo;

import java.sql.Date;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int boardCount;
	private Date boardModifyDate;
	private String boardWriter;
	private String boardCategory;
	
	public Board() {}
	
	
	public Board(String boardTitle, String boardContent, String boardCategory) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCategory = boardCategory;
	}


	public Board(int boardNo, String boardTitle, int boardCount, Date boardModifyDate, String boardWriter,
			String boardCategory) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardCount = boardCount;
		this.boardModifyDate = boardModifyDate;
		this.boardWriter = boardWriter;
		this.boardCategory = boardCategory;
	}

	

	public Board(int boardNo, String boardTitle, String boardContent, int boardCount, Date boardModifyDate,
			String boardWriter, String boardCategory) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCount = boardCount;
		this.boardModifyDate = boardModifyDate;
		this.boardWriter = boardWriter;
		this.boardCategory = boardCategory;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}


	public String getBoardTitle() {
		return boardTitle;
	}


	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public int getBoardCount() {
		return boardCount;
	}


	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}


	public Date getBoardModifyDate() {
		return boardModifyDate;
	}


	public void setBoardModifyDate(Date boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}


	public String getBoardWriter() {
		return boardWriter;
	}


	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}


	public String getBoardCategory() {
		return boardCategory;
	}


	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardCount=" + boardCount + ", boardModifyDate=" + boardModifyDate + ", boardWriter=" + boardWriter
				+ ", boardCategory=" + boardCategory + "]";
	}

	

	
	
}
