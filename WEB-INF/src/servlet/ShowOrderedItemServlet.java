package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderedItem;
import dao.OrderedItemDAO;

public class ShowOrderedItemServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";

		try {
			// OrderedItemDAOをインスタンス化し、関連メソッドを呼び出す
			OrderedItemDAO orderedItemDao = new OrderedItemDAO();
			ArrayList<OrderedItem> list = orderedItemDao.selectAll();

			// 取得したListをリクエストスコープに"ordered_list"という名前で格納する
			request.setAttribute("ordered_list", list);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
		} finally {
			if (error.equals("")) {
				// フォワード
				request.getRequestDispatcher("/view/showOrderedItem.jsp").forward(request,  response);
			} else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
