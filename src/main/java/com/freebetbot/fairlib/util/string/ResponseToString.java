package com.freebetbot.fairlib.util.string;

import com.betfair.publicapi.types.exchange.v5.CancelBetsResp;
import com.betfair.publicapi.types.exchange.v5.CancelBetsResult;
import com.betfair.publicapi.types.exchange.v5.GetAccountFundsResp;
import com.betfair.publicapi.types.exchange.v5.GetAllMarketsResp;
import com.betfair.publicapi.types.exchange.v5.GetBetResp;
import com.betfair.publicapi.types.exchange.v5.GetCompleteMarketPricesCompressedResp;
import com.betfair.publicapi.types.exchange.v5.GetMUBetsResp;
import com.betfair.publicapi.types.exchange.v5.GetMarketResp;
import com.betfair.publicapi.types.exchange.v5.MUBet;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsResp;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsResult;
import com.betfair.publicapi.types.global.v3.EventType;
import com.betfair.publicapi.types.global.v3.GetEventTypesResp;
import com.betfair.publicapi.types.global.v3.KeepAliveResp;
import com.betfair.publicapi.types.global.v3.LoginResp;
import com.betfair.publicapi.types.global.v3.LogoutResp;
import com.betfair.publicapi.types.global.v3.ViewProfileResp;
import java.util.List;

/**
 * converts betfair responses and complex types to strings
 * @author Siarhei Skavarodkin
 */
class ResponseToString {
    
    public static String loginToString(LoginResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "currency: " + resp.getCurrency() + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n"
                + "valid until: " + resp.getValidUntil().toString();
        
        return result;
    }
        
    public static String logoutToString(LogoutResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n"
                + "error code: " + resp.getErrorCode().toString();
        
        return result;
    }
    
    public static String keepAliveToString(KeepAliveResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "api version: " + resp.getApiVersion() + "\n"
                + "minor error code: " + resp.getMinorErrorCode();
        
        return result;
    }
    
    public static String getAccountFundsToString(GetAccountFundsResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "available balance: " + resp.getAvailBalance() + "\n"
                + "balance: " + resp.getBalance() + "\n"
                + "commision retain: " + resp.getCommissionRetain() + "\n"
                + "credit limit: " + resp.getCreditLimit() + "\n"
                + "current betfair points: " + resp.getCurrentBetfairPoints() + "\n"
                + "expo limit: " + resp.getExpoLimit() + "\n"
                + "exposure: " + resp.getExposure() + "\n"
                + "holiday available: " + resp.getHolidaysAvailable() + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n"
                + "next discount: " + resp.getNextDiscount() + "\n"
                + "withdraw balance: " + resp.getWithdrawBalance() + "\n"
                + "error code: " + resp.getErrorCode().toString();
        
        return result;
    }
    
    public static String viewProfileToString(ViewProfileResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "title: " + resp.getTitle().toString() + "\n"
                + "first name: " + resp.getFirstName() + "\n"
                + "surname: " + resp.getSurname() + "\n"
                + "user name: " + resp.getUserName() + "\n"
                + "forum name: " + resp.getForumName() + "\n"
                + "address1: " + resp.getAddress1() + "\n"
                + "address2: " + resp.getAddress2() + "\n"
                + "address3: " + resp.getAddress3() + "\n"
                + "town city: " + resp.getTownCity() + "\n"
                + "country state: " + resp.getCountyState() + "\n"
                + "post code: " + resp.getPostCode() + "\n"
                + "country of residence: " + resp.getCountryOfResidence() + "\n"
                + "home telephone: " + resp.getHomeTelephone() + "\n"
                + "work telephone: " + resp.getWorkTelephone() + "\n"
                + "mobile telephone: " + resp.getMobileTelephone() + "\n"
                + "email address: " + resp.getEmailAddress() + "\n"
                + "time zone: " + resp.getTimeZone() + "\n"
                + "currency: " + resp.getCurrency() + "\n"
                + "gamcare limit: " + resp.getGamcareLimit() + "\n"
                + "gamcare frequency: " + resp.getGamcareFrequency() + "\n"
                + "gamcare lost limit: " + resp.getGamcareLossLimit() + "\n"
                + "gamcare lost limit frequency: " + resp.getGamcareLossLimitFrequency() + "\n"
                + "gamcare update date: " + resp.getGamcareUpdateDate();
        
        return result;
    }
    
    public static String getEventTypesToString(GetEventTypesResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        List<EventType> list = resp.getEventTypeItems().getEventType();
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "events count: " + list.size();
        
        if (!list.isEmpty()) {
            for (EventType e : list) {
                result += "\n" + ToStringUtil.eventTypeToString(e);
            }
        }
        
        return result;
    }
        
    public static String getAllMarketsToString(GetAllMarketsResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "market data:\n" + resp.getMarketData();
        
        return result;
    }
    
    public static String getMarketToString(GetMarketResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "market:\n" + ToStringUtil.marketToString(resp.getMarket());
        
        return result;
    }
        
    public static String getCompleteMarketPricesCompressedToString(GetCompleteMarketPricesCompressedResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "currency code: " + resp.getCurrencyCode() + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n"
                + "complete market prices:\n" + resp.getCompleteMarketPrices();
        
        return result;
    }
    
    public static String getBetToString(GetBetResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "bet:\n" + ToStringUtil.betToString(resp.getBet());
        
        return result;
    }
        
    public static String placeBetsToString(PlaceBetsResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n";
        
        String betResults;
        if (resp.getBetResults() == null) {
            betResults = "bet results is null";
        } else {
            List<PlaceBetsResult> list = resp.getBetResults().getPlaceBetsResult();
            if (list == null) {
                betResults = "bet results list is null";
            } else {
                betResults = "bet results size: " + list.size();
                for (PlaceBetsResult p : list) {
                    betResults += "\n" + ToStringUtil.placeBetsResultToString(p);
                }
            }
        }
        
        result += betResults;
        
        return result;
    }

    public static String cancelBetsToString(CancelBetsResp resp) {
        if (resp == null) {
            return "response is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n";
        
        String betResults;
        if (resp.getBetResults() == null) {
            betResults = "bet results is null";
        } else {
            List<CancelBetsResult> list = resp.getBetResults().getCancelBetsResult();
            if (list == null) {
                betResults = "bet results list is null";
            } else {
                betResults = "bet results size: " + list.size();
                for (CancelBetsResult c : list) {
                    betResults += "\n" + ToStringUtil.cancelBetsResultToString(c);
                }
            }
        }
        
        result += betResults;
        
        return result;
    }

    public static String GetMUBetsToString(GetMUBetsResp resp) {
        if (resp == null) {
            return "GetMUBets responce is null";
        }
        
        String result = ""
                + "header: " + ToStringUtil.headerToString(resp.getHeader()) + "\n"
                + "error code: " + resp.getErrorCode().toString() + "\n"
                + "minor error code: " + resp.getMinorErrorCode() + "\n"
                + "total record count: " + resp.getTotalRecordCount();
        
        String muBets;
        if (resp.getBets() == null) {
            muBets = "MU bets is null";
        } else {
            List<MUBet> list = resp.getBets().getMUBet();
            if (list == null) {
                muBets = "MU bets list is null";
            } else {
                muBets = "MU bets size: " + list.size();
                for (MUBet m : list) {
                    muBets += "\n" + ToStringUtil.muBetToString(m);
                }
            }
        }
        
        result += muBets;
        
        return result;
    }
        
}
