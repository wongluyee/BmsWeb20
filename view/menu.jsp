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
			<a class="btn btn-primary" href="<%=request.getContextPath()%>/list" style="width:320px;">書籍一覧 <i class="fa-solid fa-book"></i></a><br>

			<% if (user.getAuthority().equals("1")) { %>
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/showCart" style="width:320px;">カート状況確認 <i class="fa-solid fa-cart-shopping"></i></a><br>
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/showHistoryOrderedItem" style="width:320px;">購入履歴 <i class="fa-solid fa-cash-register"></i></a><br>
			<% } %>

			<% if (user.getAuthority().equals("2")) { %>
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/view/insert.jsp" style="width:320px;">書籍登録 <i class="fa-solid fa-plus"></i></a><br>
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/insertIniData" style="width:320px;">初期データ登録（データがない場合のみ）<i class="fa-solid fa-database"></i></a><br>
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/showOrderedItem" style="width:320px;">購入状況確認 <i class="fa-solid fa-clipboard-check"></i></a><br>
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/showSalesByMonth" style="width:320px;">売上状況 <i class="fa-solid fa-chart-line"></i></a><br>
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/view/insertUser.jsp" style="width:320px;">ユーザー登録 <i class="fa-solid fa-user-plus"></i></a><br>
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/listUser" style="width:320px;">ユーザー一覧 <i class="fa-solid fa-users"></i></a><br>
			<% } %>

			<a class="btn btn-danger" href="<%=request.getContextPath()%>/view/changePassword.jsp" style="width:320px;">パスワード変更 <i class="fa-solid fa-key"></i></a><br>
			<a class="btn btn-danger" href="<%=request.getContextPath()%>/logout" style="width:320px;">ログアウト <i class="fa-solid fa-right-from-bracket"></i></a>
		</div>

		<%@include file= "../common/footer.jsp" %>
	</div>
	</body>
</html>