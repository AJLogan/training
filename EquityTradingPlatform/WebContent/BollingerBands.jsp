<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.awesome.dataAccess.GetYahooMarketData" import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bollinger Bands</title>
</head>
<body>

	<table class="table table-hover" >
		<tr>
			<th>Ticker</th>
			<th>Close Price</th>
		</tr>
		<%
		  GetYahooMarketData yahooReader = new GetYahooMarketData();
        String[] quotes = {"IBMD"};
        //IBMD,"FB","YHOO","AAPL",
        while (true)
        {
            // Get Quotes 
            Map<String, GetYahooMarketData.QuoteData> data = yahooReader.getQuote(quotes);
            for (Map.Entry<String, GetYahooMarketData.QuoteData> entry : 
                 data.entrySet())
            {
            	//Thread.sleep(1000);
                GetYahooMarketData.QuoteData quote = entry.getValue();
 //               System.out.printf ("%s \t[%.2f]\n",
 //                       entry.getKey(),
 //                       quote.closePrice);
                out.print("<tr>");
    			out.println("<td>"+ entry.getKey()+"</td><td>"
    					+ quote.closePrice + "</td>");
    			out.print("</tr>");
            }
        }
		%>
		</table>
</body>
</html>