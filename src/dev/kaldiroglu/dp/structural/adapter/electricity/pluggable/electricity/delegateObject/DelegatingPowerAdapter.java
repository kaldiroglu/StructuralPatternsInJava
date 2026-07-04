package dev.kaldiroglu.dp.structural.adapter.electricity.pluggable.electricity.delegateObject;

/**
 * <b>Pluggable adapter via a delegate object</b> &ndash; GoF technique (b), p. 143. One adapter
 * class serves any adaptee: swap the {@link PowerDelivery} delegate to re-target, no subclassing.
 */
public final class DelegatingPowerAdapter implements TurkishPowerSource {

    private final PowerDelivery delivery;

    public DelegatingPowerAdapter(PowerDelivery delivery) {
        this.delivery = delivery;
    }

    @Override
    public void providePowerAt220V() {
        System.out.println("DelegatingPowerAdapter: Converting from 110V to 220V");
    }

    @Override
    public void turnOn() {
        providePowerAt220V();delivery.deliver();
    }

    @Override
    public void turnOff() {
        delivery.cut();
    }
}
