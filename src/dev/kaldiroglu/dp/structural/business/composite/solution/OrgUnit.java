package dev.kaldiroglu.dp.structural.business.composite.solution;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrgUnit {
    String getName();
    int getHeadcount();
    BigDecimal getTotalSalaryCost();
    Optional<Employee> findEmployee(String id);
}
