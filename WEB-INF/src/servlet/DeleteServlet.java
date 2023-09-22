package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import dao.BookDAO;

public class DeleteServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";

		try {
			// BookDAOクラスのオブジェクトを生成
			BookDAO objDao = new BookDAO();

			// エンコードを設定
			response.setContentType("text/html; charset=UTF-8");

			// 画面から送信されるISBN情報を受け取る
			String isbn = request.getParameter("isbn");

			// 書籍存在するかのチェック
			if (isbn.equals("")) {
				error = "削除対象の書籍が存在しない為、書籍削除処理は行えませんでした。";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// 書籍情報を削除
			objDao.delete(isbn);

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
