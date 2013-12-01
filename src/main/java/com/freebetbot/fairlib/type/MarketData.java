package com.freebetbot.fairlib.type;

import java.util.Date;

/**
 * representation of betfair market data
 * @author Siarhei Skavarodkin
 */
public class MarketData {
    public static final int COUNT_OF_FIELDS = 16;
    
    private int marketId;
    private String marketName;
    private String marketType;
    private String marketStatus;
    private Date eventDate;
    private String menuPath;
    private String eventHierarchy;
    private String betDelay;
    private int exchangeId;
    private String countryCode;
    private Date lastRefresh;
    private int numberOfRunners;
    private int numberOfWinners;
    private double totalAmountMatched;
    private boolean bspMarket;
    private boolean turningInPlay;

    public String getBetDelay() {
        return betDelay;
    }

    public void setBetDelay(String betDelay) {
        this.betDelay = betDelay;
    }

    public boolean isBspMarket() {
        return bspMarket;
    }

    public void setBspMarket(boolean bspMarket) {
        this.bspMarket = bspMarket;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventHierarchy() {
        return eventHierarchy;
    }

    public void setEventHierarchy(String eventHierarchy) {
        this.eventHierarchy = eventHierarchy;
    }

    public int getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(int exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketStatus() {
        return marketStatus;
    }

    public void setMarketStatus(String marketStatus) {
        this.marketStatus = marketStatus;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public int getNumberOfRunners() {
        return numberOfRunners;
    }

    public void setNumberOfRunners(int numberOfRunners) {
        this.numberOfRunners = numberOfRunners;
    }

    public int getNumberOfWinners() {
        return numberOfWinners;
    }

    public void setNumberOfWinners(int numberOfWinners) {
        this.numberOfWinners = numberOfWinners;
    }

    public double getTotalAmountMatched() {
        return totalAmountMatched;
    }

    public void setTotalAmountMatched(double totalAmountMatched) {
        this.totalAmountMatched = totalAmountMatched;
    }

    public boolean isTurningInPlay() {
        return turningInPlay;
    }

    public void setTurningInPlay(boolean turningInPlay) {
        this.turningInPlay = turningInPlay;
    }
    
    public String getEventName() {
        String[] arr = this.menuPath.split("\\\\");
        return arr[arr.length-1];
    }
    
    @Override
    public String toString() {
        String res = 
            "marketId: " + marketId + "\n"
            + "marketName: " + marketName + "\n"
            + "marketType: " + marketType + "\n"
            + "marketStatus: " + marketStatus + "\n"
            + "eventDate: " + eventDate.toString() + "\n"
            + "menuPath: " + menuPath + "\n"
            + "eventHierarchy: " + eventHierarchy + "\n"
            + "betDelay: " + betDelay + "\n"
            + "exchangeId: " + exchangeId + "\n"
            + "countryCode: " + countryCode + "\n"
            + "lastRefresh: " + lastRefresh + "\n"
            + "numberOfRunners: " + numberOfRunners + "\n"
            + "numberOfWinners: " + numberOfWinners + "\n"
            + "totalAmountMatched: " + totalAmountMatched + " GBP" + "\n"
            + "bspMarket: " + bspMarket + "\n"
            + "turningInPlay: " + turningInPlay;
    
        return res;
    }
}
