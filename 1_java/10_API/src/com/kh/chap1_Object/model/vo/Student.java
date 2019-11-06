package com.kh.chap1_Object.model.vo;

public class Student extends Object { // extends Object는 생략된 것.. JVM이 추가해주나?
	
	// 필드
	private String name;
	private String classRoom;
	private int age;
	private int point;
	
	// Ctrl + space + enter
	public Student() {}

	// Shift + Alt + S -> O
	public Student(String name, String classRoom, int age, int point) {
		super();
		this.name = name;
		this.classRoom = classRoom;
		this.age = age;
		this.point = point;
	}

	// 메소드
	// get/setter
	// Shift + Alt + S -> R
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getClassRoom() {
		return classRoom;
	}
	
	public void setClassRoom (String classRoom) {
		this.classRoom = classRoom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	// Object.toString()
	// 객체의 정보 (필드)를 String 형태로 반환하는 메소드
	// 객체 내의 모든 정보를 한눈에 파악할 수 있도록 문자열에 담아 반환할 목적으로 정의된 메소드
	// --> 이러한 용도를 위해서 Object가 제공을 하고있다.
	// --> 필요한 형태로 오버라이딩하여 사용.
	
	
	//Alt + Shift + S --> toString Generate 사용해서 한 결과물
	@Override
	public String toString() {
		return "Student [name=" + name + ", classRoom=" + classRoom + ", age=" + age + ", point=" + point + "]";
	}
	// 오버라이딩하지 않고 그냥 객체 정보 출력시 API설명란처럼 객체의 저장정보가 나옴
	
	
	// Object.equals() : 객체가 가지고 있는 '값'이 동일한 값인지를 판별하는 기능
	@Override
	public boolean equals(Object obj) { //Object인 이유 : 모든 클래스는 object의 후손이므로, 클래스의 매개변수 형은 Object로해도 됨
		if(! (obj instanceof Student)) {
			// 전달받은 객체가 Student 타입의 객체인지 또는 Student를 상속받은 자식 객체인지 검사
			return false;
		}
		
		Student other = (Student)obj; // 자식 객체의 필드를 참조할 수 있게 Down-Casting해줌

		// 같은지 다른지 비교하는 알고리즘 --> 같은지 비교하는 것보다 다른걸 하나씩 없애는게 더 좋음
		if (!name.equals(other.name)) {  // 이 equals는 String 클래스가 오버라이딩한 equals
			return false;
		}
		if (!classRoom.equals(other.classRoom)) { 
			return false;
		}
		if (age!=other.age) {
			return false;
		}
		if (point!=other.point) {
			return false;
		}
		return true;
	}
	
	// VO 클래스 작성 시 꼭 작성해야 하는 내용
	// 1. 필드
	// 2. 생성자
	// 3. getter()/setter()
	// 4. toString()
	// 5. equals()
	

}
