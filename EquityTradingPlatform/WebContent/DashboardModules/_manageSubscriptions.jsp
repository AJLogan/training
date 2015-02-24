<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.feeds.*" import="java.sql.*"
	import="java.util.*"%>

<div class="panel panel-yellow">
	<div class="panel-heading">
		<h3 class="panel-title">Select Symbol</h3>
	</div>
	<div class="panel-body">

		<!-- HTML for SEARCH BAR -->
		<div id="tfheader">

			<form action="action_page.php">
				<input type="url" list="quote_list" name="link" size="18"
					maxlength="80"><br>
			</form>
			<br>

			<datalist id="quote_list">
				<!-- Connect to the database here -->
				<option label="Microsoft" value="http://www.microsoft.com">
				<option label="Google" value="http://www.google.com">
				<option label="W3Schools" value="http://www.w3schools.com">
			</datalist>



			<!-- Button says 'Submit Query' -->
			<input type="submit">

			<div class="table-responsive">
				<table class="table table-bordered table-hover table-striped">
					<tr>
						<th class="absorbing-column">Symbol</th>
						<th class="absorbing-column">Name</th>
					</tr>
				</table>
			</div>
		</div>


		<!-- This is where the new symbols will go 
		connect to yahoo finance to get list of all the symbols-->
		<%
			//ServletContext ctx = getServletContext();
			//MarketDataConsumer md = (MarketDataConsumer) ctx.getAttribute("app");
			//md.addSymbol("FB");
			//out.println(md.symbols.toString());
			//md.removeSymbol("FB");
			//out.println(md.symbols.toString());
		%>
	</div>
</div>