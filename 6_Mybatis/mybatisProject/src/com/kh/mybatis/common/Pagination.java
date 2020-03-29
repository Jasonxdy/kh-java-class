package com.kh.mybatis.common;

import com.kh.mybatis.board.model.vo.PageInfo;

public class Pagination {
	
	public static PageInfo getPageInfo(int limit, int pagingBarSize, int currentPage, int listCount) {
		int maxPage; // 전체 페이지 중 마지막 페이지
		int startPage; // 현재 페이지에서 보여질 페이징 버튼의 시작 페이지
		int endPage; // 현재 페이지에서 보여질 페이징 버튼의 끝 페이지
		
		maxPage = (int)Math.ceil( ((double)listCount / limit) );
		startPage = (currentPage-1) / pagingBarSize * pagingBarSize  + 1;
		endPage = startPage + pagingBarSize - 1;
		
		if(maxPage <= endPage) {
			endPage = maxPage;
		}
		
		return new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
	}

}
