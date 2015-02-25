<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			MarketDataConsumer md = (MarketDataConsumer) ctx.getAttribute("app");
			md.addSymbol(request.getParameter("symbol"));
		}

		if (request.getParameter("sym") != null) {
			ServletContext ctx = getServletContext();
			MarketDataConsumer md = (MarketDataConsumer) ctx.getAttribute("app");
			md.removeSymbol(request.getParameter("sym"));
		}
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
					<div class="col-lg-6">
						<%@include file="/DashboardModules/_pageHeader.jsp"%>
					</div>
					<div class="col-lg-6">
						<%@include file="/DashboardModules/_newsFeed.jsp"%>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<%@include file="/DashboardModules/_breadcrumb.jsp"%>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<%@include file="/DashboardModules/_quoteGraph.jsp"%>
					</div>
					<%-- <div class="col-lg-6">
						<%@include file="/DashboardModules/_summary.jsp"%>
					</div> --%>
				</div>
				<!-- /.row -->

				<div class="row">
					<div>
						<div class="col-lg-8">
							<%@include file="/DashboardModules/_tradePanel.jsp"%>
						</div>
						<div class="col-lg-4">
						<%@include file="/DashboardModules/_mangeStrategies.jsp"%>
							<%@include file="/DashboardModules/_manualTrader.jsp"%>
						</div>
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
