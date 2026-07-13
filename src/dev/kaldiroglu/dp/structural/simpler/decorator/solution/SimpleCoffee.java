package dev.kaldiroglu.dp.structural.simpler.decorator.solution;

public class SimpleCoffee implements Coffee {
    @Override public String description() { return "Coffee"; }
    @Override public double cost() { return 5.0; }
}
