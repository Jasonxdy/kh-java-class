package com.kh.chap2_String.controller;

import java.util.Scanner;

public class StringController {
	
	// String Class
	// 문자열 값이 수정 불가능한 불변 (immutable) 클래스 // mutant가 돌연변이인 어원과 같음
	// 문자열 내용 수정 시 새로운 문자열이 할당되어 그 주소를 반환받음
	
	public void method1() {
		String str1 = "abc";
		
		// str1.hashCode() -> 이때 String이 hashCode를 오버라이딩 해놨기 때문에 직접적인 hashCode 볼 수 없음..
		// String.hashCode()는 char값을 해시코드화시킨 것이지 그 주소를 알려주진 않음 --> 따라서, 원하는 주소값이 변형된 hashCode 출력 불가
		
		
		// System.identifyHashCode(obj) 사용해야 함 --> 객체의 실제 주소를 이용하여 만든 hashCode를 반환해주는 기능
		System.out.println("str1 : " + System.identityHashCode(str1));
		// 실행 결과 : 796533847
		
		str1 += "def";
		System.out.println("str1 : " + System.identityHashCode(str1));
		// 실행 결과 : 1449621165
		// 즉 새로운 객체를 생성했다는 의미..(기존의 str1이 참조하던 객체는 GC가 없애줄 것)
		
		String str2 = "abcdef";
		System.out.println("str2 : " + System.identityHashCode(str2));
		// 실행 결과 : 1627960023
		
		String str3 = "abcdef";
		System.out.println("str3 : " + System.identityHashCode(str3));
		// 실행 결과 : 1627960023 (str2와 동일)
		// str2와 str3은 같은 String 객체를 참조하고 있다
		// --> 즉 String 리터럴로 만든 값은 StringPool에 담기고, 이후에 똑같은 String 리터럴이 언급이 될때 새롭게 생성되는 것이 아니라 기존에 만들어져있던 StringPool에 기존에 생성되어 있던 값의 주소를 저장한다
		// cf> abc + def 한것은 자바 구동상 뭔가 사이에 있는듯...? 주소가 다름
		
		
		
		String s1 = "Hello";
		String s2 = new String("Hello"); //String 클래스의 생성자 활용
		
		System.out.println("s1 값 : "+s1);
		System.out.println("s2 값 : "+s2);
		// 결과 : Hello로 동일
		
		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
		// 결과 : 주소값이 다르다..!
	}
	
	public void method2() {
		
		//StringBuffer 기본생성자 생성 시 버퍼 크기 : 16
		StringBuffer sb = new StringBuffer();
		System.out.println("sb의 버퍼 크기 : " + sb.capacity());
		
		//StringBuffer 매개변수 있는 것 생성 시 버퍼 크기 : 8+24
		StringBuffer sb2 = new StringBuffer("java api");
		System.out.println("sb2의 버퍼 크기 : " + sb2.capacity());
		
		// 가변 vs 불변 확인
		System.out.println("sb의 해시코드 : " + sb.hashCode()); // 오버라이딩 안되어있어서 사용 가능... StringBuffer이므로
		
		//StringBuffer에 값 추가
		sb.append("Hello World"); // StringBuffer는 .append() 사용해야 함
		
		System.out.println("sb : " + sb);
		System.out.println("변화된 sb의 해시코드 : " + sb.hashCode()); // 변경된 후에도 해시코드 변화 없음
		
		sb.append(sb); // sb에 기존 sb값을 한번 더 추가
		System.out.println("sb : " + sb);
		System.out.println("sb 버퍼 크기 : " + sb.capacity()); // 버퍼 크기 : 34 = 기존 16*2 + 2
		System.out.println("또 변화된 sb의 해시코드 : " + sb.hashCode()); // 버퍼 크기를 늘려도 해시코드 변화 없음
	}
	
	public void method3() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("계산할 식을 입력하세요 : ");
		// 1 + 2 -> 한번에 입력하되, 띄어쓰기를 구분자로 사용함
		String input = sc.nextLine();
		
		//구분자를 사용하여 string을 배열로 하나 하나 따로 String 배열로 저장함
		String arr[] = input.split(" "); // 공백을 구분자로 입력된 문장을 String 배열로 반환
		
		// 입력된 값 중 숫자부분을 정수로 변환
		int num1 = Integer.parseInt(arr[0]);
		int num2 = Integer.parseInt(arr[2]);
		
		switch(arr[1]) {
		case "+" : System.out.println(num1 + num2); break;
		case "-" : System.out.println(num1 - num2); break;
		case "*" : System.out.println(num1 * num2); break;
		case "/" : System.out.println(num1 / num2); break;
		case "%" : System.out.println(num1 % num2); break;
		default : System.out.println("연산자를 잘못입력함.");
		}
		//"홍길동,20,서울,남자" -> 이런식으로 입력 받았을때 split 이용하여 따로 저장
		
		
	}

	
	
	
	
}
