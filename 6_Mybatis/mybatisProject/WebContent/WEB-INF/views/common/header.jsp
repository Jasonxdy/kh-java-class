<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<!-- c:set = 변수선언 -->
	<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application"/>
																			<!-- application : 서버가 꺼질때 까지 어디서든지 사용할 수 있는 전역변수 개념 -->


	<!-- empty : 값이 비어있거나 null인 경우 true 반환 -->
	<c:if test="${!empty msg}">
		<script>
			alert("${msg}");
		</script>
		<c:remove var="msg"/>
		<!-- msg라는 속성을 제거, scope 미지정시 모두(?) 찾아서 지움 -->
	</c:if>

	<header class="blog-header">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4 pt-1">
				<%-- <a class="text-muted" href="<% request.getContextPath() %>"> --%>
				<a class="text-muted" href="${contextPath}">
												<!-- 서버 ip: 8080/sjproject 반환 // 현재 프로젝트의 최상위 주소가 나옴(?) -->
												<!-- request: 내장객체이므로 사용가능 -->												 
				<img src="https://www.iei.or.kr/resources/images/common/top_logo.jpg"
					height="60px">
				</a>
			</div>
			
			<%-- 로그인이 되어있지 않은 경우 start --%>
			<%-- <% if (loginMember == null) { %> --%>
			<c:if test="${empty sessionScope.loginMember}">
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
								<%-- <form class="form-signin" method="POST" action="<%= request.getContextPath()%>/member/login.do" --%>
								<form class="form-signin" method="POST" action="${contextPath}/member/login.do"
									onsubmit="return loginValidate();"																		
								>
									<input type="text" class="form-control" id="memberId" name="memberId"
										placeholder="아이디" value="${cookie.saveId.value}"> <br> 
									<input type="password" class="form-control" id="memberPwd" name="memberPwd"
										placeholder="비밀번호"> <br>
									<div class="checkbox mb-3">
										
										
										<label> 
										<!-- <input type="checkbox" 아이디 저장 -->
										
										<!-- Cookie 배운 후 -->
											<c:if test="${!empty cookie.saveId}">
												<input type="checkbox" id="save" name="save" checked> 아이디 저장
											</c:if>
											<c:if test="${empty cookie.saveId}">
												<input type="checkbox" id="save" name="save"> 아이디 저장
											</c:if>
										</label>
										
									</div>
									<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
									<a class="btn btn-lg btn-secondary btn-block" href="${contextPath}/member/signUpForm.do">회원가입</a>
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
			</c:if>
			<%-- 로그인이 되어있지 않은 경우 end --%>

			<%-- 로그인이 된 경우 start --%>
			<%-- <% } else {%> --%>
			<c:if test="${!empty sessionScope.loginMember }">
				<div class="d-flex justify-content-end align-items-center">
					${sessionScope.loginMember.memberName}님 환영합니다.&nbsp;
					<a href="${contextPath}/member/mypage.do" role="button" class="btn btn-sm btn-outline-secondary">
						마이페이지
					</a>
					&nbsp;
					<a href="${contextPath}/member/Logout.do" role="button" class="btn btn-sm btn-outline-secondary">
						로그아웃
					</a>
				</div>
			</c:if>
			<%-- <% } %> --%>
			<%-- 로그인이 된 경우 end --%>
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