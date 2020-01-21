<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.kh.sjproject.board.model.vo.Board, com.kh.sjproject.board.model.vo.PageInfo"%>
	
<% 
	List<Board> bList = (List<Board>)request.getAttribute("bList");
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	String searchKey = request.getParameter("searchKey");
	String searchValue = request.getParameter("searchValue");
	
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
    <style>
    	.pagination {
            justify-content: center;
        }
        #searchForm{
            position: relative;
        }

        #searchForm>*{
            top : 0;
        }
	</style>
	
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp"%>
		<%@ include file="../common/nav.jsp"%>

		<div class="container">
	        <div>
	            <table class="table table-hover table-striped" id="list-table">
	                <thead>
	                    <tr>
	                        <th>글번호 </th>
	                        <th>카테고리 </th>
	                        <th>제목</th>
	                        <th>작성자</th>
	                        <th>조회수</th>
	                        <th>작성일</th>
	                    </tr>
	                </thead>
	                <tbody>
						<% if(bList.isEmpty()){ %>
						<tr>
							<td colspan="6">존재하는 게시글이 없습니다.</td>
						</tr>
						<% }else{ %>
							<% for(Board board : bList){ %>
							<tr>
								<td><%= board.getBoardNo() %></td>
								<td><%= board.getBoardCategory() %></td>
								<td><%= board.getBoardTitle() %></td>
								<td><%= board.getBoardWriter() %></td>
								<td><%= board.getBoardCount() %></td>
								<td><%= board.getBoardModifyDate() %></td>
							</tr>
							<% } %>
						<% } %>
	                </tbody>
	            </table>
	        </div>
	
	        <hr>
	        
	        <%-- 로그인이 되어있는 경우 --%>
	        <% if(loginMember != null) {%>
	        <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = 'insertForm';">글쓰기</button>
	        <% } %>
	        
	        <!-- 페이징바 -->
	        <div style="clear: both;">
	            <ul class="pagination">
	            	<% if(currentPage > 1) { %>
	                <li>
	                	<!-- 맨 처음으로(<<) -->
	                    <a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=1">&lt;&lt;</a>
	                </li>
	                
	                <li>
	                	<!-- 이전으로(<) -->
                   		<a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=<%= currentPage-1 %>">&lt;</a>
	                </li>
	                <% } %>
	                
	                <!-- 10개의 페이지 목록 -->
	                <% for(int p = startPage; p <= endPage; p++){ %>
	                	<% if(p == currentPage) { %>
		                <li>
		                    <a class="page-link"><%= p %></a>
		                </li>
	                	<% } else{ %>
                		<li>
	                    	<a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=<%= p %>"><%= p %></a>
	                	</li>
	                	<% } %>
					<%} %>
	                
	                <!-- 다음 페이지로(>) -->
	                <% if(currentPage < maxPage){ %>
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=<%= currentPage+1 %>">&gt;</a>
	                </li>
	                
	                <!-- 맨 끝으로(>>) -->
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=<%= maxPage %>">&gt;&gt;</a>
	                </li>
	                <% }%>
	                
	            </ul>
	        </div>
	        <div>
	            <form action="search" method="GET" class="text-center" id="searchForm">
	                <select name="searchKey" class="form-control" style="width:100px; display: inline-block;">
	                    <!-- <option value="title" selected>글제목</option> -->
	                    <option value="title">글제목</option>
	                    <option value="content">내용</option>
	                    <option value="titcont">제목+내용</option>
	                </select>
	                <input type="text" name="searchValue" class="form-control" style="width:25%; display: inline-block;">
	                <button class="form-control btn btn-primary" style="width:100px; display: inline-block;">검색</button>
	            </form>
	            
            	<script>
					$(function(){
						var searchKey = "<%= searchKey %>";
						var searchValue = "<%= searchValue %>";
						
						if(searchKey != "null" && searchValue != "null"){
							$.each($("select[name=searchKey] > option"), function(index, item){
								if($(item).val() == searchKey){
									$(item).prop("selected","true");
								} 
							});
							
							$("input[name=searchValue]").val(searchValue);
						}
							
					});
				</script>
	            
	        </div>
    	</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	
	<script>
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#list-table td").click(function(){
				var boardNo = $(this).parent().children().eq(0).text();
				// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
				location.href="<%= request.getContextPath() %>/board/detail?no="+boardNo;
			
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			
			});
			
		});
		
		
	</script>
	
	
	
</body>
</html>
