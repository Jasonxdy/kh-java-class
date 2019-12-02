package com.kh.chap03_char.run;

import com.kh.chap03_char.model.dao.FileCharDAO;

public class Run {
	public static void main(String[] args) {
		FileCharDAO fdao = new FileCharDAO();
//		fdao.fileOpen();
		fdao.fileSave();
	}

}
