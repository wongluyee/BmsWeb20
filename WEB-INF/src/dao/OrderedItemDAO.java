package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.OrderedItem;

public class OrderedItemDAO {
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

		public ArrayList<OrderedItem> selectAll() {

			Connection con = null;
			Statement smt = null;

			ArrayList<OrderedItem> list = new ArrayList<OrderedItem>();

			try {
				String sql = "SELECT o.user,b.title,o.date FROM bookinfo b INNER JOIN orderinfo o ON b.isbn=o.isbn";
				con = getConnection();
				smt = con.createStatement();
				ResultSet rs = smt.executeQuery(sql);

				// Bookインスタンス化して、各データを格納して、bookListに追加する
				while (rs.next()) {
					OrderedItem orderItem = new OrderedItem();
					orderItem.setUserid(rs.getString("user"));
					orderItem.setTitle(rs.getString("title"));
					orderItem.setDate(rs.getString("date"));
					list.add(orderItem);
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
			return list;
		}
}
