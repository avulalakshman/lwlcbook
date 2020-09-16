package com.careerit.cbook.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.careerit.cbook.dao.ContactDao;
import com.careerit.cbook.domain.Contact;

public class ContactServiceImpl implements ContactService {

	private static final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);
	private ContactDao contactDao;

	@Override
	public Contact addContact(Contact contact) {
		if (contact == null) {
			log.error("Contact object can't be empty or null");
			throw new IllegalArgumentException("Contact object can't be null");
		}
		Contact savedContact = contactDao.insertContact(contact);
		if (savedContact != null) {
			log.info("Contact added successfully with id :{}", savedContact.getCid());
		}
		return savedContact;
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
	public Contact getContact(int cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> search(String str) {
		// TODO Auto-generated method stub
		return null;
	}

}
