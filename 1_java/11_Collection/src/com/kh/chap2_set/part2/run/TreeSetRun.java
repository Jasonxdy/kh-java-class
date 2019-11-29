package com.kh.chap2_set.part2.run;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Set<String> tree = new TreeSet<String>();
		tree.add(new String("aaaa"));
		tree.add(new String("bbbb"));
		tree.add(new String("cccc"));
		tree.add(new String("dddd"));
		tree.add(new String("eeee"));
		
		for (String s : tree) {
			System.out.println(s);
		}
	}

}
