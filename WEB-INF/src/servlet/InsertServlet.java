package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;

public class InsertServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		String cmd = "";

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
				cmd = "list";
				return;
			}

			// ISBN重複のチェック
			Book checkBook = objDao.selectByIsbn(isbn);
			if (checkBook.getIsbn() != null) {
				error = "入力ISBNは既に登録済みの為、書籍登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			String title = request.getParameter("title");

			// Titleの入力チェック
			if (title.equals("")) {
				error = "Title未入力のため、書籍登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			String price = request.getParameter("price");

			// Priceの入力チェック
			if (price.equals("")) {
				error = "Price未入力のため、書籍登録処理は行えませんでした。";
				cmd = "list";
				return;
			}

			int intPrice = Integer.parseInt(price);

			// Bookオブジェクトに格納
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setPrice(intPrice);

			// Bookオブジェクトに格納された書籍データをデータベースに登録
			objDao.insert(book);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
			cmd = "menu";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "menu";
		} finally {
			if (error.equals("")) {
				// 一覧画面に戻る
				request.getRequestDispatcher("/list").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
