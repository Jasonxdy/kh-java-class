<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
</head>
<style>
	#content-main{
	height: 830px;}
</style>
<body>
	<div class="container" id="content-main">
		<jsp:include page="../common/header.jsp"/>
		<jsp:include page="../common/nav.jsp"/>

		<div class="row">
			<jsp:include page="sideMenu.jsp"/>

			<div class="col-sm-8">
				<h3>비밀번호 변경</h3>
				<hr>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="updatePwd" onsubmit="return validate();" class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id">${loginMember.memberId}</h5>
							</div>
						</div>

						<hr>
						<!-- 현재 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>현재 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="currentPwd"
									name="memberPwd">
							</div>
						</div>

						<!-- 새 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>새 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="newPwd1"
									name="newPwd1">
							</div>
						</div>

						<!-- 새 비밀번호 확인-->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>새 비밀번호 확인</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="newPwd2"
									name="newPwd2">
							</div>
						</div>
						
						<hr class="mb-4">
						<button class="btn btn-success btn-lg btn-block" type="submit">변경하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"/>

	<script>
		// submit 동작
		function validate() {
			// 비밀번호  유효성 검사
			//영어 대,소문자 + 숫자, 총 6~12글자
			var regExp = /^[A-Za-z0-9]{6,12}$/;
			if(!regExp.test($("#newPwd1").val())){ 
				alert("유효하지 않은 비밀번호 입니다.");
				$("#newPwd1").focus();
				
				return false;
            }
			
			if($("#newPwd1").val() != $("#newPwd2").val()){
				alert("새 비밀번호가 일치하지 않습니다.");
				$("#newPwd2").focus();
				
				return false;
			}

		}
		
	</script>
	
</body>
</html>
