package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Book;
import bean.User;
import dao.BookDAO;
import util.FileIn;

public class InsertIniDataServlet extends HttpServlet {
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

			// BookDAOをインスタンス化し、selectAllメソッドを呼び出す
			BookDAO bookDao = new BookDAO();
			ArrayList<Book> list = bookDao.selectAll();

			// listに１件でも書籍データがあればerror.jspにフォワードす
			if (list.size() > 0) {
				error = "データベースに書籍があるため、初期データを登録できませんでした。";
				cmd = "menu";
				return;
			}

			// 初期データ用CSVファイルよりデータを取得する
			FileIn fileIn = new FileIn();
			String path = getServletContext().getRealPath("file\\initial_data.csv");

			// csvファイルがオープンできない場合
			if (fileIn.open(path) == false) {
				error = "初期化ファイルのオープンに失敗しました。または、初期データファイルがないため、登録は行えません。";
				cmd = "menu";
				return;
			}

			String strLine;

			while ((strLine = fileIn.readLine()) != null) {
				String[] bookArray = strLine.split(",");

				// 読み込んだ1行データのISBNやTITLE、またはPRICEのデータが1つでも不足している場合
				if (bookArray.length != 3) {
					error = "初期データファイルが不備があるため、登録は行えません。";
					cmd = "menu";
					return;
				}

				String isbn = bookArray[0];
				String title = bookArray[1];
				int price = Integer.parseInt(bookArray[2]);

				// Bookのオブジェクトを生成し、isbn, title, priceを設定する。(初期データの数分)
				Book book = new Book(isbn, title, price);

				// 取得した各BookをListに追加
				list.add(book);

				// リクエストスコープに"book_list"という名前で格納する
				request.setAttribute("book_list", list);

				// 設定した各Bookのオブジェクトを引数として、BookDAOオブジェクトを生成し、関連メソッドを呼び出す
				bookDao.insert(book);
			}

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
				request.getRequestDispatcher("/view/insertIniData.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
