package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.Book;
import bean.User;
import dao.BookDAO;

public class UpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

		try {
			// ユーザーの権限確認
			// セッションから"user"のUserオブジェクトを取得する
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");

			if (user == null) {
				error = "セッションが切れたため、再度ログインしてください。";
				cmd = "logout";
				return;
			}

			if (user.getAuthority().equals("1")) {
				error = "権限がないため、閲覧できませんでした。";
				cmd = "menu";
				return;
			}

			// BookDAOクラスのオブジェクトを生成
			BookDAO bookDao = new BookDAO();

			// エンコードを設定
			response.setContentType("text/html; charset=UTF-8");

			// 画面からの入力情報を受け取り
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String price = request.getParameter("price");
			int intPrice = Integer.parseInt(price);

			// Titleの入力チェック
			if (title.trim().equals("")) {
				error = "Title未入力のため、書籍更新処理は行えませんでした。";
				cmd = "list";
				return;
			}

			// Bookオブジェクトに格納
			Book book = new Book(isbn, title, intPrice);

			// Bookオブジェクトに格納された書籍データでデータベースを更新
			bookDao.update(book);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、書籍更新はできませんでした。";
			cmd = "menu";
		} catch (Exception e) {
			e.printStackTrace();
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
