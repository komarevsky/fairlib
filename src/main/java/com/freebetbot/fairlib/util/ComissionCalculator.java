/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class ComissionCalculator {
    
    public static final double DEFAULT_COMISSION = 0.05;
    
    private static final Log LOGGER = LogFactory.getLog(ComissionCalculator.class);
    
    /**
     * returns percentage of comission
     * based on http://help.betfair.com/contents/itemId/i65767827/index.en.html
     * @param points betfair points
     * @return percentage of comission, for example 0.05
     */
    public static double calcComissionPercentByBetfairPoints(int points) {
        double result;
        LOGGER.debug("points : " + points);
        
        double discount;
        if (points < 1000) {
            discount = 0;
        } else if (points < 2500) {
            discount = 0.02;
        } else if (points < 4000) {
            discount = 0.04;
        } else if (points < 5500) {
            discount = 0.06;
        } else if (points < 7000) {
            discount = 0.08;
        } else if (points < 9000) {
            discount = 0.10;
        } else if (points < 11000) {
            discount = 0.12;
        } else if (points < 13000) {
            discount = 0.14;
        } else if (points < 15000) {
            discount = 0.16;
        } else if (points < 17000) {
            discount = 0.18;
        } else if (points < 19000) {
            discount = 0.20;
        } else if (points < 21000) {
            discount = 0.22;
        } else if (points < 23000) {
            discount = 0.24;
        } else if (points < 26000) {
            discount = 0.26;
        } else if (points < 29000) {
            discount = 0.28;
        } else if (points < 32000) {
            discount = 0.30;
        } else if (points < 36000) {
            discount = 0.32;
        } else if (points < 40000) {
            discount = 0.34;
        } else if (points < 44000) {
            discount = 0.36;
        } else if (points < 49000) {
            discount = 0.38;
        } else if (points < 55000) {
            discount = 0.40;
        } else if (points < 61000) {
            discount = 0.42;
        } else if (points < 67000) {
            discount = 0.44;
        } else if (points < 73000) {
            discount = 0.46;
        } else if (points < 80000) {
            discount = 0.48;
        } else if (points < 88000) {
            discount = 0.50;
        } else if (points < 96000) {
            discount = 0.52;
        } else if (points < 108000) {
            discount = 0.54;
        } else if (points < 126000) {
            discount = 0.56;
        } else if (points < 150000) {
            discount = 0.58;
        } else {
            discount = 0.60;
        }
        
        result = DEFAULT_COMISSION * (1-discount);
        LOGGER.debug("result: " + result);
        return result;
    }

}
