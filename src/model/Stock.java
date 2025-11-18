package model;

public class Stock extends Investment {
    String tickerSymbol;
    int shares;
    double currentSharePrice;
    double annualDividendPerShare;

    @Override
    double calculateCurrentValue() {
        return shares * currentSharePrice;
    }

    @Override
    double getProjectedAnnualReturn() {
        return shares * annualDividendPerShare;
    }
}
