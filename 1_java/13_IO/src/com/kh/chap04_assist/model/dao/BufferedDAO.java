package com.kh.chap04_assist.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedDAO {
	// Buffered ���� ��Ʈ��
	// ����¿� ���õ� �����͸� ���ۿ� ��� �ξ��ٰ� �Ѳ����� ��/����Ͽ�
	// ���� ������ ��� ��Ŵ
	// 	--> (����� ȸ�� ���ҷ� ���� �۾� �ӵ� ���)
	
	public void fileSave() {
		// BufferedWriter : ���� ��� ��� ���� ��Ʈ��
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter("c_buffer.txt"));
			// BufferedWriter ���� �� ���� ũ�⸦ �������� ������
			// 8192byte�� ũ��� �ʱ�ȭ�ȴ�
			
			bw.write("�ȳ��ϼ���\n");
			bw.write("�ݰ����ϴ�.");
			bw.newLine(); // ��½� �ٹٲ� �߰����ִ� �޼ҵ�
			
			bw.write("�� �԰��ͼ� ����.\n");
			// --> bw.write()�� ������ �ƴ� ���ۿ� ����ϰ� ����
			// �۾��� �Ϸ�ǰų� ���۰� ���� á�� �� ���Ϸ� ������ ������ ����ؾ� ��.
			// --> flush(), close()
			bw.flush(); // or close()
			// flush() : ������ �����͸� ���Ϸ� ���
			// close() : flush() + �ڿ� ��ȯ
			
			
		} catch (IOException e) {
			 e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void keyBoardInput() {
		/* Ű���� �Է� ���� ����
		 * 
		 * Ű���� �Է� 	->	Ű���� �Է� ���� -> �ڹ����α׷�	
		 * (����Ʈ)			(����Ʈ)		 -> (����Ʈ)
		 * : �ü������ �ؼ��ϴ°�
		 * 									  ^
		 * 							   ����Ʈ -> ���� ��ȯ ������Ʈ��
		 */
		
		BufferedReader br = null;
		try {
			br = new BufferedReader /* ���ڽ�Ʈ�� ���� ��� */
					(new InputStreamReader /* ����Ʈ -> ���� */
							(System.in /* ����Ʈ��� (Ű����) */));
			System.out.print("�Է� : ");
			String str = br.readLine();
			// .readLine() : �ٹٲ� ���ڰ� ������ ������ �о����
			System.out.println("�Էµ� �� : " + str);
		} catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
	
	
	
	
	
}