package util;

import model.Bond;
import model.Investment;
import model.MutualFund;
import model.Stock;

public class InvestmentFormatter {

    public static String toCsvString(Investment investment) {
        return switch (investment) {
            case Bond bond ->
                    bond.getId() + "," + bond.getName() + "," + bond.getCouponRate() + "," + bond.getFaceValue() + "," + bond.getMaturityDate();
            case Stock stock ->
                    stock.getId() + "," + stock.getName() + "," + stock.getTickerSymbol() + "," + stock.getShares() + "," + stock.getCurrentSharePrice() + "," + stock.getAnnualDividendPerShare();
            case MutualFund mutualFund ->
                    mutualFund.getId() + "," + mutualFund.getName() + "," + mutualFund.getFundCode() + "," + mutualFund.getCurrentNAV() + "," + mutualFund.getUnitsHeld() + "," + mutualFund.getAvgAnnualDistribution();
            default -> throw new IllegalStateException("Unexpected value: " + investment);
        };
    }
}
