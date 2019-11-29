package controller;

import model.dao.EmpDAO;

public class EmpController {

	
	
	public void selectAll() {
		
		EmpDAO empDAO = new EmpDAO();
		empDAO.selectAll();
		
	}

}
