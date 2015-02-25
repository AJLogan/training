package com.awesome.subscriptionController;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;

import com.awesome.feeds.MarketDataConsumer;

/**
 * Servlet implementation class ManageSubscriptions
 */
@WebServlet("/AddSubscription")
public class AddSubscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext ctx = getServletContext();
	MarketDataConsumer md = (MarketDataConsumer) ctx.getAttribute("app");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSubscription() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("symbol") != null)
		{
			HttpSession svar = request.getSession();
			svar.setAttribute("sym", request.getParameter("symbol"));
			md.addSymbol(request.getParameter("symbol"));
		}
		else
		{
			response.getWriter().print("Nothing set");
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
