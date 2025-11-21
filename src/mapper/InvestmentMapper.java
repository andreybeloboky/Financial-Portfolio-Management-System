package mapper;

import model.*;

import java.time.LocalDate;

public class InvestmentMapper {

    private static final String SPLIT_COMMA = ",";
    private static final String INCORRECT_MESSAGE = "This %s doesn't exist.";

    public static Investment createInvestment(String csvLine) {
        String[] split = csvLine.split(SPLIT_COMMA);
        InvestmentType command = InvestmentType.valueOf(split[0].toUpperCase());
        return switch (command) {
            case BOND ->
                    new Bond(split[1], split[2], Double.parseDouble(split[3]), Double.parseDouble(split[4]), LocalDate.parse(split[5]));
            case STOCK ->
                    new Stock(split[1], split[2], split[3], Integer.parseInt(split[4]), Double.parseDouble(split[5]), Double.parseDouble(split[6]));
            case MUTUAL_FUND ->
                    new MutualFund(split[1], split[2], split[3], Double.parseDouble(split[4]), Double.parseDouble(split[5]), Double.parseDouble(split[6]));
        };
    }

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
