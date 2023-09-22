<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="style.css" rel="stylesheet" />
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<title>書籍管理メニュー画面</title>
	</head>
	<body>
	<div class="container">
		<h1 class="text-center">書籍管理システムweb版ver2.0</h1>
		<hr class="border border-primary">
		<p class="menu-text">Menu</p>
		<hr class="menu-border">
		<div class="options d-flex justify-content-center ">
			<div class="w-auto">
				<a class="btn btn-primary" href="../list">書籍一覧</a>
				<a class="btn btn-primary" href="./insert.jsp">書籍登録</a>
			</div>
		</div>
		<hr class="border border-primary">
		<p class="footer">Copyright (c) 2023 all rights reserved.</p>
	</div>
	</body>
</html>