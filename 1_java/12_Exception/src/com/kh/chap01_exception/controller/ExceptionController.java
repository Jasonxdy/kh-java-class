package com.kh.chap01_exception.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ExceptionController {
	
	/* Unchecked Exception
	 * - 별도의 예외처리를 하지 않아도 되는 예외 (Exception)
	 * - 주로 프로그래머의 부주의로 인한 경우가 많기 때문에 
	 * 예외처리 보다는 조건문 등을 이용한 코드 수정 필요
	 */
	
	public void method1() {

		// Scanner를 이용하여 두 수를 입력받아 나누는 프로그램 작성
		Scanner sc = new Scanner(System.in);

		System.out.println("입력받은 두 수 나누기");
		System.out.print("입력 1 : ");
		int num1 = sc.nextInt();
		System.out.print("입력 2 : ");
		int num2 = sc.nextInt();

		if (num2 == 0) {
			System.out.println("0으로 나눌 수 없습니다.");
		} else {
			System.out.println("num1 / num2 = " + num1 / num2);
		}
		// 이런식으로 예외를 막을 수 있다
	}
	
	// Checked Exception 
	// 특정 코드가 예외를 발생시킬 가능성이 있으므로 사용 시 반드시 예외처리를 해야하는 예외.
	public void method2() {
		
		// Scanner가 없던 시절의 키보드 입력 방법 사용함
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// System.in -> 키보드 입력을 나타내는데 이게 바이트코드로 입력되어 그것을 
		// 우리가 알아볼 수 있는 형태로 바꿔주는 코드
		
		System.out.print("문자열 입력 : ");
		try { 
			String str = br.readLine();
			
			System.out.println("입력한 문자열 : " + str);
			
		} catch (IOException e) {
			System.out.println("IOException 발생");
		}
		
		
	}
	
}
