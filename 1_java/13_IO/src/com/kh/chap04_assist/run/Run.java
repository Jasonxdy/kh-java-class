package com.kh.chap04_assist.run;

import com.kh.chap04_assist.model.dao.BufferedDAO;

public class Run {
	public static void main(String[] args) {
		BufferedDAO bDAO = new BufferedDAO();
		bDAO.fileSave();
		bDAO.keyBoardInput();
	}

}
