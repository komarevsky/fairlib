/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.util;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class MathHelper {
 
    public static double roundDouble(double d, int precise) {
        int temp=(int)((d*Math.pow(10,precise)));
        return (((double)temp)/Math.pow(10,precise));
    }
    
    /**
     * calc back size to green up profit
     * @param priceBack
     * @param priceLay
     * @param laySize
     * @return 
     */
    public static double getBackSize(double priceBack, double priceLay, double laySize) {
        double firstPart = laySize * (1 / (priceLay-1)) + laySize;
        return firstPart / priceBack;
    }
    
    /**
     * calc lay size to green up profit
     * @param priceLay
     * @param priceBack
     * @param backSize
     * @return 
     */
    public static double getLaySize(double priceLay, double priceBack, double backSize) {
        double firstPart = ((priceBack-1) * backSize) + backSize;
        double secondPart = (1 / (priceLay-1)) + 1;
        return firstPart/secondPart;
    }

}