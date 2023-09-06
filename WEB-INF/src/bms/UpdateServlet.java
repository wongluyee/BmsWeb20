package bms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

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

			// 画面からの入力情報を受け取り、Bookオブジェクトに格納
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			int price = Integer.parseInt(request.getParameter("price"));
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(price);

			// Bookオブジェクトに格納された書籍データでデータベースを更新
			objDao.update(book);

			//「ListServlet」へフォワード
			request.getRequestDispatcher("/list").forward(request, response);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
		} finally {
			request.setAttribute("error", error);
		}
	}

}
