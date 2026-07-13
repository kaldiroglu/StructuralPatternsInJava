package dev.kaldiroglu.dp.structural.business.composite.solution;

import java.math.BigDecimal;
import java.util.Optional;

public final class Employee implements OrgUnit {

    private final String id;
    private final String name;
    private final BigDecimal annualSalary;

    public Employee(String id, String name, BigDecimal annualSalary) {
        this.id = id;
        this.name = name;
        this.annualSalary = annualSalary;
    }

    public String getId() { return id; }

    @Override public String getName() { return name; }
    @Override public int getHeadcount() { return 1; }
    @Override public BigDecimal getTotalSalaryCost() { return annualSalary; }

    @Override
    public Optional<Employee> findEmployee(String employeeId) {
        return id.equals(employeeId) ? Optional.of(this) : Optional.empty();
    }

    @Override
    public String toString() { return "Employee{" + id + ", " + name + "}"; }
}
