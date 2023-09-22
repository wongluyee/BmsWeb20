<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bean.User"%>
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
<div style="text-align:right;">
	<p class="m-0">名前: <%= user.getUserid() %></p>
	<% if (user.getAuthority().equals("1")) { %>
		<p class="m-0">権限: 一般ユーザー</p>
	<% } else if (user.getAuthority().equals("2")) { %>
		<p class="m-0">権限: 管理者</p>
	<% } %>
</div>