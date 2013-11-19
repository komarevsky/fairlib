/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.parser;

import com.freebetbot.fairlib.type.MarketData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class MarketDataParser {

    private static final Log LOGGER = LogFactory.getLog(MarketDataParser.class);
    
    /**
     * parses marketData string array and returns list of MarketData objects
     * @param marketData
     * @return list of MarketData
     */
    public static List<MarketData> parseMarketData (String marketData) {
        List<MarketData> result = new ArrayList<MarketData>();
        
        //replace undesired \: before splitting, by safe character
        marketData = marketData.replace("\\:","=");
        String[] markets = marketData.split(":");
        
        //we are starting from 1, because element with index 0 is empty
        for (int i=1; i<markets.length; ++i) {
            String el = markets[i];
                    
            //replace undesired \~ before splitting, by safe character
            el = el.replace("\\~","=");
            String[] marketString = el.split("~");

            if (marketString.length != MarketData.COUNT_OF_FIELDS) {
                LOGGER.warn("incorrect format of market data:\n" + el);
                continue;
            }
            
            MarketData market = new MarketData();
            try {
                market.setMarketId(Integer.parseInt(marketString[0]));
                market.setMarketName(marketString[1]);
                market.setMarketType(marketString[2]);
                market.setMarketStatus(marketString[3]);
                market.setEventDate(new Date(Long.parseLong(marketString[4])));
                market.setMenuPath(marketString[5]);
                market.setEventHierarchy(marketString[6]);
                market.setBetDelay(marketString[7]);
                market.setExchangeId(Integer.parseInt(marketString[8]));
                market.setCountryCode(marketString[9]);
                market.setLastRefresh(new Date(Long.parseLong(marketString[10])));
                market.setNumberOfRunners(Integer.parseInt(marketString[11]));
                market.setNumberOfWinners(Integer.parseInt(marketString[12]));
                market.setTotalAmountMatched(Double.parseDouble(marketString[13]));

                if (marketString[14].equals("Y")) {
                    market.setBspMarket(true);
                } else {
                    market.setBspMarket(false);
                }

                if (marketString[15].equals("Y")) {
                    market.setTurningInPlay(true);
                } else {
                    market.setTurningInPlay(false);
                }
            } catch(Exception ex) {
                String msg = "exception during conversion String array to MarketData:\n" + ex;
                LOGGER.warn(msg);
                market = null;
            }

            if (market != null) {
                result.add(market);
            }
        }
        
        return result;
    }

}
