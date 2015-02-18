package com.awesome.dataAccess;

import java.util.Map;

public class TwoMovingAverages
{
 public static void main(String[] args) throws Exception
 {
     GettData yahooReader = new GettData();
     String [] quotes = {"YHOO", "AAPL"};
     while (true)
     {
         // Get Quotes
         Map<String, GettData.Quote> data = 
                                                 yahooReader.getQuote(quotes);
         for (Map.Entry<String, GettData.Quote> entry : 
              data.entrySet())
         {
             GettData.Quote quote = entry.getValue();
             System.out.printf ("%s [%dx%.2f] x [%.2fx%d]\n",
                                entry.getKey(),
                                quote.bidSize,
                                quote.bidPrice,
                                quote.askPrice,
                                quote.askSize);
         }
     }
 }
}
