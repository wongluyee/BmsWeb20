<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="bean.Book,util.MyFormat"%>
<%
	//リクエストスコープからのデータの取得
	Book book = (Book)request.getAttribute("book");
	// Price format
	MyFormat fm = new MyFormat();
%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="style.css" type="text/css" />
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<title>書籍管理システム</title>
	</head>
	<body>
		<div class="container">
			<%@include file= "../common/header.jsp" %>

			<table style="margin:auto; width:850px">
				<tr>
					<tr>
						<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
						<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</td>
						<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/list">書籍一覧</a>]</td>
						<td style="text-align:center; font-size:24px;">書籍変更</td>
						<td style="width:80px">&nbsp;</td>
						<td style="width:80px">&nbsp;</td>
						<td style="width:80px">&nbsp;</td>
					</tr>
				</tr>
			</table>
			<hr class="menu-border">
			<div style="margin-bottom: 200px" class="text-center d-flex justify-content-center gap-3">
				<div class="before-update">
					<h5>変更前情報</h5>
					<p class="fw-bold">ISBN: <%= book.getIsbn() %></p>
					<p class="fw-bold">Title: <%= book.getTitle() %></p>
					<p class="fw-bold">価格: <%= fm.moneyFormat(book.getPrice()) %></p>
				</div>
				<div>
					<h5>変更後情報</h5>
					<p class="fw-bold mb-2"><%= book.getIsbn() %></p>
					<form action="<%=request.getContextPath()%>/update"  method="GET">
						<input type="hidden" name="isbn" value="<%= book.getIsbn()%>">
						<input type="text" class="form-control mb-2" name="title">
						<input type="number" class="form-control mb-2" name="price">
						<input class="btn btn-primary" type="submit" value="変更完了">
					</form>
				</div>
			</div>
		<%@include file= "../common/footer.jsp" %>
		</div>
	</body>
</html>