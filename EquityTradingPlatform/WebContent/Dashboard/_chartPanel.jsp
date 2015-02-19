<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="java.sql.*"
	import="com.awesome.*" import="com.awesome.dataAccess.*"
	import="com.awesome.jsonparser.*"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-money fa-fw"></i> Prices Over Time
		</h3>
	</div>
	<div class="panel-body">
	<%
			try {
				com.awesome.dataAccess.QuotesQueries qq = new com.awesome.dataAccess.QuotesQueries();
				ResultSet rs2 = qq.getAskPrice();
				while (rs2.next()) {
					QuoteToJson qtj = new QuoteToJson();
					out.println(qtj.convert(rs2).toString());//assign to javascript variable
				}// while

			} catch (SQLException e) {
				throw e;
			}// catch
		%>




	</div>
</div>

<script>
	
</script>