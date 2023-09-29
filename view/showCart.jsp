<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,bean.Book,util.MyFormat"%>
<%
	ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("book_list");
	MyFormat fm = new MyFormat();
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>カート</title>
</head>
<body>
	<div class="container text-center">
		<%@include file= "../common/header.jsp" %>
		<%@include file= "../common/userInfo.jsp" %>
		<hr class="menu-border">
		<table style="margin:auto; width:850px">
			<tr>
				<tr>
					<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
					<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/list">書籍一覧</a>]</td>
					<td style="text-align:center; font-size:24px;">カート内容</td>
					<td style="width:80px">&nbsp;</td>
					<td style="width:80px">&nbsp;</td>
				</tr>
		</table>
		<hr class="menu-border">

		<div style="margin-bottom: 80px">
			<table style="margin: auto">
			<tr>
				<th style="background-color: #6666ff; width: 200px">ISBN</th>
				<th style="background-color: #6666ff; width: 200px">Title</th>
				<th style="background-color: #6666ff; width: 200px">価格</th>
				<th style="background-color: #6666ff; width: 200px">削除</th>
			</tr>

			<%
				int total = 0;

				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Book book = (Book) list.get(i);
						total += book.getPrice();
						String formattedPrice = fm.moneyFormat(book.getPrice());
			%>

			<tr>
				<td style="text-align: center; width: 200px">
					<a href="<%=request.getContextPath()%>/detail?isbn=<%= book.getIsbn() %>&cmd=detail"><%=book.getIsbn()%></a>
				</td>
				<td style="text-align: center; width: 200px"><%= book.getTitle() %></td>
				<td style="text-align: center; width: 200px"><%= formattedPrice %></td>
				<td style="text-align: center; width: 200px">
					<a href="<%=request.getContextPath()%>/showCart?delno=<%=i%>">削除</a>
				</td>
			</tr>

			<%
					}
				} else {
			%>
			<tr>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 200px">&nbsp;</td>
				<td style="text-align: center; width: 200px">&nbsp;</td>
			</tr>
			<%
				}
			%>
			</table>

			<table style="margin:auto;" class="mt-3">
				<tr>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="background-color: #6666ff; text-align: center; width: 200px">合計：</td>
					<td style="text-align: center; width: 200px"><%= fm.moneyFormat(total) %></td>
				</tr>
			</table>

			<table style="margin:auto;" class="mt-3">
				<tr>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td style="text-align: center; width: 200px">&nbsp;</td>
					<td><form action="<%=request.getContextPath()%>/buyConfirm"  method="GET">
						<input class="text-end" type="submit" value="購入">
					</form></td>
				</tr>
			</table>
		</div>
	<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>