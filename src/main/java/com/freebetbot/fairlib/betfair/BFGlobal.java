package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.v3.bfglobalservice.BFGlobalService;
import com.betfair.publicapi.v3.bfglobalservice.BFGlobalService_Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
class BFGlobal {
    
    private static final Log LOGGER = LogFactory.getLog(BFGlobal.class);
    
    private static BFGlobalService service;
    
    public static BFGlobalService get() {
        if (service == null) {
            try {
                LOGGER.debug("trying to init BFGlobalService");
                service = new BFGlobalService_Service().getBFGlobalService();
                LOGGER.debug("init of BFGlobalService is complete");
            } catch (Exception ex) {
                LOGGER.error("error during initialization of BF Global Service", ex);
            }
        }
        return service;
    }

}
