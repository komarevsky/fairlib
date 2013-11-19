/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.types.global.v3.APIRequestHeader;
import com.betfair.publicapi.types.global.v3.APIResponseHeader;
import com.betfair.publicapi.types.global.v3.LoginResp;
import com.freebetbot.fairlib.util.HeaderChecker;
import com.freebetbot.fairlib.util.Waiter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
class SessionManager implements Runnable {

    private static final Log LOGGER = LogFactory.getLog(SessionManager.class);
    
    private static String user;
    private static String password;
    private static int productId;
    private static String sessionToken;
    private static boolean everythingOk = true;
    private static boolean restartInitiated = false;
    
    private static final Object lock = new Object();

    private SessionManager() {}
    
    /**
     * returns true if there are no errors and work with Betfair service is ok.
     * If false, then you have to check if your user and password are OK.
     * If user and password ok, then you can try to wait because SessionManager
     * will try to re-connect to Betfair.
     * @return true if there are no errors and work with Betfair service is ok.
     */
    public static boolean isEverythingOk() {
        return everythingOk;
    }

    public static void setUserPasswordProductId(String user, String password, int productId) {
        SessionManager.user = user;
        SessionManager.password = password;
        SessionManager.productId = productId;
    }
    
    /**
     * this method has to be called if logout was made
     */
    public static void clearCredintials() {
        user = "";
        password = "";
        productId = 0;
        sessionToken = "";
    }
    
    public static String getToken() {
        synchronized(lock) {
            return sessionToken;
        }
    }
    
    public static void setToken(String token) {
        synchronized(lock) {
            if (token == null || token.isEmpty()) {
                everythingOk = false;
                restartSession();
            } else {
                sessionToken = token;
            }
        }
    }
    
    public static APIRequestHeader getReqHeader()
    throws NullPointerException {
        synchronized(lock) {
            if (sessionToken == null) {
                throw new NullPointerException("sessionToken is null");
            }

            APIRequestHeader result = new APIRequestHeader();
            result.setSessionToken(sessionToken);
            
            return result;
        }
    }
    
    public static void setRespHeader(APIResponseHeader header) {
        synchronized(lock) {
            if (!HeaderChecker.isHeaderOk(header)) {
                everythingOk = false;
                restartSession();
            } else {
                sessionToken = header.getSessionToken();
            }
        }
    }
    
    public static com.betfair.publicapi.types.exchange.v5.APIRequestHeader getExReqHeader()
    throws NullPointerException {
        synchronized(lock) {
            if (sessionToken == null) {
                throw new NullPointerException("sessionToken is null");
            }

            com.betfair.publicapi.types.exchange.v5.APIRequestHeader result = 
                    new com.betfair.publicapi.types.exchange.v5.APIRequestHeader();
            result.setSessionToken(sessionToken);

            return result;
        }
    }
    
    public static void setExRespHeader(com.betfair.publicapi.types.exchange.v5.APIResponseHeader header) {
        synchronized(lock) {
            if (!HeaderChecker.isHeaderOk(header)) {
                everythingOk = false;
                restartSession();
            } else {
                sessionToken = header.getSessionToken();
            }
        }
    }
    
    public static void restartSession() {
        if (!restartInitiated) {
            SessionManager sm = new SessionManager();
            Thread t = new Thread(sm);
            t.start();
        }
    }
    
    @Override
    public void run() {
        restartSessionImpl();
    }
    
    private static void restartSessionImpl() {
        if (user == null || user.isEmpty() || password == null || password.isEmpty()) {
            LOGGER.warn("user and/or password are not set: restoring of session is not possible");
            return;
        }
        
        if (restartInitiated) {
            LOGGER.info("restart of session has ALREADY been initiated. Please, wait.");
            return;
        } else {
            LOGGER.warn("restart of session has been initiated");
            restartInitiated = true;
        }
        
        everythingOk = false;
                
        LoginResp resp = null;
        do {
            Waiter.waitForSpecifiedTime(Waiter.ONE_SECOND * 10); // every 10 seconds
            
            try{
                LOGGER.info("trying to login");
                resp = BFOperation.login(user, password, productId);
            } catch (Exception ex) {
                LOGGER.error("login attempt has failed", ex);
            }
        } while (HeaderChecker.isLoginResponseOk(resp) != true);
        
        setRespHeader(resp.getHeader());
        everythingOk = true;
        restartInitiated = false;
    }
    
}
