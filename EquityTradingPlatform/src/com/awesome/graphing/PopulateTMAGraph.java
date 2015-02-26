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

import com.awesome.model.MovingAverage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class PopulateTMAGraph
 */
@WebServlet("/PopulateTMAGraph")
public class PopulateTMAGraph extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int offset;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PopulateTMAGraph() {
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
		ArrayList<MovingAverage> data = new ArrayList<MovingAverage>();
		int off = 0;
		if (ctx.getAttribute("tmaoffset") != null) {
			off = (int) ctx.getAttribute("tmaoffset");
			off++;
		} else {
			off++;
		}
		ctx.setAttribute("tmaoffset", off);
		data = FetchData.getTwoPointGraphData(off);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(data,
				new TypeToken<List<MovingAverage>>() {
				}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}
}
