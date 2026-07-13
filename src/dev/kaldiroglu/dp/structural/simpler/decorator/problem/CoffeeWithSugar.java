package dev.kaldiroglu.dp.structural.simpler.decorator.problem;

public class CoffeeWithSugar extends Coffee {
    @Override public String description() { return "Coffee, sugar"; }
    @Override public double cost() { return 5.0 + 0.5; }
}
