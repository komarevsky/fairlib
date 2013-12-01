package com.freebetbot.fairlib.betfair;

import com.freebetbot.fairlib.util.Waiter;

/**
 * according to http://bdp.betfair.com/index.php?option=com_content&task=view&id=36&Itemid=62
 * some requests have time limitations.
 * Here is list of time limitations for requests.
 * @author Siarhei Skavarodkin
 */
public class BFWaiter extends Waiter {
    
    /**
     * login operation is allowed 24 times/minute
     */
    public static final long LOGIN = 2500; //2.5 sec
    
    /**
     * getMarket operation is allowed 5 times/minute
     */
    public static final long GET_MARKET = ONE_SECOND * 12;

    /**
     * getMarketTradedVolume operation is allowed 60 times/minute
     */
    public static final long GET_MARKET_TRADED_VOLUME = ONE_SECOND;
    
    /**
     * getMarketPrices operation is allowed 10 times/minute
     */
    public static final long GET_MARKET_PRICES = ONE_SECOND * 6;
    
    /**
     * getAccountFunds operation is allowed 12 times/minute
     */
    public static final long GET_ACCOUNT_FUNDS = ONE_SECOND * 5;
    
    /**
     * getBet operation is allowed 60 times/minute
     */
    public static final long GET_BET = ONE_SECOND;

    /**
     * getCompleteMarketPricesCompressed operation is allowed 60 times/minute
     */
    public static final long GET_COMPLETE_MARKET_PRICES_COMPRESSED = ONE_SECOND;

    /**
     * placeBets operation is allowed 100 times/minute
     */
    public static final long PLACE_BETS = 600;
    
    /**
     * cancelBets operation is not limited
     */
    public static final long CANCEL_BETS = 0;
    
    /**
     * getMatchedAndUnmatchedBets operation is allowed 60 times/minute
     */
    public static final long GET_MATCHED_AND_UNMATCHED_BETS = ONE_SECOND;


}
