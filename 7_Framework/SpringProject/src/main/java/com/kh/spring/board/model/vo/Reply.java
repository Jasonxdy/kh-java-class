package com.kh.spring.board.model.vo;

import java.sql.Timestamp;

public class Reply {
	private int replyNo;
	private String replyContent;
	private Timestamp replyModifyDate;
	private int boardNo;
	private int memberNo; // insert, update, delete, select용 
	private String memberId; // select용
	
	public Reply() { }

	public Reply(int replyNo, String replyContent, Timestamp replyModifyDate, int boardNo, int memberNo,
			String memberId) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyModifyDate = replyModifyDate;
		this.boardNo = boardNo;
		this.memberNo = memberNo;
		this.memberId = memberId;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Timestamp getReplyModifyDate() {
		return replyModifyDate;
	}

	public void setReplyModifyDate(Timestamp replyModifyDate) {
		this.replyModifyDate = replyModifyDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyModifyDate=" + replyModifyDate
				+ ", boardNo=" + boardNo + ", memberNo=" + memberNo + ", memberId=" + memberId + "]";
	}
}
