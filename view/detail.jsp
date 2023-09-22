<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.Book,util.MyFormat"%>
<%
	//リクエストスコープからのデータの取得
	Book book = (Book)request.getAttribute("book");
	// Price format
	MyFormat fm = new MyFormat();
%>
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

		<table style="margin:auto; width:850px">
			<tr>
				<tr>
					<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
					<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</td>
					<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/list">書籍一覧</a>]</td>
					<td style="text-align:center; font-size:24px;">書籍詳細画面</td>
					<td style="width:80px">&nbsp;</td>
					<td style="width:80px">&nbsp;</td>
					<td style="width:80px">&nbsp;</td>
				</tr>
			</tr>
		</table>
		<hr class="menu-border">
		<div style="margin-bottom: 230px" class="text-center">
			<div class="mb-4">
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/detail?isbn=<%=book.getIsbn()%>&cmd=update">変更</a>
				<a class="btn btn-primary" href="<%=request.getContextPath()%>/delete?isbn=<%=book.getIsbn()%>">削除</a>
			</div>
			<p class="fw-bold">ISBN: <%= book.getIsbn() %></p>
			<p class="fw-bold">Title: <%= book.getTitle() %></p>
			<p class="fw-bold">価格: <%= fm.moneyFormat(book.getPrice()) %></p>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>