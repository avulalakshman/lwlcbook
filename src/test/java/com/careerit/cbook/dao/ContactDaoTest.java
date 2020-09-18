package com.careerit.cbook.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.careerit.cbook.domain.Contact;

public class ContactDaoTest {

	private ContactDao contactDao;

	@BeforeEach
	void initSeedData() {
		contactDao = ContactDaoImpl.getInstance();
		contactDao.deleteAllContacts();
		insertContacts();
	}



	@Test
	void insertContact() {

		Contact contact = Contact.builder()
				.name("Manoj")
				.dob(LocalDate.of(1984, 8, 6))
				.email("manoj.pvn@gmail.com")
				.mobile("9090909090")
				.build();
		Contact savedContact = contactDao.insertContact(contact);
		assertTrue(savedContact.getCid() > 0);

	}
	@Test
	void selectContactsTest() {
		List<Contact> list = contactDao.selectContacts();
		list.stream().forEach(e->{
			System.out.println(e.getName());
		});
	    assertEquals(3, list.size());
	}

	private void insertContacts() {
		Contact contact1 = Contact.builder()
				.name("Manoj")
				.dob(LocalDate.of(1984, 8, 16))
				.email("manoj.pvn@gmail.com")
				.mobile("9090909091")
				.build();
		Contact contact2 = Contact.builder()
				.name("Charan")
				.dob(LocalDate.of(1983, 3, 12))
				.email("charan.k@gmail.com")
				.mobile("9090909092")
				.build();
		Contact contact3 = Contact.builder().name("Krish")
				.dob(LocalDate.of(1984, 12, 6))
				.email("krish.t@gmail.com")
				.mobile("9090909093")
				.build();
		 contactDao.insertContact(contact1);
		 contactDao.insertContact(contact2);
		 contactDao.insertContact(contact3);

	}
}
