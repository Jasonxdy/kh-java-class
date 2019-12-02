package controller;

import java.sql.Connection;
import java.util.ArrayList;

import model.dao.EmpDAO;
import model.vo.EMP;
import view.EmpView;

public class EmpController {
	// View에서 전달받은 데이터를 가공처리(데이터 변환, 디코딩) 진행 후
	// DAO로 전달하는 역할
	
	// DAO로 부터 전달받은 결과에 따라 View(출력화면)을 결정하여
	// 인코딩 후 알맞은 View로 전달.
	
	// DB 처리 결과에 따른 View 출력을 위한 EmpView 객체 생성
	private EmpView view = new EmpView();
	// 서브메뉴, 성공, 실패(오류) 화면을 출력하는 역할.
	
	// 1. 전체 사원 정보 조회
	public void selectAll() {
		
		// 1_1. View의 요청을 DB에 전달하고 결과를 반환 받을
		//      EmpDAO 객체 생성
		EmpDAO empDAO = new EmpDAO();
		
		// 1_2. DBMS 접속 및 전체 사원 정보를 조회하고
		//	     그 결과를 반환할 메소드인
		//     EmpDAO.selectAll() 메소드 작성
		
		// 1_7. DB 접속 확인을 위하여 EmpDAO.selectAll() 메소드 호출
		ArrayList<EMP> empList = empDAO.selectAll();
		// + 메인메뉴 case 1에 controller.selectAll() 호출 작성
		
		
		// 1_13. 조회 결과에 따라 보여줄 View 지정
		// 1) 반환된 empList에 저장된 데이터가 있을 경우
		//    -> 조회 결과를 모두 출력하는 View 메소드 호출
		
		// 2) empList가 비어있거나 조회 실패한 경우
		//    -> 조회 결과가 없다는 메세지를 출력할 View 메소드 호출
		
		if( empList != null && !empList.isEmpty() ) {
			// 1_14. 전체 조회 결과를 출력할 View 작성하기
			//  -> EmpView.selectAll() 메소드 작성
			
			// 1_16. 전체 사원 정보를 출력하는 View 호출
			view.selectAll(empList);
		} else {
			// 조회 결과가 없는 경우
			
			// 1_17. 조회 뿐만 아니라 DB처리 작업이 결과 값이 없거나
			// 예외가 발생하는 경우
			// 문제 내용을 출력해줄 별도의 View 작성
			// -> EmpView.displayError() 메소드 작성
			
			// 1_19. 조회 결과가 없을 경우 메세지를 출력하는 View 호출
			view.displayError("조회 결과가 없습니다.");
		}
		
	}
	
	
	// 2. 사번으로 사원 정보 조회
	public void selectEmp() {
		
		// 2_1. EmpDAO 객체 생성
		EmpDAO empDAO = new EmpDAO();
		
		// 2_2. 사번을 입력받는 View 생성
		// --> EmpView.selectEmpNo() 메소드 작성
		
		// 2_4. 사번 입력 View를 호출하여 조회할 사번을 입력받고
		//      그 값을 반환받아 저장.
		int empNo = view.selectEmpNo();
		
		// 2_5. 입력된 사번을 매개변수로 전달하여 
		//      해당 사번을 가진 사원의 정보를 조회하는
		//      EmpDAO.selectEmp(empNo) 작성
		
		// 2_13. DB에서 조회된 사원 정보를 저장
		EMP emp = empDAO.selectEmp(empNo);
		
		// 2_14. 조회 결과 유무에 따라서 View 연결 지정
		if(emp != null) { // 조회 결과가 있을 경우
			// 2_15. 사원 한 명의 정보를 출력하는 View
			// EmpView.selectEmp() 작성
			
			// 2_17. EmpView.selectEmp() 호출
			view.selectEmp(emp);
			// 메인 메뉴 case 2에서 EmpController.selectEmp();
		
		}else { // 조회 결과가 없을 경우
			// 2_18. displayError() 메소드에
			// "해당 사번의 검색 결과가 없습니다." 메세지 전달하여 출력
			view.displayError("해당 사번의 검색 결과가 없습니다.");
		}
		
	}


	
	
	
	
	
	// 6. 일정 범위 내 급여를 받는 사원 정보 조회
	public void salRange() {
		
		EmpDAO empDAO = new EmpDAO();
		
		int[] range = view.inputRange();
		
		ArrayList<EMP> empList = empDAO.salRange(range);
		
		if(!empList.isEmpty()) { 
			view.selectAll(empList);
		} else { 
			view.displayError("조회 결과가 없습니다.");
		}
	}
	
	
	
	
	

	
	
	
	
}
