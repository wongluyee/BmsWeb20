<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String error = (String) request.getAttribute("error"); %>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>エラー</title>
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
			<a href="<%=request.getContextPath() %>/view/login.jsp">[ログインページ]</a>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>