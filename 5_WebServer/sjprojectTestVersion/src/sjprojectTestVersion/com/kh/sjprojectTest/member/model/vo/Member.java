package sjprojectTestVersion.com.kh.sjprojectTest.member.model.vo;

import java.sql.Date;

public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberNm;
	private String memberPhone;
	private String memberEmail;
	private String memberAddr;
	private String memberInterest;
	private Date memberEnrollDate;
	private char memberStatus;
	private char memberGrade;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Member(int memberNo, String memberId, String memberNm, String memberPhone, String memberEmail,
			String memberAddr, String memberInterest, Date memberEnrollDate, char memberStatus, char memberGrade) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNm = memberNm;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddr = memberAddr;
		this.memberInterest = memberInterest;
		this.memberEnrollDate = memberEnrollDate;
		this.memberStatus = memberStatus;
		this.memberGrade = memberGrade;
	}



	public Member(int memberNo, String memberId, String memberPwd, String memberNm, String memberPhone,
			String memberEmail, String memberAddr, String memberInterest, Date memberEnrollDate, char memberStatus,
			char memberGrade) {
		
		this(memberNo, memberId, memberNm, memberPhone, memberEmail, memberAddr, memberInterest, memberEnrollDate, memberStatus, memberGrade);
		this.memberPwd = memberPwd;
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

	public String getMemberNm() {
		return memberNm;
	}

	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
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

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
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

	public char getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(char memberStatus) {
		this.memberStatus = memberStatus;
	}

	public char getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(char memberGrade) {
		this.memberGrade = memberGrade;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberNm="
				+ memberNm + ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberAddr="
				+ memberAddr + ", memberInterest=" + memberInterest + ", memberEnrollDate=" + memberEnrollDate
				+ ", memberStatus=" + memberStatus + ", memberGrade=" + memberGrade + "]";
	}
	
	
	
}
