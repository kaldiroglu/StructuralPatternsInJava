package dev.kaldiroglu.dp.structural.simpler.decorator.problem;

public class Demo {
    public static void main(String[] args) {
        Coffee[] menu = {
            new SimpleCoffee(),
            new CoffeeWithMilk(),
            new CoffeeWithSugar(),
            new CoffeeWithMilkAndSugar()
            // Add vanilla? Need: CoffeeWithVanilla, CoffeeWithMilkAndVanilla,
            // CoffeeWithSugarAndVanilla, CoffeeWithMilkAndSugarAndVanilla...
        };
        for (Coffee c : menu) {
            System.out.println(c.description() + " — $" + c.cost());
        }
    }
}
