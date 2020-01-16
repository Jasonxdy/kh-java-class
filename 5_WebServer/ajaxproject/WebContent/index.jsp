<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Ajax 연습하기</title>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h1>
			<br>
				Ajax 연습하기
			</h1>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-6">
			<div class="list-group">
				<h3 class="list-group-item list-group-item-action active">
					Javascript 방식의 Ajax
				</h3>
				<div class="list-group-item">
					<h5 class="list-group-item-heading">
						<a class="text-primary" href="views/javascriptAjax.jsp">JavaScript방식 Ajax 예제</a>
					</h5>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="list-group">
				<h3 class="list-group-item list-group-item-action active bg-warning border-warning">
					jQuery 방식의 Ajax
				</h3>
				<div class="list-group-item">
					<h5 class="list-group-item-heading">
						<a class="text-warning" href="views/jQueryAjax.jsp">jQuery방식 ajax 예제</a>
					</h5>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>