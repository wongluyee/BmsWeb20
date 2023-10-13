package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Order;

public class OrderDAO {
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

		public void insert(Order order) {
			Connection con = null;
			Statement smt = null;

			try {
				String sql = "INSERT INTO orderinfo VALUES(NULL,'"+ order.getUserid() + "','"+ order.getIsbn() +"',"
						+ order.getQuantity() +",CURDATE())";
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
