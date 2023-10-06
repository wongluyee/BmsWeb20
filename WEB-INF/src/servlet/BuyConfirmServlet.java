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
import dao.OrderDAO;
import util.SendMail;

public class BuyConfirmServlet extends HttpServlet {
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

			// セッションから"order_list"を取得する
			ArrayList<Order> order_list = (ArrayList<Order>)session.getAttribute("order_list");

			if (order_list.isEmpty() || order_list == null) {
				error = "カートの中身が空のため、購入できません。";
				cmd = "menu";
				return;
			}

			// BookDAOとOrderDAOをインスタンス化
			BookDAO bookDao = new BookDAO();
			OrderDAO orderDao = new OrderDAO();

			// order_listの(カート追加データ分）だけ呼び出す
			ArrayList<Sales>list = new ArrayList<Sales>();
			for (Order order : order_list) {
				Book book = bookDao.selectByIsbn(order.getIsbn());
				Sales bookInfo = new Sales();
				bookInfo.setIsbn(book.getIsbn());
				bookInfo.setTitle(book.getTitle());
				bookInfo.setPrice(book.getPrice());
				bookInfo.setQuantity(order.getQuantity());
				orderDao.insert(order);
				// 取得した各BookをListに追加する
				list.add(bookInfo);
			}

			// リクエストスコープに"book_list"という名前で格納する
			request.setAttribute("book_list", list);

			// "order_list"の注文情報内容をメール送信する
			SendMail sendMail = new SendMail();
			String userEmail = user.getEmail();
			String subject = "本のご購入ありがとうございます。";

			// メッセージ
			String message = user.getUserid() + "様\n\n本のご購入ありがとうございます。以下内容で注文を受け付けましたので、ご連絡いたします。\n\n";

			int sum = 0;
			for (Sales book : list) {
				message = message.concat(book.getIsbn() + " " + book.getTitle() + " " + book.getPrice() + "円 " + book.getQuantity() + "冊\n");
				sum += (book.getPrice() * book.getQuantity());
			}
			message = message.concat("合計 " + sum + "\n\nまたのご利用よろしくお願いします。\n");

			sendMail.sendEmail(userEmail, subject, message);

			// セッションの"order_list"情報をクリアする
			session.setAttribute("order_list", null);

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
				request.getRequestDispatcher("/view/buyConfirm.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
