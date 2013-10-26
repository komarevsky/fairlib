/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.type;

import java.util.List;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class MarketPrices {
    private int marketId;
    private int inPlayDelay;
    private List<RemovedRunner> removedRunners;
    private List<RunnerInfo> runnersInfo;

    public MarketPrices() {}

    public MarketPrices(int marketId, int inPlayDelay, 
            List<RemovedRunner> removedRunners, 
            List<RunnerInfo> runnersInfo) {
        this.marketId = marketId;
        this.inPlayDelay = inPlayDelay;
        this.removedRunners = removedRunners;
        this.runnersInfo = runnersInfo;
    }

    public int getInPlayDelay() {
        return inPlayDelay;
    }

    public void setInPlayDelay(int inPlayDelay) {
        this.inPlayDelay = inPlayDelay;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public List<RemovedRunner> getRemovedRunners() {
        return removedRunners;
    }

    public void setRemovedRunners(List<RemovedRunner> removedRunners) {
        this.removedRunners = removedRunners;
    }

    public List<RunnerInfo> getRunnersInfo() {
        return runnersInfo;
    }

    public void setRunnersInfo(List<RunnerInfo> runnersInfo) {
        this.runnersInfo = runnersInfo;
    }

    @Override
    public String toString() {
        String rRunners = "";
        for (RemovedRunner r : removedRunners) {
            rRunners += r.toString();
        }
        
        String rInfo = "";
        for (RunnerInfo r : runnersInfo) {
            rInfo += r.toString();
        }
        
        return "MarketPrices{" 
                + "\nmarketId=" + marketId 
                + "\ninPlayDelay=" + inPlayDelay 
                + "\nremovedRunners=\n" + rRunners
                + "\nrunnersInfo=\n" + rInfo
                + "\n}\n";
    }
    
    
}
