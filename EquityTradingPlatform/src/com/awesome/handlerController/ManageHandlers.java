package com.awesome.handlerController;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.feeds.MarketDataConsumer;

/**
 * Servlet implementation class ManageHandlers
 */
@WebServlet("/ManageHandlers")
public class ManageHandlers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext ctx = getServletContext();
	MarketDataConsumer md = (MarketDataConsumer) ctx.getAttribute("app");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageHandlers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
