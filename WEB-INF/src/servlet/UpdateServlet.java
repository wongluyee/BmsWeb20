package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;

public class UpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// BookDAOクラスのオブジェクトを生成
			BookDAO objDao = new BookDAO();

			// 更新後の書籍情報を格納するBookオブジェクトを生成
			Book book = new Book();

			// エンコードを設定
			response.setContentType("text/html; charset=UTF-8");

			// 画面からの入力情報を受け取り
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			// Titleの入力チェック
			if (title.equals("")) {
				error = "Title未入力のため、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			// Priceの入力チェック
			if (price.equals("")) {
				error = "Price未入力のため、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			int intPrice = Integer.parseInt(price);

			if (intPrice <= 0) {
				error = "価格が0以下の為、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			// Bookオブジェクトに格納
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(intPrice);

			// Bookオブジェクトに格納された書籍データでデータベースを更新
			objDao.update(book);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
			cmd = "menu";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (error.equals("")) {
				// 「ListServlet」へフォワード
				request.getRequestDispatcher("/list").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
