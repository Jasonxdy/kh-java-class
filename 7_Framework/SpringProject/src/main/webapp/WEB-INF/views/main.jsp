<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Project</title>
<style>
#jumbo1 {
	background-image:url("https://static.wixstatic.com/media/0edf4e_2b533081b8fa4af8b60a65a31748499a~mv2.png/v1/fill/w_630,h_336,al_c,lg_1,q_85/0edf4e_2b533081b8fa4af8b60a65a31748499a~mv2.webp");
	background-size: auto 100%;
}

#content-main {
	height: 500px;
}
</style>
</head>
<body>
	<div class="container">
		
		<jsp:include page="common/header.jsp"/>
		<jsp:include page="common/nav.jsp"/>
		
		<div class="jumbotron p-4 p-md-5 text-black rounded bg-white"
			id="jumbo1">
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
		
		<jsp:include page="common/footer.jsp"/>
	</div>
</body>
</html>
