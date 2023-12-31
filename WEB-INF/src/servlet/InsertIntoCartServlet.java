package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.Book;
import bean.Order;
import bean.User;
import dao.BookDAO;

public class InsertIntoCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// セッションから"user"のUserオブジェクトを取得する
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			if (user == null) {
				error = "セッションが切れたため、再度ログインしてください。";
				cmd = "logout";
				return;
			}

			// isbnと数量のパラメータを取得する
			String isbn = request.getParameter("isbn");
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			// BookDAOをインスタンス化し、selectByIsbnメソッドを呼び出す
			BookDAO bookDao = new BookDAO();
			Book book = bookDao.selectByIsbn(isbn);

			// Bookオブジェクトをリクエストスコープに"Book"という名前で格納する
			request.setAttribute("book",book);

			request.setAttribute("quantity", quantity);

			// Orderのインスタンスを生成
			Order order = new Order();

			// isbn、userid(ログイン者)、数量を設定する
			order.setIsbn(isbn);
			order.setUserid(user.getUserid());
			order.setQuantity(quantity);

			// セッションから"order_list"の配列を取得する
			ArrayList<Order> order_list = (ArrayList<Order>)session.getAttribute("order_list");

			// 取得出来なかった場合はArrayList<Order>配列を新規で作成する
			if (order_list == null) {
				order_list = new ArrayList<Order>();
			}

			// Orderオブジェクトorder_list配列に追加し、セッションスコープに"order_list"という名前で登録する
			order_list.add(order);
			session.setAttribute("order_list",  order_list);

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
				request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request,  response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
