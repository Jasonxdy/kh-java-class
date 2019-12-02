package com.kh.practice.point.controller;

public class RectangleController {
	

	public String calcPerimeter(int x, int y, int h, int w) {
		// TODO Auto-generated method stub
		return "둘레 : " + x + ", " + y + ", " + h + ", " + w + " / " + (2*h + 2*h);
	}

	public String calcArea(int x, int y, int h, int w) {
		// TODO Auto-generated method stub
		return "면적 : " + x + ", " + y + ", " + h + ", " + w + " / " + w*h;
	}

}
