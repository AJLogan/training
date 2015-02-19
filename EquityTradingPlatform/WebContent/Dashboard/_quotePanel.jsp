<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="java.sql.*"
	import="com.awesome.*" import="com.awesome.dataAccess.*"
	import="com.awesome.jsonparser.*"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-money fa-fw"></i> Quote Panel
		</h3>
	</div>
	<div class="panel-body">
		<div class="table-responsive">
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<th>ticker</th>
					<th>askSize</th>
					<th>askPrice</th>
					<th>bidPrice</th>
					<th>bidSize</th>
					<th>closePrice</th>
					<th>volume</th>
				</tr>
				<%
					Connection quotePanelCN = DatabaseUtils.setupDB();
					try {
						QuotesQueries qq = new QuotesQueries();
						ResultSet rs2 = qq.getAskPrice(quotePanelCN);
						while (rs2.next()) {
							QuoteToJson qtj = new QuoteToJson();
							out.println(qtj.convert(rs2).toString());
						}// while
					} catch (SQLException e) {
						throw e;
					}// catch
					finally {
						if (quotePanelCN != null) {
							quotePanelCN.close();
						}
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

<script>
	
</script>