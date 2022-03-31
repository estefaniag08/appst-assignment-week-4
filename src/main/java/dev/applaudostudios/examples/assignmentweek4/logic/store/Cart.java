package dev.applaudostudios.examples.assignmentweek4.logic.store;

import dev.applaudostudios.examples.assignmentweek4.logic.orders.Order;
import dev.applaudostudios.examples.assignmentweek4.logic.payment.PaymentMethod;

public abstract class Cart {
    private Order order;
    private final PaymentMethod paymentMethod;

    public Cart(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;

    }

    public boolean processCart(String paymentInformation) {
        if (this.order != null) {
            if (validatePaymentInformation(paymentInformation) && validateCart()) {
                if (payCart(paymentInformation)) {
                    return finalizeCart();
                }
            }
        }
        return false;
    }

    public abstract boolean validatePaymentInformation(String paymentInformation);

    public abstract boolean validateCart();

    public abstract boolean payCart(String paymentInformation);

    public abstract boolean finalizeCart();

    public final void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }

    public final PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }
}
