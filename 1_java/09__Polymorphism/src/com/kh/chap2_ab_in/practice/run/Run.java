package com.kh.chap2_ab_in.practice.run;

import com.kh.chap2_ab_in.practice.controller.PhoneController;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] print = new String[2];
		for (int i = 0; i < print.length; i++) {
			System.out.println(new PhoneController().method()[i]);
			System.out.println();
		}
	}
}
