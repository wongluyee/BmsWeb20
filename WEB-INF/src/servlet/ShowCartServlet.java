package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.Order;
import bean.Sales;
import bean.User;
import dao.BookDAO;

public class ShowCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// delno（削除対象の配列要素番号）の入力パラメータを取得する
			String delno = request.getParameter("delno");

			// セッションから"user"を取得する
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			if (user == null) {
				error = "セッションが切れたため、再度ログインしてください。";
				cmd = "logout";
				return;
			}

			// セッションから"order_list"を取得する
			ArrayList<Order> order_list = (ArrayList<Order>)session.getAttribute("order_list");

			// カートの中身が空の場合
			if (order_list == null) {
				return;
			}

			// delnoが「null」でない場合order_listから該当の書籍情報を削除する
			if (delno != null) {
				order_list.remove(Integer.parseInt(delno));
			}

			// Salesクラス型のArrayList配列を宣言する。
			ArrayList<Sales> book_list = new ArrayList<Sales>();

			// BookDAOをインスタンス化し、selectByIsbnメソッドをorder_list(カートデータ)分だけ呼び出す
			BookDAO bookDao = new BookDAO();
			for (Order order : order_list) {
				Book book = bookDao.selectByIsbn(order.getIsbn());
				Sales bookInfo = new Sales();
				bookInfo.setIsbn(book.getIsbn());
				bookInfo.setTitle(book.getTitle());
				bookInfo.setPrice(book.getPrice());
				bookInfo.setQuantity(order.getQuantity());
				// 取得した各BookをListに追加する
				book_list.add(bookInfo);
			}

			// リクエストスコープに"book_list"という名前で格納する
			request.setAttribute("book_list", book_list);

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
				request.getRequestDispatcher("/view/showCart.jsp").forward(request,  response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
