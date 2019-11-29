package com.kh.chap1_list.practice.model.compare;

import java.util.Comparator;

import com.kh.chap1_list.practice.model.vo.Music;

public class AscTitle implements Comparator<Music>{

	@Override
	public int compare(Music obj1, Music obj2) {
		/*
		String standard = obj1.getTitle();
		String object = obj2.getTitle();
		
		int result = standard.compareTo(object);
		*/
		String singer1 = obj1.getSinger();
		String singer2 = obj2.getSinger();
		/*
		if(result == 0) {
			return singer1.compareTo(singer2); 
		} 
		*/
		return singer1.compareTo(singer2);
	}

}
