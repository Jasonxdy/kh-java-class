<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-sm-4 mt-5">
	<h3>사이드 메뉴</h3>
	<ul class="list-group">
		<li class="list-group-item list-group-item-action"><a href="mypage.do">내정보</a></li>
		<li class="list-group-item list-group-item-action"><a href="changePwd.do">비밀번호 변경</a></li>
		<li class="list-group-item list-group-item-action"><a href="secession.do">회원 탈퇴</a></li>
	</ul>
</div>

<%-- 이게 모듈화가 잘 된 모양
왜냐면 어짜피 html 태그 다 만들어도 jsp는 합쳐질 때 위아래 다 떼어지고 합쳐짐 --%>