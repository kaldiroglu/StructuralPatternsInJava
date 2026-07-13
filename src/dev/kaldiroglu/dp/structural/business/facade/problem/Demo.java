package dev.kaldiroglu.dp.structural.business.facade.problem;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        CheckoutController c = new CheckoutController();
        Cart cart = new Cart("cust-1", List.of(new Cart.CartLine("SKU-A", 2)));
        System.out.println(c.placeOrder(cart, "card-good", "94107"));
        System.out.println(c.placeOrder(cart, "declined",  "94107"));
        System.out.println(c.placeOrder(cart, "card-good", "99999"));
    }
}
