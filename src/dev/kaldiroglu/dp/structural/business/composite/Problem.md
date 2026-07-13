# Composite — Org Hierarchy Rollup

## Scenario

An HR/finance application needs to answer questions about the company's organizational chart:

- Total annual salary cost for any subtree (a department, a team, the whole company).
- Total headcount under a node.
- Find an employee by id from any starting point.
- Walk the tree to apply a policy (e.g., "every IC reporting to a director must have a manager between them").

The org chart is genuinely recursive: a department contains employees and other departments; the company itself is a department of departments.

## Without the pattern

`Employee` and `Department` are unrelated types. Every consumer that walks the chart writes its own `instanceof` ladder, duplicates the recursion, and conflates the structure traversal with the question being asked. Add a third node type later (a *contractor pool*?) and every walker breaks until it's updated.

See `problem/HrReports.java` for the version where every report repeats the type-test recursion.

## With the Composite pattern

Both `Employee` and `Department` implement `OrgUnit` with a uniform contract:

```java
String getName();
int getHeadcount();
BigDecimal getTotalSalaryCost();
Optional<Employee> findEmployee(String id);
```

The `Department.getTotalSalaryCost()` recurses by summing `child.getTotalSalaryCost()` over its children — leaves and composites alike. Callers operate on `OrgUnit` and never branch on type. New node types only need to implement the four methods.

What experienced developers must still get right:

- **Cycle protection** — a department list of `OrgUnit` accidentally containing one of its ancestors will infinite-recurse. The implementation guards against this.
- **Find performance** — `findEmployee` is a depth-first walk; for large trees, callers should bias toward iterative or indexed lookups when this matters.

See `solution/`.
