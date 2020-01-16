<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javascript 방식의 Ajax</title>
<script>
	// XMLHttpRequest 객체 생성
	// - Ajax는 브라우저 내장 객체인 XMLHttpRequest 객체를 이용하여 구현
	
	var xhr;
	// XMLHttpRequest 객체를 바로 생성하지 않고
	// 크로스 브라우저 대처 작업을 진행하여 생성
	// -> 브라우저 별로 Ajax에 사용되는 객체가 다름
	
	function getXMLHttpRequest() {
		// IE 7버전 이상 또는 그 외 브라우저들
		if(window.XMLHttpRequest){ // window (자바스크립트 최상위 객체의) XMLHttpRequest객체가 있느냐?
			return new XMLHttpRequest(); // 해당 객체 생성
		}
		
		// IE 6버전 이하
		else if (window.ActiveXObject) { // window (자바스크립트 최상위 객체의) ActiveXObject객체가 있느냐?
			try{
				return ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				return null;
			}
		}
		
		// ajax를 지원하지 않는 브라우저
		else {
			return null;
		}
		
	}
	
</script>

</head>
<body>
	<h2>버튼 클릭 시 페이지가 갱신 또는 이동 되는지 꼭 확인할 것!!!!</h2>
	
	<h3>1. 버튼 클릭 시 GET 방식으로 서버에 데이터 전송 및 응답.</h3>
	<button onclick="jsAjax1();">GET 방식 전송</button>
	<p id="test1"></p>
	<hr>
	
	<h3>2. 버튼 클릭 시 POST 방식으로 서버에 데이터 전송 및 응답.</h3>
	<button onclick="jsAjax2();">POST 방식 전송</button>
	<p id="test2"></p>
	<hr>
	
	<script>
		function jsAjax1() {
			// 1. XMLHttpRequest 객체 생성
			xhr = getXMLHttpRequest();
			
			// 2. onreadystatechange
			// Ajax 통신이 (비동기로) 성공한 경우에 대한 동작 정의
			xhr.onreadystatechange = function(){
				
				// 1) readyState : 서버 응답 상태 확인
				//		-> Ajax 통신 진행 상황 확인
				if(xhr.readyState == 4) {
					//	readyState == 4 -> 요청을 정상적으로 받고 응답할 준비가 됨 (연결이 됨)
	
					// 2) status : HTTP 응답 상태 코드
					//		-> 응답이 정상적으로 이루어 졌는지 확인
					if(xhr.status == 200) {
						// status == 200 -> 응답이 정상적으로 이루어짐 (응답 가능)
						console.log("jsAjax1() - ajax 통신 성공");
						
						
						var str = xhr.responseText;
						// reponseText : 응답 데이터 문자열을 반환
						
						document.getElementById("test1").innerHTML = str;
					}
				}
			}
			
			// 3) open() : 서버와 데이터 교환 시 필요한 정보 입력
			xhr.open("GET", "<%= request.getContextPath() %>/jsAjax1.do?name=홍길동&age=20", true);
			//		 전송 방식						요청할 URL									, true == 비동기, false == 동기
															//쿼리 스트링 : input태그 없이 값 임의로 지정 가능
			
			// 4) send() : 서버로 데이터 교환 요청
	 		xhr.send();
		}
			
		function jsAjax2() {
			// 1. XMLHttpRequest 객체 생성
			xhr = getXMLHttpRequest();
			
			// 2. onreadystatechange
			// Ajax 통신이 (비동기로) 성공한 경우에 대한 동작 정의
			xhr.onreadystatechange = function(){
				
				// 1) readyState : 서버 응답 상태 확인
				//		-> Ajax 통신 진행 상황 확인
				if(xhr.readyState == 4) {
					//	readyState == 4 -> 요청을 정상적으로 받고 응답할 준비가 됨 (연결이 됨)
	
					// 2) status : HTTP 응답 상태 코드
					//		-> 응답이 정상적으로 이루어 졌는지 확인
					if(xhr.status == 200) {
						// status == 200 -> 응답이 정상적으로 이루어짐 (응답 가능)
						console.log("jsAjax2() - ajax 통신 성공");
						
						
						var str = xhr.responseText;
						// reponseText : 응답 데이터 문자열을 반환
						
						document.getElementById("test2").innerHTML = str;
					}
				}
			}
			
			// 3) open() : 서버와 데이터 교환 시 필요한 정보 입력
			xhr.open("POST", "<%= request.getContextPath() %>/jsAjax2.do", true);
			//		 전송 방식						요청할 URL									, true == 비동기, false == 동기
															//쿼리 스트링 : input태그 없이 값 임의로 지정 가능
			
			// * POST 방식 데이터 전송 시 send() 호출하기 전에 전달 되는 데이터의 Mime Type을 설정해야 함
			xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			
			
			// 4) send() : 서버로 데이터 교환 요청
	 		xhr.send("name=신사임당&age=40");
			
			
		
		}
		
	</script>
	
	
	
		

</body>
</html>