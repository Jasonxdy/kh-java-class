package com.kh.chap5_properties.run;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Run {
	public static void main(String[] args) {
			
		// properties : K,V 모두 String 타입인 Map
		
		Properties prop = new Properties(); // 무조건 타입제한 String으로
		HashMap<String, String> map  // 이건 Generic을 일부러 String으로 걸어준 HashMap
			= new HashMap<String, String>();
		
		//Properties는 무조건 K, V가 String이기 때문에, 별도의 제네릭을 사용한 타입 제한이 필요없음.
		
		// setProperty(key, value) : 값 추가
		prop.setProperty("List", "ArrayList");
		prop.setProperty("Set", "HashSet");
		prop.setProperty("Map", "HashMap");
		
		System.out.println(prop);
			
		// getProperty(key) : key값 입력 시 value값 나옴 (map에서의 get은 객체를 꺼내오는 반면 차이가 있음)
		System.out.println(prop.getProperty("List"));
		
		
		// store (OutputStream os, String Comments)
		// -> Properties 객체에 담긴 K,V 값을 파일로 출력
		try {
			prop.store(new FileOutputStream("test.properties"), "collections");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// .store는 2종류(바이트단위(~~Stream)/문자단위(Reader/Writer)
		
			
			
	}

}
