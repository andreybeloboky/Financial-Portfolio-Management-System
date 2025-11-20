package service;

import model.Investment;
import repository.InvestmentRepository;
import util.InvestmentFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PortfolioService {
    InvestmentRepository repository = new InvestmentRepository();


    public double calculateTotalPortfolioValue() {
        return 0;
    }

    public double calculateTotalProjectedAnnualReturn() {
        return 0;
    }

    public Map<String, Double> getAssetAllocationByType() {
        return null;
    }

    public List<Investment> findBondsMaturingIn(int year) {
        return null;
    }

    public Investment getHighestValueAsset() {
        return null;
    }


    public void createInvestment(Investment newInvestment) {

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
