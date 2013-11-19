/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.util;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class Waiter {
    
    public static final long ONE_SECOND = 1000; // 1s - 1000 ms
    
    private static final Log LOGGER = LogFactory.getLog(Waiter.class);
    
        /**
     * how much to sleep in milliseconds
     * @param ms how much to sleep
     */
    public static void waitForSpecifiedTime(long ms) {
        try {
            if (ms > 0) {
                Thread.sleep(ms);
            }
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
    
    /**
     * wait for speciefied time (ms - time spent since lastCall) if time spent 
     * since lastCall less than in ms parameter
     * @param ms how much to sleep
     * @param lastCall time of previous call
     */
    public static void waitForSpecifiedTime(long ms, Date lastCall) {
        long timeSpent = new Date().getTime() - lastCall.getTime();
        if (timeSpent < ms) {
            long timeLeft = ms - timeSpent;
            waitForSpecifiedTime(timeLeft);
        }
    }
    
    /**
     * calculates difference in seconds : date1 - date2
     * @param date1
     * @param date2
     * @return difference in seconds : date1 - date2
     */
    public static long secondsBetween(Date date1, Date date2) {
        return (long) (date1.getTime()-date2.getTime())/ONE_SECOND;
    }

}
