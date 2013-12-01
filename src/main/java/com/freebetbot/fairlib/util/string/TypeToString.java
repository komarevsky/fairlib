package com.freebetbot.fairlib.util.string;

import com.betfair.publicapi.types.exchange.v5.Bet;
import com.betfair.publicapi.types.exchange.v5.CancelBetsResult;
import com.betfair.publicapi.types.exchange.v5.MUBet;
import com.betfair.publicapi.types.exchange.v5.Market;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsResult;
import com.betfair.publicapi.types.global.v3.EventType;

/**
 *
 * @author Siarhei Skavarodkin
 */
class TypeToString {

    public static String eventTypeToString(EventType e) {
        if (e == null) {
            return "event type is null";
        }
        
        String result = "EventType {"
                + "    id             : " + e.getId() + "\n"
                + "    name           : " + e.getName() + "\n"
                + "    exchange id    : " + e.getExchangeId() + "\n"
                + "    next market id : " + e.getNextMarketId() + "\n"
                + "}";
        
        return result;
    }
    
    public static String marketToString(Market market) {
        String result = ""
                ;
         //TODO: output
        return result;
    }

    public static String betToString(Bet bet) {
        String result = ""
                ;
         //TODO: output
        return result;
    }

    public static String placeBetsResultToString(PlaceBetsResult resp) {
        if (resp == null) {
            return "place bets result is null";
        }
        
        String result = ""
                + "bet id: " + resp.getBetId() + "\n"
                + "average price matched: " + resp.getAveragePriceMatched() + "\n"
                + "result code: " + resp.getResultCode().toString() + "\n"
                + "size matched: " + resp.getSizeMatched() + "\n"
                + "success:" + resp.isSuccess();
        
        return result;
    }

    public static String cancelBetsResultToString(CancelBetsResult resp) {
        if (resp == null) {
            return "cancel bets result is null";
        }
        
        String result = ""
                + "bet id: " + resp.getBetId() + "\n"
                + "result code: " + resp.getResultCode().toString() + "\n"
                + "size matched: " + resp.getSizeMatched() + "\n"
                + "size cancelled: " + resp.getSizeCancelled();
        
        return result;
    }

    public static String muBetToString(MUBet resp) {
        if (resp == null) {
            return "MUBet is null";
        }
        
        String result = ""
                + "getAsianLineId:        " + resp.getAsianLineId() + "\n"
                + "getBetCategoryType:    " + resp.getBetCategoryType() + "\n"
                + "getBetId:              " + resp.getBetId() + "\n"
                + "getBetPersistenceType: " + resp.getBetPersistenceType() + "\n"
                + "getBetStatus:          " + resp.getBetStatus() + "\n"
                + "getBetType:            " + resp.getBetType() + "\n"
                + "getBspLiability:       " + resp.getBspLiability() + "\n"
                + "getHandicap:           " + resp.getHandicap() + "\n"
                + "getMarketId:           " + resp.getMarketId() + "\n"
                + "getMatchedDate:        " + resp.getMatchedDate() + "\n"
                + "getPlacedDate:         " + resp.getPlacedDate() + "\n"
                + "getPrice:              " + resp.getPrice() + "\n"
                + "getSelectionId:        " + resp.getSelectionId() + "\n"
                + "getSize:               " + resp.getSize() + "\n"
                + "getTransactionId:      " + resp.getTransactionId() + "\n";
        
        return result;
    }

}
