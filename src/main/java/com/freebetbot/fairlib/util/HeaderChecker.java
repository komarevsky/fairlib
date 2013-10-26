/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.util;

import com.betfair.publicapi.types.exchange.v5.CancelBetsErrorEnum;
import com.betfair.publicapi.types.exchange.v5.CancelBetsResp;
import com.betfair.publicapi.types.exchange.v5.GetAccountFundsErrorEnum;
import com.betfair.publicapi.types.exchange.v5.GetAccountFundsResp;
import com.betfair.publicapi.types.exchange.v5.GetAllMarketsErrorEnum;
import com.betfair.publicapi.types.exchange.v5.GetAllMarketsResp;
import com.betfair.publicapi.types.exchange.v5.GetBetErrorEnum;
import com.betfair.publicapi.types.exchange.v5.GetBetResp;
import com.betfair.publicapi.types.exchange.v5.GetCompleteMarketPricesCompressedResp;
import com.betfair.publicapi.types.exchange.v5.GetCompleteMarketPricesErrorEnum;
import com.betfair.publicapi.types.exchange.v5.GetMUBetsErrorEnum;
import com.betfair.publicapi.types.exchange.v5.GetMUBetsResp;
import com.betfair.publicapi.types.exchange.v5.GetMarketErrorEnum;
import com.betfair.publicapi.types.exchange.v5.GetMarketResp;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsErrorEnum;
import com.betfair.publicapi.types.exchange.v5.PlaceBetsResp;
import com.betfair.publicapi.types.global.v3.APIErrorEnum;
import com.betfair.publicapi.types.global.v3.APIResponseHeader;
import com.betfair.publicapi.types.global.v3.GetEventTypesResp;
import com.betfair.publicapi.types.global.v3.GetEventsErrorEnum;
import com.betfair.publicapi.types.global.v3.KeepAliveResp;
import com.betfair.publicapi.types.global.v3.LoginErrorEnum;
import com.betfair.publicapi.types.global.v3.LoginResp;
import com.betfair.publicapi.types.global.v3.LogoutErrorEnum;
import com.betfair.publicapi.types.global.v3.LogoutResp;
import com.betfair.publicapi.types.global.v3.ViewProfileErrorEnum;
import com.betfair.publicapi.types.global.v3.ViewProfileResp;
import com.freebetbot.fairlib.util.string.ToStringUtil;
import com.freebetbot.xlogger.XLogger;

/**
 * This class checks all kinds of responces. Returns true if response is ok,
 * otherwise it returns false and put into log all details about response.
 * 
 * Warning! This class checks only if response is ok, from API point of view.
 * 
 * For example, the isGetAccountFundsResponseOk method can return 
 * GetAccountFundsErrorEnum.EXPOSURE_CALC_IN_PROGRESS
 * what is not ok from user point of view, but ok from API point of view.
 * So, true will be returned
 * 
 * @author Siarhei Skavarodkin
 */
public class HeaderChecker {
    
    public static boolean isHeaderOk(com.betfair.publicapi.types.exchange.v5.APIResponseHeader header) {
        boolean result = false;
        
        if (    header != null                      &&
                header.getSessionToken() != null    &&
                !header.getSessionToken().isEmpty() &&
                header.getErrorCode() == com.betfair.publicapi.types.exchange.v5.APIErrorEnum.OK) {
            result = true;
        } else {
            XLogger.sendWarning("Header is not ok:\n" + ToStringUtil.headerToString(header));
        }
        
        return result;
    }

    public static boolean isHeaderOk(APIResponseHeader header) {
        boolean result = false;
        
        if (    header != null                      &&
                header.getSessionToken() != null    &&
                !header.getSessionToken().isEmpty() &&
                header.getErrorCode() == APIErrorEnum.OK) {
            result = true;
        } else {
            XLogger.sendWarning("Header is not ok:\n" + ToStringUtil.headerToString(header));
        }
        
        return result;
    }
    
    public static boolean isLoginResponseOk(LoginResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                (resp.getErrorCode() == LoginErrorEnum.OK || resp.getErrorCode() == LoginErrorEnum.OK_MESSAGES) &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + ToStringUtil.loginToString(resp));
        }
        
        return result;
    }

    public static boolean isLogoutResponseOk(LogoutResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() == LogoutErrorEnum.OK) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + ToStringUtil.logoutToString(resp));
        }
        
        return result;
    }
    
    public static boolean isKeepAliveResponseOk(KeepAliveResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + ToStringUtil.keepAliveToString(resp));
        }
        
        return result;
    }
    
    public static boolean isGetAccountFundsResponseOk(GetAccountFundsResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() != GetAccountFundsErrorEnum.API_ERROR &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + ToStringUtil.getAccountFundsToString(resp));
        }
        
        return result;
    }
    
    public static boolean isViewProfileResponseOk(ViewProfileResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() != ViewProfileErrorEnum.API_ERROR &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + ToStringUtil.viewProfileToString(resp));
        }
        
        return result;
    }
    
    public static boolean isGetEventTypesResponseOk(GetEventTypesResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() != GetEventsErrorEnum.API_ERROR &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + ToStringUtil.getEventTypesToString(resp));
        }
        
        return result;
    }
    
    public static boolean isGetAllMarketsResponseOk(GetAllMarketsResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() != GetAllMarketsErrorEnum.API_ERROR &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + ToStringUtil.getAllMarketsToString(resp));
        }
        
        return result;
    }
    
    public static boolean isGetMarketResponseOk(GetMarketResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() != GetMarketErrorEnum.API_ERROR &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + ToStringUtil.getMarketToString(resp));
        }
        
        return result;
    }
    
    public static boolean isGetCompleteMarketPricesCompressedResponseOk(GetCompleteMarketPricesCompressedResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() != GetCompleteMarketPricesErrorEnum.API_ERROR &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + 
                    ToStringUtil.getCompleteMarketPricesCompressedToString(resp));
        }
        
        return result;
    }
    
    public static boolean isGetBetResponseOk(GetBetResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() != GetBetErrorEnum.API_ERROR &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + 
                    ToStringUtil.getBetToString(resp));
        }
        
        return result;
    }
    
    public static boolean isPlaceBetsResponseOk(PlaceBetsResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() != PlaceBetsErrorEnum.API_ERROR &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + 
                    ToStringUtil.placeBetsToString(resp));
        }
        
        return result;
    }

    public static boolean isCancelBetsResponseOk(CancelBetsResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                resp.getErrorCode() != CancelBetsErrorEnum.API_ERROR &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + 
                    ToStringUtil.cancelBetsToString(resp));
        }
        
        return result;
    }

    public static boolean isGetMUBetsResponseOk(GetMUBetsResp resp) {
        boolean result = false;
        
        if (    resp != null &&
                (resp.getErrorCode() == GetMUBetsErrorEnum.OK ||
                resp.getErrorCode() == GetMUBetsErrorEnum.NO_RESULTS) &&
                isHeaderOk(resp.getHeader())) {
            result = true;
        } else {
            XLogger.sendWarning("Response is not ok:\n" + 
                    ToStringUtil.GetMUBetsToString(resp));
        }
        
        return result;
    }
    
}
