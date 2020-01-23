<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- https://developers.google.com  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- jQuery방식의 Ajax는 작성할 코드의 양이 많으므로 별도의 js파일을 만들어 진행 -->
<script src="<%=request.getContextPath() %>/js/JQueryAjax.js" type="text/javascript"></script>
<title>jQuery방식의 Ajax</title>
</head>
<body>
	
	<h3>1. 버튼 클릭 시 Get 방식으로 서버에 데이터 전송 및 응답</h3>
	입력 : <input type="text" id="input1">
	<button id="btn1">전송</button>
	응답 : <input type="text" id="output1" style="width:300px" readonly>
	<hr>
	
	<h3>2. 버튼 클릭 시 POST 방식으로 서버에 데이터 전송 및 응답</h3>
	입력 : <input type="text" id="input2">
	<button id="btn2">전송</button>
	응답 : <input type="text" id="output2" style="width:300px" readonly>
	<hr>
	
	
	
	<h3>3. 서버로 기본형 데이터 전송 후, 응답을 객체(Obejct)로 받기</h3>
	<h4>번호를 입력하여 조회하고자 하는 사용자 정보를 요청, 사용자가 없는 경우 "사용자 정보가 없습니다."</h4>
	사용자 번호 입력 : <input type="text" id="input3">
	<button id="btn3">조회</button><br><br>
	<textarea id="textarea3" rows="4" cols="30"></textarea>
	<!-- 
		- 객체를 전송할 경우 JSON을 사용하면 쉬움.
		- Java에서 JSON 사용하기 위해서는 라이브러리가 필요
		https://code.google.com/archive/p/json-simple/downloads
		-> json-simple-1.1.1.jar 다운로드 받아 lib 폴더에 추가
	 -->
	<hr>
	

	
	<h3>4. 서버로 기본형 데이터를 전송 후, 응답을 리스트(List)형태로 받기</h3>
	<h4>선택한 성별을 가진 모든 회원 정보 가져오기</h4>
	성별 : 남<input type="radio" name="chk_gender4" value="남" checked>
	여<input type="radio" name="chk_gender4" value="여">
	<button id="btn4">조회</button><br><br>
	<textarea id="textarea4" rows="5" cols="30"></textarea>
	<hr>
	
	<!-- json array 사용하여 전달하면 됨. 학습동영상 참조 -->
	
	
	
	<h3>5. 서버로 여러개의 기본형 데이터 전송 후, 응답을 맵(Map)형태로 받아서 테이블에 출력하기</h3>
	<h4>정보를 조회하고자하는 회원의 이름을 입력(다수 입력 시 ','로 구분)</h4>
	가입된 회원 : [박철수, 김영희, 오영심, 이민기, 홍길동]<br><br>
	이름 : <input type="text" id="input5" width="300">
	<button id="btn5">검색</button><br><br>
	<table id="memberTable5" border="1" style="text-align:center">
		<thead>
			<th>번호</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
		</thead>
		<tbody>
		</tbody>
	</table>	
	<hr>
	
	
	<h3>6. Gson을 이용하여 응답을 List 형태로 전송받아 테이블에 출력하기 </h3>
	<button id="btn6">list 조회</button>
	<table id="memberTable6" border="1" style="text-align:center">
		<thead>
			<th>번호</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
		</thead>
		<tbody>
		</tbody>
	</table>
	<hr>
	
	
	<h3>7. Gson을 이용하여 응답을 Map 형태로 전송받아 테이블에 출력하기 </h3>
	<button id="btn7">Map 조회</button>
	<table id="memberTable7" border="1" style="text-align:center">
		<thead>
			<th>번호</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
		</thead>
		<tbody>
		</tbody>
	</table>
	<hr>


</body>
</html>