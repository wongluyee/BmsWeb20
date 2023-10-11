<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="bean.Book,util.MyFormat"%>
<%
	//リクエストスコープからのデータの取得
	Book book = (Book)request.getAttribute("book");
	int quantity = (int)request.getAttribute("quantity");
	// Price format
	MyFormat fm = new MyFormat();
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>カート追加</title>
</head>
<body>
	<div class="container">
		<div class="header">
			<%@include file= "../common/header.jsp" %>
			<%@include file= "../common/userInfo.jsp" %>
			<hr>
			<table style="margin:auto; width:850px">
				<tr>
					<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
					<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/list">書籍一覧</a>]</td>
					<td style="text-align:center; font-size:24px;"><h3>カート追加</h3></td>
					<td style="width:80px">&nbsp;</td>
					<td style="width:80px">&nbsp;</td>
				</tr>
			</table>
			<hr>
		</div>
		<div class="contents">
			<h5 class="text-center mb-4">下記の書籍をカートに追加しました。</h5>
			<div class="text-center">
				<p class="fw-bold">ISBN: <%= book.getIsbn() %></p>
				<p class="fw-bold">Title: <%= book.getTitle() %></p>
				<p class="fw-bold">価格: <%= fm.moneyFormat(book.getPrice()) %></p>
				<p class="fw-bold">購入数: <%= quantity %></p>
				<div class="mb-4">
					<a class="btn btn-primary" href="<%= request.getContextPath() %>/showCart">カート確認</a>
				</div>
			</div>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>