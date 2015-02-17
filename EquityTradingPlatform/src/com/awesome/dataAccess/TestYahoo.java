package com.awesome.dataAccess;

import java.util.*;

// Get quote data from Yahoo, usage java TestYahoo <stock1> <stock2> ....

public class TestYahoo
{
    public static void main(String[] args) throws Exception
    {
        GetYahooMarketData yahooReader = new GetYahooMarketData();
        String[] quotes = {"VOD.L"};
        //IBMD,"FB","YHOO","AAPL",
        while (true)
        {
            // Get Quotes 
            Map<String, GetYahooMarketData.QuoteData> data = yahooReader.getQuote(quotes);
            for (Map.Entry<String, GetYahooMarketData.QuoteData> entry : 
                 data.entrySet())
            {
            	
            	Thread.sleep(1000);
                GetYahooMarketData.QuoteData quote = entry.getValue();
                System.out.printf ("%s \t[%.2f]\n",
                        entry.getKey(),
                        quote.closePrice);
                
            }
        }
    }
}
