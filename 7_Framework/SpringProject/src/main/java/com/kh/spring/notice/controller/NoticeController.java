package com.kh.spring.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.common.Pagination;
import com.kh.spring.common.vo.PageInfo;
import com.kh.spring.member.model.vo.Member;
import com.kh.spring.notice.model.service.NoticeService;
import com.kh.spring.notice.model.vo.Notice;

@Controller
@RequestMapping("/notice/*")
@SessionAttributes({"loginMember", "msg"})
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	
	
	/*
	 * ModelAndView 객체
	 * - Model : 응답 페이지에 값(data)을 전달할 때 Map 형식으로 저장하여 전달하는 객체
	 * - View : requestDispatcher를 이용한 페이지 이동 시 이동할 페이지의 정보(url)를 담는 객체 // MVC에서의 View와 다름 (이건 views폴더 밑의 사용자가 볼 수 있는 화면을 뜻함)
	 * 
	 * - ModelAndView : 컨트롤러 응답 처리 후 응답할 View와 View에 전달할 값을 저장하는 객체 (model level에서 속성 추가 -> view level에서 페이지 이동)
	 */
	
	
	// 공지사항 목록 출력
	@RequestMapping("list")
	public ModelAndView noticeList(ModelAndView mv,
								@RequestParam(value = "searchKey", required=false) String searchKey,
								@RequestParam(value = "searchValue", required=false) String searchValue,
								@RequestParam(value = "currentPage", required=false) Integer currentPage) { // parameter는 String 타입인데, @RequestParam으로 받아올 때 Wrapper클래스 타입으로 받아오면 형변환되어 넘어옴
		// currentPage
		// searchKey
		// searchValue
		
		try {
			// 검색 조건이 있는지 확인하여 map에 세팅
			Map<String, String> map = null;
			if(searchKey != null && searchValue != null) {
			
				map = new HashMap<String, String>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
				
			}
			
			// 1. 전체 공지사항 게시글 수 조회 (페이징 처리를 위해서)
			int listCount = noticeService.getListCount(map);
			
			// 현재 페이지 계산
			if(currentPage == null) currentPage = 1;
			
			// 페이지 정보 저장
			PageInfo pInf = Pagination.getPageInfo(10, 10, currentPage, listCount);
				
			
			// 2. 공지사항 목록 조회
			List<Notice> list = noticeService.selectList(map, pInf);
			
			mv.addObject("list", list);
			mv.addObject("pInf", pInf);
			mv.setViewName("notice/noticeList");
			
			
		} catch (Exception e) {
			// catch에서 ExceptionForward 사용하지 않는 이유 : requestMapping의 경우 받아오는 parameter가 전부 달라서 처리 불가능 (?)
			e.printStackTrace();
			mv.addObject("errorMsg", "공지사항 목록 조회중 오류 발생");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	
	
	
	// 공지사항 상세 조회
	@RequestMapping("detail")
	public ModelAndView noticeDetail(ModelAndView mv, 
			@RequestParam(value = "searchKey", required=false) String searchKey,
			@RequestParam(value = "searchValue", required=false) String searchValue,
			@RequestParam(value = "currentPage", required=false) Integer currentPage,
			@RequestParam("no") Integer no) {
		
		try {
			
			int noticeNo = no;
			
			Notice notice = noticeService.selectNotice(noticeNo);
			
			mv.addObject("notice", notice);
			
			mv.setViewName("notice/noticeDetail");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("errorMsg", "공지사항 상세조회 과정 중 오류 발생");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	
	
	
	
	
	// 공지사항 삭제
	@RequestMapping("delete")
	public ModelAndView deleteNotice (ModelAndView mv, @RequestParam("no") Integer no, RedirectAttributes rdAttr) {
		
		int noticeNo = no;
		
		String msg = null;
		
		try {
			
			int result = noticeService.deleteNotice(noticeNo);
			
			if(result>0) msg = "공지사항 삭제 성공";
			else		msg = "공지사항 삭제 실패";
			
			rdAttr.addFlashAttribute("msg", msg);
			mv.setViewName("redirect:list");
			
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("errorMsg", "공지사항 삭제 과정 중 오류 발생");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	
	
	
	
	
	// 공지사항 수정 화면 이동
	@RequestMapping("updateForm")
	public ModelAndView updateForm (ModelAndView mv, @RequestParam("no") Integer no) {
		
		int noticeNo = no;
			
		try {
			Notice notice = noticeService.selectNotice(noticeNo);
			notice.setNoticeContent(notice.getNoticeContent().replace("<br>", "\r\n"));
			
			mv.addObject("notice", notice);
			mv.setViewName("notice/noticeUpdate");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("errorMsg", "공지사항 수정 조회 과정 중 오류 발생");
			mv.setViewName("common/errorPage");
		}
		
		mv.setViewName("notice/noticeUpdate");
		return mv;
	}
	
	
	
	// 공지사항 수정
	@RequestMapping("update")
	public ModelAndView updateNotice(ModelAndView mv, @RequestParam("no") Integer no, Notice notice, RedirectAttributes rdAttr) {
		
		notice.setNoticeNo(no);
		notice.setNoticeContent(notice.getNoticeContent().replace("\r\n", "<br>"));
		String msg = null;
		String path = null;
		
		try {
			
			int result = noticeService.updateNotice(notice);
			
			if(result>0) {
				msg = "공지사항 수정 성공";
			}
			else	{
				msg = "공지사항 수정 실패";
			}
			
			rdAttr.addFlashAttribute("msg", msg);
			mv.setViewName("redirect:detail?no=" + no);
			
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("errorMsg", "공지사항 수정 과정 중 오류 발생");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	
	
	
	
	
	// 공지사항 작성 화면 이동 
	@RequestMapping("insertForm")
	public ModelAndView insertForm (ModelAndView mv) {
		mv.setViewName("notice/noticeInsert");
		return mv;
	}
	
	
	
	// 공지사항 작성
	@RequestMapping("insert")
	public String insertNotice(Model model, Notice notice, RedirectAttributes rdAttr) {
		
		notice.setNoticeWriter(((Member)model.getAttribute("loginMember")).getMemberId());
		notice.setNoticeContent(notice.getNoticeContent().replace("\r\n", "<br>"));
		String msg = null;
		String path = null;
		
		try {
			int result = noticeService.insertNotice(notice);
			if(result > 0) {
				msg = "공지사항 등록 성공";
				path = "redirect:/notice/detail?no=" + result;
			}
			else {
				msg = "공지사항 등록 실패";
				path = "redirect:list";
			}
			
			rdAttr.addFlashAttribute("msg", msg);
			
			return path;
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "공지사항 등록 과정 중 오류 발생");
			return "common/errorPage";
		}
		
	}
	
}
