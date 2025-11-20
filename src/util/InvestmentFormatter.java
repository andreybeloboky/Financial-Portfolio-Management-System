package util;

import model.Bond;
import model.Investment;
import model.MutualFund;
import model.Stock;

public class InvestmentFormatter {

    private static final String SPLIT_COMMA = ",";
    private static final String INCORRECT_MESSAGE = "This %s doesn't exist.";

    public static String toCsvString(Investment investment) {
        return switch (investment) {
            case Bond bond ->
                    bond.getId() + SPLIT_COMMA + bond.getName() + SPLIT_COMMA + bond.getCouponRate() + SPLIT_COMMA + bond.getFaceValue() + SPLIT_COMMA + bond.getMaturityDate();
            case Stock stock ->
                    stock.getId() + SPLIT_COMMA + stock.getName() + SPLIT_COMMA + stock.getTickerSymbol() + SPLIT_COMMA + stock.getShares() + SPLIT_COMMA + stock.getCurrentSharePrice() + SPLIT_COMMA + stock.getAnnualDividendPerShare();
            case MutualFund mutualFund ->
                    mutualFund.getId() + SPLIT_COMMA + mutualFund.getName() + SPLIT_COMMA + mutualFund.getFundCode() + SPLIT_COMMA + mutualFund.getCurrentNAV() + SPLIT_COMMA + mutualFund.getUnitsHeld() + SPLIT_COMMA + mutualFund.getAvgAnnualDistribution();
            default -> throw new IllegalStateException(INCORRECT_MESSAGE.formatted(investment));
        };
    }
}
