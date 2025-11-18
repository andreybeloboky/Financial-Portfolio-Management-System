package service;

import repository.InvestmentRepository;

import java.util.List;
import java.util.Map;

public class PortfolioService {
    private InvestmentRepository repository;

    public PortfolioService(InvestmentRepository repository) {
        this.repository = repository;
    }

    //  Аналитика
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

    //  CRUD
    public void createInvestment(Investment newInvestment) {

    }

    public void deleteInvestment(String investmentId) {

    }

    public List<Investment> getAllInvestments() {
        return null;
    }
}
