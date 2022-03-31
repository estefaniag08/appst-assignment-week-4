package dev.applaudostudios.examples.assignmentweek4.logic.store;

import dev.applaudostudios.examples.assignmentweek4.common.Item;
import dev.applaudostudios.examples.assignmentweek4.common.notification.Observable;
import dev.applaudostudios.examples.assignmentweek4.common.notification.Observer;

import dev.applaudostudios.examples.assignmentweek4.logic.payment.PaymentMethod;


import java.util.Iterator;

public class SimpleCart extends Cart implements Observable {

    private Inventory<?> inventory;
    private Observer<String> notificationObserver;

    public SimpleCart(PaymentMethod paymentMethod, Inventory<?> inventory) {
        super(paymentMethod);
        this.inventory = inventory;
    }

    @Override
    public boolean validatePaymentInformation(String paymentInformation) {
        if (getPaymentMethod().isValid(paymentInformation) && getOrder().getOrderList().getTotal() > 0) {
            getOrder().reserveOrder();
            System.out.print("Reservation Code: ");
            System.out.println(getOrder().getReservationCode().toString());

            notificationObserver.updated("Funds reserved");
            return true;
        }
        System.out.println("Cart couldn't be processed");
        return false;
    }

    public boolean validateCart() {
        Iterator<?> itemsIterator = getOrder().getOrderList().getListOfItems();

        while (itemsIterator.hasNext()) {
            Item item = (Item) itemsIterator.next();
            Item inventoryItem = inventory.showInventory().get(item.getCode());
            if (!this.inventory.reserve(inventoryItem, item.getUnits())) {
                System.out.println("The item couldn't be reserved");
                notificationObserver.updated("Inventory not updated");
                return false;
            }
        }
        notificationObserver.updated("Inventory updated");
        return true;
    }

    @Override
    public boolean payCart(String paymentInformation) {
        getOrder().validateOrder();
        if (getPaymentMethod().pay(paymentInformation, getOrder().getVerificationCode())) {
            System.out.print("Payment code: ");
            System.out.println(getOrder().getVerificationCode().toString());
            notificationObserver.updated("Paid order.");
            return true;
        }
        System.out.println("There was a problem with your payment.");
        return false;
    }

    @Override
    public boolean finalizeCart() {
        getOrder().generateOrder();
        System.out.print("Order Id: ");
        System.out.println(getOrder().getOrderCode().toString());
        System.out.print("Total: ");
        System.out.println(getOrder().getOrderList().getTotal());
        return true;
    }

    @Override
    public void attach(Observer observer) {
        notificationObserver = observer;
    }

    @Override
    public void detach(Observer observer) {
        notificationObserver = null;
    }
}
