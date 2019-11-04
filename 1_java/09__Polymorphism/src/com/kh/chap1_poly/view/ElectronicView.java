package com.kh.chap1_poly.view;

import com.kh.chap1_poly.controller.ElectronicController;
import com.kh.chap1_poly.model.vo.Computer;
import com.kh.chap1_poly.model.vo.Tablet;

public class ElectronicView {
	
	public void display() {
		// 1. ElectronicController 객체 생성
		ElectronicController ec = new ElectronicController();
		
		// 2. ElectroniceController의 insert() 메소드를 이용하여 전자제품을 객체 배열에 추가하기
		
		ec.insert(new Computer("삼성", "삼성컴퓨터", 2000000, "i9-9700X", "RTX2080")); // Heap영역에 새로 생성된 Computer객체 (= Electronic 객체를 부모로 포함)의 주소값이 매개변수가 된다 --> elec[0] = 주소값이 저장됨
		ec.insert(new Tablet("삼성", "갤럭시탭S6", 1000000, true));
		ec.insert(new Tablet("애플", "아이패드7세대", 1200000, false));
		
		// 3. 반복문을 이용하여 객체배열에 저장된 제품 정보 모두 출력
		
		for(int i =0; i<ElectronicController.elecIndex; i++) {
			System.out.println(ec.select(i)); // 이때 원래 toString이 아닌 inform 메소드를 사용했으면 ec.select(i).inform을 했어야 하지만 toString은 만들어놓기만 하면 그냥 객체 주소를 불렀을때 객체 정보를 (?..만들어놓은...) 반환함
			/*
			 위의 것이 up-casting인데 toString이 자식의 것이 실행된 이유 : 동적 바인딩 때문 (한번 무슨뜻인지 생각해봐)
			 무슨 말이냐하면, 컴파일당시 vs 프로그램이 실행될때 메소드가 오버라이딩된 것 중에 어떤걸 실행할지 해석하는 차이로 정적바인딩, 동적바인딩 vs 정적바인딩으로 나뉜다
			 System.out.println(ec.select(i).toString());을 하면 .toString은 Electronic의 메소드인줄 알고 잇음. (정적 바인딩)
			 그러나 실제로 그냥 실행하면 컴퓨터가 실행하다가 오버라이딩된 Computer.toString()을 발견하고 이걸로 바꾼다 (원래 up-casting되면 자식객체 내의 메소드는 보이지 않는 반면, 이게 실행되어버린 이유)
			 즉 동적 바인딩은 up-casting이 이루어진 경우에만 생긴다고....
			 */
			
			// 4. 전달받은 객체의 타입이 Computer이면 "품절" 아니면 "판매 가능" 출력
			System.out.println(ec.sell(ec.select(i)));
		}
	}

}
