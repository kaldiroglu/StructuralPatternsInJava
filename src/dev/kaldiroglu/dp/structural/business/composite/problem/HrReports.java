package dev.kaldiroglu.dp.structural.business.composite.problem;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Every report duplicates the recursion + type-test pattern. Adding a new
 * node type means hunting down every method like these and updating it.
 */
public class HrReports {

    public BigDecimal totalSalaryCost(Object node) {
        if (node instanceof Employee e) {
            return e.getAnnualSalary();
        } else if (node instanceof Department d) {
            BigDecimal sum = BigDecimal.ZERO;
            for (Object child : d.getChildren()) sum = sum.add(totalSalaryCost(child));
            return sum;
        }
        throw new IllegalArgumentException("Unknown node type");
    }

    public int headcount(Object node) {
        if (node instanceof Employee) return 1;
        if (node instanceof Department d) {
            int n = 0;
            for (Object child : d.getChildren()) n += headcount(child);
            return n;
        }
        throw new IllegalArgumentException("Unknown node type");
    }

    public Optional<Employee> findEmployee(Object node, String id) {
        if (node instanceof Employee e) {
            return e.getId().equals(id) ? Optional.of(e) : Optional.empty();
        }
        if (node instanceof Department d) {
            for (Object child : d.getChildren()) {
                Optional<Employee> hit = findEmployee(child, id);
                if (hit.isPresent()) return hit;
            }
            return Optional.empty();
        }
        throw new IllegalArgumentException("Unknown node type");
    }
}
