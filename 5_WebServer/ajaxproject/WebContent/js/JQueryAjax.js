$(function() {
	// 1. 버튼 클릭 시 GET 방식으로 서버에 입력값 전송 및 응답
	$("#btn1").click(function() {
		var input = $("#input1").val();

		$.ajax({ // Javascript 객체 형식으로 넘기는 것

			// url : 데이터를 전송할 경로 (url, 필수!)
			url : "../jqTest1.do", // -> ajaxProject 폴더의 경로를 의미함

			// data : 요청 시 전달할 파라미터 설정
			data : {
				input : input
			}, // 첫번째 input : key, 즉 String 문자열 / 두번째 input : value, 즉 위에 설정한
				// 지역변수

			// type : 전송 방식(GET / POST)
			type : "GET",

			// success : Ajax 통신 성공 시 처리할 함수를 지정하는 속성
			success : function(result) {
				// success 함수의 매개변수 (result)의 의미
				// -> 서버에서 전달받은 응답 데이터가 저장된 변수
				// -> 이 매개변수명은 임의로 지정 가능
				console.log("1번 Ajax 통신 성공");
				$("#output1").val(result);
			},

			// error : Ajax 통신 실패 시 처리할 함수를 지정하는 속성
			error : function() {
				console.log("1번 Ajax 통신 실패...ㅠㅠ");
			},

			// complete : 통신 성공 여부 관계없이 실행되는 함수를 지정하는 속성
			complete : function() {
				console.log("무조건 호출되는거 확인~!");
			}
		})

	});

	// 2. 버튼 클릭 시 POST 방식으로 서버에 데이터 전송 및 응답

	$("#btn2").click(function() {
		var input = $("#input2").val();

		$.ajax({
			url : "../jqTest2.do",
			data : {
				input : input
			},
			type : "post", // 대소문자 안가림 ㅎ

			// dataType : 서버의 응답데이터의 형식을 지정해주는 속성.
			dataType : "text",
			// -> 미작성 시 자동으로 응답데이터의 형식을 판단하여 지정함.

			success : function(data) {
				console.log("2번 Ajax 통신 성공");
				$("#output2").val(data);
			},

			error : function(e) {
				console.log("2번 Ajax 통신 실패 ... ㅠㅠ");
				console.e(e);
			}

		});

	});
	
	
	
	// 3. 서버로 기본형 데이터 전송 후 응답을 객체(Object)로 전달 받기
	$("#btn3").click(function(){
		
		var input = $("#input3").val();
		
		$.ajax({
			url : "../jqTest3.do",
			data : {input:input},
			dataType : "json", // * json 데이터를 전달하는 방법 (3)
			// dataType :  응답 데이터의 형식을 지정 (가장 간단한 방법)
			type : "get",
			success : function(obj){
				// * json 데이터를 전달하는 방법 (2)
//				obj = JSON.parse(obj);
				console.log(obj);
				
				var result = "";
				if(obj != null){
					result = "번호 : " + obj.no + "\n";
					result += "이름 : " + obj.name + "\n";
					result += "나이 : " + obj.age + "\n";
					result += "성별 : " + obj.gender;
				} else {
					result = "사용자 정보가 없습니다.";
				}
				
				$("#textarea3").val(result);
				
			},
			
			error : function(){
				console.log("ajax 통신 실패");
			}
		});
		
	});
	
	
	
	
	
	//4. 서버로 기본형 데이터를 전송 후, 응답을 리스트(List)형태로 받기
	$("#btn4").click(function(){
		var gender = $('input[name="chk_gender4"]:checked').val();
		$.ajax({
			url : "../jqTest4.do",
			data : {gender:gender},
			type : "get",
			dataType : "json",
			success:function(list){
				console.log(list);
				
				// $.each(배열명, function(index){});
				// --> 배열명[index] 각 요소에 접근 가능
				
				var result ="";
				
				$.each(list, function(i){ // index, item을 파라미터로 쓰지만 그냥 1개짜리 사용하면 index임
					result += list[i].no + " / "
							 + list[i].name + " / "
							 + list[i].age + " / "
							 + list[i].gender + "\n"
				});
				
				$("#textarea4").val(result);
			},
			error:function(){
				consol.log("ajax 통신 실패")
			}
		
		});
	});
	
	
	// 5번 건너뜀
	
	
	// 6. Gson을 이용하여 응답을 List 형태로 전송받아 테이블에 출력하기
	$("#btn6").click(function(){
		$.ajax({
			url : "../jqTest6.do",
			type : "post",
			dataType : "json",
			success:function(list){
				console.log(list);
				
				var $tableBody = $("#memberTable6 tbody");
				
				$tableBody.html("");
				
				var $result;
				
				$.each(list, function(i){
					$result = "";
					$result += "<tr><td>" + list[i].no + "</td>"
							+  "<td>" + list[i].name + "</td>"
							+  "<td>" + list[i].age + "</td>"
							+  "<td>" + list[i].gender + "</td></tr>";
					$tableBody.append($result);
				});
			},
			error:function(){
				console.log("ajax 통신 실패");
			}
		});
		
		
	});
	
	
	
	

});
