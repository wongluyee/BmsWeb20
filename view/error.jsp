<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String error = (String) request.getAttribute("error");
	String cmd = (String) request.getAttribute("cmd");
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>エラー</title>
</head>
<body>
	<div class="container text-center">
		<div class="header">
			<%@include file= "../common/header.jsp" %>
		</div>
		<div class="contents" style="margin:auto">
			<h3>エラー</h3>
			<% if (error != null) { %>
				<p><%= error %></p>
			<% } %>
			<% if (cmd.equals("menu")) { %>
				<a href="<%= request.getContextPath() %>/view/menu.jsp">[メニュー]</a>
			<% } else if (cmd.equals("list")) { %>
				<a href="<%= request.getContextPath() %>/list">[書籍一覧]</a>
			<% } else if (cmd.equals("logout")) { %>
				<a href="<%= request.getContextPath() %>/logout">[ログインページ]</a>
			<% } else if (cmd.equals("listUser")) { %>
				<form action="<%= request.getContextPath() %>/listUser" method="POST">
					[<button class="link-button" type="submit">ユーザー一覧</button>]
				</form>
			<% } %>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>