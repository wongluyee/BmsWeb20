package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import bean.Book;
import dao.BookDAO;

public class ListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";

		try {
			// BookDAOクラスのオブジェクトを生成
			BookDAO objDao = new BookDAO();

			// 検索した書籍情報を格納する配列
			ArrayList<Book> list = new ArrayList<Book>();

			// 全検索メソッドを呼び出し
			list = objDao.selectAll();

			// 検索結果を持ってlist.jspにフォワード
			request.setAttribute("list", list);
			request.getRequestDispatcher("/view/list.jsp").forward(request, response);

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
