package controller;

import model.*;
import service.PortfolioService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
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
                    case STOCK -> service.createInvestment(new Stock("1", "1", "1", 1, 1.0, 1.0));
                    case BOND -> service.createInvestment(new Bond("1", "1", 1.0, 1.0, LocalDate.of(2023, 1, 1)));
                    case MUTUALFUND -> service.createInvestment(new MutualFund("1", "1", "1", 1.0, 1.0, 1.0));
                    default -> throw new IllegalStateException("Unexpected value: " + investmentType);
                }
                break;
            case LIST:
                List<Investment> allPortfolio = service.getAllInvestments();
                for (Investment iterator : allPortfolio) {
                    switch (iterator) {
                        case Bond bond ->
                                System.out.println(bond.getId() + "," + bond.getName() + "," + bond.getCouponRate() + "," + bond.getFaceValue() + "," + bond.getMaturityDate());
                        case Stock stock ->
                                System.out.println(stock.getId() + "," + stock.getName() + "," + stock.getTickerSymbol() + "," + stock.getShares() + "," + stock.getCurrentSharePrice() + "," + stock.getAnnualDividendPerShare());
                        case MutualFund mutualFund ->
                                System.out.println(mutualFund.getId() + "," + mutualFund.getName() + "," + mutualFund.getFundCode() + "," + mutualFund.getCurrentNAV() + "," + mutualFund.getUnitsHeld() + "," + mutualFund.getAvgAnnualDistribution());
                        default -> throw new IllegalStateException("Unexpected value: " + iterator);
                    }
                }
                break;
            case DELETE:
                service.deleteInvestment(splitCommand[1]);
                break;
            case REPORT:
                System.out.println(service.calculateTotalPortfolioValue());
                break;
            case EXIT:
                System.out.println("Goodbye");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
}