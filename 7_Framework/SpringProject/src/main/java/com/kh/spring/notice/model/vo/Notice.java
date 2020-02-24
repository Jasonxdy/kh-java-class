package com.kh.spring.notice.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private int noticeCount;
	private Date noticeCreateDate;
	private Date noticeModifyDate;
	private String noticeStatus;
	
	public Notice() {}

	
	
	
	public Notice(String noticeTitle, String noticeContent, String noticeWriter) {
		super();
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
	}

	public Notice(int noticeNo, String noticeTitle, String noticeContent) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}

	public Notice(int noticeNo, String noticeTitle, String noticeWriter, int noticeCount, Date noticeModifyDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeCount = noticeCount;
		this.noticeModifyDate = noticeModifyDate;
	}

	public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeWriter, int noticeCount,
			Date noticeModifyDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.noticeCount = noticeCount;
		this.noticeModifyDate = noticeModifyDate;
	}

	public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeWriter, int noticeCount,
			Date noticeCreateDate, Date noticeModifyDate, String noticeStatus) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.noticeCount = noticeCount;
		this.noticeCreateDate = noticeCreateDate;
		this.noticeModifyDate = noticeModifyDate;
		this.noticeStatus = noticeStatus;
	}


	public int getNoticeNo() {
		return noticeNo;
	}



	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}



	public String getNoticeTitle() {
		return noticeTitle;
	}



	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}



	public String getNoticeContent() {
		return noticeContent;
	}



	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}



	public String getNoticeWriter() {
		return noticeWriter;
	}



	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}



	public int getNoticeCount() {
		return noticeCount;
	}



	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}



	public Date getNoticeCreateDate() {
		return noticeCreateDate;
	}



	public void setNoticeCreateDate(Date noticeCreateDate) {
		this.noticeCreateDate = noticeCreateDate;
	}



	public Date getNoticeModifyDate() {
		return noticeModifyDate;
	}



	public void setNoticeModifyDate(Date noticeModifyDate) {
		this.noticeModifyDate = noticeModifyDate;
	}



	public String getNoticeStatus() {
		return noticeStatus;
	}



	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}



	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeWriter=" + noticeWriter + ", noticeCount=" + noticeCount + ", noticeCreateDate="
				+ noticeCreateDate + ", noticeModifyDate=" + noticeModifyDate + ", noticeStatus=" + noticeStatus + "]";
	}

	
	
	
}
