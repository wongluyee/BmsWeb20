package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;

public class DetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// BookDAOクラスのオブジェクトを生成
			BookDAO objDao = new BookDAO();

			// 表示する書籍情報を格納するBookオブジェクトを生成
			Book book = new Book();

			// エンコードを設定
			response.setContentType("text/html; charset=UTF-8");

			// 画面から送信されるISBNとcmd情報を受け取ります。
			String isbn = request.getParameter("isbn");
			cmd = request.getParameter("cmd");

			// 書籍情報を取得
			book = objDao.selectByIsbn(isbn);

			// 取得した書籍情報を「book」という名前でリクエストスコープに登録
			request.setAttribute("book", book);

			// cmdが「detail」の場合は「detail.jsp」へフォワード
			if (cmd.equals("detail")) {
				request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
			}

			// cmdが「update」の場合は「update.jsp」へフォワード
			if (cmd.equals("update")) {
				request.getRequestDispatcher("/view/update.jsp").forward(request, response);
			}
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
			cmd = "menu";
		} catch (Exception e) {
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
