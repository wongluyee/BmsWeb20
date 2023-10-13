<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file= "../common/head.jsp" %>
	<title>書籍追加</title>
</head>
<body>
	<div class="container">
		<div class="header">
			<%@include file= "../common/header.jsp" %>
			<%@include file= "../common/userInfo.jsp" %>
			<% if(!user.getAuthority().equals("2")){
				request.setAttribute("error","権限がないため、閲覧できませんでした。");
				request.setAttribute("cmd","menu");
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				return;
			} %>
			<hr>
			<table style="margin:auto; width:850px">
				<tr>
					<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/view/menu.jsp">メニュー</a>]</td>
					<td style="text-align:center; width:80px">[<a href="<%= request.getContextPath() %>/list">書籍一覧</a>]</td>
					<td style="text-align:center; width:508px; font-size:24px;"><h3>書籍登録</h3></td>
					<td style="width:80px">&nbsp;</td>
					<td style="width:80px">&nbsp;</td>
				</tr>
			</table>
			<hr>
		</div>
		<div class="contents">
			<form action="<%= request.getContextPath() %>/insert"  method="GET">
				<label for="isbn" class="form-label">ISBN</label>
				<input type="text" class="form-control mb-3" name="isbn" required>
				<label for="title" class="form-label">Title</label>
				<input type="text" class="form-control mb-3" name="title" required>
				<label for="price" class="form-label">価格</label>
				<input type="number" class="form-control mb-3" name="price" required>
				<input class="btn btn-primary" type="submit" value="登録">
			</form>
		</div>
		<%@include file= "../common/footer.jsp" %>
	</div>
</body>
</html>