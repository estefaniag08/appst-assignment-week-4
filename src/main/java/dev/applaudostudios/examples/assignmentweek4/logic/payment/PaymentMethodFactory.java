package dev.applaudostudios.examples.assignmentweek4.logic.payment;

public class PaymentMethodFactory {

    public PaymentMethod getPaymentMethod(String selectedPayment){
        switch (selectedPayment){
            default -> {
                return new CreditCardPayment();
            }
        }
    }
}
