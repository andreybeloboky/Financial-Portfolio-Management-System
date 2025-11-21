package service;

import model.*;
import repository.InvestmentRepository;
import mapper.InvestmentMapper;

import java.time.LocalDate;
import java.util.*;

import static java.util.Objects.requireNonNull;

public class PortfolioService {

    private static final String SPLIT_COMMA = ",";
    private static final String INCORRECT_MESSAGE = "This %s doesn't exist.";

    private final InvestmentRepository repository = new InvestmentRepository();

    public double calculateTotalPortfolioValue() {
        List<Investment> portfolio = getAllInvestments();
        double totalSum = 0;
        for (Investment investment : portfolio) {
            totalSum += investment.calculateCurrentValue();
        }
        return totalSum;
    }

    public double calculateTotalProjectedAnnualReturn() {
        List<Investment> portfolio = getAllInvestments();
        double totalSum = 0;
        for (Investment investment : portfolio) {
            totalSum += investment.getProjectedAnnualReturn();
        }
        return totalSum;
    }

    public Map<String, Double> findAssetAllocationByType() {
        List<Investment> portfolio = getAllInvestments();
        Map<String, Double> assetAllocationByType = new HashMap<>();
        double bondAllocation = 0;
        double stockAllocation = 0;
        double mutualFunAllocation = 0;
        for (Investment investment : portfolio) {
            switch (investment) {
                case Bond bond -> bondAllocation += bond.calculateCurrentValue();
                case Stock stock -> stockAllocation += stock.calculateCurrentValue();
                case MutualFund mutualFund -> mutualFunAllocation += mutualFund.calculateCurrentValue();
                default -> throw new IllegalStateException(INCORRECT_MESSAGE.formatted(investment));
            }
        }
        assetAllocationByType.put(InvestmentType.STOCK.toString(), stockAllocation);
        assetAllocationByType.put(InvestmentType.BOND.toString(), bondAllocation);
        assetAllocationByType.put(InvestmentType.MUTUAL_FUND.toString(), mutualFunAllocation);
        return assetAllocationByType;
    }

    public List<Investment> findBondsMaturingIn(int year) {
        List<Investment> portfolio = getAllInvestments();
        List<Investment> bonds = new LinkedList<>();
        for (Investment investment : portfolio) {
            if (requireNonNull(investment) instanceof Bond bond) {
                LocalDate date = bond.getMaturityDate();
                int yearBond = date.getYear();
                if (yearBond == year) {
                    bonds.add(investment);
                }
            }
        }
        return bonds;
    }

    public Investment findHighestValueAsset() {
        Investment investment = null;
        List<Investment> portfolio = getAllInvestments();
        double current;
        double max = 0;
        for (Investment investmentHighestValue : portfolio) {
            current = investmentHighestValue.calculateCurrentValue();
            if (current > max) {
                max = current;
                investment = investmentHighestValue;
            }
        }
        return investment;
    }


    public void createInvestment(Investment newInvestment) {
        List<String> portfolioString = repository.readAllLines();
        String newPortfolio = InvestmentMapper.toCsvString(newInvestment);
        portfolioString.add(newPortfolio);
        repository.writeAllLines(portfolioString);
    }

    public void deleteInvestment(String investmentId) {
        List<String> portfolioString = repository.readAllLines();
        List<String> updatedLines = new LinkedList<>();
        for (String investment : portfolioString) {
            String ID = investment.split(SPLIT_COMMA)[1];
            if (!investmentId.equals(ID)) {
                updatedLines.add(investment);
            }
        }
        repository.writeAllLines(updatedLines);
    }

    public List<Investment> getAllInvestments() {
        List<String> portfolioString = repository.readAllLines();
        List<Investment> portfolio = new LinkedList<>();
        for (String list : portfolioString) {
            Investment item = InvestmentMapper.createInvestment(list);
            portfolio.add(item);
        }
        return portfolio;
    }
}
