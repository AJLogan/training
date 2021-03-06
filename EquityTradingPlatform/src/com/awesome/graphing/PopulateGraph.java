package com.awesome.graphing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awesome.feeds.MarketDataConsumer;
import com.awesome.model.Quote;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class PopulateGraph
 */
@WebServlet("/PopulateGraph")
public class PopulateGraph extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int offset;
	public Vector<String> ticker;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PopulateGraph() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletContext ctx = request.getServletContext();
		MarketDataConsumer md = (MarketDataConsumer) ctx.getAttribute("app");
		ArrayList<Quote> data = new ArrayList<Quote>();
		int offs = 0;
		ticker = null;
		if (md.symbols != null) {			
			ticker = md.symbols;
		}
		if (ctx.getAttribute("offset") != null) {
			offs = (int) ctx.getAttribute("offset");
			offs++;
		} else {
			offs++;
		}
		ctx.setAttribute("offset", offs);
		data = FetchData.getGraphData(ticker, offs);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(data,
				new TypeToken<List<Quote>>() {
				}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}
}
