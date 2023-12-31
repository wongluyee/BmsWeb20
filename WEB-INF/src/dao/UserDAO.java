package dao;

import java.sql.*;
import java.util.ArrayList;

import bean.Book;
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

	// ユーザー詳細機能
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

	public User selectByUserEmail(String email) {
		Connection con = null;
		Statement smt = null;

		User user = new User();

		try {
			String sql = "SELECT * FROM userinfo WHERE email = '" + email + "'";
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

	// For login
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

	// ユーザー一覧機能
	public ArrayList<User> selectAll() {
		Connection con = null;
		Statement smt = null;

		ArrayList<User> userList = new ArrayList<User>();

		try {
			String sql = "SELECT * FROM userinfo";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
				userList.add(user);
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
		return userList;
	}

	// ユーザー登録機能
	public int insert(User user) {
		Connection con = null;
		Statement smt = null;

		try {
			String sql = "INSERT INTO userinfo VALUES('" + user.getUserid() + "','" + user.getPassword() + "','" + user.getEmail() + "','" + user.getAuthority() + "')";
			con = getConnection();
			smt = con.createStatement();
			int count = smt.executeUpdate(sql);
			return count;

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

	// ユーザー削除機能
	public int delete(String userid) {
		Connection con = null;
		Statement smt = null;

		try {
			String sql = "DELETE FROM userinfo WHERE user = '" + userid + "'";
			con = getConnection();
			smt = con.createStatement();
			int count = smt.executeUpdate(sql);
			return count;

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

	// ユーザー更新機能
	public int update(User user) {
		Connection con = null;
		Statement smt = null;

		try {
			String sql = "UPDATE userinfo SET password='" + user.getPassword() + "',email='" + user.getEmail() + "',authority='"
					+ user.getAuthority() + "' WHERE user='" + user.getUserid() + "'";
			con = getConnection();
			smt = con.createStatement();
			int count = smt.executeUpdate(sql);
			return count;

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

	// ユーザー検索機能
	public ArrayList<User> search(String userid) {
		Connection con = null;
		Statement smt = null;

		ArrayList<User> userList = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM userinfo WHERE user LIKE '%" + userid + "%'";
			con = getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);

			// Userインスタンス化して、各データを格納して、userListに追加する
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("user"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getString("authority"));
				userList.add(user);
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
		return userList;
	}

	// パスワード変更機能
		public int updateForPassword(String userid, String password) {
			Connection con = null;
			Statement smt = null;

			try {
				String sql = "UPDATE userinfo SET password='" + password + "' WHERE user = '" + userid + "'";
				con = getConnection();
				smt = con.createStatement();
				int count = smt.executeUpdate(sql);
				return count;

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
