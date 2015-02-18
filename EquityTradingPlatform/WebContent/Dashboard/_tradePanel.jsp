<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="java.sql.*"
	import="com.awesome.*"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-money fa-fw"></i> Trade Panel
		</h3>
	</div>
	<div class="panel-body">
		<div class="table-responsive">

			<table class="table table-bordered table-hover table-striped">

				<tr>
					<th>Order #</th>
					<th>Order Date</th>
					<th>Order Time</th>
					<th>Amount (USD)</th>
				</tr>
				<%
					try {

						Class.forName("com.mysql.jdbc.Driver");

						Connection cn = null;

						cn = DriverManager
								.getConnection("jdbc:mysql://localhost:8889/EquityTrading?"
										+ "user=root&password=root");

						Statement st = cn.createStatement();
						ResultSet rs = st
								.executeQuery("select id, ticker, volume, price, dealer from trades limit 20");
						while (rs.next()) {
							out.println("<tr>");
							out.print("<td>" + rs.getInt("id") + "</td>" + "<td>"
									+ rs.getString("ticker") + "</td>" + "<td>"
									+ rs.getInt("volume") + "</td>" + "<td>"
									+ rs.getFloat("price") + "</td>" + "<td>"
									+ rs.getString("dealer") + "</td>");
							out.println("</tr>");
						}// while

					} catch (SQLException e) {
						throw e;
					}// catch
					catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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