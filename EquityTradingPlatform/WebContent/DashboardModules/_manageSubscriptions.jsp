<%@page import="com.awesome.subscriptionController.AddSubscription"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.feeds.*" import="java.sql.*"
	import="java.util.*"%>

<div class="panel panel-red">
	<div class="panel-heading">
		<h3 class="panel-title">Manage Subscriptions</h3>
	</div>
	<div class="panel-body">

		<form action="/EquityTradingPlatform/index.jsp" method="post">
			<div class="form-group input-group">
				<input name="symbol" id="symbol" type="text" class="form-control">
				<span class="input-group-btn"><button class="btn btn-default"
						type="submit">
						<i class="fa fa-search"></i>
					</button></span>
			</div>
		</form>

		<!-- The table to show what has been selected -->
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Stock</th>
					</tr>
				</thead>
				<tbody>
					<%
						ServletContext ctx = getServletContext();
						MarketDataConsumer md = (MarketDataConsumer) ctx
								.getAttribute("app");
						for (int i = 0; i < md.symbols.size(); i++) {
							out.println("<tr><td><a href='/EquityTradingPlatform/index.jsp?sym=" + md.symbols.get(i) + "'><div class='glyphicon glyphicon-remove'></div></a> " + md.symbols.get(i) + "</td></tr>");
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</div>