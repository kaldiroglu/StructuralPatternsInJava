package dev.kaldiroglu.dp.structural.simpler.decorator.problem;

public class CoffeeWithMilk extends Coffee {
    @Override public String description() { return "Coffee, milk"; }
    @Override public double cost() { return 5.0 + 1.0; }
}
