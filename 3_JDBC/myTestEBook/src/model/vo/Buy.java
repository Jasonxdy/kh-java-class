package model.vo;


public class Buy {
	
	private int bookNo;
	private String memberId;
	
	public Buy() {
	}

	public Buy(int bookNo, String memberId) {
		super();
		this.bookNo = bookNo;
		this.memberId = memberId;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Buy [bookNo=" + bookNo + ", memberId=" + memberId + "]";
	}
	
	

}
