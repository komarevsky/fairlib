package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.types.exchange.v5.ArrayOfBetId;
import com.betfair.publicapi.types.exchange.v5.ArrayOfCancelBets;
import com.betfair.publicapi.types.exchange.v5.ArrayOfCountryCode;
import com.betfair.publicapi.types.exchange.v5.ArrayOfInt;
import com.betfair.publicapi.types.exchange.v5.ArrayOfPlaceBets;
import com.betfair.publicapi.types.exchange.v5.BetStatusEnum;
import com.betfair.publicapi.types.exchange.v5.BetsOrderByEnum;
import com.betfair.publicapi.types.exchange.v5.CancelBetsReq;
import com.betfair.publicapi.types.exchange.v5.CancelBetsResp;
import com.betfair.publicapi.types.exchange.v5.GetAccountFundsReq;
import com.betfair.publicapi.types.exchange.v5.GetAccountFundsResp;
import com.betfair.publicapi.types.exchange.v5.GetAllMarketsReq;
import com.betfair.publicapi.types.exchange.v5.GetAllMarketsResp;
import com.betfair.publicapi.types.exchange.v5.GetBetReq;
import com.betfair.publicapi.types.exchange.v5.GetBetResp;
import com.betfair.publicapi.types.exchange.v5.GetCompleteMarketPricesCompressedReq;
import com.betfair.publicapi.types.exchange.v5.GetCompleteMarketPricesCompressedResp;
import com.betfair.publicapi.types.exchange.v5.GetMUBetsReq;
import com.betfair.publicapi.types.exchange.v5.GetMUBetsResp;
import com.betfair.publicapi.types.exchange.v5.GetMarketReq;
import com.betfair.publicapi.types.exchange.v5.GetMarketResp;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsReq;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsResp;
import com.betfair.publicapi.types.exchange.v5.SortOrderEnum;
import com.betfair.publicapi.types.global.v3.GetEventTypesReq;
import com.betfair.publicapi.types.global.v3.GetEventTypesResp;
import com.betfair.publicapi.types.global.v3.KeepAliveReq;
import com.betfair.publicapi.types.global.v3.KeepAliveResp;
import com.betfair.publicapi.types.global.v3.LoginReq;
import com.betfair.publicapi.types.global.v3.LoginResp;
import com.betfair.publicapi.types.global.v3.LogoutReq;
import com.betfair.publicapi.types.global.v3.LogoutResp;
import com.betfair.publicapi.types.global.v3.ViewProfileReq;
import com.betfair.publicapi.types.global.v3.ViewProfileResp;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Siarhei Skavarodkin
 */
class BFOperation {

    //********************** GENERAL *****************************************
    
    private static final Date lastCallLogin = new Date();
    public static LoginResp login(String user, String password, int productId) {
        synchronized(lastCallLogin) {
            BFWaiter.waitForSpecifiedTime(BFWaiter.LOGIN, lastCallLogin);
            
            LoginReq req = new LoginReq();
            req.setUsername(user);
            req.setPassword(password);
            req.setProductId(productId);
            req.setVendorSoftwareId(0);
            
            lastCallLogin.setTime(new Date().getTime());
            return BFGlobal.get().login(req);
        }
    }
    
    private static final Date lastCallLogout = new Date();
    public static LogoutResp logout() {
        synchronized(lastCallLogout) {
            LogoutReq req = new LogoutReq();
            req.setHeader(SessionManager.getReqHeader());

            return BFGlobal.get().logout(req);
        }
    }
    
    private static final Date lastCallKeepAlive = new Date();
    public static KeepAliveResp keepAlive() {
        synchronized (lastCallKeepAlive) {
            KeepAliveReq req = new KeepAliveReq();
            req.setHeader(SessionManager.getReqHeader());

            return BFGlobal.get().keepAlive(req);
        }
    }
    
    //********************** END OF GENERAL **********************************
    
    //********************** ACCOUNT MANAGEMENT ******************************
    
    private static final Date lastCallViewProfile = new Date();
    public static ViewProfileResp viewProfile() {
        synchronized(lastCallViewProfile) {
            ViewProfileReq req = new ViewProfileReq();
            req.setHeader(SessionManager.getReqHeader());

            return BFGlobal.get().viewProfile(req);
        }
    }
    
    private static final Date lastCallGetAccountFunds = new Date();
    public static GetAccountFundsResp getAccountFunds() {
        synchronized(lastCallGetAccountFunds) {
            BFWaiter.waitForSpecifiedTime(BFWaiter.GET_ACCOUNT_FUNDS, lastCallGetAccountFunds);

            GetAccountFundsReq req = new GetAccountFundsReq();
            req.setHeader(SessionManager.getExReqHeader());

            lastCallGetAccountFunds.setTime(new Date().getTime());
            return BFExchange.get().getAccountFunds(req);
        }
    }
    
    //********************** END OF ACCOUNT MANAGEMENT ***********************
    
    //********************** READ ONLY ***************************************
    
    private static final Date lastCallGetAllEventTypes = new Date();
    public static GetEventTypesResp getAllEventTypes(String locale) {
        synchronized(lastCallGetAllEventTypes) {
            GetEventTypesReq req = new GetEventTypesReq();
            req.setHeader(SessionManager.getReqHeader());
            if (locale != null && !locale.isEmpty()) {
                req.setLocale(locale);
            }

            return BFGlobal.get().getAllEventTypes(req);
        }
    }
    
    private static final Date lastCallGetAllMarkets = new Date();
    public static GetAllMarketsResp getAllMarkets(
            String locale,
            ArrayOfInt eventIds,
            ArrayOfCountryCode countries,
            XMLGregorianCalendar fromDate,
            XMLGregorianCalendar toDate) {
        synchronized(lastCallGetAllMarkets) {
            //creation of request
            GetAllMarketsReq req = new GetAllMarketsReq();
            req.setHeader(SessionManager.getExReqHeader());
            
            //locale
            if (locale != null && !locale.isEmpty()) {
                req.setLocale(locale);
            }
            
            //eventIds
            if (eventIds != null && !eventIds.getInt().isEmpty()) {
                req.setEventTypeIds(eventIds);
            }
            
            //countries
            if (countries != null && !countries.getCountry().isEmpty()) {
                req.setCountries(countries);
            }
            
            //fromDate
            if (fromDate != null && fromDate.isValid()) {
                req.setFromDate(fromDate);
            }
            
            //toDate
            if (toDate != null && toDate.isValid()) {
                req.setToDate(toDate);
            }

            //retrieving response with markets
            return BFExchange.get().getAllMarkets(req);
        }
    }
    
    private static final Date lastCallGetMarket = new Date();
    public static GetMarketResp getMarket(
            int marketId,
            Boolean includeCouponLinks,
            String locale) {
        synchronized(lastCallGetMarket) {
            BFWaiter.waitForSpecifiedTime(BFWaiter.GET_MARKET, lastCallGetMarket);

            GetMarketReq req = new GetMarketReq();
            req.setHeader(SessionManager.getExReqHeader());
            req.setMarketId(marketId);
            
            //includeCouponLinks
            if (includeCouponLinks != null) {
                req.setIncludeCouponLinks(includeCouponLinks);
            }
            
            //locale
            if (locale != null && !locale.isEmpty()) {
                req.setLocale(locale);
            }

            lastCallGetMarket.setTime(new Date().getTime());
            return BFExchange.get().getMarket(req);
        }
    }

    private static final Date lastCallGetCompleteMarketPricesCompressed = new Date();
    public static GetCompleteMarketPricesCompressedResp getCompleteMarketPricesCompressed(
            int marketId, String currencyCode) {
        synchronized(lastCallGetCompleteMarketPricesCompressed) {            
            BFWaiter.waitForSpecifiedTime(BFWaiter.GET_COMPLETE_MARKET_PRICES_COMPRESSED, 
                    lastCallGetCompleteMarketPricesCompressed);

            GetCompleteMarketPricesCompressedReq req = new GetCompleteMarketPricesCompressedReq();
            req.setHeader(SessionManager.getExReqHeader());
            req.setMarketId(marketId);
            
            //currencyCode
            if (currencyCode != null && !currencyCode.isEmpty()) {
                req.setCurrencyCode(currencyCode);
            }
            
            lastCallGetCompleteMarketPricesCompressed.setTime(new Date().getTime());
            return BFExchange.get().getCompleteMarketPricesCompressed(req);
        }
    }
    
    
    private static final Date lastCallGetBet = new Date();
    public static GetBetResp getBet(long betId, String locale) {
        synchronized(lastCallGetBet) {
            BFWaiter.waitForSpecifiedTime(BFWaiter.GET_BET, lastCallGetBet);

            GetBetReq req = new GetBetReq();
            req.setHeader(SessionManager.getExReqHeader());
            req.setBetId(betId);
            
            //locale
            if (locale != null && !locale.isEmpty()) {
                req.setLocale(locale);
            }
            
            lastCallGetBet.setTime(new Date().getTime());
            return BFExchange.get().getBet(req);
        }
    }
    
    private static final Date lastCallGetMatchedAndUnmatchedBets = new Date();
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
        synchronized(lastCallGetMatchedAndUnmatchedBets) {
            BFWaiter.waitForSpecifiedTime(BFWaiter.GET_MATCHED_AND_UNMATCHED_BETS, lastCallGetMatchedAndUnmatchedBets);
            
            GetMUBetsReq req = new GetMUBetsReq();
            req.setHeader(SessionManager.getExReqHeader());
            
            //betIds
            if (betIds != null) {
                req.setBetIds(betIds);
            }
            
            req.setBetStatus(betStatus);
            
            //excludeLastSecond
            if (excludeLastSecond != null) {
                req.setExcludeLastSecond(excludeLastSecond);
            }
            
            //marketId
            if (marketId != null) {
                req.setMarketId(marketId);
            }
            
            //matchedSince
            if (matchedSince != null) {
                req.setMatchedSince(matchedSince);
            }
            
            req.setOrderBy(orderBy);
            req.setRecordCount(recordCount);
            req.setSortOrder(sortOrder);
            req.setStartRecord(startRecord);
            
            lastCallGetMatchedAndUnmatchedBets.setTime(new Date().getTime());
            return BFExchange.get().getMUBets(req);
        }
    }
    
    //********************** END OF READ ONLY ********************************
    
    //********************** BET PLACEMENT ***********************************
    
    private static final Date lastCallPlaceBet = new Date();
    public static PlaceBetsResp placeBets(ArrayOfPlaceBets bets) {
        synchronized(lastCallPlaceBet) {
            BFWaiter.waitForSpecifiedTime(BFWaiter.PLACE_BETS, lastCallPlaceBet);

            PlaceBetsReq req = new PlaceBetsReq();
            req.setHeader(SessionManager.getExReqHeader());
            req.setBets(bets);

            lastCallPlaceBet.setTime(new Date().getTime());
            return BFExchange.get().placeBets(req);
        }
    }
    
    private static final Date lastCallCancelBets = new Date();
    public static CancelBetsResp cancelBets(ArrayOfCancelBets bets) {
        synchronized(lastCallCancelBets) {
            BFWaiter.waitForSpecifiedTime(BFWaiter.CANCEL_BETS, lastCallCancelBets);

            CancelBetsReq req = new CancelBetsReq();
            req.setHeader(SessionManager.getExReqHeader());
            req.setBets(bets);

            lastCallCancelBets.setTime(new Date().getTime());
            return BFExchange.get().cancelBets(req);
        }
    }
    //********************** END OF BET PLACEMENT ****************************

}
