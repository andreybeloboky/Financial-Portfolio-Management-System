package service;

import model.Bond;
import model.Investment;
import model.MutualFund;
import model.Stock;
import repository.InvestmentRepository;
import util.InvestmentFactory;
import util.InvestmentFormatter;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PortfolioService {
    InvestmentRepository repository = new InvestmentRepository();


    public double calculateTotalPortfolioValue() {
        List<Investment> portfolio = getAllInvestments();
        double totalSum = 0;
        for (Investment iterator : portfolio) {
            switch (iterator) {
                case Bond bond -> totalSum += bond.calculateCurrentValue();
                case Stock stock -> totalSum += stock.calculateCurrentValue();
                case MutualFund mutualFund -> totalSum += mutualFund.calculateCurrentValue();
                default -> throw new IllegalStateException("Unexpected value: " + iterator);
            }
        }
        return totalSum;
    }

    public double calculateTotalProjectedAnnualReturn() {
        List<Investment> portfolio = getAllInvestments();
        double totalSum = 0;
        for (Investment iterator : portfolio) {
            switch (iterator) {
                case Bond bond -> totalSum += bond.getProjectedAnnualReturn();
                case Stock stock -> totalSum += stock.getProjectedAnnualReturn();
                case MutualFund mutualFund -> totalSum += mutualFund.getProjectedAnnualReturn();
                default -> throw new IllegalStateException("Unexpected value: " + iterator);
            }
        }
        return totalSum;
    }

    public Map<String, Double> getAssetAllocationByType() {

        return null;
    }

    public List<Investment> findBondsMaturingIn(int year) {
        List<Investment> portfolio = getAllInvestments();
        List<Investment> bonds = new LinkedList<>();
        for (Investment iterator : portfolio) {
            if (Objects.requireNonNull(iterator, "Objects mustn't be null") instanceof Bond bond) {
                LocalDate date = bond.getMaturityDate();
                int yearBond = date.getYear();
                if (yearBond == year) {
                    bonds.add(iterator);
                }
            }
        }
        return bonds;
    }

    public Investment getHighestValueAsset() {
        Investment investment = null;
        List<Investment> portfolio = getAllInvestments();
        double current;
        double max = 0;
        for (Investment iterator : portfolio) {
            switch (iterator) {
                case Bond bond -> current = bond.calculateCurrentValue();
                case Stock stock -> current = stock.calculateCurrentValue();
                case MutualFund mutualFund -> current = mutualFund.calculateCurrentValue();
                default -> throw new IllegalStateException("Unexpected value: " + iterator);
            }
            if (current > max) {
                max = current;
                investment = iterator;
            }
        }
        return investment;
    }


    public void createInvestment(Investment newInvestment) {
        List<String> portfolioString = repository.readAllLines();
        String newPortfolio = InvestmentFormatter.toCsvString(newInvestment);
        portfolioString.add(newPortfolio);
        repository.writeAllLines(portfolioString);
    }

    public void deleteInvestment(String investmentId) {
        List<String> portfolioString = repository.readAllLines();
        List<String> updatedLines = new LinkedList<>();
        for (String iterator : portfolioString) {
            String ID = iterator.split(",")[1];
            if (!investmentId.equals(ID)) {
                updatedLines.add(iterator);
            }
        }
        repository.writeAllLines(updatedLines);
    }

    public List<Investment> getAllInvestments() {
        List<String> portfolioString = repository.readAllLines();
        List<Investment> portfolio = new LinkedList<>();
        for (String list : portfolioString) {
            Investment item = InvestmentFactory.createInvestment(list);
            portfolio.add(item);
        }
        return portfolio;
    }
}
