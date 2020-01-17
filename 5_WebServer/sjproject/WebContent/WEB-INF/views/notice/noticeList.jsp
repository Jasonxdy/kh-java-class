<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.kh.sjproject.member.model.vo.Notice"%>
	
<%
	List<Notice> list = (List<Notice>)request.getAttribute("list");

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
	        <button type="button" class="btn btn-primary float-right">글쓰기</button>
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
	            <form class="text-center" id="searchForm">
	                <select class="form-control" style="width:100px; display: inline-block;">
	                    <option selected>글제목</option>
	                    <option>내용</option>
	                    <option>제목+내용</option>
	                </select>
	                <input type="text" class="form-control" style="width:25%; display: inline-block;">
	                <button class="form-control btn btn-primary" style="width:100px; display: inline-block;">검색</button>
	            </form>
	        </div>
    	</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	
	<script>
	</script>
	
	
	
</body>
</html>
