package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderedItem;
import bean.User;
import dao.OrderedItemDAO;

public class ShowOrderedItemServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// ユーザーの権限確認
			// セッションから"user"のUserオブジェクトを取得する
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			if (user == null) {
				error = "セッションが切れたため、再度ログインしてください。";
				cmd = "logout";
				return;
			}

			if (user.getAuthority().equals("1")) {
				error = "権限がないため、閲覧できませんでした。";
				cmd = "menu";
				return;
			}

			// OrderedItemDAOをインスタンス化し、関連メソッドを呼び出す
			OrderedItemDAO orderedItemDao = new OrderedItemDAO();
			ArrayList<OrderedItem> list = orderedItemDao.selectAll();

			// 取得したListをリクエストスコープに"ordered_list"という名前で格納する
			request.setAttribute("ordered_list", list);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
			cmd = "logout";
		} catch (Exception e) {
			e.printStackTrace();
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (error.equals("")) {
				// フォワード
				request.getRequestDispatcher("/view/showOrderedItem.jsp").forward(request,  response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
