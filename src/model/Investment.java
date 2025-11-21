package model;

public abstract class Investment {

    protected final String id;
    protected final String name;

    public Investment(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateCurrentValue();

    public abstract double getProjectedAnnualReturn();
}
