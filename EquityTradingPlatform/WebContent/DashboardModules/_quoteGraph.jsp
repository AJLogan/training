<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="java.sql.*"
	import="com.awesome.*" import="com.awesome.dataAccess.*"
	import="com.awesome.jsonparser.*" import="org.json.*"%>
<%
	//QuotesQueries qq = new QuotesQueries();
	//ArrayList<JSONArray> quotes = new ArrayList<JSONArray>();
	//quotes.add(qq.getAsk());
	//JSONArray quotes = qq.getAsk();
	//System.out.println(quotes.length());
	//System.out.print(quotes.get(1));
%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-long-arrow-right"></i> Live quote feed from Yahoo!
		</h3>
	</div>
	<div class="panel-body">
		<div class="flot-chart-content" id="debug">
			<script type="text/javascript" src="test.js">
			</script>
			
		</div>
		<div class="flot-chart">

			<div class="flot-chart-content" id="quoteGraph"></div>
		</div>
		<div class="text-right">
			<a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
		</div>
	</div>
</div>
