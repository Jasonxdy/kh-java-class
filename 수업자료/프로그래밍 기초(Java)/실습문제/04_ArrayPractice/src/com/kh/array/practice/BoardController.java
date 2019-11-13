package com.kh.spring.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.kh.spring.board.model.exception.BoardException;
import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.BoardSearch;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.Pagination;
import com.kh.spring.member.model.vo.Member;

@Controller
public class BoardController {
	// Notice 때 했던것처럼 풀 셋팅 먼저 하자
	// BoardService로는 전체게시판 조회, 상세조회, 게시판등록, 게시판수정, 게시판삭제 정도로 셋팅해놓고와라
	
	@Autowired
	private BoardService bService;
	
	/** 게시글 목록 조회 컨트롤러
	 * @param mv
	 * @param page
	 * @return mv
	 */
	@RequestMapping("blist.kh")
	public ModelAndView boardList(ModelAndView mv, 
								  @RequestParam(value="page", required=false) Integer page) {
		
		// 커맨드 객체 사용시 해당 파라미터가 존재하지 않을 경우 null 값을 반환함.
		// page의 자료형을 int로 지정할 경우 null 값을 저장할 수 없어 IllegalStateException 발생
		// 이를 해결하기 위하여 page 값을 Integer로 선언
		int currentPage = page != null ? page : 1;
		
		// PageInfo VO,  Pagination Common Class 생성
		
		// 게시물 목록 조회
		ArrayList<Board> list = bService.selectList(currentPage); 
		
		if(list != null && list.size() > 0) {
			mv.addObject("list", list);
			mv.addObject("pi", Pagination.getPageInfo()); // 화면 출력 시 필요한 페이지 정보도 전달
			mv.setViewName("board/boardListView"); 
		}else {
			mv.addObject("msg", "게시물 목록 조회 실패");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	
	/** 게시글 쓰기 화면 이동 컨트롤러
	 * @return board/boardInsertForm
	 */
	@RequestMapping("binsertView.kh")
	public String boardInsertView() {
		return "board/boardInsertForm"; // boardInsertForm.jsp 만들러 가자
	}
	
	/** 게시글 등록 컨트롤러
	 * @param request
	 * @param board
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("binsert.kh")
	public String boardInsert(HttpServletRequest request, Board board, Model model,
							  @RequestParam(value="uploadFile", required=false) List<MultipartFile> uploadFiles) {
		
		
		//int result = bService.insertBoard(board, uploadFile, request); 
		
		System.out.println(uploadFiles.get(0).getOriginalFilename());
		System.out.println(uploadFiles.get(1).getOriginalFilename());
		//System.out.println(uploadFile2.getOriginalFilename());
		
		/*String path = null;
		
		if(result > 0) {
			path = "redirect:blist.kh";
		}else {
			model.addAttribute("msg","게시글 등록 실패");
			path = "common/errorPage";
		}	
	
		return path;*/
		
		return null;
	}
	
	

	/** 게시글 상세 조회
	 * @param mv
	 * @param bId
	 * @param page
	 * @return
	 */
	@RequestMapping("bdetail.kh")
	public ModelAndView boardDetail(ModelAndView mv, int bId,
									@RequestParam("page") Integer page) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		
		bService.addReadCount(bId);
		Board board = bService.selectBoard(bId);
		
		if(board != null) {
			// 메소드체이닝방식
			mv.addObject("board", board)
			  .addObject("currentPage", currentPage)
			  .setViewName("board/boardDetailView"); // boardDetailView.jsp 만들러 가자
		}else{
			mv.addObject("msg", "게시글 상세조회 실패")
			  .setViewName("common/errorPage");
		}
		
		return mv;
	}
	

	
	@RequestMapping("bupView.kh")
	public ModelAndView boardUpdateView(ModelAndView mv, int bId,
										@RequestParam("page") Integer page) {
		mv.addObject("board", bService.selectBoard(bId))
		  .addObject("currentPage", page)
		  .setViewName("board/boardUpdateView"); // boardUpdateForm.jsp 만들러 가자
		
		return mv;
	}
	
	@RequestMapping("bupdate.kh")
	public ModelAndView boardUpdate(ModelAndView mv, Board board,
									HttpServletRequest request, 
									@RequestParam("page") Integer page,
									@RequestParam(value="reloadFile", required=false) MultipartFile reloadFile) {

		int result = bService.updateBoard(board,reloadFile, request); // mapper 완성시키고 오삼
		
		if(result > 0) {
			mv.setViewName("redirect:bdetail.kh?bId="+board.getbId()+"&page="+page);
		}else {
			mv.addObject("msg", "게시글 수정 실패")
			  .setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	
	@RequestMapping("bdelete.kh")
	public ModelAndView boardDelete(int bId, ModelAndView mv) {
		
		int result = bService.deleteBoard(bId);
		
		if(result > 0) {
			mv.setViewName("redirect:blist.kh");
		}else {
			mv.addObject("msg", "게시글 수정 실패")
			  .setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	
	//----------- ajax 프로젝트 후에 추가해줄 것 -------------
	/**
	 * 1번. Stream 이용해서 json배열 보내기
	 * 
	 * @param response
	 * @throws IOException
	 */
	/*@RequestMapping("topList.kh")
	public void boardTopList(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/json; charset=utf-8");
		
		ArrayList<Board> list = bService.selectTopList(); //selectTopList 메소드 추가해주자
		
		// 조회수 top 리스트들을 조회해온 list를 JSONArray로 바꿈
		JSONArray jArr = new JSONArray();
		
		// 다만 createDate와 같은 Date 형식은 SimpleDateFormat을 이용해서 바꿔주자
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for(Board b : list) {
			JSONObject jObj = new JSONObject();
			jObj.put("bId", b.getbId());
			jObj.put("bTitle", b.getbTitle());
			jObj.put("bWriter", b.getbWriter());
			jObj.put("originalFileName", b.getOriginalFileName());
			jObj.put("bCount", b.getbCount());
			jObj.put("bCreateDate", sdf.format(b.getbCreateDate()));
			
			jArr.add(jObj);
		}
		System.out.println(jArr);
		JSONObject sendJson	= new JSONObject();
		sendJson.put("list", jArr);
		
		PrintWriter out = response.getWriter();
		out.println(sendJson);
		out.flush();
		out.close();
		
	}*/
	
	/**
	 * 2. jackson ObjectMapper를 이용하는 방식
	 * 
	 * jackson 라이브러리(ObjectMapper, JsonGenerator)에 의존
	 * 
	 * * jackson 방법
	 * 
	 * 1. 반환값을 String으로 두고 ObjectMapper를 이용해서 list를 String으로 반환하는 방법
	 * 2. 반환값을 list로 두고  list자체를 반환하는 방법
	 * 	  --> MessageConverter 관련 bean을 등록해서 사용해야된다.(자바 객체를 자바스크립트객체로 바꿔주는 작업을 도와줌)
	 * 
	 * 하지만 우리는 Date형식을 가지고 있는 list를 반환해야되기 때문에 MessageConverter를 이용해서 반환하면 뷰단에서 포맷작업을 해줘야된다.
	 * 그래서 우리는 1번 방법으로 하겠다.
	 * 
	 * 기존의 Stream을 이용해서 컬렉션을 보내줄때는 JsonArray에 담아서 보내주는 귀찮은 작업을 했지만
	 * jackson을 이용하면 컬렉션을 String으로 바로 바꿔서 보내주는 편리함이 있다. 
	 * 
	 * @return
	 * @throws JsonIOException 
	 * @throws IOException
	 */
	/*@RequestMapping("topList.kh")
	@ResponseBody
	public String boardTopList() throws IOException {
		
		ArrayList<Board> list = bService.selectTopList(); //selectTopList 메소드 추가해주자
		
		for(Board b : list) {
			b.setbTitle(URLEncoder.encode(b.getbTitle(), "utf-8")); // 인코딩 작업을 해줘야된다.
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 포맷해야되는데 이렇게 포맷해도 되고
		mapper.setDateFormat(sdf);
		
//		mapper.setDateFormat(new ISO8601DateFormat()); // SimpleDateFormat 안쓴다면 이렇게도 된다. 
		
		String jsonStr = mapper.writeValueAsString(list);
		
		return jsonStr;
		
	}*/
	
	/**
	 * 3. Gson이용하는 방법
	 * 
	 * 컬렉션을 아주 쉽게 json 객체로 전송하는 방법으로 
	 * Stream을 이용하거나 Jackson을 이용하는 방법에서는 어떤 처리를 했었지만 
	 * Gson을 이용한다면 어떠한 처리없이 바로 컬렉션을 보낼 수 있다.  --> 개굿
	 * 
	 * 보통 Gson을 그냥 생성해서 보내지만 어떠한 셋팅을 해야된다면 GsonBuilder를 이용해서 Gson을 생성하면된다.
	 * @param response
	 * @throws JsonIOException
	 * @throws IOException
	 */
	@RequestMapping("topList.kh")
	public void boardTopList(HttpServletResponse response) throws JsonIOException, IOException{
		
		ArrayList<Board> list = bService.selectTopList(); //selectTopList 메소드 추가해주자
		
		for(Board b : list) {
			b.setbTitle(URLEncoder.encode(b.getbTitle(), "utf-8")); // 인코딩 작업을 해줘야된다.
		}
		
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // 날짜 포맷하기 위해 GsonBuilder를 이용해서 Gson객체 생성
		gson.toJson(list, response.getWriter());

	}
	
	
	
	
	
	// 여기까지 했으면 댓글 등록하기로 가볼까나? boardDetailView.jsp로 가자
	/*@RequestMapping("rList.kh")
	public void getReplyList(HttpServletResponse response, int bId) throws JsonIOException, IOException {
		ArrayList<Reply> rList = bService.selectReplyList(bId);
		
		for(Reply r : rList) {
			r.setrContent(URLEncoder.encode(r.getrContent(), "utf-8"));
		}
		
		response.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(rList, response.getWriter());
	}*/

	
	@ResponseBody
	@RequestMapping(value="rList.kh", 
		produces = "application/json; charset=utf8")
	public String getReplyList(int bId) {
		ArrayList<Reply> rList = bService.selectReplyList(bId);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(rList);
	}
	
	
	
	
	
	
	
	
	@RequestMapping("addReply.kh")
	@ResponseBody
	public String addReply(Reply r, HttpSession session) {
		
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		String rWriter = loginUser.getId();
		
		r.setrWriter(rWriter);
		
		int result = bService.insertReply(r);
		
		if(result > 0) {
			return "success";
		}else {
			throw new BoardException("댓글 등록 실패!!");
		}
	}
	
	
	
	
	// 게시물 검색
	@RequestMapping("bsearch.kh")
	public ModelAndView boardSearch(BoardSearch search, ModelAndView mv, Integer page) {
		int currentPage = page != null ? page : 1;
		
		System.out.println(Arrays.toString(search.getSearchOption()));
		System.out.println(search.getSearchValue());
		System.out.println(search.getExistFile());
		
		ArrayList<Board> searchList = bService.selectSearchList(search, currentPage);
		
	
		for(Board b : searchList) { 
			System.out.println(b);
		}
		
		mv.addObject("list", searchList)
		  .addObject("search", search)
		  .addObject("pi", Pagination.getPageInfo())
		  .setViewName("board/boardSearchListView");
		return mv;
	}
	
	
	
	
	
	
}
