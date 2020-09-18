package com.careerit.cbook.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.careerit.cbook.service.ContactService;
import com.careerit.cbook.service.ContactServiceImpl;

/**
 * Servlet implementation class RemoveContactServlet
 */
@WebServlet("/remove")
public class RemoveContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private ContactService contactService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveContactServlet() {
        contactService = ContactServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int cid = Integer.parseInt(request.getParameter("cid"));
			boolean res = contactService.deleteContact(cid);
			if(res) {
				response.sendRedirect("/cbook");
			}else {
				request.setAttribute("message", "Contact couldn't be deleted with id:"+cid);
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				
				rd.forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
