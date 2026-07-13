package dev.kaldiroglu.dp.structural.simpler.decorator.solution;

public class Demo {
    public static void main(String[] args) {
        Coffee[] orders = {
            new SimpleCoffee(),
            new MilkDecorator(new SimpleCoffee()),
            new SugarDecorator(new MilkDecorator(new SimpleCoffee())),
            new VanillaDecorator(new SugarDecorator(new MilkDecorator(new SimpleCoffee())))
        };
        for (Coffee c : orders) {
            System.out.println(c.description() + " — $" + c.cost());
        }
    }
}
