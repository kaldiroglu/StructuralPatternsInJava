package dev.kaldiroglu.dp.structural.business.facade.solution;

/** Controller is now a one-liner. The facade owns orchestration. */
public class CheckoutController {

    private final CheckoutFacade checkout;

    public CheckoutController(CheckoutFacade checkout) {
        this.checkout = checkout;
    }

    public CheckoutResult placeOrder(Cart cart, String paymentMethod, String addressZip) {
        return checkout.placeOrder(cart, paymentMethod, addressZip);
    }
}
