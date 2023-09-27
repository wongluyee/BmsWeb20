<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css" type="text/css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<title>書籍管理システム</title>
</head>
<body>
	<div class="container">
		<%@include file= "../common/header.jsp" %>
		<div style="margin-bottom: 250px">
			<h3>ログイン</h3>
			<form action="<%=request.getContextPath()%>/login"  method="POST">
				<label for="userid" class="form-label">ユーザー</label>
				<input type="text" class="form-control mb-3" name="userid">
				<label for="password" class="form-label">パスワード</label>
				<input type="password" class="form-control mb-3" name="password">
				<input class="btn btn-primary" type="submit" value="ログイン">
			</form>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>

</body>
</html>