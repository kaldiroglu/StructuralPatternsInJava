package dev.kaldiroglu.dp.structural.business.facade.solution;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        CheckoutFacade facade = new CheckoutFacade(
            new Subsystems.InventoryService(),
            new Subsystems.PricingService(),
            new Subsystems.TaxService(),
            new Subsystems.PaymentGateway(),
            new Subsystems.ShippingService(),
            new Subsystems.OrderRepository(),
            new Subsystems.NotificationService()
        );

        CheckoutController controller = new CheckoutController(facade);
        Cart cart = new Cart("cust-1", List.of(new Cart.CartLine("SKU-A", 2)));

        print(controller.placeOrder(cart, "card-good", "94107"));
        print(controller.placeOrder(cart, "declined",  "94107"));
        print(controller.placeOrder(cart, "card-good", "99999"));
    }

    private static void print(CheckoutResult r) {
        System.out.println(switch (r) {
            case CheckoutResult.Placed p -> "OK:" + p.orderId();
            case CheckoutResult.Failed f -> "FAILED:" + f.reason() + " (" + f.detail() + ")";
        });
    }
}
