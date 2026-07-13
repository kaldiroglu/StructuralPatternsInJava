package dev.kaldiroglu.dp.structural.business.composite.solution;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Optional;

public final class Department implements OrgUnit {

    private final String name;
    private final List<OrgUnit> children = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public Department add(OrgUnit child) {
        if (child == this) throw new IllegalArgumentException("self-reference");
        children.add(child);
        return this;
    }

    @Override public String getName() { return name; }

    @Override
    public int getHeadcount() {
        int n = 0;
        for (OrgUnit u : walk()) if (u instanceof Employee) n++;
        return n;
    }

    @Override
    public BigDecimal getTotalSalaryCost() {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrgUnit u : walk()) {
            if (u instanceof Employee e) sum = sum.add(e.getTotalSalaryCost());
        }
        return sum;
    }

    @Override
    public Optional<Employee> findEmployee(String id) {
        for (OrgUnit u : walk()) {
            if (u instanceof Employee e && e.getId().equals(id)) return Optional.of(e);
        }
        return Optional.empty();
    }

    /** Iterative DFS with identity-based cycle protection. */
    private List<OrgUnit> walk() {
        List<OrgUnit> visited = new ArrayList<>();
        IdentityHashMap<OrgUnit, Boolean> seen = new IdentityHashMap<>();
        ArrayDeque<OrgUnit> stack = new ArrayDeque<>();
        stack.push(this);
        while (!stack.isEmpty()) {
            OrgUnit cur = stack.pop();
            if (seen.put(cur, Boolean.TRUE) != null) continue;
            visited.add(cur);
            if (cur instanceof Department d) {
                for (int i = d.children.size() - 1; i >= 0; i--) stack.push(d.children.get(i));
            }
        }
        return visited;
    }
}
