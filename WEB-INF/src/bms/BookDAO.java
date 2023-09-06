package bms;

import java.sql.*;
import java.util.ArrayList;

public class BookDAO {

	// データベース接続情報
	private static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/mybookdb";
	private static String USER = "root";
	private static String PASS = "root123";

	private static Connection getConnection() {
		try {
			// JDBCドライバーを読み込む
			Class.forName(RDB_DRIVE);

			// Connectionする
			Connection con = DriverManager.getConnection(URL, USER, PASS);

			return con;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	// 一覧メソッド
	public ArrayList<Book> selectAll() {

		Connection con = null;
		Statement smt = null;

		ArrayList<Book> bookList = new ArrayList<Book>();

		try {
			String sql = "SELECT * FROM bookinfo";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);

			// Bookインスタンス化して、各データを格納して、bookListに追加する
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				bookList.add(book);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return bookList;
	}

	// 追加メソッド
	public void insert(Book book) {

		Connection con = null;
		Statement smt = null;

		try {
			String sql = "INSERT INTO bookinfo VALUES('" + book.getIsbn() + "','" + book.getTitle() + "',"
					+ book.getPrice() + ")";
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	// 詳細メソッド
	public Book selectByIsbn(String isbn) {

		Connection con = null;
		Statement smt = null;

		Book book = new Book();

		try {
			String sql = "SELECT isbn,title,price FROM bookinfo WHERE isbn = '" + isbn + "'";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);

			// 結果セットから書籍データを取り出し、Bookオブジェクトに格納する
			while (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return book;
	}

	// 削除メソッド
	public void delete(String isbn) {
		Connection con = null;
		Statement smt = null;

		try {
			String sql = "DELETE FROM bookinfo WHERE isbn = '" + isbn + "'";
			con = getConnection();
			smt = con.createStatement();
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
}
