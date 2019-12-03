package controller;

import java.util.ArrayList;

import model.dao.EmpDAO;
import model.vo.EMP;
import view.EmpView;

public class EmpController {

	
	private EmpView view = new EmpView();
	
	
public void selectAll() {
		
		// 1_1. View의 요청을 DB에 전달하고 결과를 반환 받을 EmpDao 객체 생성
		EmpDAO empDAO = new EmpDAO();
		
		// 1_2. DBMS 접속 및 전체 사원 정보를 조회하고 
		// 그 결과를 반환할 메소드인 EmpDAO.selectAll() 메소드 작성
		// --> DAO로 넘어가자!		
		
		
		// 1_7. DB 접속 확인을 위하여 EmpDAO.selectAll() 메소드 호출
		ArrayList<EMP> empList = empDAO.selectAll(); // --반환받은 ArrayList 담아줌
		// + 메인메뉴 case 1에 controller.selectAll() 호출
		
		
		
		// 1_13. 조회 결과에 따라 보여줄 View 지정
		// 1) 반환된 empList에 저장된 데이터가 있을 경우
		//	  -> 조회 결과를 모두 출력하는 View 메소드 호출
		
		// 2) empList가 비어있거나 조회 실패한 경우
		//	  -> 조회 결과가 없다는 메세지를 출력할 View 메소드 호출
		
		if (empList != null && !empList.isEmpty()) {
			// 1_14. 전체 조회 결과를 출력할 View 작성하기
			// -> EmpView.selectAll() 메소드 작성
			
			// 1_16. 전체 사원 정보를 출력하는 View 호출 
			view.selectAll(empList);
		} else {
			// 조회 결과가 없는 경우
			
			// 1_17. 조회 뿐만 아니라 DB처리 작업이 결과 값이 없거나 예외가 발생하는 경우
			// 문제 내용을 출력해줄 별도의 View 작성
			// EmpView.displayError() 메소드 작성
			
			
			// 1_19. 조회 결과가 없을 경우 메세지를 출력하는 View 호출
			view.displayError("조회 결과가 없습니다");
			
		}
	}

}
