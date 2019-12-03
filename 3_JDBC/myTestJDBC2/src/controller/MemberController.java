package controller;

import model.dao.MemberDAO;
import model.service.MemberService;
import model.vo.Member;
import view.MemberView;

public class MemberController {
	
	private MemberView view = new MemberView();
	private MemberService mService = new MemberService();
	
	public void insertMember() {
		
		
		Member member = view.insertMember();
		
		try {
			int result = mService.insertMember(member);
			
			if(result >0) {
				// MemberView.displaySuccess(msg) 작성
				view.displaySuccess(result + "개의 행이 삽입되었습니다.");
			} else {
				view.displayFail("데이터 삽입 실패");
			}
		} catch (Exception e) {
			view.displayError("데이터 삽입 과정 중 오류 발생", e);
		}
	}
}
