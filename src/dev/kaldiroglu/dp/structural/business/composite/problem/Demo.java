package dev.kaldiroglu.dp.structural.business.composite.problem;

import java.math.BigDecimal;

public class Demo {
    public static void main(String[] args) {
        Department company = new Department("Acme");
        Department eng = new Department("Engineering");
        Department platform = new Department("Platform");
        platform.add(new Employee("e1", "Ada",  new BigDecimal("180000")));
        platform.add(new Employee("e2", "Brad", new BigDecimal("160000")));
        eng.add(platform);
        eng.add(new Employee("e3", "Cleo", new BigDecimal("210000")));
        company.add(eng);
        company.add(new Employee("e4", "Dax", new BigDecimal("250000")));

        HrReports hr = new HrReports();
        System.out.println("Total cost: $" + hr.totalSalaryCost(company));
        System.out.println("Headcount:  " + hr.headcount(company));
        System.out.println("Find e2:    " + hr.findEmployee(company, "e2"));
    }
}
