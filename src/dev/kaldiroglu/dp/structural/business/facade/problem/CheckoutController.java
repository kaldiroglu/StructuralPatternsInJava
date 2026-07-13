package dev.kaldiroglu.dp.structural.business.facade.problem;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * The controller does infrastructure work: orchestration, compensation, and
 * exception mapping all live here. Every other entry point that places an order
 * (mobile API, CSV import, admin tool) will copy this dance.
 */
public class CheckoutController {

    private final Subsystems.InventoryService inventory = new Subsystems.InventoryService();
    private final Subsystems.PricingService pricing = new Subsystems.PricingService();
    private final Subsystems.TaxService tax = new Subsystems.TaxService();
    private final Subsystems.PaymentGateway payment = new Subsystems.PaymentGateway();
    private final Subsystems.ShippingService shipping = new Subsystems.ShippingService();
    private final Subsystems.OrderRepository orders = new Subsystems.OrderRepository();
    private final Subsystems.NotificationService notify = new Subsystems.NotificationService();

    public String placeOrder(Cart cart, String paymentMethod, String addressZip) {
        String reservation;
        try {
            reservation = inventory.reserve(cart);
        } catch (RuntimeException e) {
            return "FAILED:OUT_OF_STOCK:" + e.getMessage();
        }

        BigDecimal subtotal = pricing.price(cart);
        BigDecimal total = subtotal.add(tax.tax(subtotal, addressZip));

        String paymentId;
        try {
            paymentId = payment.charge(cart.customerId(), total, paymentMethod);
        } catch (RuntimeException e) {
            inventory.release(reservation);
            return "FAILED:PAYMENT:" + e.getMessage();
        }

        String orderId = "ORD-" + UUID.randomUUID();
        try {
            shipping.createShipment(orderId, addressZip);
        } catch (RuntimeException e) {
            payment.refund(paymentId);
            inventory.release(reservation);
            return "FAILED:SHIPPING:" + e.getMessage();
        }

        orders.save(orderId, cart, total);
        notify.sendConfirmation(cart.customerId(), orderId);
        return "OK:" + orderId;
    }
}
