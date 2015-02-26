package com.awesome.graphing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		ArrayList<Quote> data = new ArrayList<Quote>();
		int off = 0;
		if (ctx.getAttribute("offset") != null) {
			off = (int) ctx.getAttribute("offset");
			off++;
		} else {
			off++;
		}
		ctx.setAttribute("offset", off);
		data = FetchData.getGraphData(off);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(data,
				new TypeToken<List<Quote>>() {
				}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}
}
