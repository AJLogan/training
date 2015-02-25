<%@page import="com.awesome.subscriptionController.AddSubscription"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.feeds.*" import="java.sql.*"
	import="java.util.*"%>

<div class="panel panel-yellow">
	<div class="panel-heading">
		<h3 class="panel-title">Manage Subscriptions</h3>
	</div>
	<div class="panel-body">

		<!-- HTML for SEARCH BAR -->
		<div id="tfheader">
			<form action="index.jsp" method="post">
				<div class="form-group input-group">
					<input id="symbol" type="text" class="form-control"> 
					<span
						class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</form>
			
			<!-- The table to show what has been selected -->
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Stock</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>X /index.jsp</td>
						</tr>
						<tr>
							<td><%if(session.getAttribute("sym") != null)
								{
									out.print(session.getAttribute("sym"));
								}%></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>