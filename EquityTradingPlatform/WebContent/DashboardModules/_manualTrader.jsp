
<%@page import="com.awesome.subscriptionController.AddSubscription"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.feeds.*" import="java.sql.*"
	import="com.awesome.*" import="com.awesome.graphing.*"
	import="java.util.*" import="com.awesome.dataAccess.*"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<span class="glyphicon glyphicon-transfer"></span> Manual Trader
		</h3>
	</div>
	<div class="panel-body">
		<form role="form" method="post"
			action="/EquityTradingPlatform/index.jsp">
			<div class="form-group">

				<!-- Dropdown select stock -->
				<label>Stock</label> <select class="form-control" name="ddSymbol">
					<%
						for (int i = 0; i < md.symbols.size(); i++) {
							out.println("<option>" + md.symbols.get(i) + "</option>");
						}
					%>
				</select>
			</div>

			<!-- Radio Buttons -->
			<div class="form-group">
				<!-- Buy -->
				<label class="radio-inline"> <input id="buyRadio"
					type="radio"
					<%if (request.getParameter("ddSymbol") != null) {

				if (request.getParameter("optionsRadiosInline").equals("buy")) {
					out.print("checked");
				} else {
					out.print("");
				}
			}%>
					value="buy" name="optionsRadiosInline"
					onChange="this.form.submit();">Buy
				</label>
				<input type="hidden" id="trade" name="trade" value="<%
				if(request.getParameter("optionsRadiosInline") != null)
				{
					if (request.getParameter("optionsRadiosInline").equals("buy")) {
							out.print("buy"); 		
					}
					else
					{
						out.print("sell"); 
					}
				}
				%>" />

				<!-- Sell -->
				<label class="radio-inline"> <input id="sellRadio"
					type="radio" value="sell"
					<%if (request.getParameter("ddSymbol") != null) {
				if (request.getParameter("optionsRadiosInline").equals("sell")) {
					out.print("checked");
				} else {
					out.print("");
				}
			}%>
					name="optionsRadiosInline" onChange="this.form.submit();">Sell
				</label>
			</div>

			<!-- Input text boxes -->
			<div class="form-group">
				<label>Quantity</label> <input class="form-control" type="text"
					name="quantity"
					value="<%//if(request.getParameter("done") != null)
			{
			//out.print(request.getParameter("quantity") + " " + request.getParameter("price") +
			//request.getParameter("ddSymbol") + request.getParameter("optionsRadiosInline"));
			}%>">
			</div>

			<div class="form-group">
				<label>Price</label><input class="form-control" type="text"
					name="price"
					value="<%if (request.getParameter("ddSymbol") != null) {
				//out.print(request.getParameter("ddSymbol") + " " +
				//request.getParameter("optionsRadiosInline"));
				if (request.getParameter("optionsRadiosInline").equals("buy")) {
					Connection cn = DatabaseUtils.setupDB();
					QuotesQueries qq = new QuotesQueries();
					ResultSet rs = qq.getAskPrice(cn,
							request.getParameter("ddSymbol"));
					rs.next();
					out.print(rs.getFloat(1));
					if (cn != null) {
						cn.close();
					}
				}

				if (request.getParameter("optionsRadiosInline").equals("sell")) {
					Connection cn = DatabaseUtils.setupDB();
					QuotesQueries qq = new QuotesQueries();
					ResultSet rs = qq.getBidPrice(cn,
							request.getParameter("ddSymbol"));
					rs.next();
					out.print(rs.getFloat(1));
					if (cn != null) {
						cn.close();
					}
				}
			}
			%>" />
			</div>
			<%
			//HERE NEW CODE
			if (request.getParameter("done") != null) {
				Connection manualTradeConnection = DatabaseUtils.setupDB();
				TradeQueries tq = new TradeQueries();
				if (request.getParameter("trade").equals("buy")) {
					tq.insertManualTradeBuy(manualTradeConnection,
							request.getParameter("ddSymbol"),
							request.getParameter("quantity"),
							request.getParameter("price"));
							System.out.println("BUY");
				} else {
					tq.insertManualTradeSell(manualTradeConnection,
							request.getParameter("ddSymbol"),
							request.getParameter("quantity"),
							request.getParameter("price"));
							System.out.println("SELL");
				}
				if (manualTradeConnection != null) {
					manualTradeConnection.close();
				}

			}
			%>
			<div class="form-group">
				<input class="btn btn-sm btn-success" type="submit" id="done"
					name="done" value="Done" />
			</div>
		</form>
	</div>
</div>
