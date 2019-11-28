package com.kh.chap02_trycatch.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchController {
	
	
	/* try ~ catch ���� : Exception�� �߻��� ������ ���� ó���ϴ� ���� ó�� ���
	 * 
	 * - try : ���ܰ� �߻��� ���ɼ��� �ִ� �ڵ带 try ����{} ���� �ְ� �õ�
	 * 
	 * - catch : try ���� ������ �߻��ϴ� (�������� throws) Exception�� ��Ƴ��� ó���ϴ� ����� ���
	 * 
	 * - finally : try~catch ���� �� ���������� �ݵ�� �����ؾ��ϴ� �ڵ带 �ۼ��ϴ� �κ�
	 * 
	 */
	
	public void method1() {
		// Scanner�� �̿��Ͽ� �Էµ� �� �� ������
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("�Էµ� �� �� ������");
			System.out.print("�Է� 1 : ");
			int num1 = sc.nextInt();
			System.out.print("�Է� 2 : ");
			int num2 = sc.nextInt();
			
			System.out.println("�Է�1/�Է�2 : " + num1/num2);
			
		} catch (ArithmeticException e) {
			System.out.println("0���� �������� �����ϴ�");
		} catch (InputMismatchException e) {
			// catch ������ ������ �ۼ� ������.
			// ��, ���� �� �ۼ� �� ��� ���踦 �����ؾ� ��..!
			System.out.println("�߸� �Է��ϼ̽��ϴ�. ������ �Է����ּ���");
		} catch (Exception e) { // ��� exception�� ������ Exception Ŭ������ �ۼ�
			System.out.println("���� ��Ȳ �߻�");
//			catch ������ ������ �ۼ��� ���,
//			��� ���迡�� ���� �ڽĿ� ��ġ�� Exception�� ���� �ۼ��� ��..!
//			-> �ֳĸ�, try ���Ͽ��� exception�� �߻��ϸ� 
//			catch������ ���� ���κк��� Exception ��ġ ���θ� �˻��ϴµ�
//			�θ�Ÿ���� Exception�� ���κп� �ڸ���� �Ǹ� �ش� Ÿ���� �ڽ� Exception����
//			��� �����ϰ� ��
		}
	}
	
	
	public void method2() {
		System.out.println("BufferedReader�� �̿��Ͽ� �� �� ������");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			// ��ü ������ try catch�� �ȿ��� �ϴ� ���� : ������ ����ϴ� �Ͱ� �����Ƿ�
			// �����ܰ���� try ~ catch �ȿ��� ������ִ� ���� ����
			System.out.print("�Է� 1 : ");
			int num1 = Integer.parseInt(br.readLine()); // Integer wrapper class�� parseInt()���
			
			if(num1 == 1) return;
			
			System.out.print("�Է� 2 : ");
			int num2 = Integer.parseInt(br.readLine());
			
			System.out.println("num1/num2 : " + num1/num2);
			// ���⼭ ���� ArithmeticException�� �߻��� �� �־ if������ �ɸ����� �����ϱ� ���ؼ�
		} catch (IOException e) {
			System.out.println("�Է� �� ���� �߻�.");
		} catch (ArithmeticException e) {
			System.out.println("0���� ���� �� �����ϴ�.");
		} catch (NumberFormatException e) {
			System.out.println("������ �Է��� �ּ���.");
		} catch (Exception e) {
			System.out.println("���� ��Ȳ �߻�.");
		} finally {
			// finally : Exception �߻� ���ο� ���� ���� �ݵ�� ó���ؾ� �ϴ� ������ ����ϴ� �κ�

			
			// ��� �Ϸ�� ���� ��ȯ
			try {
				System.out.println("BufferedReader ��ȯ");
				br.close();
				System.out.println("���α׷� ����.");
			} catch (IOException e) {
				System.out.println("BufferedReader ��ȯ �� ���� �߻�");
			}
		}
		//System.out.println("���α׷� ����.");
		
	}
	
	public void method3() {
		// try ~ with ~ resource
		// try ���� ���� �� �Ű������� try ���ο��� ����� �ڿ��� �̸� �Բ� �����Ͽ�
		// try ���� ���� �� �ڵ����� ��ȯ�ϰ� �ϴ� ����.
		
		
		System.out.println("BufferedReader�� �̿��Ͽ� �� �� ������");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){


			System.out.print("�Է� 1 : ");
			int num1 = Integer.parseInt(br.readLine());
			
			System.out.print("�Է� 2 : ");
			int num2 = Integer.parseInt(br.readLine());
			
			System.out.println("num1/num2 : " + num1/num2);

		} catch (IOException e) {
			System.out.println("�Է� �� ���� �߻�.");
		} catch (ArithmeticException e) {
			System.out.println("0���� ���� �� �����ϴ�.");
		} catch (NumberFormatException e) {
			System.out.println("������ �Է��� �ּ���.");
		} catch (Exception e) {
			System.out.println("���� ��Ȳ �߻�.");
		} finally {

			System.out.println("���α׷� ����.");
		}
	}

}