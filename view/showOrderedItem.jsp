<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,bean.OrderedItem"%>
<%
	ArrayList<OrderedItem> list = (ArrayList<OrderedItem>) request.getAttribute("ordered_list");
%>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>購入状況</title>
</head>
<body>
	<div class="container">
		<%@include file= "../common/header.jsp" %>
		<%@include file= "../common/userInfo.jsp" %>
		<hr class="menu-border">
		<table style="margin:auto; width:850px">
			<tr>
				<td style="text-align:center; width:80px">[<a href="<%=request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
				<td style="text-align:center; width:508px; font-size:24px;">購入状況</td>
				<td style="width:80px">&nbsp;</td>
			</tr>
		</table>
		<hr class="menu-border">
		<table style="margin: auto" class="text-center">
			<tr>
				<th style="background-color: #6666ff; width: 200px; border-right: solid white"">ユーザー</th>
				<th style="background-color: #6666ff; width: 200px; border-right: solid white"">Title</th>
				<th style="background-color: #6666ff; width: 200px">注文日</th>
			</tr>
			<%
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						OrderedItem orderedItem = (OrderedItem) list.get(i);
			%>
					<tr>
						<td style="text-align: center; width: 200px"><%= orderedItem.getUserid() %></td>
						<td style="text-align: center; width: 200px"><%= orderedItem.getTitle() %></td>
						<td style="text-align: center; width: 200px"><%= orderedItem.getDate() %></td>
					</tr>
				<% } %>
			<% } %>
		</table>
		<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>