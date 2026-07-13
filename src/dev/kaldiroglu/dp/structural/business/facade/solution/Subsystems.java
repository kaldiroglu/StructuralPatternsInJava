package dev.kaldiroglu.dp.structural.business.facade.solution;

import java.math.BigDecimal;

public final class Subsystems {

    public static class InventoryService {
        public String reserve(Cart cart) {
            for (Cart.CartLine l : cart.lines()) {
                if ("OOS".equals(l.sku())) throw new RuntimeException("OutOfStock:" + l.sku());
            }
            return "RES-" + cart.customerId();
        }
        public void release(String reservationId) { System.out.println("released " + reservationId); }
    }

    public static class PricingService {
        public BigDecimal price(Cart cart) { return new BigDecimal(cart.lines().size() * 25); }
    }

    public static class TaxService {
        public BigDecimal tax(BigDecimal subtotal, String addressZip) {
            return subtotal.multiply(new BigDecimal("0.08"));
        }
    }

    public static class PaymentGateway {
        public String charge(String customerId, BigDecimal amount, String paymentMethod) {
            if ("declined".equals(paymentMethod)) throw new RuntimeException("payment declined");
            return "PAY-" + customerId;
        }
        public void refund(String paymentId) { System.out.println("refunded " + paymentId); }
    }

    public static class ShippingService {
        public String createShipment(String orderId, String addressZip) {
            if ("99999".equals(addressZip)) throw new RuntimeException("undeliverable");
            return "SHIP-" + orderId;
        }
    }

    public static class OrderRepository {
        public void save(String orderId, Cart cart, BigDecimal total) {
            System.out.println("saved " + orderId + " total=$" + total);
        }
    }

    public static class NotificationService {
        public void sendConfirmation(String customerId, String orderId) {
            System.out.println("notified " + customerId + " for " + orderId);
        }
    }
}
