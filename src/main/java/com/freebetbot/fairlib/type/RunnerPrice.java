/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.type;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class RunnerPrice {
    private double price;
    private double backAmountAvailable;
    private double layAmountAvailable;
    private double totalBSPBackAvailableAmount;
    private double totalBSPLayAvailableAmount;
    
    public RunnerPrice() {}

    public double getBackAmountAvailable() {
        return backAmountAvailable;
    }

    public void setBackAmountAvailable(double backAmountAvailable) {
        this.backAmountAvailable = backAmountAvailable;
    }

    public double getLayAmountAvailable() {
        return layAmountAvailable;
    }

    public void setLayAmountAvailable(double layAmountAvailable) {
        this.layAmountAvailable = layAmountAvailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalBSPBackAvailableAmount() {
        return totalBSPBackAvailableAmount;
    }

    public void setTotalBSPBackAvailableAmount(double totalBSPBackAvailableAmount) {
        this.totalBSPBackAvailableAmount = totalBSPBackAvailableAmount;
    }

    public double getTotalBSPLayAvailableAmount() {
        return totalBSPLayAvailableAmount;
    }

    public void setTotalBSPLayAvailableAmount(double totalBSPLayAvailableAmount) {
        this.totalBSPLayAvailableAmount = totalBSPLayAvailableAmount;
    }

    @Override
    public String toString() {
        return "RunnerPrice{" 
                + "\n    price=" + price 
                + "\n    backAmountAvailable=" + backAmountAvailable 
                + "\n    layAmountAvailable=" + layAmountAvailable 
                + "\n    totalBSPBackAvailableAmount=" + totalBSPBackAvailableAmount 
                + "\n    totalBSPLayAvailableAmount=" + totalBSPLayAvailableAmount 
                + "\n}\n";
    }
    
}
