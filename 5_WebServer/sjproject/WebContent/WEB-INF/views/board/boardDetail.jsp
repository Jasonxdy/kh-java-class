<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.sjproject.board.model.vo.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.sjproject.board.model.vo.Board"%>
<%
	Board board = (Board) request.getAttribute("board");
	ArrayList<Attachment> files = (ArrayList<Attachment>) request.getAttribute("files");

	String currentPage = request.getParameter("currentPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<style>
#board-area {
	margin-bottom: 100px;
}

#board-content {
	padding-bottom: 150px;
}

.boardImgArea {
	height: 300px;
}

.boardImg {
	width: 100%;
	height: 100%;
	max-width: 300px;
	max-height: 300px;
	margin: auto;
}

/* 이미지 화살표 색 조정
	-> fill='%23000' 부분의 000을
	   RGB 16진수 값을 작성하여 변경 가능 
	 */
.carousel-control-prev-icon {
	background-image:
		url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M5.25 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E")
		!important;
}

.carousel-control-next-icon {
	background-image:
		url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M2.75 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E")
		!important;
}

.replyWrite>table {
	width: 90%;
	align: center;
}

#replyContentArea {
	width: 90%;
}

#replyContentArea>textarea {
	resize: none;
	width: 100%;
}

#replyBtnArea {
	width: 100px;
	text-align: center;
}

.rWriter {
	margin-right: 30px;
}

.rDate {
	font-size: 0.7em;
	color: gray;
}

#replyListArea {
	list-style-type: none;
}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp"%>
		<%@ include file="../common/nav.jsp"%>

		<div>

			<div id="board-area">

				<!-- Category -->
				<h6 class="mt-4">
					카테고리 : [<%=board.getBoardCategory()%>]
				</h6>

				<!-- Title -->
				<h3 class="mt-4"><%=board.getBoardTitle()%></h3>

				<!-- Writer -->
				<p class="lead">
					작성자 :
					<%=board.getBoardWriter()%>
				</p>

				<hr>

				<!-- Date -->
				<p>
					<%=board.getBoardModifyDate()%>
					<span class="float-right">조회수 <%=board.getBoardCount()%></span>
				</p>

				<hr>

				<%
					if (files != null) {
				%>
				<div class="carousel slide m-3" id="carousel-325626">

					<div class="carousel-inner boardImgArea">

						<%
							for (int i = 0; i < files.size(); i++) {
									String src = request.getContextPath() + "/resources/uploadImages/"
											+ files.get(i).getFileChangeName();

									String imgClass = "carousel-item";
									if (i == 0)
										imgClass += " active";
						%>
						<div class="<%=imgClass%>">
							<img class="d-block w-100 boardImg" src="<%=src%>" /> <input
								type="hidden" value=<%=files.get(i).getFileNo()%>>
						</div>
						<%
							}
						%>

					</div>


					<a class="carousel-control-prev" href="#carousel-325626"
						data-slide="prev"><span class="carousel-control-prev-icon"></span>
						<span class="sr-only">Previous</span></a> <a
						class="carousel-control-next" href="#carousel-325626"
						data-slide="next"><span class="carousel-control-next-icon"></span>
						<span class="sr-only">Next</span></a>
				</div>
				<%
					}
				%>



				<!-- Content -->
				<div id="board-content"><%=board.getBoardContent()%></div>


				<hr>


				<div>
					<%
						if (loginMember != null && (board.getBoardWriter().equals(loginMember.getMemberId()))) {
					%>
					<a href="delete?no=" class="btn btn-primary float-right">삭제</a> <a
						href="updateForm?no="
						class="btn btn-primary float-right ml-1 mr-1">수정</a>
					<%
						}
					%>

					<a href="list?currnetPage=<%=currentPage%>"
						class="btn btn-primary float-right">목록으로</a>
				</div>
			</div>

			<hr>
			<div id="reply-area ">
				<!-- 댓글 출력 부분 -->
				<div class="replyList">
					<ul id="replyListArea">
						<li class="reply-row" id="1"><span class="rWriter">작성자</span>
							<span class="rDate">2020.01.23</span>
							<p class="rContent">댓글 내용</p></li>
						<hr>
					</ul>
				</div>



				<!-- 댓글 작성 영역 -->
				<div class="replyWrite">
					<table align="center">
						<tr>
							<td id="replyContentArea"><textArea rows="2"
									id="replyContent"></textArea></td>
							<td id="replyBtnArea">
								<button class="btn btn-primary" id="addReply">댓글등록</button>
							</td>
						</tr>
					</table>
				</div>
			</div>


			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>

	<script>
		$("#deleteBtn").on("click",function(){
			if(confirm("정말 삭제 하시겠습니까?")) location.href = "delete?no=";
		});
		
		
		// 이미지 클릭 시 다운로드
		$(".boardImg").on("click",function(){
			var fNo = $(this).next().val();
			location.href="<%=request.getContextPath()%>/board/download?fNo="+fNo;
		});
		
		
		
		// 댓글 등록 버튼 동작
		$("#addReply").on("click", function(){
			var writer;
			// 로그인 검사 필요
			var boardNo = <%=board.getBoardNo()%>;
			var content = $("#replyContent").val();
			
			// 로그인 검사
			<%if (loginMember == null) {%>
				alert("로그인 후 이용해 주세요");
			<%} else {%>
				writer = "<%=loginMember.getMemberNo()%>";

			$.ajax({ // ajax 시작함
				url : "insertReply", // URL은 ajax 사용 시 필수 속성
				type : "post", // filter 만들어 두어서 자유롭게 인코딩 생각 안하고 사용 가능
				data : {
					writer : writer,
					content : content,
					boardNo : boardNo
				},
				/*  key:value */
				// 객체 형태로 데이터 넘김
				// cf> 이때 data 안에 자바스크립트의 객체 형태로 문자열이 전달되는 경우 : JSON임
				success : function(result) {
					if (result > 0) {
						$("#replyContent").val("");
						selectRlist();

					} else {
						alert("댓글 등록 실패");
					}

				},
				error : function() {
					console.log("ajax 통신 실패 ㅠㅠ");
				}
			});
	<%}%>
		});

		// 댓글 출력 함수
		function selectRlist() {

			var boardNo = <%=board.getBoardNo()%>;
		$.ajax({
				url : "selectReplyList",
				type : "POST",
				dataType : "json",
				data : {boardNo : boardNo},

				success : function(rList) {

					var $replyListArea = $("#replyListArea");

					$replyListArea.html(""); // 기존 내용 삭제

					$.each(rList, function(i) {

						/* HTML 반복적으로 뽑아내는 방법! */
						var $li = $("<li>"); // == <li></li>을 의미
						var $rWriter = $("<span>").prop("class", "rWriter")
								.html(rList[i].replyWriter); // class 속성을 추가하는데 그 속성값
						var $rDate = $("<span>").prop("class", "rDate").html(
								rList[i].replyModifyDate);
						var $rContent = $("<p>").prop("class", "rContent")
								.html(rList[i].replyContent);
						var $hr = $("<hr>");

						$li.append($rWriter).append($rDate).append($rContent);
						// .append : 선택된 요소의 태그 안쪽에 작성됨

						$replyListArea.append($li).append($hr);

					});
				},
				error : function () {
					console.log("ajax 연결 실패");
				}
			});

		}
		
		
		
		
		$(function(){
			// 화면이 로딩 됏을때 바로 selectRlist()를 실행시키기!
			
			// 일정시간 (3초) 마다 댓글 자동 갱신
			setInterval(function(){
				selectRlist();
			}, 1000);
		});
	</script>
</body>
</html>
