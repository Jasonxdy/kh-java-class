<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
	#notice-area{ margin-bottom:200px;}
	#notice-content{ padding-bottom:150px;}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp"%>
		<%@ include file="../common/nav.jsp"%>

		<div class="container">

			<h3>공지사항 등록</h3>
		      <hr>
		      <div class="bg-white rounded shadow-sm container py-3">
		        <form method="POST" action="insert" role="form" onsubmit="return validate();">
		          <div class="form-inline mb-2">
		            <div class="input-group">
		              <label class="input-group-addon mr-3">제목</label>
		              <input type="text" class="form-control" id="title" name="title" size="70">
		            </div>
		          </div>
		
		          <div class="form-inline mb-2">
		            <div class="input-group">
		              <label class="input-group-addon mr-3">작성자</label>
		              <h5 class="my-0" id="writer"><%= loginMember.getMemberId() %></h5>
		            </div>
		          </div>
		
		
		          <div class="form-inline mb-2">
		            <div class="input-group">
		              <label class="input-group-addon mr-3">작성일</label>
		              <h5 class="my-0" id="today"></h5>
		            </div>
		          </div>
		
		          <hr>
		
		          <div class="form-group">
		            <div><label for="content">내용</label> </div>
		            <textarea class="form-control" id="content" name="content" rows="10" style="resize: none;"></textarea>
		          </div>
		
		
		        <hr class="mb-4">
		
		        <div class="text-center">
					<button type="submit" class="btn btn-primary">등록</button>
					<a href="list" class="btn btn-primary">목록으로</a>
				</div>
		        
		        </form>
		      </div>

			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	
	<script>
		// 오늘 날짜 출력 
   		var today = new Date();
	
	  	var str = today.getFullYear() + "-" //FullYear: 2020 , year : 20
        		+ today.getMonth()+1 + "-"
        		+ today.getDate();
		$("#today").html(str);
		
		
		// 유효성 검사
		function validate(){
			if( $("#title").val().trim().length == 0){
				alert("제목을 입력해 주세요.");
				$("#title").focus();
				return false;
			}
			
			if( $("#content").val().trim().length == 0){
				alert("내용을 입력해 주세요.");
				$("#content").focus();
				return false;
			}
		}
		
	</script>
</body>
</html>
