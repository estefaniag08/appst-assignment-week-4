package dev.applaudostudios.examples.assignmentweek4.logic.store;

import dev.applaudostudios.examples.assignmentweek4.common.Item;
import dev.applaudostudios.examples.assignmentweek4.persistence.InventoryRepository;
import dev.applaudostudios.examples.assignmentweek4.persistence.model.Product;

import java.util.*;

/**Stores a collection of Items
 * @param <T>
 * @author Estefanía Garcia Gonzalez
 */
public class Inventory<T extends Product> {
    //Associated repository;
    private final InventoryRepository<T> inventoryRepository;
    //Maps from Item of the Inventory to Product of the repository
    private final Map<Item, T> inventory;

    /**
     * Initializes the inventory with the data of the repository.
     *
     * @param inventoryRepository The repository from the persistence.
     */
    public Inventory(InventoryRepository<T> inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventory = new HashMap<>();
        initializeInventory();
    }

    /**
     * Creates a mapper to map from the Item to the Product of the repository
     */
    private void initializeInventory() {
        Iterator<T> productIterator = this.inventoryRepository.iterator();
        while (productIterator.hasNext()) {
            T product = productIterator.next();
            Item item = new Item(product.getCode(), product.getName(), product.getStock(), product.getUnitPrice());
            this.inventory.put(item, product);

        }
    }

    /**
     * Shows the inventory
     *
     * @return An unmodifiable map of the inventory items
     */
    public Map<Long, Item> showInventory() {
        Map<Long, Item> mapInventory = new HashMap<>();
        if (this.inventory.size() == 0) {
            initializeInventory();
        }
        for (Item item : this.inventory.keySet()) {
            mapInventory.put(item.getCode(), item);
        }
        return Collections.unmodifiableMap(mapInventory);
    }

    /**
     * Verifies if an Item and a selected quantity can be reserved
     *
     * @param item  The item that wants to be reserved
     * @param units The quantity that wants to be reserved
     * @return True if it can be reserved, False otherwise
     */
    public boolean canReserve(Item item, int units) {
        long code = this.inventory.get(item).getCode();
        int stock = this.inventory.get(item).getStock();
        return !(code > 239 && code < 384) && stock > 0 && units <= stock;
    }

    /**
     * Reserves the selected units of the Item
     *
     * @param item  The item to be reserved
     * @param units The quantity to be reserved
     * @return True if it could be reserved, False otherwise
     */
    public boolean reserve(Item item, int units) {
        if (canReserve(item, units)) {
            T product = this.inventory.get(item);
            //Reserves the stock
            product.reserveStock(units);
            //Updates the element of the repository
            this.inventoryRepository.update(product);
            //Updates the Item element of the inventory

            this.inventory.remove(item);
            this.inventory.put(new Item(product.getCode(), product.getName(),
                    product.getStock(), product.getUnitPrice()), product);
            return true;
        }
        return false;
    }
}
