package dev.kaldiroglu.dp.structural.simpler.decorator.solution;

public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee wrapped) {
        super(wrapped);
    }

    @Override
    public String description() {
        return wrapped.description() + ", milk";
    }

    @Override
    public double cost() {
        return wrapped.cost() + 1.0;
    }
}
