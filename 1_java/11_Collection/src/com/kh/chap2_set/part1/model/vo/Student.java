package com.kh.chap2_set.part1.model.vo;

public class Student {
	
	private String name;
	private int age;
	private int score;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String name, int age, int score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", score=" + score + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		return true;
	}
	
//	@Override
//	public boolean equals(Object obj) {  // equals는 object의 메소드
//		// 객체가 가지고 있는 값이 동일한지 판별하는 메소드
//		if(!(obj instanceof Student)) {
//			return false;
//		} // 타입이 같이 않은경우 비교도 하지 말것
//		
//		// 같은 타입인 경우 비교할 수 있게 obj를 Student 타입으로 다운캐스팅
//		Student other = (Student)obj;
//		
//		if(!name.equals(other.name)) {
//			// name 값이 같지 않은 경우
//			return false;
//		}
//		
//		if(age != other.age) {
//			return false;
//		}
//		
//		if(score != other.score) {
//			return false;
//		}
//		return true;
//	}
//	
//	@Override
//	public int hashCode() {
//		// 같은 필드값을 가지고 있으면 같은 정수가 나올 수 있도록 오버라이딩
//		
//		final int PRIME = 31; // prime : 소수
//		// 31이라는 수가 해시코드로 변환 시 중복을 가장 최소화하면서 연산속도가 가장 우수한 숫자
//		// 2 * 31 = 62 --> 컴퓨터는 이게 엄청 오래걸린다.. 2를 31번 더함(곱하기가 없어서)
//		// 2 << 5 - 2 = 62 2의 이진수에서 5칸 왼쪽으로 이동... 띠용
//		
//		int result = 1;
//		result = PRIME * result + age;
//		result = PRIME * result + score;
//		result = PRIME * result + (name == null? 0 : name.hashCode()/*String의 해시코드*/);
//		return result;
//	}
	
	
	
	
	
	
	
	

}
