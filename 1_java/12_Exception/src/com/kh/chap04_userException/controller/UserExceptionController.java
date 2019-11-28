package com.kh.chap04_userException.controller;

import java.util.Scanner;

import com.kh.chap04_userException.model.exception.UserException;

public class UserExceptionController {
	// ���� 3���� �Է¹޾� ���� ����ϴ� �޼ҵ�
	// ��, 0 �Է� �� �ٷ� ���α׷� ����
	
	public void method1() {
		try {
			method2();
			System.out.println("���α׷� ���� ����");
		} catch (UserException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}
	
	
	public void method2() throws UserException{
		Scanner sc = new Scanner (System.in);
		
		int sum = 0;
		int input = 0;
		for (int i = 1; i < 4; i++) {
			System.out.print("�Է� " + i + " : ");
			input = sc.nextInt();
			if(input == 0) {
				throw new UserException("���α׷� ���� ����");
			}
			sum += input;
		}
		
		System.out.println("�� : " + sum);
	
	}
	
	
}