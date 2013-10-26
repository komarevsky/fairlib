/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.v5.bfexchangeservice.BFExchangeService;
import com.betfair.publicapi.v5.bfexchangeservice.BFExchangeService_Service;
import com.freebetbot.xlogger.XLogger;

/**
 *
 * @author Siarhei Skavarodkin
 */
class BFExchange {

    private static BFExchangeService service;
    
    public static BFExchangeService get() {
        if (service == null) {
            try {
                XLogger.sendFinest("trying to init BFExchangeService");
                service = new BFExchangeService_Service().getBFExchangeService();
                XLogger.sendFinest("init of BFExchangeService is complete");
            } catch (Exception ex) {
                XLogger.sendSevere(ex);
            }
        }
        return service;
    }
}
