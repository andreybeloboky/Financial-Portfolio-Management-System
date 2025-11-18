abstract class Investment {
    private String id;
    private String name;

    abstract double calculateCurrentValue();

    abstract double getProjectedAnnualReturn();
}
