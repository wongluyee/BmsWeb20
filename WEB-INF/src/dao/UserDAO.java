package dao;

import java.sql.*;
import bean.User;

public class UserDAO {

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

	public User selectByUser(String userid) {

		Connection con = null;
		Statement smt = null;

		User user = new User();

		try {
			String sql = "SELECT * FROM userinfo WHERE user = '" + userid + "'";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
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
		return user;
	}

	public User selectByUser(String userid, String password) {

		Connection con = null;
		Statement smt = null;

		User user = new User();

		try {
			String sql = "SELECT * FROM userinfo WHERE user = '" + userid + "' AND password='" + password + "'";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);

			if (rs.next()) {
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
				return user;
			} else {
				return null;
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
	}

}
