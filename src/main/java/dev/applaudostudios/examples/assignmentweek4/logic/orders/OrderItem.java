package dev.applaudostudios.examples.assignmentweek4.logic.orders;

import dev.applaudostudios.examples.assignmentweek4.common.Item;

/**
 * @author Estefanía Garcia Gonzalez
 */
public final class OrderItem extends Item {
    public OrderItem(Item inventoryItem, int units) {
        super(inventoryItem.getCode(), inventoryItem.getName(), units, inventoryItem.getPrice());
    }
}
