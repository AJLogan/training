//package com.awesome.dataAccess;
//import java.util.*;
//
////Get quote data from Yahoo, usage java TestYahoo <stock1> <stock2> ....
//
//public class TestYahoo
//{
// public static void main(String[] args) throws Exception
// {
//     GetYahooMarketData yahooReader = new GetYahooMarketData();
//     String [] quotes = {"IBMD"};
//     while (true)
//     {
//         // Get Quotes
//         Map<String, GetYahooMarketData.QuoteData> data = yahooReader.getQuote(quotes);
//         
//         for (Map.Entry<String, GetYahooMarketData.QuoteData> entry : 
//              data.entrySet())
//         {
//             GetYahooMarketData.QuoteData quote = entry.getValue();
//             System.out.printf ("%s [%.2f] x [%d]\n",
//                                entry.getKey(),
//                                quote.closePrice,
//                                quote.volume);
//         }
//     }
// }
//}
