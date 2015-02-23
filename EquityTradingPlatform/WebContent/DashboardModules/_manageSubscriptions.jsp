<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.feeds.*"%>

<div class="panel panel-yellow">
	<div class="panel-heading">
		<h3 class="panel-title">Select Symbol</h3>
	</div>
	<div class="panel-body">
	
		<!-- HTML for SEARCH BAR -->
		<div id="tfheader">
				<input type="text" class="tftextinput" name="q" size="18"
					maxlength="80"><input type="submit" value="Look Up"
					class="tfbutton">

		</div>

		<!-- This is where the new symbols will go 
		connect to yahoo finance to get list of all the symbols-->
		<%
			//ServletContext ctx = getServletContext();
			//MarketDataConsumer md = (MarketDataConsumer) ctx.getAttribute("app");
			//md.addSymbol("FB");
			//out.println(md.symbols.toString());
			//md.removeSymbol("FB");
			//out.println(md.symbols.toString());
		%>
	</div>
</div>