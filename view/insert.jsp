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
	<h1 class="text-center">書籍管理システムweb版ver2.0</h1>
	<hr class="border border-primary">

	<table style="margin:auto; width:850px">
		<tr>
			<tr>
				<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/list">書籍一覧</a>]</td>
				<td style="text-align:center; width:508px; font-size:24px;">書籍登録</td>
				<td style="width:80px">&nbsp;</td>
				<td style="width:80px">&nbsp;</td>
			</tr>
		</tr>
	</table>

	<hr class="menu-border">
	<div style="margin-bottom: 250px">
		<form action="<%=request.getContextPath()%>/insert"  method="GET">
			<label for="isbn" class="form-label">ISBN</label>
			<input type="text" class="form-control mb-3" name="isbn">
			<label for="title" class="form-label">Title</label>
			<input type="text" class="form-control mb-3" name="title">
			<label for="price" class="form-label">価格</label>
			<input type="number" class="form-control mb-3" name="price">
			<input class="btn btn-primary" type="submit" value="登録">
		</form>
	</div>
		<hr class="border border-primary">
		<p class="footer">Copyright (c) 2023 all rights reserved.</p>
	</div>
</body>
</html>