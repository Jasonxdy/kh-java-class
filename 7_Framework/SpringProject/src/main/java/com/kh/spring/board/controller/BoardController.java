package com.kh.spring.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.FileRename;
import com.kh.spring.common.Pagination;
import com.kh.spring.common.vo.PageInfo;
import com.kh.spring.member.model.vo.Member;

@Controller
@RequestMapping("/board/*")
@SessionAttributes({"loginMember", "msg"})
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	// 게시글 목록 조회
	@RequestMapping("list")
	public String boardList(Model model,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "searchKey", required = false) String searchKey,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "searchCategory", required = false) String[] searchCategory
			) {
		
		
		try {
			// 검색 조건이 있는지 확인하여 map에 세팅
			Map<String, Object> map = null;
			if (searchKey != null && searchValue != null || searchCategory != null) {
				map = new HashMap<String, Object>();
				map.put("searchKey", searchKey);
				map.put("searchValue", searchValue);
				map.put("searchCategory", searchCategory);
			}

			// 전체 게시글 수 조회
			int listCount = boardService.getListCount(map);

			// 현재 페이지 확인
			if (currentPage == null) currentPage = 1;

			// 페이지 정보 저장
			PageInfo pInf = Pagination.getPageInfo(5, 10, currentPage, listCount);

			// 게시글 목록 조회
			List<Board> list = boardService.selectList(map, pInf);

			// 썸네일 목록 조회(이따 할 것)
			model.addAttribute("list", list);
			model.addAttribute("pInf", pInf);

			return "board/boardList";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "게시글 목록 조회 과정에서 오류 발생");
			return "common/errorPage";
		}
	}
	
	
	// 게시글 등록 화면 이동
	@RequestMapping("insertForm")
	public String insertForm() {
		return "board/boardInsert";
	}
	
	
	// 게시글 등록
	@RequestMapping("insert")
	public String insertBoard(Board board, // 커맨드 객체 : board -> @ModelAttribute가 생략된 형태 (field랑 name속성이 같은 경우) 
							Model model, // session 접근용
							HttpServletRequest request, // 파일 경로 
							RedirectAttributes rdAttr, // 리다이렉트시 메세지 전달용
							@RequestParam(value="thumbnail", required=false) MultipartFile thumbnail, // thumbnail 받아옴
							@RequestParam(value="images", required=false) List<MultipartFile> images // 배열 대신 리스트로 받아도 됨
							) { 
		// Session에서 회원 번호 얻어오기
		Member loginMember = (Member)model.getAttribute("loginMember"); // Controller 위에 @SessionAttributes 선언했기 때문에 가능
		int boardWriter = loginMember.getMemberNo();
		
		// 회원 번호를 커맨드 객체 board에 저장 (커맨드 객체 재활용)
		board.setBoardWriter(boardWriter + "");
		
		// 파일 저장 경로
		String root = request.getSession().getServletContext().getRealPath("resources");
		// 브라우저 상에서는 : /springProject/resources로 인식 (요청 주소 : domain)
		// 이것에 대한 진짜 주소 (RealPath) : /c:/workspace/~~   --> 이것을 얻어옴
		
		String savePath = root + "/uploadFiles";
		
		// 저장 폴더 선택
		File folder = new File(savePath);
		// 만약 해당 폴더가 없는 경우 --> 폴더 만들기
		if(!folder.exists()) folder.mkdir(); // folder가 없는 경우 -> 그 폴더를 만들어라
		
		try {
			
			/* // 전송된 파일 확인 // @RequestParam을 이용하여 input type="file" 파라미터를 얻어올 경우 // 값이 없을
			 * 때는 value가 "" (빈 문자열)이 전달됨.
			 * 
			 * // 썸네일 확인 System.out.println("thumbnail : " +
			 * thumbnail.getOriginalFilename());
			 * 
			 * // 이미지 업로드 확인 for (int i = 0; i < images.size(); i++) {
			 * System.out.println("images[" + i + "] : " +
			 * images.get(i).getOriginalFilename()); }
			 */
			
			
			// 업로드된 파일을 하나의 List에 저장
			List<Attachment> files = new ArrayList<Attachment>();
			Attachment at = null;
			
			// thumbnail 업로드 이미지 rename 작업 후 추가
			if(!thumbnail.getOriginalFilename().equals("")) {
				// 썸네일이 등록된 경우
				
				// 파일명 rename
				String fileChangeName = FileRename.rename(thumbnail.getOriginalFilename());
				
				// Attachment 객체 생성
				at = new Attachment(thumbnail.getOriginalFilename(), 
									fileChangeName, 
									savePath);
				
				// thumbnail의 fileLevel == 0
				at.setFileLevel(0);
				
				// files list에 추가
				files.add(at);
			}
			
			// images 업로드 이미지 rename 작업 후 추가
			for(MultipartFile mf : images) {
				if(!mf.getOriginalFilename().equals("")) {
					
					// 상단 thumbnail과 유사
					String fileChangeName = FileRename.rename(mf.getOriginalFilename());
					at = new Attachment(mf.getOriginalFilename(), 
										fileChangeName, 
										savePath);
					at.setFileLevel(1);
					files.add(at);
				}
			}
			
			
			// 게시글 + 이미지 삽입 Service 호출
			int result = boardService.insertBoard(board, files);
			
			String msg = null;
			String url = null;
			
			if(result>0) { // DB에 게시글 삽입 성공 시
				
				// 서버에 파일 저장
				/*
				 * 여기서 원리에 대해 알아야 할 것 
				 * MultipartFile : 실제 넘어온 파일 (비어있을 수 있음 "") 	 					// 실물이라고 생각할 것
				 * Attachment : DB에 저장할 이름, 경로 등을 담은 객체 (비어있지 않음 -> 골라냈으므로)		// 실물에 대한 정보를 담고있는 표지같은 것
				 * 
				 * 따라서 실물을 저장하는 단계가 필요함
				 * 
				 * --> Attachment를 담고있는 files : ArrayList를 돌면서 이름이 같을 때 실물을 저장 (.transferTo(경로)를 이용) 
				 */
				for(Attachment file : files) {
					if(file.getFileLevel() == 0) { // 현재 접근한 파일이 thumbnail 이미지인 경우
						thumbnail.transferTo(new File(file.getFilePath() + "/" + file.getFileChangeName())); 
						// .transferTo()가 정상호출 될 경우 파일이 저장됨.
					} else { // 현재 접근한 파일이 일반 이미지인 경우
						for(MultipartFile mf : images) {
							if(mf.getOriginalFilename().equals(file.getFileOriginName())) {
								mf.transferTo(new File(file.getFilePath() + "/" + file.getFileChangeName()));
								break;
							}
						}
					}
				} // end for
				
				msg = "게시글 등록 성공";
				// url = "detail?no=" + result + "&currentPage=1";
				// 상세조회 완성 후 사용
				
				url = "list";
				
			} else {
				msg = "게시글 등록 실패";
				url = "list";
			}
			
			
			rdAttr.addFlashAttribute("msg", msg);
			return "redirect:" + url;
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "게시글 등록 과정 중 오류 발생");
			return "common/errorPage";
		}	
		
	}
	
	
	
	

}
