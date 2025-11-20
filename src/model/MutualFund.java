package model;

public class MutualFund extends Investment {
    String fundCode;
    double unitsHeld;
    double currentNAV;
    double avgAnnualDistribution;

    public MutualFund(String id, String name, String fundCode, double currentNAV, double avgAnnualDistribution, double unitsHeld) {
        super(id, name);
        this.fundCode = fundCode;
        this.currentNAV = currentNAV;
        this.avgAnnualDistribution = avgAnnualDistribution;
        this.unitsHeld = unitsHeld;
    }

    public String getFundCode() {
        return fundCode;
    }

    public double getUnitsHeld() {
        return unitsHeld;
    }

    public double getCurrentNAV() {
        return currentNAV;
    }

    public double getAvgAnnualDistribution() {
        return avgAnnualDistribution;
    }

    @Override
    public double calculateCurrentValue() {
        return unitsHeld * currentNAV;
    }

    @Override
    public double getProjectedAnnualReturn() {
        return unitsHeld * avgAnnualDistribution;
    }
}
