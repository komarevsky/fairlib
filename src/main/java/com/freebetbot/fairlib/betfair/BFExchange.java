package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.v5.bfexchangeservice.BFExchangeService;
import com.betfair.publicapi.v5.bfexchangeservice.BFExchangeService_Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
class BFExchange {

    private static final Log LOGGER = LogFactory.getLog(BFExchange.class);
    
    private static BFExchangeService service;
    
    public static BFExchangeService get() {
        if (service == null) {
            try {
                LOGGER.debug("trying to init BFExchangeService");
                service = new BFExchangeService_Service().getBFExchangeService();
                LOGGER.debug("init of BFExchangeService is complete");
            } catch (Exception ex) {
                LOGGER.error("error during initialization of Exchnage Service", ex);
            }
        }
        return service;
    }
}
