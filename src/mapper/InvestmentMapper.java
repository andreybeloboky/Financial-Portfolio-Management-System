package mapper;

import model.*;

import java.time.LocalDate;

public class InvestmentMapper {

    private static final String SPLIT = ",";

    public static Investment createInvestment(String csvLine) {
        String[] split = csvLine.split(SPLIT);
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
}
