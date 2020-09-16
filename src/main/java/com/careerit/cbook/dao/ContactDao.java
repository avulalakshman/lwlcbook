package com.careerit.cbook.dao;

import java.util.List;

import com.careerit.cbook.domain.Contact;

public interface ContactDao {

	Contact insertContact(Contact contact);

	Contact updateContact(Contact contact);

	boolean deleteContact(int cid);

	Contact selectContact(int cid);

	List<Contact> selectContacts();

	List<Contact> search(String str);

	int deleteAllContacts();

	int insertContacts(List<Contact> contacts);
}
