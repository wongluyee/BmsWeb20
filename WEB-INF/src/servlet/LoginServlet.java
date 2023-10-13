package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.User;
import dao.UserDAO;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// セッションオブジェクトの生成
			HttpSession session = request.getSession();

			// UserDAOクラスのオブジェクトを生成
			UserDAO userDao = new UserDAO();

			// エンコードを設定
			response.setContentType("text/html; charset=UTF-8");

			// userid, password入力パラメータを取得する
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");

			// user情報を取得
			User user = userDao.selectByUser(userid, password);

			if (user == null) {
				request.setAttribute("message", "入力内容が間違っています。");
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
				return;
			}

			if (user != null) {
				session.setAttribute("user",  user);
				Cookie userCookie = new Cookie("user", user.getUserid());
				Cookie passwordCookie = new Cookie("password", user.getPassword());
				userCookie.setMaxAge(172800);
				passwordCookie.setMaxAge(172800);
				response.addCookie(userCookie);
				response.addCookie(passwordCookie);
				request.getRequestDispatcher("/view/menu.jsp").forward(request, response);
			}

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
			cmd = "logout";
		} catch (Exception e) {
			e.printStackTrace();
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (!error.equals("")) {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
