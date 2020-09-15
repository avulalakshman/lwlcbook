package com.careerit.cbook.service;

import java.util.List;

import com.careerit.cbook.domain.Contact;

public interface ContactService {

	Contact addContact(Contact contact);
	
	Contact updateContact(Contact contact);

	boolean deleteContact(int cid);

	Contact getContact(int cid);

	List<Contact> getContacts();

	List<Contact> search(String str);
}
