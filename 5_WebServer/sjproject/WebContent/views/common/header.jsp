<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
		integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
		crossorigin="anonymous">
</head>
<body>
	<header class="blog-header">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4 pt-1">
				<a class="text-muted" href="<%= request.getContextPath()%>">
												<!-- 서버 ip: 8080/sjproject 반환 // 현재 프로젝트의 최상위 주소가 나옴(?) -->
												<!-- request: 내장객체이므로 사용가능 -->												 
				<img src="https://www.iei.or.kr/resources/images/common/top_logo.jpg"
					height="60px">
				</a>
			</div>
			
			<div class="col-4 d-flex justify-content-end align-items-center">
				<a id="modal-120930" href="#modal-container-120930" role="button"
					class="btn btn-sm btn-outline-secondary" data-toggle="modal">로그인</a>

				<div class="modal fade" id="modal-container-120930" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="myModalLabel">로그인 모달창</h5>
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">×</span>
								</button>
							</div>
							<div class="modal-body">
																		<!-- action의 주소 : 완전 임의로 지정 가능한듯? -->
								<form class="form-signin" method="POST" action="<%= request.getContextPath()%>/member/login.do"
									onsubmit="return loginValidate();"																		
								>
									<input type="text" class="form-control" id="memberId" name="memberId"
										placeholder="아이디"> <br> 
									<input type="password" class="form-control" id="memberPwd" name="memberPwd"
										placeholder="비밀번호"> <br>
									<div class="checkbox mb-3">
										<label> <input type="checkbox"> 아이디 저장
										</label>
									</div>
									<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
									<a class="btn btn-lg btn-secondary btn-block" href="#">회원가입</a>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	
	
	<script>
	
		// 로그인 유효성 검사
		function loginValidate(){
			// trim() : 문자열 앞뒤 공백 제거
			if($("#memberId").val().trim().length == 0) {
				alert("아이디를 입력하세요");
				$("#memberId").focus();
				
				return false;
				// form태그 기본 동작 속성 제거
			}
			
			if($("#memberPwd").val().trim().length == 0) {
				alert("비밀번호를 입력하세요");
				$("#memberPwd").focus();
				
				return false;
				// form태그 기본 동작 속성 제거
			}
			return true;
		}
	
	</script>
	
	

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>