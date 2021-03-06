<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.feeds.*" import="java.sql.*"
	import="com.awesome.*" import="com.awesome.graphing.*"
	import="java.util.*" import="com.awesome.dataAccess.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/DashboardModules/_head.jsp"%>
</head>
<body>
	<%
	
		if (request.getParameter("symbol") != null) {
			session.setAttribute("sym", request.getParameter("symbol"));
			ServletContext ctx = getServletContext();
			MarketDataConsumer md = (MarketDataConsumer) ctx
					.getAttribute("app");
			md.addSymbol(request.getParameter("symbol"));
		}

		if (request.getParameter("sym") != null) {
			ServletContext ctx = getServletContext();
			MarketDataConsumer md = (MarketDataConsumer) ctx
					.getAttribute("app");
			if(md.symbols.size()>1)
			{
				md.removeSymbol(request.getParameter("sym"));
			}
		}
		//NEW CODE
		
	%>
	<div id="wrapper">
		<%@include file="/DashboardModules/_navigation.jsp"%>
		<!-- Top Menu Items -->
		<%-- <%@include file="/DashboardModules/_topMenu.jsp"%> --%>

		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<%@include file="/DashboardModules/_sidebarElements.jsp"%>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-8">
						<%@include file="/DashboardModules/_pageHeader.jsp"%>
					</div>
					<div class="col-lg-4">
						<%@include file="/DashboardModules/_bloombergTV.jsp"%>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div id="test"></div>
						<%@include file="/DashboardModules/_breadcrumb.jsp"%>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-8">
						<%@include file="/DashboardModules/_quoteGraph.jsp"%>
						<%@include file="/DashboardModules/_tmaGraph.jsp"%>
					</div>
					<div class="col-lg-4">
						<%@include file="/DashboardModules/_manualTrader.jsp"%>
						<%@include file="/DashboardModules/_tradePanel.jsp"%>
						<%@include file="/DashboardModules/_newsFeed.jsp"%>
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<%@include file="/DashboardModules/_jsResources.jsp"%>

</body>
</html>
