package com.kh.spring.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private String memberAddress;
	private String memberInterest;
	private Date memberEnrollDate;
	private String memberStatus;
	private String memberGrade;
	
	// 기본생성자
	public Member() { }

	public Member(String memberId, String memberPwd) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
	}
	
	public Member(int memberNo, String memberId, String memberName, String memberPhone, String memberEmail,
			String memberAddress, String memberInterest, Date memberEnrollDate, String memberStatus,
			String memberGrade) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberInterest = memberInterest;
		this.memberEnrollDate = memberEnrollDate;
		this.memberStatus = memberStatus;
		this.memberGrade = memberGrade;
	}

	public Member(int memberNo, String memberId, String memberPwd, String memberName, String memberPhone,
			String memberEmail, String memberAddress, String memberInterest, Date memberEnrollDate, String memberStatus,
			String memberGrade) {
		this(memberNo, memberId, memberName, memberPhone, memberEmail, memberAddress, memberInterest, memberEnrollDate, memberStatus, memberGrade);
		this.memberPwd = memberPwd;
	}
	
	

	public Member(String memberId, String memberPwd, String memberName, String memberPhone, String memberEmail,
			String memberAddress, String memberInterest) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberInterest = memberInterest;
	}
	
	public Member(int memberNo, String memberId, String memberName, String memberPhone, String memberEmail,
			String memberAddress, String memberInterest) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberInterest = memberInterest;
	}
	
	
	public Member(String memberId, String memberPhone, String memberEmail, String memberAddress,
			String memberInterest) {
		super();
		this.memberId = memberId;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddress = memberAddress;
		this.memberInterest = memberInterest;
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

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberInterest() {
		return memberInterest;
	}

	public void setMemberInterest(String memberInterest) {
		this.memberInterest = memberInterest;
	}

	public Date getMemberEnrollDate() {
		return memberEnrollDate;
	}

	public void setMemberEnrollDate(Date memberEnrollDate) {
		this.memberEnrollDate = memberEnrollDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberAddress="
				+ memberAddress + ", memnerInterest=" + memberInterest + ", memberEnrollDate=" + memberEnrollDate
				+ ", memberStatus=" + memberStatus + ", memberGrade=" + memberGrade + "]";
	}
	
	
	
	
}