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
public class PriceConverter {

    /**
     * method which determines what price you have to choose if current is sourcePrice
     * @param sourcePrice current price on market
     * @return value of price you can use for bet or -1 if new price can not be determined
     */
    public static double convertPrice(double sourcePrice) {
        double result;
        
        Double tmp = roundResult(sourcePrice);
        result = (tmp != null) ? tmp.doubleValue() : -1;
        
        return result;
    }
    
    /**
     * rounding value according to 
     * http://help.betfair.com/contents/itemId/i65767327/index.en.html
     * @param value to round
     * @return rounded value or null if any error
     */
    private static Double roundResult(double value) {
        int roundSize;    // remainder of division result on roundSize has to be 0
        value = MathHelper.roundDouble(value, 2);
        
        //clarify round size
        if (value >1 && value < 2) {
            roundSize = 1;
        } else if (value >=2 && value < 3) {
            roundSize = 2;
        } else if (value >=3 && value < 4) {
            roundSize = 5;
        } else if (value >=4 && value < 6) {
            roundSize = 10;
        } else if (value >=6 && value < 10) {
            roundSize = 20;
        } else if (value >=10 && value < 20) {
            roundSize = 50;
        } else if (value >=20 && value < 30) {
            roundSize = 100;
        } else if (value >=30 && value < 50) {
            roundSize = 200;
        } else if (value >=50 && value < 100) {
            roundSize = 500;
        } else if (value >=100 && value < 1000) {
            roundSize = 1000;
        } else {
            return null;
        }
        
        int price = (int)(value*100);
        while (price % roundSize != 0) {
            price += 1;
        }
        
        double result = price/100.0d;
        
        if (result >=1000) {
            return null;
        }
        
        return result;
    }

}
