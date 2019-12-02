package com.kh.variable;

public class E_OverFlow {
	
	// 오버플로우 확인하기
	public void overflow() {
		
		// 오버플로우는 대입연산자 이외의 상황에서 발생 가능
		byte bNum = 127; //바이트 양의 최댓값
		System.out.println("연산 전 : " + bNum); //127
		bNum = (byte)(bNum + 1);
		System.out.println("연산 후 : " + bNum); // -128
		
		byte bNum2 = 127;
		int result = bNum2 + 1;
		System.out.println("result : " + result);
		
		float fNum = 12.001f;
		double dNum = fNum + 1;
		System.out.println("dNum = " + dNum);
		
	}

}
