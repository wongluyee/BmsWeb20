<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList,bean.Book,util.MyFormat"%>
<%
	ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("list");
	MyFormat fm = new MyFormat();
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>書籍一覧</title>
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
				<% if (user.getAuthority().equals("1")) { %>
					<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/showCart">カート状況</a>]</td>
				<% } %>
				<% if (user.getAuthority().equals("2")) { %>
					<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</td>
				<% } %>
				<td style="text-align:center; width:508px; font-size:24px;"><h3>書籍一覧</h3></td>
				<td style="width:80px">&nbsp;</td>
				<td style="width:80px">&nbsp;</td>
			</tr>
		</table>
		<hr>
	</div>
	<div class="contents">
		<table style="margin: auto" class="mb-3">
			<tr>
				<td>
					<form action="<%= request.getContextPath() %>/search">
						ISBN：<input type=text size="30" name="isbn">
						Title：<input type=text size="30" name="title">
						価格：<input type=text size="30" name="price">
						<input type="submit" name="search" value="検索">
					</form>
				</td>
				<td>
					<form action="<%= request.getContextPath() %>/list">
						<input type="submit" name="searchall" value="全件表示">
					</form>
				</td>
			</tr>
		</table>

		<table style="margin: auto">
			<tr>
				<th style="background-color: #6666ff; width: 200px; border-right: solid white">ISBN</th>
				<th style="background-color: #6666ff; width: 200px; border-right: solid white">Title</th>
				<th style="background-color: #6666ff; width: 200px; border-right: solid white">価格</th>
				<% if (user.getAuthority().equals("1")) { %>
					<th style="background-color: #6666ff; width: 250px" colspan="2">購入数</th>
				<% } %>

				<% if (user.getAuthority().equals("2")) { %>
					<th style="background-color: #6666ff; width: 200px" colspan="2">変更/削除</th>
				<% } %>
			</tr>
			<%
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Book book = (Book) list.get(i);
						String formattedPrice = fm.moneyFormat(book.getPrice());
			%>
			<tr>
				<td style="text-align: center; width: 200px">
				<a href="<%= request.getContextPath() %>/detail?isbn=<%= book.getIsbn() %>&cmd=detail"><%= book.getIsbn() %></a></td>
				<td style="text-align: center; width: 200px"><%= book.getTitle() %></td>
				<td style="text-align: center; width: 200px"><%= formattedPrice %></td>

				<% if (user.getAuthority().equals("1")) { %>
					<td style="text-align: center; width: 125px">
						<form action="<%= request.getContextPath() %>/insertIntoCart"  method="GET">
							<input type="hidden" name="isbn" value="<%= book.getIsbn() %>">
							<label for="quantity">数量：</label>
							<input type="number" name="quantity" style="width:56px">
							<input type="submit" value="カートに入れる">
						</form>
					</td>
				<% } %>

				<% if (user.getAuthority().equals("2")) { %>
					<td style="text-align: center; width: 100px">
						<a href="<%= request.getContextPath() %>/detail?isbn=<%= book.getIsbn() %>&cmd=update">変更</a>
					</td>
					<td style="text-align: center; width: 100px">
						<a href="<%= request.getContextPath() %>/delete?isbn=<%= book.getIsbn() %>">削除</a>
					</td>
				<% } %>
			</tr>
			<%
					}
				} else {
			%>
			<tr>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 250px" colspan="2">&nbsp;</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>