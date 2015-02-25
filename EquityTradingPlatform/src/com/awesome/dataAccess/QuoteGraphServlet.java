package com.awesome.dataAccess;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import com.awesome.jsonparser.ParseToJson;

/**
 * Servlet implementation class QuoteGraphServlet
 */
@WebServlet("/QuoteGraphServlet")
public class QuoteGraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuoteGraphServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		QuotesQueries qq = new QuotesQueries();
		DatabaseUtils du = new DatabaseUtils();
		try {
			JSONArray quotes = this.getAsk();
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			out.print(quotes);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet getAskPr(Connection cn) throws SQLException {
		ServletContext ct = getServletContext();
		int start = 0;
		if (ct.getAttribute("start") != null) {
			start = (int) ct.getAttribute("start");
		} else {
			ct.setAttribute("start", start);
		}
		start++;
		ct.setAttribute("start", start);
		String query = "select ticker, askPrice from EquityTrading.quotes limit 1000 offset "
				+ start;
//		System.out.println(query);
		ResultSet rs = DatabaseUtils.executeQuery(cn, query);
		return rs;
	}

	public JSONArray getAsk() throws JSONException, SQLException {
		JSONArray jArray = new JSONArray();
		Connection quotePanelCN = DatabaseUtils.setupDB();
		Random rn = new Random();
		try {
			QuotesQueries qq = new QuotesQueries();
			ResultSet rs2 = this.getAskPr(quotePanelCN);
			while (rs2.next()) {
				ParseToJson qtj = new ParseToJson();
				// jarray.add(qtj.convert(rs2));
				rs2.getFloat("askPrice");
				// out.println(qtj.convert(rs2).toString());
				jArray.put(rs2.getFloat("askPrice"));// Proper code
				// jArray.put(rn.nextInt(150));
			}// while
			return jArray;
		} catch (SQLException e) {
			throw e;
		}// catch
		finally {
			if (quotePanelCN != null) {
				quotePanelCN.close();
			}
		}
	}

}
