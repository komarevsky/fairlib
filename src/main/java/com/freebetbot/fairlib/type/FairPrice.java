package com.freebetbot.fairlib.type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class FairPrice {

    private static final double MIN_PRICE = 1.01;
    private static final double MAX_PRICE = 1000;

    // list of rules for every possible interval
    private static final List<Rule> ruleList;

    //initialization of ruleList
    static {
        // array of [FROM_PRICE, TO_PRICE, INCREMENT]
        double[][] betfairRules = {
            {MIN_PRICE, 2, 0.01},
            {2, 3, 0.02},
            {3, 4, 0.05},
            {4, 6, 0.1},
            {6, 10, 0.2},
            {10, 20, 0.5},
            {20, 30, 1},
            {30, 50, 2},
            {50, 100, 5},
            {100, MAX_PRICE, 10}
        };
        
        ruleList = new ArrayList<Rule>();
        for (double[] rule : betfairRules) {
            double from = rule[0];
            double to = rule[1];
            double increment = rule[2];

            for (double d=from; d<to; ) {
                BigDecimal bdTo = new BigDecimal(d+increment).setScale(2, RoundingMode.HALF_UP);
                ruleList.add(new Rule(d, bdTo.doubleValue(), increment));
                d = bdTo.doubleValue();
            }
        }
    }

    private double price;

    public static double getMaxPrice() {
        return MAX_PRICE;
    }

    public static double getMinPrice() {
    return MIN_PRICE;
    }

    public FairPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isValid() {
        if (price < MIN_PRICE || price > MAX_PRICE) {
            return false;
        }

        for (Rule r : ruleList) {
            if (price == r.getFrom() || price == r.getTo()) {
                return true;
            }
        }

        return false;
    }

    public FairPrice getNextPrice() {
        if (price < MIN_PRICE) {
            return new FairPrice(MIN_PRICE);
        } else if (price >= MAX_PRICE) {
            return new FairPrice(MAX_PRICE);
        }

        for (Rule r : ruleList) {
            double from = r.getFrom();
            double to = r.getTo();
            double increment = r.getIncrement();

            if (price >= from && price < to) {
                BigDecimal newPriceValue = new BigDecimal(from + increment).setScale(2, RoundingMode.HALF_UP);
                return new FairPrice(newPriceValue.doubleValue());
            }
        }

        return null;
    }

    public FairPrice getPreviousPrice() {
        if (price <= MIN_PRICE) {
            return new FairPrice(MIN_PRICE);
        } else if (price > MAX_PRICE) {
            return new FairPrice(MAX_PRICE);
        }

        for (Rule r : ruleList) {
            double from = r.getFrom();
            double to = r.getTo();
            double increment = r.getIncrement();

            if (price > from && price <= to) {
                BigDecimal newPriceValue = new BigDecimal(to - increment).setScale(2, RoundingMode.HALF_DOWN);
                return new FairPrice(newPriceValue.doubleValue());
            }
        }

        return null;
    }

}

class Rule {

    private double from;
    private double to;
    private double increment;

    public Rule(double from, double to, double increment) {
        this.from = from;
        this.to = to;
        this.increment = increment;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Rule{" + "from=" + from + "; to=" + to + "; increment=" + increment + '}';
    }
}
