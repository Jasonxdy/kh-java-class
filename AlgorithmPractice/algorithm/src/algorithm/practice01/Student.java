package algorithm.practice01;

import java.util.ArrayList;
import java.util.List;

public class Student {
	
	private String name;
	private int no;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Student(String name) {
		super();
		this.name = name;
	}



	public Student(String name, int no) {
		super();
		this.name = name;
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", no=" + no + "]";
	}
	
	
		
		
		
	

}
