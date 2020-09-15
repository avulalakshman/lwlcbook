package com.careerit.cbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.careerit.cbook.domain.Contact;

public class Manager {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/batch_4";
		String username = "user";
		String password = "user";
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, username, password);
			st = con.createStatement();
			rs = st.executeQuery("select cid,name,email,mobile,dob from contact");
			while (rs.next()) {
				int cid = rs.getInt("cid");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				LocalDate dob = rs.getDate("dob").toLocalDate();
				Contact contact = Contact.builder().name(name).cid(cid).email(email).mobile(mobile).dob(dob).build();
				System.out.println(contact);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(st!=null)
					st.close();
				if(con!=null)
					con.close();
			}catch (Exception e) {
				System.out.println("While closing resources :"+e);
			}
		}
	}
}
