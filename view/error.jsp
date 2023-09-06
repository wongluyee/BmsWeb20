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
		<h1 class="text-center">書籍管理システムweb版ver.1.0</h1>
		<hr class="border border-primary">
		<div style="margin:auto;">
			<h3>エラー</h3>
			<% if (error != null) { %>
				<p><%= error %></p>
			<% } %>
			<a href="<%=request.getContextPath() %>/list">[書籍一覧]</a>
		</div>
		<hr class="border border-primary">
		<p class="footer">Copyright (c) 2023 all rights reserved.</p>
	</div>
</body>
</html>