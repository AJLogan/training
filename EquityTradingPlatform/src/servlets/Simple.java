package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Simple
 */
@WebServlet(urlPatterns = { "/home", "*.do" }, initParams = { @WebInitParam(name = "ProductName", value = "Welcome Application") })
public class Simple extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String appName = "ETP";

	@Override
	public void init() throws ServletException {
		appName = getInitParameter("ProductName");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Simple() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// http://localhost:8080/EquityTradingPlatform/home?name=andrew
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name != null) {
			response.setContentType("text/xml");
			response.getWriter().printf("<application>" + 
			"<name>Hello %s</name>" +
			"<product>Hello %s</product>"
			+ "</application>", name, appName);
		} else {
			response.getWriter().write("Please enter a name");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name != null && name != "") {
			response.getWriter().printf("Hello %s", name);
		} else {
			response.sendRedirect("Index.jsp");
		}
	}
}
