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
		<%@ include file="../common/header.jsp"%>
		<%@ include file="../common/nav.jsp"%>

		<div class="row">
			<%@ include file="sideMenu.jsp"%>

			<div class="col-sm-8">
				<h3>비밀번호 변경</h3>
				<hr>
				<div class="bg-white rounded shadow-sm container p-3">
					<form class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id">세션에 저장된 아이디</h5>
							</div>
						</div>

						<hr>
						<!-- 현재 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>현재 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control" id="currentPwd"
									name="currentPwd">
							</div>
						</div>

						<!-- 새 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>새 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control" id="newPwd1"
									name="newPwd1">
							</div>
						</div>

						<!-- 새 비밀번호 확인-->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>새 비밀번호 확인</h6>
							</div>
							<div class="col-md-6">
								<input type="text" class="form-control" id="newPwd2"
									name="newPwd2">
							</div>
						</div>
						
						<hr class="mb-4">
						<button class="btn btn-primary btn-lg btn-block" type="submit">변경하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>

	<script type="text/javascript">
		$(".phone").on("input", function() {
			if ($(this).val().length > $(this).prop("maxLength")) {
				$(this).val($(this).val().slice(0, $(this).prop("maxLength")));
			}
		});
	</script>

	<script>
		// 각 유효성 검사 결과를 저장할 객체
		var singUpCheck = {
			"phone" : false,
			"email" : false
		};

		// 실시간 입력 형식 검사
		// 정규표현식
		$(document).ready(function() {

			// jQuery 변수 : 변수에 직접적으로 jQuery메소드를 사용할 수 있음.
			var $phone2 = $("#phone2");
			var $phone3 = $("#phone3");
			var $email = $("#email");
		});


		// submit 동작
		function validate() {

			for ( var key in singUpCheck) {
				if (!singUpCheck[key]) {
					alert("일부 입력값이 잘못되었습니다.");
					var id = "#" + key;
					$(id).focus();
					return false;
				}
			}
		}
		
	</script>
	
</body>
</html>
