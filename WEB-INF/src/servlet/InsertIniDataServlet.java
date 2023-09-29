package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertIniDataServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";

		try {


		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示はできませんでした。";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
		} finally {
			if (error.equals("")) {
				// フォワード
				request.getRequestDispatcher("/view/insertIniData.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
