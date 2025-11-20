package controller;

import model.*;
import service.PortfolioService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static final String EXIT_MESSAGE = "Goodbye";
    private static final String SPLIT = ",";
    private static final String ENTER_YEAR_MESSAGE = "Enter the year which you want to get:";


    public static void main(String[] args) {
        PortfolioService service = new PortfolioService();
        Scanner scanner = new Scanner(System.in);
        String userCommand = scanner.nextLine();
        String[] splitCommand = userCommand.split(" ");
        Command command = Command.valueOf(splitCommand[0].toUpperCase());
        switch (command) {
            case ADD:
                InvestmentType investmentType = InvestmentType.valueOf(splitCommand[1].toUpperCase());
                switch (investmentType) {
                    case STOCK -> {
                        Stock newInvestment = new Stock("ID321", "Microsoft Corp.", "MSFT", 75, 310.50, 2.25);
                        service.createInvestment(newInvestment);
                    }
                    case BOND -> {
                        Bond newInvestment = new Bond("ID654", "Corporate Bond XYZ", 5000, 0.045, LocalDate.of(2028, 6, 30));
                        service.createInvestment(newInvestment);
                    }
                    case MUTUALFUND -> {
                        MutualFund newInvestment = new MutualFund("ID987", "Emerging Markets Fund", "EMF456", 120.75, 18.40, 0.95);
                        service.createInvestment(newInvestment);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + investmentType);
                }
                break;
            case LIST:
                List<Investment> allPortfolio = service.getAllInvestments();
                for (Investment iterator : allPortfolio) {
                    switch (iterator) {
                        case Bond bond ->
                                System.out.println(bond.getId() + SPLIT + bond.getName() + SPLIT + bond.getCouponRate() + SPLIT + bond.getFaceValue() + SPLIT + bond.getMaturityDate());
                        case Stock stock ->
                                System.out.println(stock.getId() + SPLIT + stock.getName() + SPLIT + stock.getTickerSymbol() + SPLIT + stock.getShares() + SPLIT + stock.getCurrentSharePrice() + SPLIT + stock.getAnnualDividendPerShare());
                        case MutualFund mutualFund ->
                                System.out.println(mutualFund.getId() + SPLIT + mutualFund.getName() + SPLIT + mutualFund.getFundCode() + SPLIT + mutualFund.getCurrentNAV() + SPLIT + mutualFund.getUnitsHeld() + SPLIT + mutualFund.getAvgAnnualDistribution());
                        default -> throw new IllegalStateException("Unexpected value: " + iterator);
                    }
                }
                break;
            case DELETE:
                service.deleteInvestment(splitCommand[1]);
                break;
            case REPORT:
                CommandReport commandReport = CommandReport.valueOf(splitCommand[1].toUpperCase());
                switch (commandReport) {
                    case VALUE -> System.out.println(service.calculateTotalPortfolioValue());
                    case RETURN -> System.out.println(service.calculateTotalProjectedAnnualReturn());
                    case HIGHEST -> {
                        Investment s = service.getHighestValueAsset();
                        System.out.println(s.getName());
                    }
                    case ALLOCATION -> {
                        Map<String, Double> assetAllocationByType = service.getAssetAllocationByType();
                        for (Map.Entry<String, Double> entry : assetAllocationByType.entrySet()) {
                            System.out.println("Key: " + entry.getKey() + ", value: " + entry.getValue());
                        }
                    }
                    case YEAR -> {
                        System.out.println(ENTER_YEAR_MESSAGE);
                        int year = scanner.nextInt();
                        List<Investment> bondInvestment = service.findBondsMaturingIn(year);
                        if (!bondInvestment.isEmpty()) {
                            for (Investment bondIterator : bondInvestment) {
                                if (Objects.requireNonNull(bondIterator) instanceof Bond bond) {
                                    System.out.println(bond.getId() + SPLIT + bond.getName());
                                }
                            }
                        } else {
                            System.out.println("There is no such a year in this list");
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + commandReport);
                }
                break;
            case EXIT:
                System.out.println(EXIT_MESSAGE);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
}