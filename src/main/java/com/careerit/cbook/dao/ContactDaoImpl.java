package com.careerit.cbook.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.careerit.cbook.domain.Contact;

public class ContactDaoImpl implements ContactDao {

	private static final String SELECT_ALL = "select cid,name,email,mobile,dob from contact";
	private static final String SELECT_BY_ID = "select cid,name,email,mobile,dob from contact where cid=?";
	private static final String ADD_CONTACT = "insert into contact(name,email,mobile,dob) values(?,?,?,?)";
	private static final String DELETE_CONTACTS = "delete from contact";

	private static ContactDao contactDao = null;

	private ContactDaoImpl() {

	}

	public static ContactDao getInstance() {

		if (contactDao == null) {
			synchronized (ContactDaoImpl.class) {
				if (contactDao == null) {
					contactDao = new ContactDaoImpl();
				}
			}
		}
		return contactDao;

	}

	private static final Logger log = LoggerFactory.getLogger(ContactDaoImpl.class);

	@Override
	public Contact insertContact(Contact contact) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			pst = con.prepareStatement(ADD_CONTACT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getEmail());
			pst.setString(3, contact.getMobile());
			pst.setDate(4, Date.valueOf(contact.getDob()));

			log.debug("Query :{}", ADD_CONTACT);
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			int cid = 0;
			if (rs.first()) {
				cid = rs.getInt(1);
			}
			log.info("Contact is added with id:{}", cid);
			contact.setCid(cid);

		} catch (Exception e) {
			log.error("While adding contact :{}", e);
		}

		return contact;
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

		log.info("Looking for contact with id :{}", inputCid);
		Contact contact = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = DbUtil.getConnection();
			pst = con.prepareStatement(SELECT_BY_ID);
			pst.setInt(1, inputCid);
			rs = pst.executeQuery(SELECT_ALL);
			log.debug("Query :{}", SELECT_BY_ID);
			while (rs.next()) {
				contact = extractToContact(rs);
			}
		} catch (SQLException e) {
			System.out.println("While getting all contacts :" + e);
		} finally {
			DbUtil.close(rs, pst, con);
		}
		if (contact == null) {
			log.info("Contact is not found for the given id:{}", inputCid);
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

	@Override
	public int deleteAllContacts() {
		Connection con = null;
		Statement st = null;
		int count = 0;
		try {
			con = DbUtil.getConnection();
			st = con.createStatement();
			count = st.executeUpdate(DELETE_CONTACTS);
			log.info("Total {} contacts deleted from DB", count);
		} catch (SQLException e) {
			log.error("While getting all contacts :" + e);
		} finally {
			DbUtil.close(st, con);
		}
		return count;
	}

	@Override
	public int insertContacts(List<Contact> contacts) {
		// TODO Auto-generated method stub
		return 0;
	}

}
