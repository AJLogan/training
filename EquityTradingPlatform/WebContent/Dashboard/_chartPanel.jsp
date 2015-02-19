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
			Connection chartPanelCN = DatabaseUtils.setupDB();
			try {
				QuotesQueries qq = new QuotesQueries();
				ResultSet rs2 = qq.getAskPrice(chartPanelCN);
				while (rs2.next()) {
					QuoteToJson qtj = new QuoteToJson();
					out.println(qtj.convert(rs2).toString());//assign to javascript variable
				}// while
			} catch (SQLException e) {
				throw e;
			}// catch
			finally {
				if (chartPanelCN != null) {
					chartPanelCN.close();
				}
			}
		%>




	</div>
</div>

<script>
	
</script>