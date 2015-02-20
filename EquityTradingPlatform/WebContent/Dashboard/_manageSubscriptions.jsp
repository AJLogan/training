<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.feeds.*"%>
<div class="panel panel-yellow">
	<div class="panel-heading">
		<h3 class="panel-title">Panel title</h3>
	</div>
	<div class="panel-body">
		Panel content
		<%
		ServletContext ctx = getServletContext();
		MarketDataConsumer md = (MarketDataConsumer) ctx.getAttribute("app");
		out.println(md.symbols.toString());
		md.addSymbol("FB");
		md.removeSymbol("FB");
		out.println(md.symbols.toString());
	%>
	</div>
</div>