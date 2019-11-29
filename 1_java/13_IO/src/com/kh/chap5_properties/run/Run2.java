package com.kh.chap5_properties.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Run2 {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		Scanner sc = new Scanner(System.in);
		
		// load (FileInputStream fis)
		// 파일에 있는 정보를 불러와 Properties 객체에 저장
		while(true) {
			System.out.print("입력 : ");
			int num = sc.nextInt();
			if(num == 1) {
				try {
					prop.load(new FileInputStream("test.properties"));
					System.out.println(prop); //prop 객체에 저장되어 있는지 확인
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				System.out.println("List 정보 : " + prop.getProperty("List"));
				System.out.println("Set 정보 : " + prop.getProperty("Set"));
				System.out.println("Map 정보 : " + prop.getProperty("Map"));
				System.out.println("Collection 정보 : " + prop.getProperty("Collection"));				
			} // end if
		} // end while
	} // end main
} // end class
