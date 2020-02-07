package algorithm.practice02;

import java.util.List;

public class main {
	
	public static void main (String[] args) {
		
		int i = 2; // È½¼ö
		int[] arr= new int[31];
		arr[0] = 1;
		System.out.print(arr[0] + "/ ");
		
		arr[1] = 1;
		System.out.print(arr[1] + "/ ");
		
		while (i<=30) {
			arr[i] = arr[i-2] + arr[i-1];
			System.out.print(arr[i] + "/ ");
			i++;
		}
		
		
		
		
	}

}
