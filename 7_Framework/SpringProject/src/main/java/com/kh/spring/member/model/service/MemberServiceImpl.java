package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDAO;
import com.kh.spring.member.model.vo.Member;

@Service // Service 레이어, 비즈니스 로직을 가진 클래스라는걸 명시하는 것 + Bean 등록
public class MemberServiceImpl implements MemberService {
	
	
	@Autowired // 별도로 객체 생성 (new) 없이 의존성 주입 (dependency injection : DI)으로 알맞은 객체가 주입됨. 
	private MemberDAO memberDAO;

	@Override
	public Member loginMember(Member member) throws Exception {
		// Spring에서는 service단에서 SqlSession을 얻어오지 않음.
		// -> DAO에서 바로 얻어올 예정
		// 왜? SqlSession을 개발자가 생성 또는 얻어올 필요 없이 DAO에서 바로 DI를 통해
		// 얻어올 수 있고 추후 AOP를 이용하여 트랜잭션 처리도 할 수 있기 때문에..
		
		Member loginMember = memberDAO.selectMember(member);
		
		return loginMember;
	}
	
	

}
