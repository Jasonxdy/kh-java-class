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

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

// 프레젠테이션 레이어, 웹 애플리케이션에서 View에서  전달받은 웹 요청과
// 응답을 처리하는 클래스임을 나타냄 + Bean 등록
@Controller
@RequestMapping("/member/*") // 내부 메소드 레벨에서 매핑되는 주소에 공통되는 부분 작성.
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
	
	
	
	
	// 5. @ModelAttribute 어노테이션 생략
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String memberLogin(Member member, HttpSession session, Model model) {
												// HttpSession session 이렇게 써주면 매개변수로 session이 얻어와짐... 그냥 사용하면 됨 (단 request는 이렇게 쓰지 않고 밑에처럼 model)
												// Model은 응답으로 전달하고자 하는 데이터를 맵 형식(K,V)으로 담아 전달하는 역할.
												// scope는 기본적으로 request임
//		System.out.println(member.getMemberId() + " / " + member.getMemberPwd());
		
		try {
			Member loginMember = memberService.loginMember(member);
//			System.out.println("loginMember : " + loginMember);
			
			if(loginMember != null) {
				session.setAttribute("loginMember", loginMember);
			} else {
				session.setAttribute("msg", "로그인 정보가 유효하지 않습니다");
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
	public String memberLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	
	
	
	

}
