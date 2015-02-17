<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.dataAccess.GetYahooMarketData"
	import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/jquery-2.1.3.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bollinger Bands</title>
</head>
<body>

	<table class="table table-hover">
		<tr>
			<th>Ticker</th>
			<th>Close Price</th>
		</tr>
		<%
			GetYahooMarketData yahooReader = new GetYahooMarketData();
			String[] quotes = { "IBMD", "FB","YHOO","AAPL","VOD.L"};
			//IBMD,"FB","YHOO","AAPL",
		
				Map<String, GetYahooMarketData.QuoteData> data = yahooReader
						.getQuote(quotes);
				
				//enhanced for
				for (Map.Entry<String, GetYahooMarketData.QuoteData> entry : data
						.entrySet()) {
					//Thread.sleep(1000);
					
					GetYahooMarketData.QuoteData quote = entry.getValue();
					out.print("<tr>");
					out.println("<td>" + entry.getKey() + "</td><td>"
							+ quote.closePrice + "</td>");
					out.print("</tr>");
				}
		//	}
		%>
	</table>
</body>
</html>