package dev.applaudostudios.examples.assignmentweek4.logic.payment;

import java.util.UUID;

public class CreditCardPayment implements PaymentMethod{

    @Override
    public boolean isValid(String paymentInformation) {
        int cardNumber = Integer.parseInt(paymentInformation);
        return cardNumber >= 4111 && cardNumber <= 4200;
    }

    @Override
    public boolean pay(String paymentInformation, UUID paymentOrderId) {
        if(isValid(paymentInformation)){
            return true;
        }
        return false;
    }
}
