package model.vo;

public class Book {
	
	private int bookNo;
	private String title;
	private String author;
	private String summary;
	private int price;
	private String content;
	
	public Book() {
	}
	
	

	public Book(int bookNo, String title, String author, String summary, int price) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.summary = summary;
		this.price = price;
	}
	
	


	public Book(int bookNo, String title, String author, int price, String content) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.price = price;
		this.content = content;
	}



	public Book(int bookNo, String title, String author, String content) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.content = content;
	}



	public Book(int bookNo, String title, String author, int price) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.price = price;
	}


	public Book(int bookNo, String title, String author, String summary, int price, String content) {
		this(bookNo, title, author, summary, price);
		this.content = content;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", title=" + title + ", author=" + author + ", summary=" + summary
				+ ", price=" + price + ", content=" + content + "]";
	}
	
	

}
