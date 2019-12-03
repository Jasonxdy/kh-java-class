package controller;

import model.service.MemberService;
import model.vo.Member;
import view.MemberView;

public class MemberController {

	// 동작 중 필요한 서브메뉴, 결과를 반환할 뷰를 호출하기 위한 MemberView 객체 선언
	private MemberView view = new MemberView();
	
	
	// 1_4의 동작
	private MemberService mService = new MemberService();
	
	
	// 1. 새로운 회원 정보 추가
	// 입력받은 회원 정보를 DB로 전달한 후 결과에 따라 알맞은 View에 연결 처리하는 메소드
	
	/**
	 *  새로운 회원 정보 추가 Controller
	 */
	public void insertMember() {
		
		// 1_1. 새로운 회원 정보를 입력받기 위한 서브메뉴
		// MemberView.insertMember() 메소드 작성
		
		// 1_3. 입력받은 회원 정보를 Member타입 변수에 저장
		Member member = view.insertMember();
		
		// 1_4. MemberService 객체를 필드에 생성
		// -> 위에 필드에 생성함
		
		// 1_5. 회원 정보에 대한 비즈니스 로직을 수행한 후 
		// 		DAO에 전달하고 DAO로부터 전달받은 값에 따라
		//		트랜잭션 처리를 할 MemberService.insertMember(member) 메소드 작성
		
		
		// 1_26. MemberService.insertMember()를 호출하여 반환값 저장
		// 		 -> throws Exception 예외처리할 수 있도록 try-catch 구문으로 작성
		
		try {
			int result = mService.insertMember(member);
			
			// 1_27. DB 삽입 결과에 따른 View 연결처리
			if(result > 0) { // 삽입 성공
				// 1_28. DML 구문 처리 성공 시 메세지를 출력할
				//		 MemberView.displaySuccess(mag) 작성
				
				
				// 1_30. 삽입 성공 메세지 출력
				view.displaySuccess(result + "개의 행이 추가되었습니다.");
			} else { // 삽입 실패
				// 1_31. 삽입 실패 메세지 출력용 View 
				// 		 -> MemberView.displayFail(msg) 작성
				
				// 1_33. 삽입 실패 메세지 출력
				view.disaplayFail("데이터 삽입 실패");
			}
		} catch (Exception e) {
			
			// 1_34. 예외 메세지 출력용 View
			// 		 MemberView.displayError(msg, e) 작성
			
			// 1_36. 예외 메세지 출력
			view.displayError("데이터 삽입 과정 중 오류 발생", e);
		}
		
		
		// 1_37. 메인메뉴 case 1에 mController.insertMember() 호출 구문 작성
		
		
	}

}
