<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="java.sql.*"
	import="com.awesome.*" import="com.awesome.dataAccess.*"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-money fa-fa"></i> Trades
		</h3>
	</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th>Ticker</th>
					<th>Size</th>
					<th>Total Cost</th>
					<th>P&L</th>
					<th>Dealer</th>
				</tr>
				<%
					Connection tradePanelCN = DatabaseUtils.setupDB();
					try {
						TradeQueries tq = new TradeQueries();
						ResultSet rs = tq.getStocksBeingWatched(tradePanelCN,
								md.symbols);
						while (rs.next()) {
							out.println("<tr>");
							out.print("<td>" + rs.getString("t") + "</td>"
									+ "<td>" + rs.getInt("v") + "</td>" + "<td>"
									+ rs.getFloat("p") + "</td>" + "<td>"
									+ rs.getString("pl") + "</td>" + "<td>"
									+ rs.getString("d") + "</td>");
							out.println("</tr>");
						}// while
					} catch (SQLException e) {
						throw e;
					}// catch
					finally {
						if (tradePanelCN != null) {
							tradePanelCN.close();
						}
					}
				%>
			</table>
		</div>
	</div>
</div>