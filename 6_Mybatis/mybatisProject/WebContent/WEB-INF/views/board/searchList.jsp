<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
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
						<c:if test="${empty searchList }">
							<tr>
								<td colspan="6">존재하는 게시글이 없습니다.</td>
							</tr>
						</c:if>
						
						<c:if test="${!empty searchList }">
						
							<c:forEach var="board" items="${searchList}" varStatus="vs">
							
								<tr>
									<td>${board.boardNo}</td>
									
									<td>${board.boardCategory}</td>
									
									<td class="boardTitle">
										${board.boardTitle}
									</td>
									
									<td>${board.boardWriter}</td>
									
									<td>${board.boardCount}</td>
									
									<td>${board.boardModifyDate}</td>
									
								</tr>
								
							</c:forEach>
							
						</c:if>
	                </tbody>
	            </table>
	        </div>
	
	        <hr>
	        
	        <%-- 로그인이 되어있는 경우 --%>
	       	<c:if test="${!empty loginMember }">
		        <button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = 'insertForm';">글쓰기</button>
	        </c:if>
	        <%-- <% } %> --%>
	        
	        <!-- 페이징바 -->
	        <c:url var="searchUrl" value="/board/search2">
	        	<c:param name="searchKey" value="${param.searchKey }"/>
	        	<c:param name="searchValue" value="${param.searchValue }"/>
	        	
	        	<!-- c:if문 써주는 이유 : 아무것도 체크가 안되었을 시 빈 문자열로 넘어가기 때문에... 주소창에 쿼리 스트링이 searchCategory= 이렇게 들어감 -->
	        	<c:if test="${!empty paramValues.searchCategory}">
	        		<c:forEach var="ct" items="${paramValues.searchCategory}">
		        		<c:param name="searchCategory" value="${ct}"/>
	        		</c:forEach>
	        	</c:if>
	        </c:url>
	        
	        <div style="clear: both;">
	            <ul class="pagination">
	            	<c:if test="${pInf.currentPage > 1}">
		                <li>
		                	<!-- 맨 처음으로(<<) -->
		                    <a class="page-link" href="${searchUrl}&currentPage=1">&lt;&lt;</a>
		                </li>
		                
		                <li>
		                	<!-- 이전으로(<) -->
	                   		<a class="page-link" href="${searchUrl}&currentPage=${pInf.currentPage-1}">&lt;</a>
		                </li>
	                </c:if>
	                
	                <!-- 10개의 페이지 목록 -->
	                <c:forEach var="p" begin="${pInf.startPage}" end="${pInf.endPage}">
	                
	                
	                	<c:if test="${p == pInf.currentPage}">
			                <li>
			                    <a class="page-link">${p}</a>
			                </li>
		                </c:if>
	                	
	                	<c:if test="${p != pInf.currentPage}">
	                		<li>
		                    	<a class="page-link" href="${searchUrl}&currentPage=${p}">${p}</a>
		                	</li>
	                	</c:if>
	                	
                	</c:forEach>
	                
	                <!-- 다음 페이지로(>) -->
	                <c:if test="${pInf.currentPage < pInf.maxPage }">
		                <li>
							<a class="page-link" href="${searchUrl}&currentPage=${pInf.currentPage+1}">&gt;</a>
		                </li>
		                
		                <!-- 맨 끝으로(>>) -->
		                <li>
		                    <a class="page-link" href="${searchUrl}&currentPage=${pInf.maxPage}">&gt;</a>
		                </li>
	                
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
	            
	            
	            
	            <hr>
                <h4>다중 조건 검색</h4>
               <form action="search2" method="GET" class="text-center" id="searchForm2" style="margin-bottom:100px;">
                  <span>
                     카테고리(다중 선택 가능)<br>
                      <label for="exercise">운동</label> 
                      <input type="checkbox" name="searchCategory" value="운동" id="exercise">
                      &nbsp;
                      <label for="movie">영화</label> 
                      <input type="checkbox" name="searchCategory" value="영화" id="movie">
                      &nbsp;
                      <label for="music">음악</label> 
                      <input type="checkbox" name="searchCategory" value="음악" id="music">
                      &nbsp;
                      <label for="cooking">요리</label> 
                      <input type="checkbox" name="searchCategory" value="요리" id="cooking">
                      &nbsp;
                      <label for="game">게임</label> 
                      <input type="checkbox" name="searchCategory" value="게임" id="game">
                      &nbsp;
                      <label for="etc">기타</label> 
                      <input type="checkbox" name="searchCategory" value="기타" id="etc">
                      &nbsp;
                   </span>
                   <br>
                   <select name="searchKey" class="form-control" style="width:100px; display: inline-block;">
                       <option value="title">글제목</option>
                       <option value="content">내용</option>
                       <option value="titcont">제목+내용</option>
                   </select>
                   <input type="text" name="searchValue" class="form-control" style="width:25%; display: inline-block;">
                   <button class="form-control btn btn-primary" style="width:100px; display: inline-block;">검색</button>
               </form>
	            
	            
	            
	            
            	<script>
					$(function(){
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
					
					// script 태그 내에서도 JSTL 사용 가능
					// 대신 주의할 점이 있음!
					// -> 서버 동작 시 읽어나가는 코드 종류의 순서
					// JAVA > EL/JSTL > HTML > Javascript(jQuery)
					// -- 단, 이는 script태그 안에 있으므로 우선순위 상관 없음..! script태그 불러질때 시작됨
					<c:forEach var="ct" items="${paramValues.searchCategory}" varStatus="vs">
						$.each($("input[name=searchCategory]"), function(index, item){
							if($(item).val() == "${ct}"){
								$(item).prop("checked", "true");
							}
						});
					</c:forEach>
				</script>
	            
	        </div>
    	</div>
    	
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
