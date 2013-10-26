/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.util.string;

import com.betfair.publicapi.types.exchange.v5.Bet;
import com.betfair.publicapi.types.exchange.v5.CancelBetsResp;
import com.betfair.publicapi.types.exchange.v5.CancelBetsResult;
import com.betfair.publicapi.types.exchange.v5.GetAccountFundsResp;
import com.betfair.publicapi.types.exchange.v5.GetAllMarketsResp;
import com.betfair.publicapi.types.exchange.v5.GetBetResp;
import com.betfair.publicapi.types.exchange.v5.GetCompleteMarketPricesCompressedResp;
import com.betfair.publicapi.types.exchange.v5.GetMUBetsResp;
import com.betfair.publicapi.types.exchange.v5.GetMarketResp;
import com.betfair.publicapi.types.exchange.v5.MUBet;
import com.betfair.publicapi.types.exchange.v5.Market;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsResp;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsResult;
import com.betfair.publicapi.types.global.v3.APIRequestHeader;
import com.betfair.publicapi.types.global.v3.APIResponseHeader;
import com.betfair.publicapi.types.global.v3.EventType;
import com.betfair.publicapi.types.global.v3.GetEventTypesResp;
import com.betfair.publicapi.types.global.v3.KeepAliveResp;
import com.betfair.publicapi.types.global.v3.LoginResp;
import com.betfair.publicapi.types.global.v3.LogoutResp;
import com.betfair.publicapi.types.global.v3.ViewProfileResp;
import java.util.List;

/**
 * Returns string-presentation of different classes
 * @author Siarhei Skavarodkin
 */
public class ToStringUtil {

    //================================
    // HEADER TO STRING
    //================================
    
    public static String headerToString(com.betfair.publicapi.types.exchange.v5.APIResponseHeader header) {
        return HeaderToString.headerToString(header);
    }

    public static String headerToString(APIResponseHeader header) {
        return HeaderToString.headerToString(header);
    }
    
    public static String headerToString(APIRequestHeader header) {
        return HeaderToString.headerToString(header);
    }
    
    public static String headerToString(com.betfair.publicapi.types.exchange.v5.APIRequestHeader header) {
        return HeaderToString.headerToString(header);
    }

    //================================
    // TYPE TO STRING
    //================================

    public static String eventTypeToString(EventType e) {
        return TypeToString.eventTypeToString(e);
    }

    public static String marketToString(Market market) {
        return TypeToString.marketToString(market);
    }
    
    public static String betToString(Bet bet) {
        return TypeToString.betToString(bet);
    }
    
    public static String placeBetsResultToString(PlaceBetsResult resp) {
        return TypeToString.placeBetsResultToString(resp);
    }

    public static String cancelBetsResultToString(CancelBetsResult resp) {
        return TypeToString.cancelBetsResultToString(resp);
    }

    public static String muBetToString(MUBet resp) {
        return TypeToString.muBetToString(resp);
    }
    
    //================================
    // RESPONCE TO STRING
    //================================
    
    public static String loginToString(LoginResp resp) {
        return ResponseToString.loginToString(resp);
    }
        
    public static String logoutToString(LogoutResp resp) {
        return ResponseToString.logoutToString(resp);
    }
    
    public static String keepAliveToString(KeepAliveResp resp) {
        return ResponseToString.keepAliveToString(resp);
    }
    
    public static String getAccountFundsToString(GetAccountFundsResp resp) {
        return ResponseToString.getAccountFundsToString(resp);
    }
    
    public static String viewProfileToString(ViewProfileResp resp) {
        return ResponseToString.viewProfileToString(resp);
    }
    
    public static String getEventTypesToString(GetEventTypesResp resp) {
        return ResponseToString.getEventTypesToString(resp);
    }

    public static String getAllMarketsToString(GetAllMarketsResp resp) {
        return ResponseToString.getAllMarketsToString(resp);
    }
    
    public static String getMarketToString(GetMarketResp resp) {
        return ResponseToString.getMarketToString(resp);
    }
    
    public static String getCompleteMarketPricesCompressedToString(GetCompleteMarketPricesCompressedResp resp) {
        return ResponseToString.getCompleteMarketPricesCompressedToString(resp);
    }
    
    public static String getBetToString(GetBetResp resp) {
        return ResponseToString.getBetToString(resp);
    }
        
    public static String placeBetsToString(PlaceBetsResp resp) {
        return ResponseToString.placeBetsToString(resp);
    }

    public static String cancelBetsToString(CancelBetsResp resp) {
        return ResponseToString.cancelBetsToString(resp);
    }

    public static String GetMUBetsToString(GetMUBetsResp resp) {
        return ResponseToString.GetMUBetsToString(resp);
    }
    
}
