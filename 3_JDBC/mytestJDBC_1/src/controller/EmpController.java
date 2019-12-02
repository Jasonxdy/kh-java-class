package controller;

import java.util.ArrayList;

import model.dao.EmpDAO;
import model.vo.EMP;
import view.EmpView;

public class EmpController {

	
	private EmpView view = new EmpView();
	
	
	public void selectAll() {
		
		EmpDAO empDAO = new EmpDAO();
		ArrayList<EMP> empList = empDAO.selectAll();
		view.selectAll(empList);
	}

}
