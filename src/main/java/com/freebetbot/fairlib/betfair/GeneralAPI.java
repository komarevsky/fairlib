package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.types.global.v3.KeepAliveResp;
import com.betfair.publicapi.types.global.v3.LoginResp;
import com.betfair.publicapi.types.global.v3.LogoutResp;
import com.freebetbot.fairlib.util.HeaderChecker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class GeneralAPI {
    
    private static final Log LOGGER = LogFactory.getLog(GeneralAPI.class);
    private static final int DEVELOPER_PRODUCT_ID = 82;

    public static int getDeveloperProductId() {
        return DEVELOPER_PRODUCT_ID;
    }
    
    /**
     * tries to login to Betfair.
     * @param user Betfair login
     * @param password Betfair password
     * @param productId Betfair product id (use 82 if you don't know what to use)
     * @return LoginResp with answer or null if any exception
     */
    public static LoginResp login(String user, String password, int productId) {
        LoginResp result;
        
        try {
            SessionManager.setUserPasswordProductId(user, password, productId);
            result = BFOperation.login(user, password, productId);
            if (HeaderChecker.isLoginResponseOk(result)) {
                SessionManager.setRespHeader(result.getHeader());
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error("login failure", ex);
        }
        
        return result;
    }
    
    /**
     * tries to logout from Betfair. If logout is not possible then Restart of
     * Session will be initiated
     * @return LogoutResp or null if any session issue
     */
    public static LogoutResp logout() {
        LogoutResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.logout();
            if (HeaderChecker.isLogoutResponseOk(result)) {
                SessionManager.clearCredintials();
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error("logout failure", ex);
            SessionManager.restartSession();
        }
        
        return result;
    }

    public static KeepAliveResp keepAlive() {
        KeepAliveResp result;
        
        if (!SessionManager.isEverythingOk()) {
            return null;
        }
        
        try {
            result = BFOperation.keepAlive();
            if (HeaderChecker.isKeepAliveResponseOk(result)) {
                SessionManager.setRespHeader(result.getHeader());
            } else {
                SessionManager.restartSession();
            }
        } catch(Exception ex) {
            result = null;
            LOGGER.error("keepAlive failure", ex);
            SessionManager.restartSession();
        }
        
        return result;
    }
    
}
