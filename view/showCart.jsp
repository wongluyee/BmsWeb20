<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,bean.Book,util.MyFormat"%>
<%
	ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("book_list");
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
				<a href="<%=request.getContextPath()%>/detail?isbn=<%= book.getIsbn() %>&cmd=detail"><%=book.getIsbn()%></a></td>
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
		</div>
	<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>