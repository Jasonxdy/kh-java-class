<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보</title>
</head>
<style>
#content-main {
	height: 830px;
}
</style>
<body>
	<div class="container" id="content-main">
		<%@ include file="../common/header.jsp"%>
		<%@ include file="../common/nav.jsp"%>

		<div class="row">
			<%@ include file="sideMenu.jsp"%>

			<div class="col-sm-offset-2 col-sm-8">
				<h3>회원 탈퇴</h3>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="deleteMember.do" onsubmit="return validate();" 
						class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id">${loginMember.memberId}</h5>
							</div>
						</div>

						<!-- 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="currentPwd"
									name="currentPwd">
							</div>
						</div>

						<hr>
						<div class="panel panel-default">

							<div class="panel-body">
								<div class="form-group pull-left">
									<label class="control-label"> 회원 탈퇴 약관 </label>
									<div class="col-xs-12">
										<textarea class="form-control" readonly rows="10" cols="100">
제1조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4


제2조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4
</textarea>
									</div>
									<div class="checkbox pull-right">
										<div class="custom-checkbox">
											<div class="form-check">
												<input type="checkbox" name="agree" id="agree"
													class="form-check-input custom-control-input"
													> <br>
												<label class="form-check-label custom-control-label"
													for="agree">위 약관에 동의합니다.</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<hr class="mb-4">
						<button class="btn btn-primary btn-lg btn-block" id="btn"
							type="submit">탈퇴</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>

	<script>
		function validate(){
			/* checkbox에는 checked 속성이 있는데 기본값이 false인 것을 이용함 */
			if( !$("#agree").prop("checked") ){
				alert("약관에 동의해주세요");
				return false;
			} else {
				return confirm("정말 탈퇴하시겠습니까?");
			}
		}
	
	</script>

</body>
</html>
