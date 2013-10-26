/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.util.string;

import com.betfair.publicapi.types.global.v3.APIRequestHeader;
import com.betfair.publicapi.types.global.v3.APIResponseHeader;

/**
 *
 * @author Siarhei Skavarodkin
 */
class HeaderToString {

    public static String headerToString(com.betfair.publicapi.types.exchange.v5.APIResponseHeader header) {
        if (header == null) {
            return "response header is null";
        }
        
        String result = "";
        
        String token = header.getSessionToken();
        if (token == null) {
            token = "token is null";
        } else if (token.isEmpty()) {
            token = "token is empty";
        } else {
            token = "token is OK";
        }
        
        result +=
                "error code: "         + header.getErrorCode().toString() + "\n"
                + "minor error code: " + header.getMinorErrorCode()       + "\n"
                + "session token: "    + token                            + "\n"
                + "timestamp: "        + header.getTimestamp().toString();
        
        return result;
    }

    public static String headerToString(APIResponseHeader header) {
        if (header == null) {
            return "response header is null";
        }
        
        String result = "";
        
        String token = header.getSessionToken();
        if (token == null) {
            token = "token is null";
        } else if (token.isEmpty()) {
            token = "token is empty";
        } else {
            token = "token is OK";
        }
        
        result +=
                "error code: "         + header.getErrorCode().toString() + "\n"
                + "minor error code: " + header.getMinorErrorCode()       + "\n"
                + "session token: "    + token                            + "\n"
                + "timestamp: "        + header.getTimestamp().toString();
        
        return result;
    }
    
    public static String headerToString(APIRequestHeader header) {
        if (header == null) {
            return "request header is null";
        }
        
        String result = "";
        
        String token = header.getSessionToken();
        if (token == null) {
            token = "token is null";
        } else if (token.isEmpty()) {
            token = "token is empty";
        } else {
            token = "token is OK";
        }
        
        result +=
                "session token: " + token + "\n"
                + "clientstamp: " + header.getClientStamp();
        
        return result;
    }
    
    public static String headerToString(com.betfair.publicapi.types.exchange.v5.APIRequestHeader header) {
        if (header == null) {
            return "request header is null";
        }
        
        String result = "";
        
        String token = header.getSessionToken();
        if (token == null) {
            token = "token is null";
        } else if (token.isEmpty()) {
            token = "token is empty";
        } else {
            token = "token is OK";
        }
        
        result +=
                "session token: " + token + "\n"
                + "clientstamp: " + header.getClientStamp();
        
        return result;
    }

}
