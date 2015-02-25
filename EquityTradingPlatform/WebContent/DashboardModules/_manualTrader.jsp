<%@page import="com.awesome.subscriptionController.AddSubscription"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.feeds.*" import="java.sql.*"
	import="java.util.*"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			<span class="glyphicon glyphicon-transfer"></span> Manual Trader
		</h3>
	</div>
	<div class="panel-body">
		<form role="form">
			<div class="form-group">
				<label>Stock</label> <select class="form-control">
					<%
						for (int i = 0; i < md.symbols.size(); i++) {
							out.println("<option>" + md.symbols.get(i) + "</option>");
						}
					%>
				</select>
			</div>
			<div class="form-group">
				<label class="radio-inline"> <input
					id="optionsRadiosInline1" type="radio" checked="checked"
					value="option1" name="optionsRadiosInline"> Buy
				</label> <label class="radio-inline"> <input
					id="optionsRadiosInline2" type="radio" value="option2"
					name="optionsRadiosInline"> Sell 
			</div>
			<div class="form-group">
				<label>Quantity</label> <input class="form-control"></input>
			</div>
			<div class="form-group">
				<label>Price</label><input class="form-control"></input>
			</div>
			<div class="form-group">
				<button class="btn btn-sm btn-success" type="button">Done</button>
			</div>
		</form>
	</div>
</div>
