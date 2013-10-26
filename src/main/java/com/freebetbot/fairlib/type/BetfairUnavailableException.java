/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.type;

/**
 * This exception means that there is an issue beetween client and betfair.
 * @author Siarhei Skavarodkin
 */
public class BetfairUnavailableException extends Exception {

    public BetfairUnavailableException() {
    }

    public BetfairUnavailableException(String message) {
        super(message);
    }

    public BetfairUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public BetfairUnavailableException(Throwable cause) {
        super(cause);
    }

    public BetfairUnavailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    

}
