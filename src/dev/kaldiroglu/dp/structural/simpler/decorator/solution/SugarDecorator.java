package dev.kaldiroglu.dp.structural.simpler.decorator.solution;

public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee wrapped) {
        super(wrapped);
    }

    @Override
    public String description() {
        return wrapped.description() + ", sugar";
    }

    @Override
    public double cost() {
        return wrapped.cost() + 0.5;
    }
}
