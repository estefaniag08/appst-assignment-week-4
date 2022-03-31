package dev.applaudostudios.examples.assignmentweek4.logic.orders;

import java.util.UUID;

public class VerifiedOrderState implements IOrderState{
    @Override
    public void handle(Order order) {
        UUID uuid = UUID.randomUUID();
        order.setVerificationCode(uuid);
    }
}
