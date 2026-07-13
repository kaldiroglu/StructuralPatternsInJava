package dev.kaldiroglu.dp.structural.business.composite.problem;

import java.math.BigDecimal;

public class Employee {
    private final String id;
    private final String name;
    private final BigDecimal annualSalary;

    public Employee(String id, String name, BigDecimal annualSalary) {
        this.id = id;
        this.name = name;
        this.annualSalary = annualSalary;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getAnnualSalary() { return annualSalary; }
}
