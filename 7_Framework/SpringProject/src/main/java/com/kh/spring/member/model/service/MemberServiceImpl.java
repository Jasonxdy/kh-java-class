package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.vo.Member;

@Service // Service 레이어, 비즈니스 로직을 가진 클래스라는걸 명시하는 것 + Bean 등록
public class MemberServiceImpl implements MemberService {
	
	
	@Autowired // 별도로 객체 생성 (new) 없이 의존성 주입 (dependency injection : DI)으로 알맞은 객체가 주입됨. 
	private MemberDAO memberDAO;
	
	//암호화를 위한 객체 DI (의존성 주입)
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	

	@Override
	public Member loginMember(Member member) throws Exception {
		// Spring에서는 service단에서 SqlSession을 얻어오지 않음.
		// -> DAO에서 바로 얻어올 예정
		// 왜? SqlSession을 개발자가 생성 또는 얻어올 필요 없이 DAO에서 바로 DI를 통해
		// 얻어올 수 있고 추후 AOP를 이용하여 트랜잭션 처리도 할 수 있기 때문에..
		
		Member loginMember = memberDAO.selectMember(member);
		
		
		// bCryptPasswordEncoder.matches(입력된 pwd, 저장된 pwd) : true, false 반환... encode 된 2개를 비교해줌
		if(!bCryptPasswordEncoder.matches(member.getMemberPwd(), loginMember.getMemberPwd())) {
			loginMember = null;
		}
		
		return loginMember;
	}

	/*
	 * 
	 * Spring Framework는 트랜잭션을 처리할 방법을 지원해줌 (코드기반, 선언적)
	 * 
	 * 선언적 트랜잭션 처리 방법
	 *
	 * 1) <tx:advice>  -> AOP + tx 를 이용한 XML 작성 방식
	 *
	 * 2) @Transactional 어노테이션을 이용한 방식 (클래스, 인터페이스, 메소드에 작성 가능)
	 * * @Transactional 사용 조건
	 * 	- 인터페이스를 구현한 클래스로 선언된 bean은 메소드에 한해서 트랜잭션 처리가 적용됨
	 *  - 트랜잭션 처리를 위한 Transaction Manager가 bean으로 등록되어 있어야 한다.
	 *  --> root-context.xml에 등록
	 *   
	 * */
	
	
	
	// @Transactional은 기본적으로 RuntimeException이 발생했을 때만 rollback을 함 (기본값).
	// 하지만 DB 관련 Exception (ex. SQLException 등등)은 대부분 CheckedException이므로 
	// rollbackFor를 통해 롤백이 발생되는 예외를 지정
	// rollbackFor = Exception.class -> 예외 발생 시 rollback 해라
	@Transactional(rollbackFor = Exception.class) 
	@Override
	public int signUp(Member signUpMember) throws Exception {
		
		// 암호화된 비밀번호를 저장
		String encPwd = bCryptPasswordEncoder.encode(signUpMember.getMemberPwd());
		signUpMember.setMemberPwd(encPwd);
		
		int result = memberDAO.signUp(signUpMember);
		return result;
	}
	
	
	
	@Override
	public int idDupCheck(String memberId) throws Exception {
		return memberDAO.idDupCheck(memberId);
	}
	
	
}
