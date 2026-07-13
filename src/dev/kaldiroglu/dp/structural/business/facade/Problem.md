# Facade — Checkout Orchestration

## Scenario

Placing an order in an e-commerce platform spans many subsystems:

1. `InventoryService.reserve(cartLines)` — atomic multi-SKU reservation, may fail with `OutOfStock(sku)`.
2. `PricingService.priceCart(cart, customer)` — applies promotions, loyalty discounts.
3. `TaxService.calculateTax(priced, address)` — jurisdiction-specific.
4. `PaymentGateway.charge(amount)` — may fail.
5. `ShippingService.createShipment(order, address)` — may fail; needs to be created *after* payment.
6. `OrderRepository.save(order)`.
7. `NotificationService.sendConfirmation(customer, order)`.

The order matters. Some failures must **compensate** prior steps: a payment failure must release the inventory reservation; a shipping failure after a successful charge must trigger a refund.

## Without the pattern

Every controller/CLI/job that places an order re-implements the seven-step dance, the rollback rules, and the boundary between "user error" and "system error." Twenty lines turn into hundreds, and any subsystem signature change ripples across them. Someone forgets to release a reservation on payment failure and inventory drifts.

See `problem/CheckoutController.java` for the version where the controller is doing infrastructure work.

## With the Facade pattern

`CheckoutFacade.placeOrder(cart, customer, paymentMethod, address)` returns a typed `CheckoutResult`. The facade owns:

- The orchestration order.
- Compensation: release reservation on payment failure, refund on shipment failure.
- Mapping subsystem exceptions to a small, stable result type the controller can switch on.

The controller becomes a one-liner. New entry points (mobile API, admin tool, headless integration) all call the same facade. Subsystems can be replaced — a different payment gateway, a feature-flagged tax engine — without touching the facade's surface.

The point experienced developers must hold onto: a facade is **not** the same as a god object. The subsystems remain independently usable; the facade is the *common path*, not the only path. Listing line items in the inventory dashboard still talks to `InventoryService` directly.

See `solution/`.
