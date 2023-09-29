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
import bean.User;
import dao.BookDAO;
import dao.OrderDAO;
import util.SendMail;

public class BuyConfirmServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";

		try {
			// セッションから"user"のUserオブジェクトを取得する
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			// セッションから"order_list"を取得する
			ArrayList<Order> order_list = (ArrayList<Order>)session.getAttribute("order_list");

			// BookDAOとOrderDAOをインスタンス化
			BookDAO bookDao = new BookDAO();
			OrderDAO orderDao = new OrderDAO();

			// order_listの(カート追加データ分）だけ呼び出す
			ArrayList<Book>list = new ArrayList<Book>();
			for (Order order : order_list) {
				Book book = bookDao.selectByIsbn(order.getIsbn());
				orderDao.insert(order);
				// 取得した各BookをListに追加する
				list.add(book);
			}

			// リクエストスコープに"book_list"という名前で格納する
			request.setAttribute("book_list", list);

			// "order_list"の注文情報内容をメール送信する
			SendMail sendMail = new SendMail();

			// セッションの"order_list"情報をクリアする
			session.setAttribute("order_list", null);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
		} finally {
			if (error.equals("")) {
				// フォワード
				request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
