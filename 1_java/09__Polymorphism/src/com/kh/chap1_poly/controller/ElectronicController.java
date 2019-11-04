package com.kh.chap1_poly.controller;

import com.kh.chap1_poly.model.vo.Computer;
import com.kh.chap1_poly.model.vo.Electronic;

public class ElectronicController {
	
	// 필드
	// 1. 부모타입 객체배열을 선언 및 할당하여 자식 객체들을 일괄 관리
	private Electronic[] elec = new Electronic[3];
	
	// 객체배열의 데이터 추가 시 인덱스를 관리 해주는 변수 선언
	public static int elecIndex = 0;
	
	
	// 2. 전자제품 등록
	// 외부에서 생성된 객체의 주소를 전달받아 필드에 있는 객체배열에 순서대로 저장하는 메소드 생성
	public void insert(Electronic el) {
		elec[elecIndex] = el;
		elecIndex++;
	}
	
	// 3. 전자제품 정보 조회
	// 전달받은 인덱스에 해당하는 배열 요소의 정보를 출력
	
	public Electronic select(int index) {
//  접근제한자	  반환자료형	      자료명
		return elec[index]; // elec[index] = 객체 주소를 반환함
	}
	
	// 4. 상품 판매 가능여부 체크
	public String sell(Electronic e) {
		if(e instanceof Computer) {
			return "품절";
		} else {
			return "판매가능";
		}
	}


}
