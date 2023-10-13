<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList,bean.Book,util.MyFormat"%>
<%
	ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("book_list");
	MyFormat fm = new MyFormat();
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>初期データ登録</title>
</head>
<body>
	<div class="container text-center">
		<div class="header">
			<%@include file= "../common/header.jsp" %>
			<%@include file= "../common/userInfo.jsp" %>
			<hr>
			<table style="margin:auto; width:850px">
				<tr>
					<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
					<td style="text-align:center; width:508px; font-size:24px;"><h3>初期データ登録</h3></td>
					<td style="width:80px">&nbsp;</td>
				</tr>
			</table>
			<hr>
		</div>
		<div class="contents">
			<h5>初期データとして以下のデータを登録しました。</h5>
			<table style="margin: auto" class="text-center">
				<tr>
					<th style="background-color: #6666ff; width: 200px; border-right: solid white">ISBN</th>
					<th style="background-color: #6666ff; width: 200px; border-right: solid white">Title</th>
					<th style="background-color: #6666ff; width: 200px">価格</th>
				</tr>
				<%
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							Book book = (Book) list.get(i);
				%>
						<tr>
							<td style="text-align: center; width: 200px"><%= book.getIsbn() %></td>
							<td style="text-align: center; width: 200px"><%= book.getTitle() %></td>
							<td style="text-align: center; width: 200px"><%= fm.moneyFormat(book.getPrice()) %></td>
						</tr>
					<% } %>
				<% } %>
			</table>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>