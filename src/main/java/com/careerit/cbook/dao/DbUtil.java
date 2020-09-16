package com.careerit.cbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DbUtil {

	private DbUtil() {

	}
	

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch_4", "user", "user");
		} catch (SQLException e) {
			System.out.println("While connecting with db:" + e);
		}
		return con;
	}

	public static void close(ResultSet rs, Statement st, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println("While closing resources :" + e);
		}
	}


	public static void close(Statement st, Connection con) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			System.out.println("While closing resources :" + e);
		}
		
	}
}
