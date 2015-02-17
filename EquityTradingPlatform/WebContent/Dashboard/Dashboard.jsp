<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="_head.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@include file="_navigation.jsp"%>
		<!-- Top Menu Items -->
		<ul class="nav navbar-right top-nav">
			<%@include file="_messagesDropDown.jsp"%>
			<%@include file="_alertsDropDown.jsp"%>
			<%@include file="_userMenu.jsp"%>
		</ul>
		
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<%@include file="_sidebarElements.jsp"%>
		</div>
		
		<!-- /.navbar-collapse -->
		</nav>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<%@include file="_pageHeader.jsp"%>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<%@include file="_recentFeatures.jsp"%>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-3 col-md-6">
						<%@include file="_commentsLabeledButton.jsp"%>
					</div>
					<div class="col-lg-3 col-md-6">
						<%@include file="_tasksLabeledButton.jsp"%>
					</div>
					<div class="col-lg-3 col-md-6">
						<%@include file="_ordersLabeledButton.jsp"%>
					</div>
					<div class="col-lg-3 col-md-6">
						<%@include file="_supportTicketLabeledButton.jsp"%>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<%@include file="_areaChartPanel.jsp"%>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-4">
						<%@include file="_donughtChartPanel.jsp"%>
					</div>

					<div class="col-lg-4">
						<%@include file="_taskPanel.jsp"%>
					</div>

					<div class="col-lg-4">
						<%@include file="_tradePanel.jsp"%>
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<%@include file="_jsResources.jsp"%>
</body>
</html>