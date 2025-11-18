import java.time.LocalDate;

public class Bond extends Investment {

    private double faceValue;
    private double couponRate;
    private LocalDate maturityDate;

    @Override
    double calculateCurrentValue() {
        return faceValue;
    }

    @Override
    double getProjectedAnnualReturn() {
        return faceValue * couponRate;
    }
}
