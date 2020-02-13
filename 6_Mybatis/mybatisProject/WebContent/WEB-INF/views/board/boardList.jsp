<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%-- <%@page import="java.util.List, com.kh.sjproject.board.model.vo.Board, com.kh.sjproject.board.model.vo.PageInfo"%>
<%@page import="com.kh.sjproject.board.model.vo.Attachment"%>
	
<% 
	List<Board> bList = (List<Board>)request.getAttribute("bList");
	List<Attachment> fList = (List<Attachment>)request.getAttribute("fList");
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	String searchKey = request.getParameter("searchKey");
	String searchValue = request.getParameter("searchValue");
	
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
	
	System.out.println(fList);
%> --%>
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
        
        .boardTitle > img{
        	width: 50px;
        	height: 50px;
        }
        
	</style>
	
</head>
<body>
	<div class="container">
		<%-- <%@ include file="../common/header.jsp"%>
		<%@ include file="../common/nav.jsp"%> --%>
		<jsp:include page="../common/header.jsp"/>
		<jsp:include page="../common/nav.jsp"/>

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
						<%-- <% if(bList.isEmpty()){ %> --%>
						<c:if test="${empty bList }">
							<tr>
								<td colspan="6">존재하는 게시글이 없습니다.</td>
							</tr>
						</c:if>
						
						<%-- <% }else{ %> --%>
						<c:if test="${!empty bList }">
						
							<%-- <% for(Board board : bList){ %> --%>
							<c:forEach var="board" items="${bList}" varStatus="vs">
							
								<tr>
									<%-- <td><%= board.getBoardNo() %></td> --%>
									<td>${board.boardNo}</td>
									
									<%-- <td><%= board.getBoardCategory() %></td> --%>
									<td>${board.boardCategory}</td>
									
									<td class="boardTitle">
										<!-- 썸네일은 제거 -->
										<%-- <%= board.getBoardTitle() %> --%>
										${board.boardTitle}
									</td>
									
									<%-- <td><%= board.getBoardWriter() %></td> --%>
									<td>${board.boardWriter}</td>
									
									<%-- <td><%= board.getBoardCount() %></td> --%>
									<td>${board.boardCount}</td>
									
									<%-- <td><%= board.getBoardModifyDate() %></td> --%>
									<td>${board.boardModifyDate}</td>
									
								</tr>
								
							<%-- <% } %> --%>
							</c:forEach>
							
						<%-- <% } %> --%>
						</c:if>
	                </tbody>
	            </table>
	        </div>
	
	        <hr>
	        
	        <%-- 로그인이 되어있는 경우 --%>
	       	<%--  <% if(loginMember != null) {%> --%>
	       	<c:if test="${!empty loginMember }">
		        <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = 'insertForm';">글쓰기</button>
	        </c:if>
	        <%-- <% } %> --%>
	        
	        <!-- 페이징바 -->
	        <div style="clear: both;">
	            <ul class="pagination">
	            	<%-- <% if(currentPage > 1) { %> --%>
	            	<c:if test="${pInf.currentPage > 1}">
		                <li>
		                	<!-- 맨 처음으로(<<) -->
		                    <%-- <a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=1">&lt;&lt;</a> --%>
		                    <a class="page-link" href="${contextPath}/board/list?currentPage=1">&lt;&lt;</a>
		                </li>
		                
		                <li>
		                	<!-- 이전으로(<) -->
	                   		<%-- <a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=<%= currentPage-1 %>">&lt;</a> --%>
	                   		<a class="page-link" href="${contextPath}/board/list?currentPage=${pInf.currentPage-1}">&lt;</a>
		                </li>
	                </c:if>
	                <%-- <% } %> --%>
	                
	                <!-- 10개의 페이지 목록 -->
	               	<%--  <% for(int p = startPage; p <= endPage; p++){ %> --%>
	                <c:forEach var="p" begin="${pInf.startPage}" end="${pInf.endPage}">
	                
	                
	                	<%-- <% if(p == currentPage) { %> --%>
	                	<c:if test="${p == pInf.currentPage}">
			                <li>
			                   <%--  <a class="page-link"><%= p %></a> --%>
			                    <a class="page-link">${p}</a>
			                </li>
		                </c:if>
	                	<%-- <% } else{ %> --%>
	                	
	                	<c:if test="${p != pInf.currentPage}">
	                		<li>
		                    	<%-- <a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=<%= p %>"><%= p %></a> --%>
		                    	<a class="page-link" href="${contextPath}/board/list?currentPage=${p}">${p}</a>
		                	</li>
	                	</c:if>
	                	<%-- <% } %> --%>
	                	
                	</c:forEach>
					<%-- <%} %> --%>
	                
	                <!-- 다음 페이지로(>) -->
	               <%--  <% if(currentPage < maxPage){ %> --%>
	                <c:if test="${pInf.currentPage < pInf.maxPage }">
		                <li>
		                    <%-- <a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=<%= currentPage+1 %>">&gt;</a> --%>
							<a class="page-link" href="${contextPath}/board/list?currentPage=${pInf.currentPage+1}">&gt;</a>
		                </li>
		                
		                <!-- 맨 끝으로(>>) -->
		                <li>
		                    <%-- <a class="page-link" href="<%= request.getContextPath() %>/board/list?currentPage=<%= maxPage %>">&gt;</a> --%>
		                    <a class="page-link" href="${contextPath}/board/list?currentPage=${pInf.maxPage}">&gt;&gt;</a>
		                </li>
	                
	                <%-- <% }%> --%>
	                </c:if>
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
						<%-- var searchKey = "<%= searchKey %>";
						var searchValue = "<%= searchValue %>"; --%>
						var searchKey = "${param.searchKey}";
						var searchValue = "${param.searchValue}";
						
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
    	
		<%-- <%@ include file="../common/footer.jsp"%> --%>
		<jsp:include page="../common/footer.jsp"/>
	</div>
	
	<script>
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#list-table td").click(function(){
				var boardNo = $(this).parent().children().eq(0).text();
				// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
				<%-- location.href="<%= request.getContextPath() %>/board/detail?no=" + boardNo +"&currentPage="+<%=currentPage %>; --%>
				location.href="${contextPath}/board/detail?no=" + boardNo +"&currentPage="+${pInf.currentPage};
			
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			
			});
			
		});
		
		
	</script>
	
	
	
</body>
</html>
