<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="bean.Book,util.MyFormat"%>
<%
	//リクエストスコープからのデータの取得
	Book book = (Book)request.getAttribute("book");
	// Price format
	MyFormat fm = new MyFormat();
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>書籍詳細</title>
</head>
<body>
	<div class="container">
		<%@include file= "../common/header.jsp" %>
		<%@include file= "../common/userInfo.jsp" %>
		<hr class="menu-border">

		<table style="margin:auto; width:850px">
			<tr>
				<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</td>
				<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/list">書籍一覧</a>]</td>
				<td style="text-align:center; font-size:24px;"><h3>書籍詳細画面</h3></td>
				<td style="width:80px">&nbsp;</td>
				<td style="width:80px">&nbsp;</td>
				<td style="width:80px">&nbsp;</td>
			</tr>
		</table>
		<hr class="menu-border">
		<div style="margin-bottom: 150px" class="text-center">
			<p class="fw-bold">ISBN: <%= book.getIsbn() %></p>
			<p class="fw-bold">Title: <%= book.getTitle() %></p>
			<p class="fw-bold">価格: <%= fm.moneyFormat(book.getPrice()) %></p>
			<% if(user.getAuthority().equals("2")){ %>
				<div class="mb-4">
					<a class="btn btn-primary" href="<%= request.getContextPath()%>/detail?isbn=<%=book.getIsbn() %>&cmd=update">変更</a>
					<a class="btn btn-primary" href="<%= request.getContextPath()%>/delete?isbn=<%=book.getIsbn() %>">削除</a>
				</div>
			<% } %>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>