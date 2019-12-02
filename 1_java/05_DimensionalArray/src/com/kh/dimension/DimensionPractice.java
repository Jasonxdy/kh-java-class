package com.kh.dimension;

import java.util.Arrays;
import java.util.Scanner;

public class DimensionPractice {
	
	public void practice1() {
		/*
		3행 3열짜리 문자열 배열을 선언 및 할당하고
		인덱스 0행 0열부터 2행 2열까지 차례대로 접근하여 “(0, 0)”과 같은 형식으로 저장 후 출력하세요.
		ex.
		(0, 0)(0, 1)(0, 2)
		(1, 0)(1, 1)(1, 2)
		(2, 0)(2, 1)(2, 2)
		*/
		
		String[][] arr = new String[3][3];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = "("+i+", "+j+")";
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	
	public void practice2() {
		/*
		4행 4열짜리 정수형 배열을 선언 및 할당하고
		1) 1 ~ 16까지 값을 차례대로 저장하세요.
		2) 저장된 값들을 차례대로 출력하세요.
		ex.
		1 2 3 4
		5 6 7 8
		9 10 11 12
		13 14 15 16
		*/
		
		int num =1;
		
		int[][] arr = new int[4][4];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = num++;
				System.out.printf("%2d ",arr[i][j]);
			}
			System.out.println();
		}
	}
	
	public void practice3() {
		/*
		4행 4열짜리 정수형 배열을 선언 및 할당하고
		1) 16 ~ 1과 같이 값을 거꾸로 저장하세요.
		2) 저장된 값들을 차례대로 출력하세요.
		ex.
		16 15 14 13
		12 11 10 9
		8 7 6 5
		4 3 2 1
		*/
		
		int num = 16;

		int[][] arr = new int[4][4];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = num--;
				System.out.printf("%2d ", arr[i][j]);
			}
			System.out.println();

		}
	}
	
	public void practice4() {
		/*
		4행 4열 2차원 배열을 생성하여 0행 0열부터 2행 2열까지는 1~10까지의 임의의 정수 값 저장 후
		아래의 내용처럼 처리하세요.
		ex.
		2 4 3 9
		10 2 2 14
		7 3 8 18
		19 9 13 62
		*/
		
		Scanner sc = new Scanner(System.in);
		int[][] arr = new int[4][4];
		
		for (int i = 0; i < arr.length; i++) {
			if (i < arr.length - 1) {
				arr[i][3] = 0;
				for (int j = 0; j < arr[i].length - 1; j++) {
					System.out.print(i + "행 " + j + "열 값 입력 (1~10사이 정수) : ");
					arr[i][j] = sc.nextInt();
					arr[i][3] += arr[i][j];
				}
			} else {
				for (int j = 0; j < arr.length; j++) {
					for (int k = 0; k < arr.length - 1; k++) {
						arr[i][j] += arr[k][j];
					}
				}
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
			System.out.println();
		}
	}
	
	public void practice5() {
		/*
		2차원 배열의 행과 열의 크기를 사용자에게 직접 입력받되, 1~10사이 숫자가 아니면
		“반드시 1~10 사이의 정수를 입력해야 합니다.” 출력 후 다시 정수를 받게 하세요.
		크기가 정해진 이차원 배열 안에는 영어 대문자가 랜덤으로 들어가게 한 뒤 출력하세요.
		(char형은 숫자를 더해서 문자를 표현할 수 있고 65는 A를 나타냄, 알파벳은 총 26글자)
		ex.
		행 크기 : 5
		열 크기 : 4
		T P M B
		U I H S
		Q M B H
		H B I X
		G F X I
		*/
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("행 크기 (1~10사이) : ");
			int row = sc.nextInt();
			System.out.print("열 크기 (1~10사이) : ");
			int clm = sc.nextInt();
			char[][] arr = new char[row][clm];

			if (1 <= row && row <= 10 && 1 <= clm && clm <= 10) {
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < clm; j++) {
						arr[i][j] = (char) ((int) (Math.random() * 27 + 65));
					}
				}
			} else {
				System.out.println("반드시 1~10 사이의 정수를 입력해야 합니다.");
				continue;
			}
			for (int i = 0; i < arr.length; i++) {
				System.out.print(Arrays.toString(arr[i]));
				System.out.println();
			}
			break;
		}
	}
	public void practice6() {
		/*
		String[][] strArr = new String[][] {{"이", "까", "왔", "앞", "힘"}, {"차", "지", "습", "으", "냅"}, {"원",
			"열", "니", "로", "시"}, {"배", "심", "다", "좀", "다"}, {"열", "히", "! ", "더", "!! "}};
			위의 초기화되어 있는 배열을 가지고 아래의 ‘[그림] 실습문제4 흐름’과 같은 방식으로 출력하세요.
			단, print()를 사용하고 값 사이에 띄어쓰기(“ “)가 존재하도록 출력하세요.
		*/
		String[][]strArr = {{"이", "까", "왔", "앞", "힘"}, {"차", "지", "습", "으", "냅"}, {"원","열", "니", "로", "시"}, {"배", "심", "다", "좀", "다"}, {"열", "히", "! ", "더", "!! "}};
		for (int i = 0; i < strArr.length; i++) {
			for (int j = 0; j < strArr.length; j++) {
				System.out.print(strArr[j][i]);
			}
			System.out.println();
		}
	}
	
	public void practice7() {
		/*
		사용자에게 행의 크기를 입력 받고 그 수만큼의 반복을 통해 열의 크기도 받아
		문자형 가변 배열을 선언 및 할당하세요.
		그리고 각 인덱스에 ‘a’부터 총 인덱스의 개수만큼 하나씩 늘려 저장하고 출력하세요.
		ex.
		행의 크기 : 4
		0열의 크기 : 2
		1열의 크기 : 6
		2열의 크기 : 3
		3열의 크기 : 5
		a b
		c d e f g h
		i j k
		l m n o p
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("행의 크기 : ");
		int row = sc.nextInt();
		char[][] arr = new char[row][];
		for (int i = 0; i < arr.length; i++) {
			System.out.print(i+"열의 크기 : ");
			int clm = sc.nextInt();
			arr[i] = new char[clm];
		}
		
		char a = 'a';
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = (char)(a++);
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void practice8() {
		/*
		ex.
		== 1분단 ==
		강건강 남나나
		도대담 류라라
		문미미 박보배
		== 2분단 ==
		송성실 윤예의
		진재주 차천축
		피풍표 홍하하
		
		위 문제에서 자리 배리 배치한 것을 가지고 학생 이름을 검색하여
		해당 학생이 어느 자리에 앉았는지 출력하세요.
		
		ex.
		== 1분단 ==
		강건강 남나나
		도대담 류라라
		문미미 박보배
		== 2분단 ==
		송성실 윤예의
		진재주 차천축
		피풍표 홍하하
		============================
		검색할 학생 이름을 입력하세요 : 차천축
		검색하신 차천축 학생은 2분단 두 번째 줄 오른쪽에 있습니다.
		*/
		
		Scanner sc = new Scanner(System.in);
		String[][] class1 = {{"강건강","남나나"},{"도대담","류라라"},{"문미미","박보배"}};
		String[][] class2 = {{"송성실","윤예의"},{"진재주","차천축"},{"피풍표","홍하하"}};
		System.out.print("검색할 학생 이름을 입력하세요 : ");
		String student = sc.nextLine();
		boolean flag = true;
		for (int i = 0; i < class1.length; i++) {
			for (int j = 0; j < class1[i].length; j++) {
				
				if(class1[i][j].equals(student)) {
					String row = null;
					String side = null;
					switch(i) {
					case 0 : row = "첫 번째 줄 "; break;
					case 1 : row = "두 번째 줄 "; break;
					case 2 : row = "세 번째 줄 "; break;
					}
					switch(j) {
					case 0 : side = "왼쪽"; break;
					case 1 : side = "오른쪽"; break;
					}
					System.out.println("검색하신 " + student+"학생은 1분단 " + row + side +"에 있습니다.");
					flag = false;
					break;
				}
			}
		}
		for (int i = 0; i < class2.length; i++) {
			for (int j = 0; j < class2[i].length; j++) {
				
				if(class2[i][j].equals(student)) {
					String row = null;
					String side = null;
					switch(i) {
					case 0 : row = "첫 번째 줄 "; break;
					case 1 : row = "두 번째 줄 "; break;
					case 2 : row = "세 번째 줄 "; break;
					}
					switch(j) {
					case 0 : side = "왼쪽"; break;
					case 1 : side = "오른쪽"; break;
					}
					System.out.println("검색하신 " + student+"학생은 2분단 " + row + side +"에 있습니다.");
					flag = false;
					break;
				}
			}
		}
		
		if(flag) {
			System.out.println("그런 학생은 없슴당");
		}
	}
	
	public void practice9() {
		/*
		String 2차원 배열 6행 6열을 만들고 행의 맨 위와 열의 맨 앞은 각 인덱스를 저장하세요.
		그리고 사용자에게 행과 열을 입력 받아 해당 좌표의 값을 'X'로 변환해 2차원 배열을 출력하세요.
		ex.
		행 인덱스 입력 : 4
		열 인덱스 입력 : 2
		0 1 2 3 4
		0
		1
		2
		3
		4 X
		*/
		
		Scanner sc = new Scanner(System.in);
		String[][] arr = new String[6][6];
		
		for (int i = 0; i < arr.length; i++) {
			if (i == 0) {
				arr[i][0] = " ";
				for (int j = 1; j < arr[i].length; j++) {
					arr[i][j] = j-1 + "";
				}
			} else {
				for (int j = 0; j < arr[i].length; j++) {
					if (j == 0) {
						arr[i][j] = i - 1 + "";
					} else {
						arr[i][j] = " ";
					}
				}
			}
		}
		
		System.out.print("행 인덱스 입력 : ");
		int row = sc.nextInt();
		System.out.print("열 인덱스 입력 : ");
		int clm = sc.nextInt();
		arr[row+1][clm+1] = "X";
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
