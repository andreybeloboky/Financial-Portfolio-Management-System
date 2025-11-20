package controller;

import model.Bond;
import model.Investment;
import model.MutualFund;
import model.Stock;
import service.PortfolioService;

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
            case REPORT:

            case EXIT:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
}