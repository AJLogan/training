<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="java.sql.*"
	import="com.awesome.*" %>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-money fa-fw"></i> Quote Panel
		</h3>
	</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th>ticker</th>
					<th>askPrice</th>
					<th>askSize</th>
					<th>bidPrice</th>
					<th>bidSize</th>
					<th>closePrice</th>
					<th>volume</th>
				</tr>
				<%
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			out.println("Error loading driver: " + e);
		}

		// Connect to a database
		Connection cn2 = null;
		try {
			cn2 = DriverManager
					.getConnection("jdbc:mysql://localhost/classfiles?"
							+ "user=root&password=password");
		} catch (SQLException e) {
			System.out.println("Error connecting to a database: " + e);
		}

		Statement st2;
		try {
			// Error occuring here
			st2 = cn2.createStatement();
			ResultSet rs2 = st2
					.executeQuery("select ticker, askPrice, askSize, bidPrice, bidSize, closePrice, volume from quotes");

			//out.println("<table>");
			while (rs2.next()) {
				out.println("<tr>");
				out.print("<td>" + rs2.getString("ticker") + "</td>");
				out.print("<td>" + rs2.getFloat(2) + "</td>");
				out.print("<td>" + rs2.getInt(3) + "</td>");
				out.print("<td>" + rs2.getFloat(4) + "</td>");
				out.print("<td>" + rs2.getInt(5) + "</td>");
				out.print("<td>" + rs2.getFloat(6) + "</td>");
				out.print("<td>" + rs2.getInt(7) + "</td>");
				out.println("</tr>");
			}

			//out.println("/table");

		} catch (SQLException sqle) {
			out.println("Server error: " + sqle);
		}
	%>
				
				
			</table>
		</div>
		<div class="text-right">
			<a href="#">View All Transactions <i
				class="fa fa-arrow-circle-right"></i></a>
		</div>
	</div>
</div>

<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.Connection"
	import="java.sql.DriverManager" import="java.sql.SQLException"
	import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>Quotes</h3>
<table>
<tr>
<th>ticker</th>
<th>askPrice</th>
<th>askSize</th>
<th>bidPrice</th>
<th>bidSize</th>
<th>closePrice</th>
<th>volume</th>
</tr>
	<%
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			out.println("Error loading driver: " + e);
		}

		// Connect to a database
		Connection cn = null;
		try {
			cn = DriverManager
					.getConnection("jdbc:mysql://localhost/classfiles?"
							+ "user=root&password=password");
		} catch (SQLException e) {
			System.out.println("Error connecting to a database: " + e);
		}

		Statement st;
		try {
			// Error occuring here
			st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("select ticker,askPrice,askSize,bidPrice,bidSize,closePrice,volume from quotes");

			//out.println("<table>");
			while (rs.next()) {
				out.println("<tr>");
				out.print("<td>" + rs.getString(1) + "</td>");
				out.print("<td>" + rs.getFloat(2) + "</td");
				out.print("<td>" + rs.getInt(3) + "</td");
				out.print("<td>" + rs.getFloat(4) + "</td");
				out.print("<td>" + rs.getInt(5) + "</td");
				out.print("<td>" + rs.getFloat(6) + "</td");
				out.print("<td>" + rs.getInt(7) + "</td");
				out.println("</tr>");
			}

			//out.println("/table");

		} catch (SQLException sqle) {
			out.println("Server error: " + sqle);
		}
	%>
</table>



</body>
</html> --%>