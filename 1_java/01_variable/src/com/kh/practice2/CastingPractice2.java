package com.kh.practice2;

import java.util.Scanner;

public class CastingPractice2 {
	
	public void practice2() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("±¹¾î : ");
		double lang = sc.nextDouble();
		System.out.print("¿µ¾î : ");
		double eng = sc.nextDouble();
		System.out.print("¼öÇÐ : ");
		double mat = sc.nextDouble();
		
		
		System.out.println("ÃÑÁ¡ : " + (int)(lang+eng+mat));
		System.out.println("Æò±Õ : " + (int)((lang+eng+mat)/3));
		
		System.out.printf("ÃÑÁ¡ : %d\nÆò±Õ: %d\n", (int)(lang+eng+mat), 
										(int)(((lang+eng+mat))/3));
	}
 
}
