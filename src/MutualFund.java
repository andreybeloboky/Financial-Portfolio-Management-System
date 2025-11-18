public class MutualFund extends Investment {
    String fundCode;
    double unitsHeld;
    double currentNAV;
    double avgAnnualDistribution;


    @Override
    double calculateCurrentValue() {
        return unitsHeld * currentNAV;
    }

    @Override
    double getProjectedAnnualReturn() {
        return unitsHeld * avgAnnualDistribution;
    }
}
