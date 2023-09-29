<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String user = "";
	String pass = "";
	// クッキーをゲット
	Cookie[] userCookie = request.getCookies();
	// クッキーあるか判定
	if (userCookie != null) {
		for (int i = 0; i < userCookie.length; i++) {
			if (userCookie[i].getName().equals("user")) {
				user = userCookie[i].getValue();
			}

			if (userCookie[i].getName().equals("password")) {
				pass = userCookie[i].getValue();
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>ログイン</title>
</head>
<body>
	<div class="container">
		<%@include file= "../common/header.jsp" %>
		<div style="margin-bottom: 250px">
			<h3>ログイン</h3>
			<form action="<%=request.getContextPath()%>/login"  method="POST">
				<label for="userid" class="form-label">ユーザー</label>
				<input type="text" class="form-control mb-3" name="userid" value=<%= user %>>
				<label for="password" class="form-label">パスワード</label>
				<input type="password" class="form-control mb-3" name="password" value=<%= pass %>>
				<input class="btn btn-primary" type="submit" value="ログイン">
			</form>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>

</body>
</html>