/*
 * FairLib software is published under GPLv2 license
 *
 * Author : Siarhei Skavarodkin
 * email  : komarevsky (at) gmail (dot) com
 *
 */

package com.freebetbot.fairlib.parser;

import com.freebetbot.fairlib.type.MarketPrices;
import com.freebetbot.fairlib.type.RemovedRunner;
import com.freebetbot.fairlib.type.RunnerInfo;
import com.freebetbot.fairlib.type.RunnerPrice;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class MarketPricesParser {

    private static final Log LOGGER = LogFactory.getLog(MarketPricesParser.class);
    
    /**
     * parses result of getCompleteMarketPricesCompressed
     * @param parseable what to parse
     * @return result of getCompleteMarketPricesCompressed or null if any error
     */
    public static MarketPrices parseMarketPrices(String parseable) {
        MarketPrices result = new MarketPrices();
        
        //SpeedyLogger.sendFinest("MarketPrices before parsing:\n" + parseable);
        
        try {
            //remove all esc sequences
            parseable = parseable.replace("\\:","=");
            parseable = parseable.replace("\\;","=");
            parseable = parseable.replace("\\,","=");
            parseable = parseable.replace("\\|","=");
            parseable = parseable.replace("\\~","=");

            String[] array = parseable.split(":");

            String sMarketId = array[0].split("~")[0];
            String sInPlayDelay = array[0].split("~")[1];
            int marketId = Integer.parseInt(sMarketId);
            int inPlayDelay = Integer.parseInt(sInPlayDelay);
            result.setMarketId(marketId);
            result.setInPlayDelay(inPlayDelay);
            
            //remove from string what we already parsed
            parseable = parseable.substring(sMarketId.length() + sInPlayDelay.length() + 2);
            
            List<RemovedRunner> removedRunners = new ArrayList<RemovedRunner>();
            //check if info about removed runners is present
            if (parseable.charAt(0) != ':') {
                array = parseable.split(";");
                for (int i = 0; i < array.length - 1; ++i) {
                    String selectionName = array[i].split(",")[0];
                    String removedTime = array[i].split(",")[1];
                    String reductionFactor = array[i].split(",")[2];
                    
                    RemovedRunner removedRunner = new RemovedRunner();
                    removedRunner.setSelectionName(selectionName);
                    removedRunner.setRemovedTime(removedTime);
                    removedRunner.setReductionFactor(reductionFactor);
                    
                    removedRunners.add(removedRunner);
                }
                
                //remove what we already parsed
                parseable = array[array.length - 1];
            }
            result.setRemovedRunners(removedRunners);
            
            //remove first : char
            parseable = parseable.substring(1);
            
            List<RunnerInfo> runnerInfoList = new ArrayList<RunnerInfo>();
            
            //split string on runner info
            array = parseable.split(":");
            for (String sRunnerInfo : array) {
                RunnerInfo rInfo = parseRunnerInfo(sRunnerInfo);
                if (rInfo != null) {
                    runnerInfoList.add(rInfo);
                }
            }
            result.setRunnersInfo(runnerInfoList);
                        
        } catch (Exception ex) {
            LOGGER.error("market prices parsing has failed", ex);
            result = null;
        }
        
        return result;
    }
    
    private static RunnerInfo parseRunnerInfo(String input) {
        RunnerInfo result = new RunnerInfo();
        
        try {
            String parseable = input;
            
            //replace all NaN, -INF, INF with spaces
            parseable = parseable.replace("NaN", " ");
            parseable = parseable.replace("-INF", " ");
            parseable = parseable.replace("INF", " ");

            //replace all ~~ with ~ ~
            while (parseable.contains("~~")) {
                parseable = parseable.replace("~~", "~ ~");
            }

            String[] parts = parseable.split("\\|");
            if (parts.length == 0) {
                LOGGER.warn("number of parts to parse is 0");
                return null;
            }
                        
            //part with RunnerInfo header
            String[] array = parts[0].split("~");
            
            // 11 is amount of fields of RunnerInfo(except collections)
            if (array.length == 11) {

                //selection id
                if (array[0].isEmpty() || array[0].equals(" ")) {
                    LOGGER.warn("selection id is not specified");
                    return null;
                }
                result.setSelectionId(Integer.parseInt(array[0]));
                
                if (!array[1].equals(" ")) {
                    result.setOrderIndex(Integer.parseInt(array[1]));
                }

                if (!array[2].equals(" ")) {
                    result.setTotalAmountMatched(Double.parseDouble(array[2]));
                }
                
                if (!array[3].equals(" ")) {
                    result.setLastPriceMatched(Double.parseDouble(array[3]));
                }
                
                if (!array[4].equals(" ")) {
                    result.setHandicap(Double.parseDouble(array[4]));
                }
                
                if (!array[5].equals(" ")) {
                    result.setReductionFactor(Double.parseDouble(array[5]));
                }
                
                if (!array[6].equals(" ")) {
                    result.setVacant(Boolean.parseBoolean(array[6]));
                }
                
                if (!array[7].equals(" ")) {
                    result.setAsianLineId(Integer.parseInt(array[7]));
                }
                
                if (!array[8].equals(" ")) {
                    result.setFarSPPrice(Double.parseDouble(array[8]));
                }
                
                if (!array[9].equals(" ")) {
                    result.setNearSPPrice(Double.parseDouble(array[9]));
                }
                
                if (!array[10].equals(" ")) {
                    result.setActualSPPrice(Double.parseDouble(array[10]));
                }

            } else {
                LOGGER.warn("Not found 11 fields of RunnerInfo:\n" + parseable);
                result = null;
            }
            
            List<RunnerPrice> list = new ArrayList<RunnerPrice>();
            
            //part with prices
            int numberFieldsOfRunnerPrice = 5; //5 is number of fields in RunnerPrice
            array = parts[1].split("~");
            if (array.length % numberFieldsOfRunnerPrice != 0) {
                LOGGER.warn("Number of fields is not " + numberFieldsOfRunnerPrice);
            } else {
                int numberPrices = array.length / numberFieldsOfRunnerPrice;
                for (int i = 0; i < numberPrices; ++i) {
                    RunnerPrice price = createRunnerPrice(
                            array[i*numberFieldsOfRunnerPrice + 0],
                            array[i*numberFieldsOfRunnerPrice + 1],
                            array[i*numberFieldsOfRunnerPrice + 2],
                            array[i*numberFieldsOfRunnerPrice + 3],
                            array[i*numberFieldsOfRunnerPrice + 4]);
                    if (price != null) {
                        list.add(price);
                    }
                }
            }
            
            result.setRunnerPrices(list);

        } catch (Exception ex) {
            LOGGER.error("runner info parsing has failed", ex);
            result = null;
        }

        return result;
    }
    
    private static RunnerPrice createRunnerPrice(String price, 
            String backAmountAvailable,
            String layAmountAvailable,
            String totalBSPBackAvailableAmount,
            String totalBSPLayAvailableAmount) {
        
        RunnerPrice result = new RunnerPrice();
        
        try {
            result.setPrice(Double.parseDouble(price));
            result.setBackAmountAvailable(Double.parseDouble(backAmountAvailable));
            result.setLayAmountAvailable(Double.parseDouble(layAmountAvailable));
            result.setTotalBSPBackAvailableAmount(Double.parseDouble(totalBSPBackAvailableAmount));
            result.setTotalBSPLayAvailableAmount(Double.parseDouble(totalBSPLayAvailableAmount));
        } catch(Exception ex) {
            LOGGER.error("runner price creation has failed", ex);
            result = null;
        }
        
        return result;
    }

}
