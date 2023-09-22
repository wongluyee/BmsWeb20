package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;

public class UpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";

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

			// Priceの入力チェック
			if (price.equals("")) {
				error = "Price未入力のため、書籍登録処理は行えませんでした。";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			int intPrice = Integer.parseInt(price);

			if (intPrice <= 0) {
				error = "価格が0以下の為、書籍登録処理は行えませんでした。";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// Bookオブジェクトに格納
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(intPrice);

			// Bookオブジェクトに格納された書籍データでデータベースを更新
			objDao.update(book);

			// 「ListServlet」へフォワード
			request.getRequestDispatcher("/list").forward(request, response);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
			request.setAttribute("error", error);
			request.getRequestDispatcher("/view/error.jsp").forward(request, response);
		}
	}

}
