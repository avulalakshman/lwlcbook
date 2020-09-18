package com.careerit.cbook.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.careerit.cbook.domain.Contact;
import com.careerit.cbook.service.ContactService;
import com.careerit.cbook.service.ContactServiceImpl;

/**
 * Servlet implementation class ViewContactServlet
 */
@WebServlet("/view")
public class ViewContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ContactService contactService;

	public ViewContactServlet() {
		contactService = ContactServiceImpl.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			List<Contact> contactList = contactService.getContacts();
			
			
			request.setAttribute("contacts", contactList);
			RequestDispatcher rd = request.getRequestDispatcher("/viewcontacts.jsp");
			rd.forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
