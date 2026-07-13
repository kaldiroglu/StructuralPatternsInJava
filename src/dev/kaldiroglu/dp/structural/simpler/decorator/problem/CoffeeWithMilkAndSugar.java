package dev.kaldiroglu.dp.structural.simpler.decorator.problem;

public class CoffeeWithMilkAndSugar extends Coffee {
    @Override public String description() { return "Coffee, milk, sugar"; }
    @Override public double cost() { return 5.0 + 1.0 + 0.5; }
}
