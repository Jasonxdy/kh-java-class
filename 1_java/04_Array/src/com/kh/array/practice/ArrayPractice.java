package com.kh.array.practice;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayPractice {
	
	public void practice1() {
		/*
		길이가 10인 배열을 선언하고 1부터 10까지의 값을 반복문을 이용하여
		순서대로 배열 인덱스에 넣은 후 그 값을 출력하세요.
		ex.
		1 2 3 4 5 6 7 8 9 10
		*/
		
		int[] arr = new int[10];
		for(int i=0; i<arr.length;i++) {
			arr[i] = i+1;
		}
		System.out.print(Arrays.toString(arr));
	}
	
	public void practice2() {
		/*
길이가 10인 배열을 선언하고 1부터 10까지의 값을 반복문을 이용하여
역순으로 배열 인덱스에 넣은 후 그 값을 출력하세요.
ex.
10 9 8 7 6 5 4 3 2 1
		 */
		
		
		int[] arr = new int[10];
		for(int i = 0; i<arr.length; i++) {
			arr[i] = arr.length-i;
		}
		System.out.println(Arrays.toString(arr));
	}
	
	public void practice3() {
		
		/*
사용자에게 입력 받은 양의 정수만큼 배열 크기를 할당하고
1부터 입력 받은 값까지 배열에 초기화한 후 출력하세요.
ex.
양의 정수 : 5
1 2 3 4 5
		 */
		Scanner sc = new Scanner(System.in);
		System.out.print("양의 정수 : ");
		int input = sc.nextInt();
		int[] arr = new int[input];
		for(int i =0; i<arr.length; i++) {
			arr[i] = i+1;
		}
		System.out.println(Arrays.toString(arr));
	}
	
	public void practice4() {
		/*
길이가 5인 String배열을 선언하고 “사과”, “귤“, “포도“, “복숭아”, “참외“로 초기화 한 후
배열 인덱스를 활용해서 귤을 출력하세요.
ex.
귤
		 */
		String[] arr = {"사과","귤","포도","복숭아","참외"};
		System.out.println(arr[1]);
	}
	
	
	public void practice5() {
		/*
		문자열을 입력 받아 문자 하나하나를 배열에 넣고 검색할 문자가 문자열에 몇 개 들어가 있는지
		개수와 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요.
		ex.
		문자열 : application
		문자 : i
		application에 i가 존재하는 위치(인덱스) : 4 8
		i 개수 : 2
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 : ");
		String str = sc.nextLine();
		System.out.print("문자 : ");
		char ch = sc.nextLine().charAt(0);
		//charAt(index): 문자열의 해당 index에 있는 문자 하나를 추출
		
		//2. 사용자가 입력한 문자열에서 문자 하나하나를 char 배열에 저장하기
		//2-1. 문자열의 길이만큼의 char 배열을 선언 및 할당
		char[] arr= new char[str.length()];
		// 스트링.length() : 메소드
		// 그림 떠올리기!!
		
		//2-1. 반복문을 통해 str.charAt(i)값을 arr[i]에 저장
		for(int i = 0; i<arr.length;i++) { //배열.length : 변수 개념 (메소드X)
			arr[i] = str.charAt(i);
		}
		
		//3. 검색할 문자가 배열 내에 몇개가 있는지 검색 + 검색된 인덱스 출력
		
		int count = 0;
		// 반복문 실행 전 구문 출력
		System.out.print(str + "에" + ch + "가 존재하는 위치 (인덱스) : ");
		
		// 반복문을 이용하여 배열의 각 요소에 접근
		for (int i = 0; i<arr.length;i++) {
			if(arr[i]==ch) { // 해당 인덱스의 요소가 검색될 문자와 같을 경우
				System.out.print(i + " ");
				count++;
			}
		}
		
		System.out.println(); //줄바꿈
		
		// 결과 출력
		System.out.println(ch + "개수 : "+count);
		
		
	}

	public void practice6() {
		/*
		“월“ ~ “일”까지 초기화된 문자열 배열을 만들고 0부터 6까지 숫자를 입력 받아
		입력한 숫자와 같은 인덱스에 있는 요일을 출력하고
		범위에 없는 숫자를 입력 시 “잘못 입력하셨습니다“를 출력하세요.
		ex.
		0 ~ 6 사이 숫자 입력 : 4 0 ~ 6 사이 숫자 입력 : 7
		금요일 잘못 입력하셨습니다.
		*/
		Scanner sc = new Scanner(System.in);
		char[] arr = {'월','화','수','목','금','토','일'};
		System.out.print("0~6 사이 숫자 입력 : ");
		int input = sc.nextInt();
		if (0<=input&&input<=6) {
			System.out.println(arr[input] + "요일");
		} else {
			
			System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	public void practice7() {
		/*
		사용자가 배열의 길이를 직접 입력하여 그 값만큼 정수형 배열을 선언 및 할당하고
		배열의 크기만큼 사용자가 직접 값을 입력하여 각각의 인덱스에 값을 초기화 하세요.
		그리고 배열 전체 값을 나열하고 각 인덱스에 저장된 값들의 합을 출력하세요.
		ex.
		정수 : 5
		배열 0번째 인덱스에 넣을 값 : 4
		배열 1번째 인덱스에 넣을 값 : -4
		배열 2번째 인덱스에 넣을 값 : 3
		배열 3번째 인덱스에 넣을 값 : -3
		배열 4번째 인덱스에 넣을 값 : 2
		4 -4 3 -3 2
		총 합 : 2
		*/
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int input = sc.nextInt();
		int[] arr = new int[input];
		int count =0;
		for(int i=0; i<arr.length;i++) {
			System.out.print("배열 "+i+"번째 인덱스에 넣을 값 : ");
			arr[i] = sc.nextInt();
			count += arr[i];
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(count);
	}

	public void practice8() {
		
		/*
		3이상인 홀수 자연수를 입력 받아 배열의 중간까지는 1부터 1씩 증가하여 오름차순으로 값을 넣고,
		중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어 출력하세요.
		단, 입력한 정수가 홀수가 아니거나 3 미만일 경우 “다시 입력하세요”를 출력하고
		다시 정수를 받도록 하세요.
		ex.
		정수 : 4
		다시 입력하세요.
		정수 : -6
		다시 입력하세요.
		정수 : 5
		1, 2, 3, 2, 1
		*/
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("정수 : ");
			int input = sc.nextInt();
			if (input % 2 == 1 && input >= 3) {
				// 입력받은 크기만큼의 int형 배열 선언 및 할당
				int[] arr = new int[input];

				// 배열 요소에 저장할 값을 만들 변수 생성
				int value = 0;
				// 배열의 중간까지는 value++, 배열 중간 이후부터는 value--

				// 반복문을 이용하여 배열 요소에 알맞은 값 대입
				for (int i = 0; i < arr.length; i++) {
					if (i <= arr.length / 2) {
						value++;
					} else {
						value--;
					}
					// 현재 배열 요소에 value 대입
					arr[i] = value;
					System.out.print(arr[i] + " ");
				} // for end.
				break;
			} else {
				System.out.println("다시 입력하세요.");
			}
		}
	}
	
	public void practice9() {
		/*
		3이상인 홀수 자연수를 입력 받아 배열의 중간까지는 1부터 1씩 증가하여 오름차순으로 값을 넣고,
		중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어 출력하세요.
		단, 입력한 정수가 홀수가 아니거나 3 미만일 경우 “다시 입력하세요”를 출력하고
		다시 정수를 받도록 하세요.
		ex.
		정수 : 4
		다시 입력하세요.
		정수 : -6
		다시 입력하세요.
		정수 : 5
		1, 2, 3, 2, 1
		*/
		
		Scanner sc = new Scanner(System.in);
		String[] chicken = {"양념", "후라이드", "간장"};
		System.out.print("치킨 이름을 입력하세요 : ");
		String search = sc.nextLine();
		boolean flag = true;
		for(int i =0; i<chicken.length; i++) {
			if(chicken[i].equals(search)) {
				System.out.println(search+"치킨 배달 가능");
				flag = false;
				break;
			}
		}
		if(flag) {
			System.out.println(search+"치킨은 없는 메뉴입니다.");
		}
	}
	
	public void practice10() {
		/*
		주민등록번호 성별자리 이후부터 *로 가리고 출력하세요.
		단, 원본 배열 값은 변경 없이 배열 복사본으로 변경하세요.
		ex.
		주민등록번호(-포함) : 123456-1234567
		123456-1******
		*/
		Scanner sc = new Scanner(System.in);
		System.out.print("주민등록번호(-포함) : ");
		String input = sc.nextLine();
		char[] pNum = new char[input.length()];
		for(int i=0;i<pNum.length;i++) {
			pNum[i] = input.charAt(i);		
		}
		char[] print = pNum;
		for(int i =0; i<print.length;i++) {
			if(i<8) {
				System.out.print(print[i]);
			} else {
				System.out.print("*");
			}
		}
	}
	
	public void practice11() {
		/*
		10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고
		1~10 사이의 난수를 발생시켜 배열에 초기화한 후 출력하세요.
		ex.
		9 7 6 2 5 10 7 2 9 6
		*/
		int[] arr = new int[10];
		for(int i =0; i<arr.length;i++) {
			arr[i] = (int)(Math.random()*10+1);
		}
		System.out.println(Arrays.toString(arr));
	}
	
	public void practice12() {
		/*
		10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고
		1~10 사이의 난수를 발생시켜 배열에 초기화 후
		배열 전체 값과 그 값 중에서 최대값과 최소값을 출력하세요.
		ex.
		5 3 2 7 4 8 6 10 9 10
		최대값 : 10
		최소값 : 2
		*/
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*10+1);
		}
		for(int i=1; i<arr.length;i++) {
			for(int j=0; j<i; j++) {
				if(arr[j]>arr[i]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
		System.out.println("최대값 : " + arr[9]);
		System.out.println("최소값 : " + arr[0]);
		
		
	}
	
	public void practice13() {
		
		/*
		10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고
		1~10 사이의 난수를 발생시켜 중복된 값이 없게 배열에 초기화한 후 출력하세요.
		ex.
		4 1 3 6 9 5 8 10 7 2
		*/
		
		int[] arr = new int[10];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*10+1);
			for(int j =0; j<i; j++) {
				if (arr[i]==arr[j]) {
					i--;
					break;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	//13번 문제 틀림
	
	public void practice14() {
		/*
		로또 번호 자동 생성기 프로그램을 작성하는데 중복 값 없이 오름차순으로 정렬하여 출력하세요.
		ex.
		3 4 15 17 28 40
		*/
		
		int[] arr = new int[6];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random()*45+1);
			for(int j=0; j<i;j++) {
				if(arr[i]==arr[j]) {
					i--;
					break;
				}
				if(arr[j]>arr[i]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}
// 14번도 다시 볼것 (난수에서 범위 설정.. 간단한건데 어렵게 생각함)	
	
	public void practice15() {
		/*
		문자열을 입력 받아 문자열에 어떤 문자가 들어갔는지 배열에 저장하고
		문자의 개수와 함께 출력하세요.
		ex.
		문자열 : application
		문자열에 있는 문자 : a, p, l, i, c, t, o, n
		문자 개수 : 8
		*/
		// 1. 사용자에게 문자열 입력받기
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자열 : ");
		String str = sc.nextLine();
		
		// 2. 입력받은 문자열을 char 배열에 저장
		char[] arr = new char[str.length()];
		for (int i = 0; i<arr.length;i++) {
			arr[i] = str.charAt(i);
		}
		System.out.print("문자열에 있는 문자 : ");
		
		// 3. 반복문을 이용하여 char 배열에서 중복값이 존재하는 경우 출력 X
		
		// 비교 기준 설정
		int count = 0;
		for (int i = 0; i<arr.length; i++) {
			boolean flag = true; // 깃발을 올리거나 내리거나 하는 방식
			// 중복 체크용 boolean
			
			for(int j = 0; j<i; j++) { // 비교 대상 선정
				if(arr[i]==arr[j]) {
					// 기준과 비교 대상이 같을 경우 == 중복 발생 시
					flag = false;
					break;
				}
			}
			
			//중복이 발생하지 않은 경우 출력 및 count 증가
			if(flag) {
				if(i==0) {
					System.out.print(arr[i]);
				} else {
					System.out.print(", " + arr[i]);
				}
				count++;
			}
		}
		System.out.println();
		System.out.println("문자 개수 : " + count);
	}
	
	public void practice16() {
		/*
		사용자가 입력한 배열의 길이만큼의 문자열 배열을 선언 및 할당하고
		배열의 인덱스에 넣을 값 역시 사용자가 입력하여 초기화 하세요.
		단, 사용자에게 배열에 값을 더 넣을지 물어보고 몇 개를 더 입력할 건지,
		늘린 곳에 어떤 데이터를 넣을 것인지 받으세요.
		사용자가 더 이상 입력하지 않겠다고 하면 배열 전체 값을 출력하세요.
		배열의 크기를 입력하세요 : 3
1번째 문자열 : 자바의 정석
2번째 문자열 : 알고리즘
3번째 문자열 : C프로그래밍
더 값을 입력하시겠습니까?(Y/N) : y
더 입력하고 싶은 개수 : 2
4번째 문자열 : 인간관계
5번째 문자열 : 자기계발
더 값을 입력하시겠습니까?(Y/N) : y
더 입력하고 싶은 개수 : 1
6번째 문자열 : 영단어600
더 값을 입력하시겠습니까?(Y/N) : n
[자바의 정석, 알고리즘, C프로그래밍, 인간관계, 자기계발, 영단어600]
		*/
		
		
		Scanner sc = new Scanner(System.in);
		System.out.print("배열의 크기를 입력하세요 : ");
		int input = sc.nextInt();
		sc.nextLine();
		String[] str = new String[input];
		for (int i = 0; i < str.length; i++) {
			System.out.print(i+"번째 문자열 : ");
			str[i] = sc.nextLine();
		}
		while(true) {
			System.out.print("더 값을 입력하시겠습니까? (Y/N) : ");
			char choice = sc.nextLine().charAt(0);

			if (choice=='Y') {
				System.out.print("더 입력하고 싶은 개수 : ");
				int add = sc.nextInt();
				sc.nextLine();
				String[] newArr = new String[str.length + add];

				for (int i = 0; i < newArr.length; i++) {
					if(i<str.length) {
						newArr[i] = str[i];
					} else {
						System.out.print(i+"번째 문자열 : ");
						newArr[i] = sc.nextLine();
					}
				}
				str = newArr;
			} else {
				break;
			}
		}
		
		System.out.println(Arrays.toString(str));
	}
	// 배열 복사개념... 메모리가 할당됐을때 그것을 확장하는 방법은 같은걸 복사하여 거기에 새로운 것을 추가하는 개념...
	
	
	
}
