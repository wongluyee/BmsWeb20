<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="bean.User"%>
<%
	//セッションからユーザー情報を取得
	User user = (User)session.getAttribute("user");
	//セッション切れか確認
	if(user == null){
		//セッション切れならerror.jspへフォワード
		request.setAttribute("error","セッション切れの為、メニュー画面が表示できませんでした。");
		request.setAttribute("cmd","logout");
		request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		return;
	}
%>
<div class="text-end">
	<button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
		<i class="fa-solid fa-user"></i>
	</button>
	<ul class="dropdown-menu">
		<li class="dropdown-item-text">
			<p class="mb-0">名前: <%= user.getUserid() %></p>
			<% if (user.getAuthority().equals("1")) { %>
				<p class="mb-0">権限: 一般ユーザー</p>
			<% } else if (user.getAuthority().equals("2")) { %>
				<p class="mb-0">権限: 管理者</p>
			<% } %>
		</li>
		<li><hr class="dropdown-divider"></li>
		<li><a class="dropdown-item" href="<%= request.getContextPath() %>/view/changePassword.jsp">パスワード変更 <i class="fa-solid fa-key"></i></a></li>
		<li><a class="dropdown-item" href="<%= request.getContextPath() %>/logout">ログアウト <i class="fa-solid fa-right-from-bracket"></i></a></li>
	</ul>
</div>