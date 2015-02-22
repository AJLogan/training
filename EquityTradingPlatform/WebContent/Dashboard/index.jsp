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
		<%@include file="_topMenu.jsp"%>

		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<%@include file="_sidebarElements.jsp"%>

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
					<div class="col-lg-6">
						<%@include file="_quoteGraph.jsp"%>
					</div>
					<div class="col-lg-6">
						<%@include file="_summary.jsp"%>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-6">
						<%@include file="_mangeStrategies.jsp"%>
					</div>
					<div class="col-lg-6">
						<div>
							<%@include file="_equity.jsp"%>

						</div>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div>
						<div class="col-lg-8">
							<%@include file="_tradePanel.jsp"%>
						</div>
						<div class="col-lg-4">
							<%@include file="_manualTrader.jsp"%>
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
	<%@include file="_jsResources.jsp"%>
</body>
</html>