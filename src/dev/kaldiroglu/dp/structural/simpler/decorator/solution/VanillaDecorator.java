package dev.kaldiroglu.dp.structural.simpler.decorator.solution;

public class VanillaDecorator extends CoffeeDecorator {

    public VanillaDecorator(Coffee wrapped) {
        super(wrapped);
    }

    @Override
    public String description() {
        return wrapped.description() + ", vanilla";
    }

    @Override
    public double cost() {
        return wrapped.cost() + 0.75;
    }
}
