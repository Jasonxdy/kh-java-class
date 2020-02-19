<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"/>
	
	<c:if test="${!empty msg}">
		<script>alert("${msg}")</script>
		<c:remove var="msg"/>
	</c:if>

	<header class="blog-header">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4 pt-1">
				<a class="text-muted" href="${contextPath}"> 
				<img src="${contextPath}/resources/images/springLogo.png" height="60px">
				</a>
			</div>
			
			<%-- 로그인이 되어있지 않을 경우 로그인 버튼 노출 --%>
			<c:if test="${ empty sessionScope.loginMember }" >
			<div class="col-4 d-flex justify-content-end align-items-center">
				<a id="modal-120930" href="#modal-container-120930" role="button"
					class="btn btn-sm btn-outline-secondary " data-toggle="modal">로그인</a>

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
								<form class="form-signin" method="POST" action="${contextPath}/member/login"
									onsubmit="return loginValidate();">
									<input type="text" class="form-control" id="memberId" name="memberId"
										placeholder="아이디" value="${cookie.saveId.value }"> <br> 
									<input type="password" class="form-control" id="memberPwd" name="memberPwd"
										placeholder="비밀번호"> <br>
									<div class="checkbox mb-3">
										<label> 
											<!-- <input type="checkbox"> 아이디 저장 -->
											
											<!-- Cookie 배운 후 -->
											<c:if test="${! empty cookie.saveId.value }">
												<input type="checkbox" name="save" id="save" checked> 아이디 저장
											</c:if>
											<c:if test="${ empty cookie.saveId.value }">
												<input type="checkbox" name="save" id="save"> 아이디 저장
											</c:if>
											
										</label>
									</div>
									<button class="btn btn-lg btn-success btn-block" type="submit">로그인</button>
									<a class="btn btn-lg btn-secondary btn-block" href="${contextPath}/member/signUpForm">회원가입</a>
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
			<%-- 로그인 되어있지 않은 경우 END --%>
			</c:if>
			
			<%-- 로그인 된 경우 --%>
			<c:if test="${! empty sessionScope.loginMember }">
			<div class="d-flex justify-content-end align-items-center">
				${sessionScope.loginMember.memberName}님 환영합니다. &nbsp;
				<a href="${contextPath}/member/mypage" role="button" class="btn btn-sm btn-outline-success">마이페이지</a> &nbsp;
				<a href="${contextPath}/member/logout" role="button" class="btn btn-sm btn-outline-success">로그아웃</a>
					
			</div>
			<%-- 로그인 된 경우END --%>
			</c:if>
		</div>
	</header>
	
	<script>
		// 로그인 유효성 검사
		function loginValidate(){
			if($("#memberId").val().trim().length == 0){
				alert("아이디를 입력하세요.");
				$("#memberId").focus();
				
				return false;
			}
			
			if($("#memberPwd").val().trim().length == 0){
				alert("비밀번호를 입력하세요.");
				$("#memberPwd").focus();
				
				return false;
			}
			
			return true;
		}
	</script>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>