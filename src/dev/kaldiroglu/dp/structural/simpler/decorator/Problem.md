# Decorator — Problem

A coffee shop offers a base coffee plus optional add-ons (milk, sugar, vanilla, ...). Each add-on changes the description and the price. Customers can mix and match freely.

## Without the pattern

If we model each combination as its own subclass, we get `SimpleCoffee`, `CoffeeWithMilk`, `CoffeeWithSugar`, `CoffeeWithMilkAndSugar`, `CoffeeWithMilkAndVanilla`, `CoffeeWithMilkAndSugarAndVanilla`, ... — the classic subclass explosion. Three add-ons already give *2³ = 8* classes. The combinations are also fixed at compile time, which kills runtime configurability.

See `problem/`.

## With the Decorator pattern

We define a `Coffee` interface and one concrete `SimpleCoffee`. Every add-on is a `CoffeeDecorator` — it implements `Coffee` and *wraps* another `Coffee`, delegating and adding behavior. Decorators stack at runtime:

```java
Coffee c = new VanillaDecorator(new SugarDecorator(new MilkDecorator(new SimpleCoffee())));
```

Each new add-on is one new class, and any combination is just composition.

See `solution/`.
