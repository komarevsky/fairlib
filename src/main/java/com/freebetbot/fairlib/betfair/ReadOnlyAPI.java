package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.types.exchange.v5.ArrayOfBetId;
import com.betfair.publicapi.types.exchange.v5.ArrayOfCountryCode;
import com.betfair.publicapi.types.exchange.v5.ArrayOfInt;
import com.betfair.publicapi.types.exchange.v5.BetStatusEnum;
import com.betfair.publicapi.types.exchange.v5.BetsOrderByEnum;
import com.betfair.publicapi.types.exchange.v5.GetAllMarketsResp;
import com.betfair.publicapi.types.exchange.v5.GetBetResp;
import com.betfair.publicapi.types.exchange.v5.GetCompleteMarketPricesCompressedResp;
import com.betfair.publicapi.types.exchange.v5.GetMUBetsResp;
import com.betfair.publicapi.types.exchange.v5.GetMarketResp;
import com.betfair.publicapi.types.exchange.v5.SortOrderEnum;
import com.betfair.publicapi.types.global.v3.GetEventTypesResp;
import com.freebetbot.fairlib.util.HeaderChecker;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class ReadOnlyAPI {
    
    private static final Log LOGGER = LogFactory.getLog(ReadOnlyAPI.class);
    
    /**
     * 
     * @param locale optional(can be empty or null)
     * @return 
     */
    public static GetEventTypesResp getAllEvents(String locale) {
        GetEventTypesResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.getAllEventTypes(locale);
            if (HeaderChecker.isGetEventTypesResponseOk(result)) {
                SessionManager.setRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error("getAllEvents failure", ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
    
    /**
     * 
     * @param locale optional(can be empty or null)
     * @param eventIds optional(can be empty or null)
     * @param countries optional(can be empty or null)
     * @param fromDate optional(can be empty or null)
     * @param toDate optional(can be empty or null)
     * @return 
     */
    public static GetAllMarketsResp getAllMarkets(
            String locale,
            ArrayOfInt eventIds,
            ArrayOfCountryCode countries,
            XMLGregorianCalendar fromDate,
            XMLGregorianCalendar toDate) {
        GetAllMarketsResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.getAllMarkets(locale, eventIds, countries, fromDate, toDate);
            if (HeaderChecker.isGetAllMarketsResponseOk(result)) {
                SessionManager.setExRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error("getAllMarkets failure", ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
    
    /**
     * 
     * @param marketId
     * @param includeCouponLinks optional(can be null)
     * @param locale optional(can be empty or null)
     * @return 
     */
    public static GetMarketResp getMarket(
            int marketId,
            Boolean includeCouponLinks,
            String locale) {
        GetMarketResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.getMarket(marketId, includeCouponLinks, locale);
            if (HeaderChecker.isGetMarketResponseOk(result)) {
                SessionManager.setExRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error("getMarket failure", ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
    
    /**
     * 
     * @param marketId
     * @param currencyCode optional(can be empty or null)
     * @return 
     */
    public static GetCompleteMarketPricesCompressedResp getCompleteMarketPricesCompressed(
            int marketId, String currencyCode) {
        GetCompleteMarketPricesCompressedResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.getCompleteMarketPricesCompressed(marketId, currencyCode);
            if (HeaderChecker.isGetCompleteMarketPricesCompressedResponseOk(result)) {
                SessionManager.setExRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error("getCompleteMarketPricesCompressed failure", ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
    
    /**
     * 
     * @param betId
     * @param locale optional(can be empty or null)
     * @return 
     */
    public static GetBetResp getBet(long betId, String locale) {
        GetBetResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.getBet(betId, locale);
            if (HeaderChecker.isGetBetResponseOk(result)) {
                SessionManager.setExRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error("getBet failure", ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
    
    /**
     * 
     * @param betIds optional(can be null)
     * @param betStatus
     * @param excludeLastSecond optional(can be null)
     * @param marketId optional(can be null)
     * @param matchedSince optional(can be null)
     * @param orderBy
     * @param recordCount
     * @param sortOrder
     * @param startRecord
     * @return 
     */
    public static GetMUBetsResp getMatchedAndUnmatchedBets(
            ArrayOfBetId betIds,
            BetStatusEnum betStatus,
            Boolean excludeLastSecond,
            Integer marketId,
            XMLGregorianCalendar matchedSince,
            BetsOrderByEnum orderBy,
            Integer recordCount,
            SortOrderEnum sortOrder,
            Integer startRecord
            ) {
        GetMUBetsResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.getMatchedAndUnmatchedBets(betIds, betStatus, 
                    excludeLastSecond, marketId, matchedSince, orderBy, 
                    recordCount, sortOrder, startRecord);
            if (HeaderChecker.isGetMUBetsResponseOk(result)) {
                SessionManager.setExRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error("getMatchedAndUnmatchedBets failure", ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
    
    public static GetMUBetsResp getMatchedAndUnmatchedBets(int marketId) {
        final int MAXIMUM_RECORD_COUNT_TO_RETURN = 200; //max value according API documentation
        return getMatchedAndUnmatchedBets(null, BetStatusEnum.MU, Boolean.FALSE,
                marketId, null, BetsOrderByEnum.PLACED_DATE, MAXIMUM_RECORD_COUNT_TO_RETURN,
                SortOrderEnum.ASC, 0);
    }

}
