package com.freebetbot.fairlib.util;

import com.betfair.publicapi.types.exchange.v5.ArrayOfBetId;
import com.betfair.publicapi.types.exchange.v5.ArrayOfCountryCode;
import com.betfair.publicapi.types.exchange.v5.ArrayOfInt;
import com.betfair.publicapi.types.exchange.v5.ArrayOfPlaceBets;
import com.betfair.publicapi.types.exchange.v5.BetCategoryTypeEnum;
import com.betfair.publicapi.types.exchange.v5.BetPersistenceTypeEnum;
import com.betfair.publicapi.types.exchange.v5.BetTypeEnum;
import com.betfair.publicapi.types.exchange.v5.PlaceBets;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class TypeCreator {

    private static final Log LOGGER = LogFactory.getLog(TypeCreator.class);
    
    public static ArrayOfInt createArrayOfInt(int... numbers) {
        ArrayOfInt result = new ArrayOfInt();
        
        for (int i : numbers) {
            result.getInt().add(i);
        }
        
        return result;
    }
    
    public static ArrayOfCountryCode createArrayOfCountryCode(String... countries) {
        ArrayOfCountryCode result = new ArrayOfCountryCode();
        result.getCountry().addAll(Arrays.asList(countries));
        
        return result;
    }
    
    public static XMLGregorianCalendar createXMLGregorianCalendar(Date date) {
        XMLGregorianCalendar result;
        
        if (date == null) {
            return null;
        }
        
        GregorianCalendar time = new GregorianCalendar();
        time.setTime(date);
        try {
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(time);
        } catch (DatatypeConfigurationException ex) {
            result = null;
            String msg = "creation of XMLGregorianCalendar has failed. Input param="
                    + date;
            LOGGER.error(msg, ex);
        }
        
        return result;
    }
    
    public static ArrayOfPlaceBets createArrayOfPlaceBets(PlaceBets... bets) {
        ArrayOfPlaceBets result = new ArrayOfPlaceBets();
        result.getPlaceBets().addAll(Arrays.asList(bets));
        
        return result;
    }
    
    public static PlaceBets createPlaceBets(
            int asianLineId,
            BetCategoryTypeEnum betCategory,
            BetPersistenceTypeEnum betPersistence,
            BetTypeEnum betType,
            double bspLiability,
            int marketId, 
            int selectionId, 
            double money, 
            double price) {
        PlaceBets bet = new PlaceBets();
        
        bet.setAsianLineId(asianLineId);
        bet.setBetCategoryType(betCategory);
        bet.setBetPersistenceType(betPersistence);
        bet.setBetType(betType);
        bet.setBspLiability(bspLiability);
        bet.setMarketId(marketId);
        bet.setSelectionId(selectionId);
        bet.setSize(money);
        bet.setPrice(price);
        
        return bet;
    }
    
    /**
     * creates back bet with specified parameters
     * @param marketId
     * @param selectionId
     * @param money
     * @param price
     * @return PlaceBets instance
     */
    public static PlaceBets createBackBet(int marketId, int selectionId, double money, double price) {
        return createPlaceBets(
            0, //no handicap
            BetCategoryTypeEnum.E, //exchange
            BetPersistenceTypeEnum.NONE, //if market turns in-play and bet is unmatched, then it's canceled
            BetTypeEnum.B, //back bet
            0.0, //0.0 is constant for exchange bets
            marketId,
            selectionId,
            money,
            price
        );
    }
    
    /**
     * creates lay bet with specified parameters
     * @param marketId
     * @param selectionId
     * @param money what we are ready to bet
     * @param price we are ready to give
     * @return PlaceBets instance
     */
    public static PlaceBets createLayBet(int marketId, int selectionId, double money, double price) {
        money = money * ( 1 / (price - 1) );    //convert back price to lay and determine profit
        money = MathHelper.roundDouble(money, 2);   //how much we want to win.

        return createPlaceBets(
            0, //no handicap
            BetCategoryTypeEnum.E, //exchange
            BetPersistenceTypeEnum.NONE, //if market turns in-play and bet is unmatched, then it's canceled
            BetTypeEnum.L, //lay bet
            0.0, //0.0 is constant for exchange bets
            marketId,
            selectionId,
            money,
            price
        );

    }
    
    public static ArrayOfBetId createArrayOfBetId(long... ids) {
        ArrayOfBetId result = new ArrayOfBetId();
        
        for (long l : ids) {
            result.getBetId().add(l);
        }
        
        return result;
    }
    
}
