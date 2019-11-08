package com.kh.chap1_list.practice.model.compare;

import java.util.Comparator;

import com.kh.chap1_list.practice.model.vo.Music;

public class DescSinger implements Comparator<Music> {
	
	// Music에 정의된 가수명 오름차순 이외의 정렬을 위하여 생성한 클래스
	
	@Override
	public int compare(Music o1, Music o2) {
		// TODO Auto-generated method stub
		//o1을 기준으로 해서 o1이 o2보다 큰지 작은지 비교
		
		return -o1.getSinger().compareTo(o2.getSinger()); // 양수 음수 곱셈의 법칙에 의해 -를 붙여서 내림차순? 으로 하는듯...
		// o1이 더 사전적으로 앞이면 음수, o1이 사전적으로 뒤면 양수, o1 == o2이면 0이 반환
	}

}
