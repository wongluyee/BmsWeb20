package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッション情報をクリアする
		HttpSession session = request.getSession();
		session.invalidate();

		// login.jspにフォワードする
		request.getRequestDispatcher("/view/login.jsp").forward(request, response);
	}
}
