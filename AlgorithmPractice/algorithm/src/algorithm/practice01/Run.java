package algorithm.practice01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {

	/*
	 * 1. 학생정보들을 저장하고, 학생이름으로 검색했을때 학번을 출력하는 프로그램 작성.
	 * 
	 * - Student 클래스 생성 String name, no를 가짐 (이름과 학번)
	 * 
	 * 학생들을 ArrayList에 저장1
	 * 
	 * 검색을 하겠느냐 y 종료하고싶으면 n
	 * 
	 * 학생이름이있는경우 그학생의 학번을 저장 학생이름이 없으면, 없는 학생이름이라고 출력
	 */

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		List<Student> list = new ArrayList<Student>();
		list.add(new Student("조동영"));
		list.add(new Student("정환조"));
		list.add(new Student("김인호"));

		System.out.print("검색을 하시겠습니까? (y/n) : ");
		char sel = sc.nextLine().charAt(0);

		if (sel == 'n') {
			System.out.println("프로그램을 종료합니다.");
		} else {
			System.out.print("학생이름 검색 : ");
			String name = sc.nextLine();

			boolean flag = true;

			for (Student student : list) {
				if (student.getName().equals(name)) {
					System.out.print("해당 학생 학번 입력 : ");
					int no = sc.nextInt();
					sc.nextLine();
					student.setNo(no);
					flag = false;
					break;
				}
			}

			if (flag) {
				System.out.println("해당 학생은 존재하지 않습니다.");
			}

		}

	}

}
