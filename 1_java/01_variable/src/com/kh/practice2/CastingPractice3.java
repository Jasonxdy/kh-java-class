package com.kh.practice2;

import java.util.Scanner;

public class CastingPractice3 {
	
	public void practice3() {
		Scanner sc = new Scanner(System.in);
		
		int iNum1 = 10;
		int iNum2 = 4;
		
		float fNum = 3.0f;
		
		double dNum = 2.5;
		
		char ch = 'A';
		
		System.out.println(iNum1/iNum2);
		System.out.println((int)(dNum));
		
		System.out.println(iNum2*dNum);
		System.out.println((double)(iNum1));
		
		System.out.println(((double)iNum1/iNum2));
		System.out.println(dNum);
		
		System.out.println((int)(fNum));
		System.out.println(iNum1/(int)(fNum));
		
		System.out.println(iNum1/fNum);
		System.out.println((double)(iNum1)/fNum);
		// 이게 조금 어려움
		System.out.println("'"+ch+"'");
		System.out.println((int)(ch));
		System.out.println((int)(ch)+iNum1);
		// 이것도 개선가능 : ch + iNum1 하면 됨 -> ch가 int로 강제 형변환됨
		System.out.println((char)(ch+iNum1));
		
	}

}
