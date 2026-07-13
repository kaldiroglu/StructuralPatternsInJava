package dev.kaldiroglu.dp.structural.simpler.decorator.solution;

public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee wrapped;

    protected CoffeeDecorator(Coffee wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String description() {
        return wrapped.description();
    }

    @Override
    public double cost() {
        return wrapped.cost();
    }
}
