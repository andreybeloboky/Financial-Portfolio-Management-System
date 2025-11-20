package model;

import java.time.LocalDate;

public class Bond extends Investment {

    private final double faceValue;
    private final double couponRate;
    private final LocalDate maturityDate;

    public Bond(String id, String name, double faceValue, double couponRate, LocalDate maturityDate) {
        super(id, name);
        this.faceValue = faceValue;
        this.couponRate = couponRate;
        this.maturityDate = maturityDate;
    }

    public double getFaceValue() {
        return faceValue;
    }

    public double getCouponRate() {
        return couponRate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    @Override
    public double calculateCurrentValue() {
        return faceValue;
    }

    @Override
    public double getProjectedAnnualReturn() {
        return faceValue * couponRate;
    }
}
