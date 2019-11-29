package com.kh.chap1_list.practice.model.vo;

public class Music implements Comparable<Music> { // comparable<T> : T = type.. 비교할 타입을 정함
	
//	- title:String
//	- singer:String
//	+ Music()
//	+ Music(title:String, singer:String)
//	+ setter() / getter()
//	+ toString() : String
//	+ hashCode() : int
//	+ equals(obj:Object) : boolean
//	+ compareTo(o:Object) : int
	
	private String title;
	private String singer;
	
	public Music() {
		// TODO Auto-generated constructor stub
	}

	public Music(String title, String singer) {
		super();
		this.title = title;
		this.singer = singer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return title + " - " + singer;
	}

	@Override
	public int compareTo(Music o) { // 자기 자신을 기준으로 했을 때 다른 객체랑 비교함
		return title.compareTo(o.getTitle());
	}
}
