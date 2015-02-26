<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.strategies.*"%>
<div class="panel panel-green">
	<div class="panel-heading">
		<div class="row">
			<div class="col-xs-3">
				<i class="fa fa-shopping-cart fa-5x"></i>
			</div>
			<div class="col-xs-9 text-right">
				<div id="pandl" class="huge">
					<%
						ServletContext ctx = getServletContext();
						TMA twoPoint = (TMA) ctx.getAttribute("tma");
						double pl = twoPoint.getProfitLoss();
						String profitLoss = Double.toString(pl);
					%>
				</div>
				<div>Profit & Loss</div>
			</div>
		</div>
	</div>
</div>