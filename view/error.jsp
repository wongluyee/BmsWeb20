<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String error = (String) request.getAttribute("error"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css" type="text/css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<title>Error</title>
</head>
<body>
	<div class="container text-center">
		<%@include file= "../common/header.jsp" %>
		<div style="margin:auto;">
			<h3>エラー</h3>
			<% if (error != null) { %>
				<p><%= error %></p>
			<% } %>
			<a href="<%=request.getContextPath() %>/list">[書籍一覧]</a>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>