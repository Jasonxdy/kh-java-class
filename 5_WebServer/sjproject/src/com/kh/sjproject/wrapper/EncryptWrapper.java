package com.kh.sjproject.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper{
	
	// 상속받은 HttpServletRequestWrapper 클래스는 기본생성자가 없음
	// 반드시 명시적으로  HttpServletRequest를 매개변수로 하는 생성자 작성이 필요
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	// ServletRequestWrapper의 getParameter() 오버라이딩 필요
	
	@Override
	public String getParameter(String key) {
		
		// 요청 데이터의 key에 대응되는 value를 저장할 변수 선언
		String value = "";
		
		if(key != null && // 전달된 parameter에 name 속성이 있으면서
				(key.equals("memberPwd")) || key.equals("pwd1")
				|| key.equals("newPwd") 
				|| key.equals("currentPwd") 
				|| key.equals("newPwd1")) { // 회원가입, 비밀번호 변경, 회원 탈퇴, 로그인시 전달되는 비밀번호값인 경우
			
			// 암호화 진행 -> SHA-512 해시함수 암호화
			// 해시함수란?
			// 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수
			
			value = getSha512(super.getParameter(key));
			
		} else { // 조건과 일치하지 않는 경우 그대로 전달
			value = super.getParameter(key);
		}
		
		return value;
		
	}
	
	
	
	/** SHA-512 해시함수를  사용하여 암호화
	 * @param pwd : String
	 * @return encPwd : String
	 */
	public static String getSha512(String pwd) {
		
		String encPwd = null;
		
		MessageDigest md = null;
		// -> 지정된 알고리즘에 따라 해시 함수를 진행하는 클래스
		
		try {
			// MessageDigest.getInstance("알고리즘명")
			// 지정된 알고리즘을 사용해 MessageDigest 객체를 작성하여 반환
			md = MessageDigest.getInstance("SHA-512");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// 암호화를 진행하기 위해서는 전달받은 문자열(비밀번호)를 바이트 배열로 변환해야 함.
		byte[] bytes = pwd.getBytes(Charset.forName("UTF-8"));
									// UTF-8로 변환한 후 바이트 배열로 변환함.
		
		// md 객체에 pwd 바이트 배열을 전달하여 갱신
		// -> 실제 암호화(해시함수 적용)
		md.update(bytes);
		// 결과 : 00101001110100... 이런식으로 됨
		
		// java.util.Base64 인코더를 이용하여 암호화된 바이트 배열을 인코딩해서 문자열로 출력
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		// 위의 결과를 Base64의 인코더를 사용하여 bas12Wsd.. 이런식으로 바꿈 (이진법은 너무 기니까)
		
		System.out.println("암호화 전 : " + pwd);
		System.out.println("암호화 후 : " + encPwd);
		
		return encPwd;
	} 

}
