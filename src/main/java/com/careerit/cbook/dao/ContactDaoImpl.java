package com.careerit.cbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.careerit.cbook.domain.Contact;

public class ContactDaoImpl implements ContactDao {

	private static final String SELECT_ALL = "select cid,name,email,mobile,dob from contact";
	private static final String SELECT_BY_ID = "select cid,name,email,mobile,dob from contact where cid=?";

	@Override
	public Contact insertContact(Contact contact) {
		// Get inserted id and set to the return object
		
		return null;
	}

	@Override
	public Contact updateContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteContact(int cid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Contact selectContact(int inputCid) {
		Contact contact = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			pst = con.prepareStatement(SELECT_BY_ID);
			pst.setInt(1, inputCid);
			rs = pst.executeQuery(SELECT_ALL);
			while (rs.next()) {
				contact = extractToContact(rs);

			}
		} catch (SQLException e) {
			System.out.println("While getting all contacts :" + e);
		} finally {
			DbUtil.close(rs, pst, con);
		}
		return contact;
	}

	@Override
	public List<Contact> selectContacts() {
		List<Contact> list = new ArrayList<Contact>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Contact contact = extractToContact(rs);
				list.add(contact);
			}
		} catch (SQLException e) {
			System.out.println("While getting all contacts :" + e);
		} finally {
			DbUtil.close(rs, st, con);
		}
		return list;
	}

	@Override
	public List<Contact> search(String str) {
		
		return null;
	}

	private Contact extractToContact(ResultSet rs) throws SQLException {
		Contact contact = null;
		int cid = rs.getInt("cid");
		String name = rs.getString("name");
		String email = rs.getString("email");
		String mobile = rs.getString("mobile");
		LocalDate dob = rs.getDate("dob").toLocalDate();
		contact = Contact.builder().name(name).cid(cid).email(email).mobile(mobile).dob(dob).build();
		return contact;
	}

}
