package dev.applaudostudios.examples.assignmentweek4.logic.payment;

import java.util.UUID;

public interface PaymentMethod {
    boolean isValid(String paymentInformation);
    boolean pay(String paymentInformation, UUID paymentOrderId);
}
