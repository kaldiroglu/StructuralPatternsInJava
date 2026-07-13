package dev.kaldiroglu.dp.structural.business.facade.solution;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Orchestrates the checkout flow. Owns the order, the compensation rules,
 * and the mapping from subsystem exceptions to a stable result type.
 */
public class CheckoutFacade {

    private final Subsystems.InventoryService inventory;
    private final Subsystems.PricingService pricing;
    private final Subsystems.TaxService tax;
    private final Subsystems.PaymentGateway payment;
    private final Subsystems.ShippingService shipping;
    private final Subsystems.OrderRepository orders;
    private final Subsystems.NotificationService notify;

    public CheckoutFacade(Subsystems.InventoryService inventory,
                          Subsystems.PricingService pricing,
                          Subsystems.TaxService tax,
                          Subsystems.PaymentGateway payment,
                          Subsystems.ShippingService shipping,
                          Subsystems.OrderRepository orders,
                          Subsystems.NotificationService notify) {
        this.inventory = inventory;
        this.pricing = pricing;
        this.tax = tax;
        this.payment = payment;
        this.shipping = shipping;
        this.orders = orders;
        this.notify = notify;
    }

    public CheckoutResult placeOrder(Cart cart, String paymentMethod, String addressZip) {
        String reservation;
        try {
            reservation = inventory.reserve(cart);
        } catch (RuntimeException e) {
            return new CheckoutResult.Failed(CheckoutResult.Reason.OUT_OF_STOCK, e.getMessage());
        }

        BigDecimal subtotal = pricing.price(cart);
        BigDecimal total = subtotal.add(tax.tax(subtotal, addressZip));

        String paymentId;
        try {
            paymentId = payment.charge(cart.customerId(), total, paymentMethod);
        } catch (RuntimeException e) {
            inventory.release(reservation);
            return new CheckoutResult.Failed(CheckoutResult.Reason.PAYMENT_DECLINED, e.getMessage());
        }

        String orderId = "ORD-" + UUID.randomUUID();
        try {
            shipping.createShipment(orderId, addressZip);
        } catch (RuntimeException e) {
            payment.refund(paymentId);
            inventory.release(reservation);
            return new CheckoutResult.Failed(CheckoutResult.Reason.UNDELIVERABLE, e.getMessage());
        }

        orders.save(orderId, cart, total);
        notify.sendConfirmation(cart.customerId(), orderId);
        return new CheckoutResult.Placed(orderId);
    }
}
