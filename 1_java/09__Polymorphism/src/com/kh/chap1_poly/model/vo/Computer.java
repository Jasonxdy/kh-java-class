package com.kh.chap1_poly.model.vo;

public class Computer extends Electronic {
	private String cpu;
	private String graphic;
	
	public Computer() {
	}

	public Computer(String brand, String name, int price, String cpu, String graphic) {
		super(brand, name, price);
		this.cpu = cpu;
		this.graphic = graphic;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getGraphic() {
		return graphic;
	}

	public void setGraphic(String graphic) {
		this.graphic = graphic;
	}
	
	@Override
	public String toString() { //부모가 오버라이딩한 toString을 또 오버라이딩하여 사용
		return super.toString() + ", " + cpu + ", " + graphic;
	}

}
