package dev.kaldiroglu.dp.structural.business.composite.solution;

import java.math.BigDecimal;

public class Demo {
    public static void main(String[] args) {
        OrgUnit company = new Department("Acme")
            .add(new Department("Engineering")
                .add(new Department("Platform")
                    .add(new Employee("e1", "Ada",  new BigDecimal("180000")))
                    .add(new Employee("e2", "Brad", new BigDecimal("160000"))))
                .add(new Employee("e3", "Cleo", new BigDecimal("210000"))))
            .add(new Employee("e4", "Dax", new BigDecimal("250000")));

        System.out.println("Total cost: $" + company.getTotalSalaryCost());
        System.out.println("Headcount:  " + company.getHeadcount());
        System.out.println("Find e2:    " + company.findEmployee("e2").orElse(null));
        System.out.println("Find none:  " + company.findEmployee("nope").orElse(null));
    }
}
