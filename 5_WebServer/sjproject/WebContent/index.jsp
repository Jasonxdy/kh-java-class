<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet/JSP</title>
<style>
	#jumbo1 {
		background-image:
			url("http://cfs4.tistory.com/upload_control/download.blog?fhandle=YmxvZzY1NDU4QGZzNC50aXN0b3J5LmNvbTovYXR0YWNoLzAvMDIwMDAwMDAwMDAwLmpwZw%3D%3D");
	}
	#content-main {
		height: 500px;
	}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="views/common/header.jsp"%>
		<%@ include file="views/common/nav.jsp"%>

		<div class="jumbotron p-4 p-md-5 text-white rounded bg-dark"
			id="jumbo1">
			<div class="col-md-6 px-0">
				<h1 class="display-4 font-italic">Servlet/JSP 실습</h1>
			</div>
		</div>

		<div class="row mb-2" id="content-main">
			<div class="col-md-6">
				<div
					class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col p-4 d-flex flex-column position-static">
						<strong class="d-inline-block mb-2 text-primary">Area1</strong>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div
					class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
					<div class="col p-4 d-flex flex-column position-static">
						<strong class="d-inline-block mb-2 text-success">Area2</strong>
					</div>
				</div>
			</div>
		</div>
		
		<%@ include file="views/common/footer.jsp"%>
	</div>
</body>
</html>
