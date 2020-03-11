<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@page import="com.kh.sjproject.board.model.vo.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.sjproject.board.model.vo.Board"%>
<% 
	Board board = (Board)request.getAttribute("board");
	ArrayList<Attachment> files = (ArrayList<Attachment>)request.getAttribute("files");
	String currentPage = request.getParameter("currentPage");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<style>
	#board-area{ margin-bottom:100px;}
	#board-content{ padding-bottom:150px;}

	.boardImgArea{
		height: 300px;
	}

	.boardImg{
		width : 100%;
		height: 100%;
		
		max-width : 300px;
		max-height: 300px;
		
		margin : auto;
	}
	
	/* 이미지 화살표 색 조정
	-> fill='%23000' 부분의 000을
	   RGB 16진수 값을 작성하여 변경 가능 
	 */
	.carousel-control-prev-icon {
 		background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E") !important;
	}
	
	.carousel-control-next-icon {
  		background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E") !important;
	}
	
	.replyWrite > table{
		width: 90%;
		align: center;
	}
	
	#replyContentArea{ width: 90%; }
	
	#replyContentArea > textarea{
	    resize: none;
    	width: 100%;
	}
	
	#replyBtnArea{
	    width: 100px;
	    text-align: center;
	}
	
	.rWriter{ margin-right: 30px;}
	.rDate{
		font-size: 0.7em;
		color : gray;
	}
	
	#replyListArea{
		list-style-type: none;
	}
</style>
</head>
<body>
	<div class="container">
		<jsp:include page="../common/header.jsp"/>
		<jsp:include page="../common/nav.jsp"/>

		<div>

			<div id="board-area">

				<!-- Category -->
				<h6 class="mt-4">카테고리 : [${board.boardCategory }]</h6>
				
				<!-- Title -->
				<h3 class="mt-4">${board.boardTitle }</h3>

				<!-- Writer -->
				<p class="lead">
					작성자 : ${board.boardWriter }
				</p>

				<hr>

				<!-- Date -->
				<p>
					${board.boardModifyDate }
			 		<span class="float-right">조회수 ${board.boardCount }</span>
				</p>

				<hr>
				
				<!-- 이미지 부분 -->
                <c:if test="${!empty files }">
                
					<div class="carousel slide m-3" id="carousel-325626">
	                    
	                    <div class="carousel-inner boardImgArea">
	                    
	                    	
	                    	<c:forEach var="at" items="${files}" varStatus="vs">
	                    		<c:set var="src" value="${contextPath}/resources/uploadFiles/${at.fileChangeName}"/>
	                    		
		                        <div class="carousel-item <c:if test="${vs.index == 0}"> active</c:if>">
		                            <img class="d-block w-100 boardImg" src="${src}" />
		                            <input type="hidden" value="${at.fileNo}">
		                        </div>
	                        </c:forEach>
		                    
	                    </div> 
	                    
	                    
	                    <a class="carousel-control-prev" href="#carousel-325626" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-325626" data-slide="next">
	                    <span class="carousel-control-next-icon"></span> 
	                    <span class="sr-only">Next</span></a>
	                </div>
                </c:if>
				


				<!-- Content -->
				<div id="board-content">${board.boardContent }</div>

				<hr>
				
				<div>
					<c:if test="${(loginMember != null) && (board.boardWriter == loginMember.memberId)}">
						<a href="delete?no=" class="btn btn-success float-right">삭제</a> 
						<a href="updateForm?no=${board.boardNo }" class="btn btn-success float-right ml-1 mr-1">수정</a>
					</c:if>
					
					<a class="btn btn-primary float-right"
						href="
						<c:url value="list">
	                   		<c:if test="${!empty param.searchKey }">
				        		<c:param name="searchKey" value="${param.searchKey}"/>
				        	</c:if>
				        	
				        	<c:if test="${!empty param.searchValue }">
				        		<c:param name="searchValue" value="${param.searchValue}"/>
				        	</c:if>
				        	
				        	<c:if test="${!empty paramValues.searchCategory }">
							       		<c:forEach var="ct" items="${paramValues.searchCategory}" varStatus="vs">
							       			<c:param name="searchCategory" value="${ct}"/>
							       		</c:forEach>
						       		</c:if>
	                   		<c:param name="currentPage" value="${param.currentPage}"/>
	                   	</c:url>" >목록으로
                	</a>
				</div>
			</div>

			<hr>
	  		<div id="reply-area ">
	  			<!-- 댓글 출력 부분 -->
	  			<div class="replyList">
	                <ul id="replyListArea">
	                    <li class="reply-row" id="1"> 
	                   		<span class="rWriter">작성자</span> 
	                   		<span class="rDate">2020.01.23</span>
	                   		<p class="rContent">댓글 내용</p>
	                    </li>
	                    <hr>
	                </ul>
                </div>

				<div class="replyWrite">
					<table align="center">
						<tr>
							<td id="replyContentArea">
								<textArea rows="2" id="replyContent"></textArea>
							</td>
							<td id="replyBtnArea">
								<button class="btn btn-success" id="addReply" >댓글등록</button>
							</td>
						</tr>
					</table>
				</div>
            </div>


			<jsp:include page="../common/footer.jsp"/>
		</div>
	</div>
	
	<script>
		$("#deleteBtn").on("click",function(){
			if(confirm("정말 삭제 하시겠습니까?")) location.href = "delete?no=${param.no}";
		});
		
	</script>
</body>
</html>
