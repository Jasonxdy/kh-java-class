package com.kh.chap02_byte.run;

import com.kh.chap02_byte.model.dao.FileByteDAO;

public class Run {
	public static void main(String[] args) {
		FileByteDAO fdao = new FileByteDAO();
//		fdao.fileOpen();
		fdao.fileSave();
	
	}

}
