/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class RunnerInfo {
    private int selectionId;
    private int orderIndex;
    private double totalAmountMatched;
    private double lastPriceMatched;
    private double handicap;
    private double reductionFactor;
    private boolean vacant;
    private int asianLineId;
    private double farSPPrice;
    private double nearSPPrice;
    private double actualSPPrice;
    private List<RunnerPrice> runnerPrices;

    public RunnerInfo() {}

    public double getActualSPPrice() {
        return actualSPPrice;
    }

    public void setActualSPPrice(double actualSPPrice) {
        this.actualSPPrice = actualSPPrice;
    }

    public int getAsianLineId() {
        return asianLineId;
    }

    public void setAsianLineId(int asianLineId) {
        this.asianLineId = asianLineId;
    }

    public double getFarSPPrice() {
        return farSPPrice;
    }

    public void setFarSPPrice(double farSPPrice) {
        this.farSPPrice = farSPPrice;
    }

    public double getHandicap() {
        return handicap;
    }

    public void setHandicap(double handicap) {
        this.handicap = handicap;
    }

    public double getLastPriceMatched() {
        return lastPriceMatched;
    }

    public void setLastPriceMatched(double lastPriceMatched) {
        this.lastPriceMatched = lastPriceMatched;
    }

    public double getNearSPPrice() {
        return nearSPPrice;
    }

    public void setNearSPPrice(double nearSPPrice) {
        this.nearSPPrice = nearSPPrice;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public double getReductionFactor() {
        return reductionFactor;
    }

    public void setReductionFactor(double reductionFactor) {
        this.reductionFactor = reductionFactor;
    }

    public int getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    public double getTotalAmountMatched() {
        return totalAmountMatched;
    }

    public void setTotalAmountMatched(double totalAmountMatched) {
        this.totalAmountMatched = totalAmountMatched;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }

    public List<RunnerPrice> getRunnerPrices() {
        return runnerPrices;
    }

    public void setRunnerPrices(List<RunnerPrice> runnerPrices) {
        this.runnerPrices = runnerPrices;
    }
    
    /**
     * returns sorted BackPrices. Element with id 0 has best BackPrice
     * @return sorted BackPrices
     */
    public List<RunnerPrice> getBestBackPrices() {
        List<RunnerPrice> prices = new ArrayList<RunnerPrice>();
        
        for (RunnerPrice p : runnerPrices) {
            if (p.getBackAmountAvailable() > 0) {
                prices.add(p);
            }
        }
        
        RunnerPriceComparatorByBack backComp = new RunnerPriceComparatorByBack();
        Collections.sort(prices, backComp);
        
        return prices;
    }
    
    /**
     * returns sorted LayPrices. Element with id 0 has best LayPrice
     * @return sorted LayPrices
     */
    public List<RunnerPrice> getBestLayPrices() {
        List<RunnerPrice> prices = new ArrayList<RunnerPrice>();
        
        for (RunnerPrice p : runnerPrices) {
            if (p.getLayAmountAvailable() > 0) {
                prices.add(p);
            }
        }
        
        RunnerPriceComparatorByLay layComp = new RunnerPriceComparatorByLay();
        Collections.sort(prices, layComp);
        
        return prices;
    }

    public String toStringWithList() {
        String rPrices = "";
        for (RunnerPrice r : runnerPrices) {
            rPrices += r.toString();
        }
        
        return "RunnerInfo{" 
                + "\n    selectionId=" + selectionId
                + "\n    orderIndex=" + orderIndex 
                + "\n    totalAmountMatched=" + totalAmountMatched 
                + "\n    lastPriceMatched=" + lastPriceMatched 
                + "\n    handicap=" + handicap 
                + "\n    reductionFactor=" + reductionFactor 
                + "\n    vacant=" + vacant 
                + "\n    asianLineId=" + asianLineId 
                + "\n    farSPPrice=" + farSPPrice 
                + "\n    nearSPPrice=" + nearSPPrice 
                + "\n    actualSPPrice=" + actualSPPrice
                + "\n    runnersPrices:\n" + rPrices
                + "\n}\n";
    }
    
    @Override
    public String toString() {
        List<RunnerPrice> bestBackPrices = getBestBackPrices();
        String backPrices = "";
        for (int i=0; i<bestBackPrices.size() && i<3; ++i) {
            backPrices += bestBackPrices.get(i).toString();
        }
        
        List<RunnerPrice> bestLayPrices = getBestLayPrices();
        String layPrices = "";
        for (int i=0; i<bestLayPrices.size() && i<3; ++i) {
            layPrices += bestLayPrices.get(i).toString();
        }


        return "RunnerInfo{" 
                + "\n    selectionId=" + selectionId
                + "\n    orderIndex=" + orderIndex 
                + "\n    totalAmountMatched=" + totalAmountMatched 
                + "\n    lastPriceMatched=" + lastPriceMatched 
                + "\n    handicap=" + handicap 
                + "\n    reductionFactor=" + reductionFactor 
                + "\n    vacant=" + vacant 
                + "\n    asianLineId=" + asianLineId 
                + "\n    farSPPrice=" + farSPPrice 
                + "\n    nearSPPrice=" + nearSPPrice 
                + "\n    actualSPPrice=" + actualSPPrice
                + "\n    best back prices=\n" + backPrices
                + "\n    best lay prices=\n" + layPrices
                + "\n}\n";
    }
    
}

class RunnerPriceComparatorByBack implements Comparator<RunnerPrice> {

    @Override
    public int compare(RunnerPrice p1, RunnerPrice p2) {
        Double p1Back = new Double(p1.getPrice());
        return -( p1Back.compareTo(p2.getPrice()) );
    }
}

class RunnerPriceComparatorByLay implements Comparator<RunnerPrice> {

    @Override
    public int compare(RunnerPrice p1, RunnerPrice p2) {
        Double p1Lay = new Double(p1.getPrice());
        return p1Lay.compareTo(p2.getPrice());
    }
}
