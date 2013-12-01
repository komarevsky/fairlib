package com.freebetbot.fairlib.type;

/**
 *
 * @author Siarhei Skavarodkin
 */
public class RemovedRunner {
    private String selectionName;
    private String removedTime;
    private String reductionFactor;

    public RemovedRunner() {}
    
    public RemovedRunner(String selectionName, String removedTime, String reductionFactor) {
        this.selectionName = selectionName;
        this.removedTime = removedTime;
        this.reductionFactor = reductionFactor;
    }

    public String getReductionFactor() {
        return reductionFactor;
    }

    public void setReductionFactor(String reductionFactor) {
        this.reductionFactor = reductionFactor;
    }

    public String getRemovedTime() {
        return removedTime;
    }

    public void setRemovedTime(String removedTime) {
        this.removedTime = removedTime;
    }

    public String getSelectionName() {
        return selectionName;
    }

    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
    }

    @Override
    public String toString() {
        return "RemovedRunner{" 
                + "\n    selectionName=" + selectionName 
                + "\n    removedTime=" + removedTime
                + "\n    reductionFactor=" + reductionFactor 
                + "\n}\n";
    }
    
    
}
