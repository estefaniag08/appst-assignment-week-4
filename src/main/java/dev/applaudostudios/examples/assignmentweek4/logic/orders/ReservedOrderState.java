package dev.applaudostudios.examples.assignmentweek4.logic.orders;

import java.util.UUID;

public class ReservedOrderState implements IOrderState{

    @Override
    public void handle(Order order) {
        UUID uuid = UUID.randomUUID();
        order.setReservationCode(uuid);
    }
}
