<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피자 주문</title>
<style type="text/css">
	table{border: 1px solid black; border-collapse: collapse;}
	th,td {border:1px solid black; padding:10px;}
</style>
</head>
<body>
	<h1>오늘은 <span style="color: pink;"><%@ include file="today.jsp" %></span> 입니다.</h1>
	<h1>피자 아카데미</h1>
	
	<table>
		<tr>
			<th>종류</th>
			<th>이름</th>
			<th>가격</th>
			<th rowspan="12"></th>
			<th>종류</th>
			<th>이름</th>
			<th>가격</th>
		</tr>
		
		<tr>
			<td rowspan="5">피자</td>
			<td>치즈피자</td>
			<td>5000</td>
			<td rowspan="12">사이드</td>
			<td>오븐구이통닭</td>
			<td>9000</td>
		</tr>
		<tr>
			<td>콤비네이션피자</td>
			<td>6000</td>
			<td>치킨스틱&윙</td>
			<td>4900</td>
		</tr>
		<tr>
			<td>포테이토피자</td>
			<td>7000</td>
			<td>치즈오븐스파게티</td>
			<td>4000</td>
		</tr>
		<tr>
			<td>고구마피자</td>
			<td>7000</td>
			<td>새우링&웨지감자</td>
			<td>3500</td>			
		</tr>
		<tr>
			<td>불고기피자</td>
			<td>8000</td>
			<td>갈릭포테이토</td>
			<td>3000</td>
		</tr>
		
		<tr>
			<td rowspan="6">토핑</td>
			<td>고구마무스</td>
			<td>1000</td>
			<td>콜라</td>
			<td>1500</td>
		</tr>
		<tr>
			<td>콘크림무스</td>
			<td>1500</td>
			<td>사이다</td>
			<td>1500</td>
			
		</tr>
		<tr>
			<td>파인애플토핑</td>
			<td>2000</td>
			<td>갈릭소스</td>
			<td>500</td>
		</tr>
		<tr>
			<td>치즈토핑</td>
			<td>2000</td>
			<td>피클</td>
			<td>300</td>
		</tr>
		<tr>
			<td>치즈크러스트</td>
			<td>2000</td>
			<td>핫소스</td>
			<td>100</td>
		</tr>
		<tr>
			<td>치즈바이트</td>
			<td>3000</td>
			<td>파마산 치즈가루</td>
			<td>100</td>
		</tr>
	</table>
	
	<br><br>
	
	<form action="/2_JSP/confirmPizza.do" name="myInfoForm" method="post">
		피자&nbsp;&nbsp;&nbsp; : 
		<select id="pizza" name="pizza" required>
			<option value="치즈피자">치즈피자</option>
			<option value="콤비네이션피자">콤비네이션피자</option>
			<option value="포테이토피자">포테이토피자</option>
			<option value="고구마피자">고구마피자</option>
			<option value="불고기피자">불고기피자</option>
		</select>
		
		<br>
		
		토핑&nbsp;&nbsp;&nbsp; :
		<input type="checkbox" name="topping" id="topping1" value="고구마무스">고구마무스
		<input type="checkbox" name="topping" id="topping2" value="콘크림무스">콘크림무스
		<input type="checkbox" name="topping" id="topping3" value="파인애플토핑">파인애플토핑
		<input type="checkbox" name="topping" id="topping4" value="치즈토핑">치즈토핑
		<input type="checkbox" name="topping" id="topping5" value="치즈크러스트">치즈크러스트
		<input type="checkbox" name="topping" id="topping6" value="치즈바이트">치즈바이트
		
		<br>
		
		사이드 :
		<input type="checkbox" name="side" id="side1" value="오븐구이통닭">오븐구이통닭
		<input type="checkbox" name="side" id="side2" value="치킨스틱&윙">치킨스틱&윙
		<input type="checkbox" name="side" id="side3" value="치즈오븐스파게티">치즈오븐스파게티
		<input type="checkbox" name="side" id="side4" value="새우링&웨지감자">새우링&웨지감자
		<input type="checkbox" name="side" id="side5" value="갈릭포테이토">갈릭포테이토
		<input type="checkbox" name="side" id="side6" value="콜라">콜라
		<input type="checkbox" name="side" id="side7" value="사이다">사이다
		<input type="checkbox" name="side" id="side8" value="갈릭소스">갈릭소스
		<input type="checkbox" name="side" id="side9" value="피클">피클
		<input type="checkbox" name="side" id="side10" value="핫소스">핫소스
		<input type="checkbox" name="side" id="side11" value="파마산 치즈가루">파마산 치즈가루
		
		<br>
		<br>
		
		<input type="submit" value="확인">
	</form>	
</body>
</html>