package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;

public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// BookDAOクラスのオブジェクトを生成
			BookDAO objDao = new BookDAO();

			// 画面から送信される検索条件を受け取るためのエンコードを設定
			response.setContentType("text/html; charset=UTF-8");

			// 画面から送信される検索条件を受け取り
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");

			// BookDAOクラスに定義したsearch（）メソッドを利用して書籍情報を取得
			ArrayList<Book> book_list = objDao.search(isbn, title, price);

			// 取得した書籍情報を「list」という名前でリクエストスコープに登録
			request.setAttribute("list", book_list);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
			cmd = "menu";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (error.equals("")) {
				// 検索結果を持ってlist.jspにフォワード
				request.getRequestDispatcher("/view/list.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
