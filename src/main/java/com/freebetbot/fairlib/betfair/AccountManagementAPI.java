/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

 package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.types.exchange.v5.GetAccountFundsResp;
import com.betfair.publicapi.types.global.v3.ViewProfileResp;
import com.freebetbot.fairlib.util.HeaderChecker;
import com.freebetbot.xlogger.XLogger;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class AccountManagementAPI {
    
    public static GetAccountFundsResp getAccountFunds() {
        GetAccountFundsResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.getAccountFunds();
            if (HeaderChecker.isGetAccountFundsResponseOk(result)) {
                SessionManager.setExRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            XLogger.sendSevere(ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
    
    public static ViewProfileResp viewProfile() {
        ViewProfileResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.viewProfile();
            if (HeaderChecker.isViewProfileResponseOk(result)) {
                SessionManager.setRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            XLogger.sendSevere(ex);
            SessionManager.restartSession();
        }
        
        return result;
    }

}
