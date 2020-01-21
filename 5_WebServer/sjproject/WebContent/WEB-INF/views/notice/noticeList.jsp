<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.kh.sjproject.notice.model.vo.Notice"%>
	
<%
	List<Notice> list = (List<Notice>)request.getAttribute("list");

	// 이전 페이지 요청 (request)에 있는 파라미터를 얻어옴
	String searchKey = request.getParameter("searchKey");
	String searchValue = request.getParameter("searchValue");
	

%>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
	                        <th>제목</th>
	                        <th>작성자</th>
	                        <th>조회수</th>
	                        <th>작성일</th>
	                    </tr>
	                </thead>
	                <tbody>
	               		<% if(list.isEmpty()) {%>
                      <tr>
                         <td colspan="5">존재하는 공지사항이 없습니다.</td>
                      </tr>
                      <% } else{%>
                         <% for(Notice notice : list){ %>
                         <tr>
                            <td><%= notice.getNoticeNo() %></td>
                            <td><%= notice.getNoticeTitle() %></td>
                            <td><%= notice.getNoticeWriter() %></td>
                            <td><%= notice.getNoticeCount() %></td>
                            <td><%= notice.getNoticeModifyDt() %></td>
                         </tr>
                         <% } %>
                      <% } %>
	                </tbody>
	            </table>
	        </div>
	
	        <hr>
	        
	        <%-- 로그인된 계정이 관리자 등급인 경우에만 글쓰기 버튼 노출 --%>
	        
	        <% if(loginMember != null && loginMember.getMemberGrade().equals("A")) { %>
	        <button type="button" class="btn btn-primary float-right" onclick="location.href='insertForm';">글쓰기</button>
	        <% } %>
	        
	        
	        
	        <div style="clear: both;">
	            <ul class="pagination">
	                <li>
	                    <a class="page-link" href="#">Previous</a>
	                </li>
	                <li>
	                    <a class="page-link" href="#">1</a>
	                </li>
	                <li>
	                    <a class="page-link" href="#">2</a>
	                </li>
	                <li>
	                    <a class="page-link" href="#">3</a>
	                </li>
	                <li>
	                    <a class="page-link" href="#">4</a>
	                </li>
	                <li>
	                    <a class="page-link" href="#">5</a>
	                </li>
	                <li>
	                    <a class="page-link" href="#">Next</a>
	                </li>
	            </ul>
	        </div>
	        <div>
	            <form method="GET" action="search" class="text-center" id="searchForm">
	                <select name="searchKey" class="form-control" style="width:100px; display: inline-block;">
	                    <option selected value="title">글제목</option>
	                    <option value="content">내용</option>
	                    <option value="titcont">제목+내용</option>
	                </select>
	                <input type="text" name="searchValue" class="form-control" style="width:25%; display: inline-block;">
	                <button class="form-control btn btn-primary" style="width:100px; display: inline-block;">검색</button>
	            </form>
	            
	            <script>
	            	$(function(){
	            		
	            		
	            		// 검색 후 검색창에 검색한 이전 기록 남기기 Script
	            		var searchKey = "<%= searchKey %>";
	            		var searchValue = "<%= searchValue %>";
	            		
	            		// null과 "null"이 다르기 때문에  뒤에 저장된 거에 기반하여 쌍따옴표를 추가해야 함
	            		if(searchKey != "null" && searchValue != "null") {
	            			// 검색한 경우가 있을때
	            			
	            			// $.each(요소 , 요소에대한함수(index, item)) // index : 인덱스 , item : 선택된 요소
	            			$.each($("select[name=searchKey] > option"), function(index, item){
	            				if($(item).val() == searchKey){
	            					//$(item) : 현재 접근 요소
	            					$(item).prop("selected", "true");
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
		// 공지사항 상세조회 기능
		$(function(){
			$("#list-table td").on("click", function(){
				var noticeNo = $(this).parent().children().eq(0).text();
				//				이벤트발생	의 부모	의 자식 중 첫번째?;; 암튼 글번호
				// 쿼리스트링을 이용하여 GET 방식으로 글번호를 server로 전달
				location.href= "<%= request.getContextPath() %>/notice/detail?no=" +noticeNo;
			}).on("mouseenter", function(){
				$(this).parent().css("cursor", "pointer");
			});
		});
	</script>
	
	
	
</body>
</html>
