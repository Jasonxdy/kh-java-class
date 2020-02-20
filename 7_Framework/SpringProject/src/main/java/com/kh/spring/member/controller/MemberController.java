package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

// @Controller
// 프레젠테이션 레이어, 웹 애플리케이션에서 View에서  전달받은 웹 요청과
// 응답을 처리하는 클래스임을 나타냄 + Bean 등록

// @SessionAttributes
// Model에 담긴 데이터 중 Key 값이 
// @SessionAttributes의 매개변수에 작성된 값과 같은 경우 
// 해당 데이터의 scope를 session으로 변경

@Controller
@RequestMapping("/member/*") // 내부 메소드 레벨에서 매핑되는 주소에 공통되는 부분 작성.
@SessionAttributes({"loginMember", "msg"}) // session scope로 넘겨줄 것들의 key값 작성
public class MemberController {
	
	// @Autowired 사용 시 bean scanning을 통해 등록된 bean 중 
	// 알맞은 bean을 의존성 주입 (DI) 해줌 --> servlet-context.xml에 있는 component-scan이 해줌
	@Autowired
	private MemberService memberService;
//	private MemberService memberService = new MemberServiceImpl(); --> 원래는 이렇게 해야하지만, Bean으로 등록되어 있기 때문에 이렇게 안해도 됨
	
	
	
//	// 1. HttpServletRequest를 이용하여 파라미터 받기 (기존 servlet 방식)
//	@RequestMapping(value="login", method=RequestMethod.POST)
//	//			/member/login 요청을 매핑하는 메소드  + post 방식 요청만!
//	public String memberLogin(HttpServletRequest request) {
//		// 매개변수로 HttpServletRequest를 작성하면 스프링 컨테이너가
//		// 자동으로 요청 페이지의 HttpServletRequest 객체를 매개변수로 주입해줌.
//		String memberId = request.getParameter("memberId");
//		String memberPwd = request.getParameter("memberPwd");
//		
//		System.out.println("memberId : " + memberId);
//		System.out.println("memberPwd : " + memberPwd);
//		
//		return "main";
//	}
	
	
	
	
	// 2. @RequestParam 어노테이션 사용 
	/*
	 * request 객체를 이용하여 파라미터를 전달받는 어노테이션
	 * 
	 * @RequestParam("name속성값") String 원하는변수명 // 이런 형태로 작성
	 * 
	 * 만약 요청 페이지의 input 값(value)이 비어있다면 "" (빈 문자열)로 전달됨. 
	 * 
	 */
//	@RequestMapping(value="login", method=RequestMethod.POST)
//	public String memberLogin(@RequestParam("memberId") String memberId, @RequestParam("memberPwd") String memberPwd) {
//		
//		System.out.println("memberId : " + memberId);
//		System.out.println("memberPwd : " + memberPwd);
//		
//		return "main";
//	}
	
	
	
	
	// 3. @RequestParam 어노테이션 생략
	/*
	 * @RequestParam 어노테이션 생략 시
	 * 매개변수명을 전달되는 파라미터의 name 속성 값과 똑같이 작성하면
	 * 해당 매개변수에 파라미가 매핑되어 자동 저장됨.
	 * 
	 * ** Annotation 생략은 코드를 읽는 가독성을 낮추므로 Annotation을 작성하는 것을 권장함!
	 * 
	 * @ModelAttribute와 @RequestParam 혼용 시 
	 * 둘 중 하나만 생략하고 나머지 하나는 명시하는 형태로 많이 사용됨
	 */
	
//	@RequestMapping(value="login", method = RequestMethod.POST)
//	public String memberLogin(String memberId, String memberPwd) {
//		
//		System.out.println("memberId : " + memberId);
//		System.out.println("memberPwd : " + memberPwd);
//		return "main";
//		
//	}
	
	
	
	
	// 4. @ModelAttribute를 이용한 파라미터 전달받기
	/*
	 * 요청 페이지에서 전달하는 파라미터가 많고 전달되는 파라미터들이 
	 * 특정 VO 클래스의 필드에 저장될 형태일 때 사용.
	 * 
	 * (주의사항)
	 * 1) 전달되는 파라미터의 name 속성값과 VO 클래스의 필드명이 같아야 한다.
	 * 2) VO 클래스에는 기본생성자 + setter가 반드시 존재해야 함.
	 * 
	 * --> 커맨드 객체
	 */
	
//	@RequestMapping(value="login", method = RequestMethod.POST)
//	public String memberLogin(@ModelAttribute Member member) {
//		
//		System.out.println(member.getMemberId() + " / " + member.getMemberPwd());
//		return "main";
//	}
	
	
	
	
//	// 5. @ModelAttribute 어노테이션 생략
//	@RequestMapping(value="login", method = RequestMethod.POST)
//	public String memberLogin(Member member, HttpSession session, Model model) {
//												// HttpSession session 이렇게 써주면 매개변수로 session이 얻어와짐... 그냥 사용하면 됨 (단 request는 이렇게 쓰지 않고 밑에처럼 model)
//												// Model은 응답으로 전달하고자 하는 데이터를 맵 형식(K,V)으로 담아 전달하는 역할.
//												// scope는 기본적으로 request임
////		System.out.println(member.getMemberId() + " / " + member.getMemberPwd());
//		
//		try {
//			Member loginMember = memberService.loginMember(member);
////			System.out.println("loginMember : " + loginMember);
//			
//			if(loginMember != null) {
//				session.setAttribute("loginMember", loginMember);
//			} else {
//				session.setAttribute("msg", "로그인 정보가 유효하지 않습니다");
//			}
//			
////			return "main"; // forward 방식
//			return "redirect:/main"; // redirect 방식
//							// /main : / = 최상위루트
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("errorMsg", "로그인 과정에서 오류 발생");
//			return "common/errorPage";
//		}
//	}
	
	
	
	
	
	
	// 6. @SessionAttributes 사용하기 --> 클래스 명 위쪽에 작성해야함
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String memberLogin(Member member, Model model) {
		// HttpSession session 이렇게 써주면 매개변수로 session이 얻어와짐... 그냥 사용하면 됨 (단 request는 이렇게 쓰지 않고 밑에처럼 model)
		// Model은 응답으로 전달하고자 하는 데이터를 맵 형식(K,V)으로 담아 전달하는 역할.
		// scope는 기본적으로 request임
//		System.out.println(member.getMemberId() + " / " + member.getMemberPwd());
		
		try {
			Member loginMember = memberService.loginMember(member);
//			System.out.println("loginMember : " + loginMember);
			
			
			if(loginMember != null) {
				model.addAttribute("loginMember", loginMember);
			} else {
				model.addAttribute("msg", "로그인 정보가 유효하지 않습니다");
			}
			
//			return "main"; // forward 방식
			return "redirect:/main"; // redirect 방식
			// /main : / = 최상위루트
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "로그인 과정에서 오류 발생");
			return "common/errorPage";
		}
	}
	
	
	
	// 로그아웃 기능
	@RequestMapping("logout") // method를 지정하지 않으면 전송 방식에 상관 없이 mapping됨 (servlet에서도 doGet, doPost등으로 알아서 분리됐었음)
	public String memberLogout(SessionStatus status/* HttpSession session */) {
//		세션 무효화
//		session.invalidate();
		
		// SessionStatus 객체 : 세션의 상태를 관리할 수 있는 객체
		// @SessionAttributes 사용 시 Session을 무효화시키기 위해서는 
		// SessionStatus 객체를 사용해야 함.
		status.setComplete();
		return "redirect:/main";
	}
	
	
	
	// 회원 가입 페이지 이동
	@RequestMapping("signUpForm")
	public String signUpForm() {
		return "member/signUpForm";
	}
	
	
	// 회원 가입
	@RequestMapping("signUp") 
	public String signUp(Member member, Model model, String phone1, String phone2, String phone3,				// 뒤에 memberInterest 변수 써야하므로 interest 사용
			String post, String address1, String address2, @RequestParam(value="memberInterest", required=false) String[] interest) {
															// @RequestParam의 required : 해당 파라미터가 필수인지 여부를 지정 (==null이 넘어와도 된다)
															// 							기본값은 true
		// 전화번호를 '-'를 구분자로 하여 하나의 String으로 합치기
		String memberPhone = phone1 + "-" + phone2 + "-" + phone3;
		
		// 주소를 ','를 구분자로 하여 하나의 String으로 합침
		String memberAddress = post + "," + address1 + "," + address2;
		
		// 관심 분야를 ','를 구분자로 하여 합침
		String memberInterest = null;
		
		if(interest != null) {
			memberInterest = String.join(",", interest);
		}
		
		Member signUpMember = new Member(member.getMemberId(), 
										member.getMemberPwd(), 
										member.getMemberName(), 
										memberPhone, 
										member.getMemberEmail(), 
										memberAddress, 
										memberInterest);
		
		try {
			int result = memberService.signUp(signUpMember);
			String msg = null;
			
			if(result > 0) msg = "가입 성공";
			else			msg = "가입 실패";
			
			model.addAttribute("msg", msg);
			return "redirect:/main";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "회원 가입 과정 중 오류 발생");
			return "common/errorPage";
		}
		
		/*
		 * 1. 비밀번호를 평문으로 저장하면 어떻게 될까?
		 * 	-> 범죄 행위
		 * 
		 * 2. SHA-512 해시함수를 이용한 암호화
		 *  -> 단방향 해시 함수 (복호화 불가능)
		 *  문제점 : 같은 비밀번호는 암호화 내용 (다이제스트)이 똑같다.
		 *  ex) 1234 -> abcd
		 *	-> 다이제스트가 많이 모이면 원래 비밀번호를 검색을 통해 찾아낼 가능성이 있음 (해킹에 취약). 
		 *
		 *  - 일반적인 장비로도 1초에 56억개의 다이제스트를 만들 수 있음.
		 *  
		 * 3. bcrypt 해시 함수를 이용한 암호화 (salting 기법 : 소금치기)
		 *  - 입력된 문자열을 암호화 할 때 바로 해시함수에 대입하는 것이 아닌
		 *    임의의 값 (salt)을 문자열에 추가하여 암호화를 진행
		 *    
		 *  ex) 1234 -> abcd
		 *  	1234 -> zxcv ... 이런식으로 같은 값 입력해도 다른값 전달
		 *  
		 *  Spring Security 모듈에서 지원해줌
		 *  -> pom.xml에 라이브러리 추가
		 *  -> 그다음 servlet-context.xml 복사해서 상위폴더로 복붙 -> spring-security.xml로 이름 변경
		 *  
		 */
		
	}
	
	
	/*
	 * @ResponseBody란?
	 * 메소드에서 리턴되는 값을 View를 통해 출력하는 것이 아닌 
	 * 리턴값을 HTTP Response Body에 담는 역할
	 * 	--> jsp로 화면이 이동되는 것이 아닌 기존 페이지로 데이터만 전달됨 
	 * 
	 * string, model and view --> view 이동이 목적
	 * ajax data, json --> Data 자체의 전달이 목적
	 * 
	 */
	
	// 아이디 중복 검사
	@ResponseBody
	@RequestMapping("idDupCheck")
	public String idDupCheck(String memberId, Model model) {
		try {
			return memberService.idDupCheck(memberId) == 0 ? true+"" : false+"";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "아이디 중복 검사과정 중 오류 발생");
			return "common/errorPage";
		}
	}
	
	

}
