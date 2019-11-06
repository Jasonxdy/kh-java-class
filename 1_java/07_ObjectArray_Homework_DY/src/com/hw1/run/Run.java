package com.hw1.run;

import com.hw1.model.vo.Employee;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee[] employ = new Employee[3];
		
		employ[0] = new Employee(0, null, null, null, 0, '0', 0, 0, null, null);
		employ[1] = new Employee(1, "홍길동", null, null, 19, 'M', 0, 0.0, "01022223333", "서울 잠실");
		employ[2] = new Employee(2, "강말순", "교육부", "강사", 20, 'F', 1000000, 0.01, "01011112222", "서울 마곡");
		
		for (int i = 0; i < employ.length; i++) {
			System.out.println("employ["+i+"] : " + employ[i].toString());
		}
		
		//emp[0] : 0, 김말똥, 영업부, 팀장, 30, M, 3000000, 0.2, 01055559999, 전라도 광주
		employ[0].setEmpName("김말똥");
		employ[0].setDept("영업부");
		employ[0].setJob("팀장");
		employ[0].setAge(30);
		employ[0].setGender('M');
		employ[0].setSalary(3000000);
		employ[0].setBonusPoint(0.2);
		employ[0].setPhone("01055559999");
		employ[0].setAddress("전라도 광주");
		
		//emp[1] : 1, 홍길동, 기획부, 부장, 19, M, 4000000, 0.3, 01022223333, 서울 잠실
		employ[1].setDept("기획부");
		employ[1].setJob("부장");
		employ[1].setSalary(4000000);
		employ[1].setBonusPoint(0.3);
		
		System.out.println("================================================================================");
		
		for (int i = 0; i < employ.length-1; i++) {
			System.out.println("employ["+i+"] : " + employ[i].toString());
		}
		
		// 직원 각각의 보너스가 적용된 1년 연봉을 계산하여 출력
		// 연봉 = (급여 + (급여 * 보너스포인트)) * 12
		/*
		김말똥의 연봉 : 43200000원
		홍길동의 연봉 : 62400000원
		강말순의 연봉 : 12120000원
		*/
		System.out.println("================================================================================");
		
		int sum = 0;
		for (int i = 0; i < employ.length; i++) {
			System.out.println(employ[i].getEmpName() + "의 연봉 : " + (int)(employ[i].getSalary()+(employ[i].getSalary()*employ[i].getBonusPoint()))*12);
			sum += (int)(employ[i].getSalary()+(employ[i].getSalary()*employ[i].getBonusPoint()))*12;
		}
		// 3명의 직원의 연봉 평균을 구하여 출력
		// 직원들의 연봉의 평균 : 39240000원
		System.out.println("직원들 연봉의 평균 : " + sum/3);
	}
}
