package bms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class InsertServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";

		try {
			// BookDAOクラスのオブジェクトを生成
			BookDAO objDao = new BookDAO();

			// 登録する書籍情報を格納するBookオブジェクトを生成
			Book book = new Book();

			// 画面からの入力情報を受け取るためのエンコードを設定
			response.setContentType("text/html; charset=UTF-8");

			// 画面からの入力情報を受け取り
			String isbn = request.getParameter("isbn");

			// ISBNの入力チェック
			if (isbn.equals("")) {
				error = "ISBN未入力のため、書籍登録処理は行えませんでした。";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			// ISBN重複のチェック
			Book checkBook = objDao.selectByIsbn(isbn);
			if (checkBook.getIsbn() != null) {
				error = "入力ISBNは既に登録済みの為、書籍登録処理は行えませんでした。";
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

			String title = request.getParameter("title");
			int price = Integer.parseInt(request.getParameter("price"));

			// Bookオブジェクトに格納
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(price);

			// Bookオブジェクトに格納された書籍データをデータベースに登録
			objDao.insert(book);

			// 一覧画面に戻る
			request.getRequestDispatcher("/list").forward(request, response);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
		}
	}

}
