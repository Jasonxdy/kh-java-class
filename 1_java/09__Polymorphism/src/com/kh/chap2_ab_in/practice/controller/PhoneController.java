package com.kh.chap2_ab_in.practice.controller;

import com.kh.chap2_ab_in.practice.model.vo.GalaxyNote9;
import com.kh.chap2_ab_in.practice.model.vo.Phone;
import com.kh.chap2_ab_in.practice.model.vo.V40;

public class PhoneController {
	private String[] result = new String[2];
	public String[] method() {
		Phone[] phone = new Phone[2];
		phone[0] = new GalaxyNote9();
		phone[1] = new V40();
		for (int i = 0; i < phone.length; i++) {

		}
		
		return result;
	}

}
