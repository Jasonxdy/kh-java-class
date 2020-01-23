package jquery.model.vo;

public class User {
	private int no;
	private String name;
	private int age;
	private char gender;
	
	
	
	public User() {
		super();
	}


	public User(int no, String name, int age, char gender) {
		super();
		this.no = no;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "Member [no=" + no + ", name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
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


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}
	
	
	
	
}