<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.awesome.dataAccess.*"
	import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Two Moving Averages</title>
</head>
<body>
	<table border="2">
		<tr>
			<th>Key</th>
			<th>BidSize</th>
			<th>Bid Price</th>
			<th>Ask Price</th>
			<th>Ask Size</th>
		</tr>
		<%
			GetData yahooReader = new GetData();
			String[] quotes = { "YHOO", "AAPL" };
			//while (true) {
				// Get Quotes
				Map<String, GetData.Quote> data = yahooReader
						.getQuote(quotes);
				for (Map.Entry<String, GetData.Quote> entry : data
						.entrySet()) {
					GetData.Quote quote = entry.getValue();
					out.println("<tr>");
					System.out.printf("%s [%dx%.2f] x [%.2fx%d]\n",
							entry.getKey(), quote.bidSize, quote.bidPrice,
							quote.askPrice, quote.askSize);

					out.print("<td>" + entry.getKey() + "</td>" + "<td>"
							+ quote.bidSize + "</td>" + "<td>" + quote.askPrice
							+ "</td>" + "<td>" + quote.askSize + "</td>");
					out.println("</tr>");
				}
			//}
		%>
	</table>
</body>
</html>