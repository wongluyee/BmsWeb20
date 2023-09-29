<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<%@include file= "../common/head.jsp" %>
	<title>メニュー</title>
	</head>
	<body>
	<div class="container">
		<%@include file= "../common/header.jsp" %>
		<%@include file= "../common/userInfo.jsp" %>

		<hr class="menu-border">
		<h3 class="m-0" style="text-align:center;">Menu</h3>
		<hr class="menu-border">

		<div class="d-flex align-items-center flex-column options">
			<a class="btn btn-primary" href="<%=request.getContextPath()%>/list" style="width:320px;">書籍一覧</a><br>
			<a class="btn btn-primary" href="<%=request.getContextPath()%>/view/insert.jsp" style="width:320px;">書籍登録</a><br>
			<a class="btn btn-primary" href="<%=request.getContextPath()%>/showCart" style="width:320px;">カート状況確認</a><br>
			<a class="btn btn-primary" href="<%=request.getContextPath()%>/insertIniData" style="width:320px;">初期データ登録（データがない場合のみ）</a><br>
			<a class="btn btn-primary" href="<%=request.getContextPath()%>/showOrderedItem" style="width:320px;">購入状況確認</a><br>
			<a class="btn btn-danger" href="<%=request.getContextPath()%>/logout" style="width:320px;">ログアウト</a>
		</div>

		<%@include file= "../common/footer.jsp" %>
	</div>
	</body>
</html>