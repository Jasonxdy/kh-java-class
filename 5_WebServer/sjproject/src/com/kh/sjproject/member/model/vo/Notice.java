package com.kh.sjproject.member.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private int noticeCount;
	private Date noticeCreateDt;
	private Date noticeModifyDt;
	private String noticeStatus;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(int noticeNo, String noticeTitle, String noticeWriter, int noticeCount, Date noticeModifyDt) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeCount = noticeCount;
		this.noticeModifyDt = noticeModifyDt;
	}

	public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeWriter, int noticeCount,
			Date noticeCreateDt, Date noticeModifyDt, String noticeStatus) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.noticeCount = noticeCount;
		this.noticeCreateDt = noticeCreateDt;
		this.noticeModifyDt = noticeModifyDt;
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

	public Date getNoticeCreateDt() {
		return noticeCreateDt;
	}

	public void setNoticeCreateDt(Date noticeCreateDt) {
		this.noticeCreateDt = noticeCreateDt;
	}

	public Date getNoticeModifyDt() {
		return noticeModifyDt;
	}

	public void setNoticeModifyDt(Date noticeModifyDt) {
		this.noticeModifyDt = noticeModifyDt;
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
				+ ", noticeWriter=" + noticeWriter + ", noticeCount=" + noticeCount + ", noticeCreateDt="
				+ noticeCreateDt + ", noticeModifyDt=" + noticeModifyDt + ", noticeStatus=" + noticeStatus + "]";
	}
	
	
}
