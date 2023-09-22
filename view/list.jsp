<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,bean.Book,util.MyFormat"%>
<% MyFormat fm = new MyFormat(); %>
<%
	ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("list");
	String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css" type="text/css" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<title>書籍一覧</title>
</head>
<body>
<div class="container text-center">
	<%@include file= "../common/header.jsp" %>

	<table style="margin:auto; width:850px">
		<tr>
			<tr>
				<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/insert.jsp">書籍登録</a>]</td>
				<td style="text-align:center; width:508px; font-size:24px;">書籍一覧</td>
				<td style="width:80px">&nbsp;</td>
				<td style="width:80px">&nbsp;</td>
			</tr>
	</table>

	<hr class="menu-border">
	<div style="margin-bottom: 250px">

		<table style="margin: auto" class="mb-3">
			<tr>
				<td>
					<form action="<%=request.getContextPath()%>/search">
						ISBN：<input type=text size="30" name="isbn"></input> Title：<input
							type=text size="30" name="title"></input> 価格：<input type=text
							size="30" name="price"></input> <input type="submit"
							name="search" value="検索"></input>
					</form>
				</td>
				<td>
					<form action="<%=request.getContextPath()%>/list">
						<input type="submit" name="searchall" value="全件表示"></input>
					</form>
				</td>
			</tr>
		</table>

		<table style="margin: auto">
			<tr>
				<th style="background-color: #6666ff; width: 200px">ISBN</th>
				<th style="background-color: #6666ff; width: 200px">Title</th>
				<th style="background-color: #6666ff; width: 200px">価格</th>
				<th style="background-color: #6666ff; width: 250px" colspan="2">変更/削除</th>
			</tr>
			<%
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Book book = (Book) list.get(i);
						String formattedPrice = fm.moneyFormat(book.getPrice());
			%>
			<tr>
				<td style="text-align: center; width: 200px">
				<a href="<%=request.getContextPath()%>/detail?isbn=<%= book.getIsbn() %>&cmd=detail"><%=book.getIsbn()%></a></td>
				<td style="text-align: center; width: 200px"><%= book.getTitle() %></td>
				<td style="text-align: center; width: 200px"><%= formattedPrice %></td>
				<td style="text-align: center; width: 125px">
				<a href="<%=request.getContextPath()%>/detail?isbn=<%=book.getIsbn()%>&cmd=update">変更</a>
				</td>
				<td style="text-align: left; width: 125px">
				<a href="<%=request.getContextPath()%>/delete?isbn=<%=book.getIsbn()%>">削除</a>
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