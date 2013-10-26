/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.betfair;

import com.betfair.publicapi.v3.bfglobalservice.BFGlobalService;
import com.betfair.publicapi.v3.bfglobalservice.BFGlobalService_Service;
import com.freebetbot.xlogger.XLogger;

/**
 *
 * @author Siarhei Skavarodkin
 */
class BFGlobal {
    
    private static BFGlobalService service;
    
    public static BFGlobalService get() {
        if (service == null) {
            try {
                XLogger.sendFinest("trying to init BFGlobalService");
                service = new BFGlobalService_Service().getBFGlobalService();
                XLogger.sendFinest("init of BFGlobalService is complete");
            } catch (Exception ex) {
                XLogger.sendSevere(ex);
            }
        }
        return service;
    }

}
