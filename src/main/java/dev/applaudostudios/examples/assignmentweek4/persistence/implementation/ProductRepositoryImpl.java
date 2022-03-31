package dev.applaudostudios.examples.assignmentweek4.persistence.implementation;

import java.util.*;

import dev.applaudostudios.examples.assignmentweek4.persistence.InventoryRepository;
import dev.applaudostudios.examples.assignmentweek4.persistence.model.Product;

/**
 * Implementation of the InventoryRepository abstract class.
 *
 * @param <T> The type of Product that will be stored.
 */
public class ProductRepositoryImpl<T extends Product> extends InventoryRepository<T> {
    private List<T> productList;
    private HashMap<String, Comparator<T>> comparators;

    /**
     * Initializes the needed elements.
     */
    public void initializeRepository() {
        productList = new ArrayList<>();
        initializeComparators();
    }

    /**
     * Saves the element into the repository.
     *
     * @param element Element to be stored.
     * @return True if the element was stored, False otherwise.
     */
    @Override
    public boolean save(T element) {
        if (productList.contains(element)) {
            int index = productList.indexOf(element);
            T product = productList.get(index);
            return product.addStock(element.getStock());
        }
        return productList.add(element);
    }

    /**
     * Performs a full update of the element.
     *
     * @param element The element to be updated.
     * @return True if the element was updated, False otherwise.
     */
    @Override
    public boolean update(T element) {
        if (productList.contains(element)) {
            //int index = productList.indexOf(element);
            //T product = productList.get(index);
            //
            //return product.reserveStock(element.getStock());
            productList.add(element);
        }
        return false;
    }

    /**
     * Counts the elements of the repository.
     *
     * @return The number of elements.
     */
    @Override
    public int count() {
        return productList.size();
    }

    /**
     * Finds an element in the repository by its position
     *
     * @param index The position of the element
     * @return The found element or null if not found.
     */
    @Override
    public T find(int index) {
        if (index >= 0 && index < productList.size()) {
            return productList.get(index);
        } else {
            return null;
        }
    }

    /**
     * Returns an iterator of the repository.
     *
     * @return The iterator
     */
    @Override
    public Iterator<T> iterator() {
        return this.productList.iterator();
    }

    /**
     * Initializes the comparators for the fields of the elements
     */
    private void initializeComparators() {
        this.comparators = new HashMap<>();
        this.comparators.put("name", new ComparatorName<>());
        this.comparators.put("stock", new ComparatorStock<>());
    }

    /**
     * Gets the comparator based on the field of the element
     * @param field The key to find the comparator
     * @return The comparator
     */
    private Comparator<T> getComparator(String field) {
        return this.comparators.get(field);
    }

    /**
     * Sorts the elements by the field.
     * @param field The field.
     * @return The sorted list.
     */
    @Override
    public List<T> getAllSortedBy(String field) {
        List<T> sortedList = new ArrayList<>(productList);
        Comparator<T> comparator = getComparator(field);

        sortedList.sort(comparator);

        return Collections.unmodifiableList(sortedList);
    }

    /**
     * Comparator for the name field
     *
     * @param <P> Receives a Product child
     */
    private class ComparatorName<P extends Product> implements Comparator<P> {
        @Override
        public int compare(P product1, P product2) {
            String name1 = product1.getName().trim();
            String name2 = product2.getName().trim();
            return name1.compareToIgnoreCase(name2);
        }
    }

    /**
     * Comparator for the stock field
     *
     * @param <P> Receives a Product child
     */
    private class ComparatorStock<P extends Product> implements Comparator<P> {
        @Override
        public int compare(P product1, P product2) {
            int stockProduct1 = product1.getStock();
            int stockProduct2 = product2.getStock();
            return Integer.compare(stockProduct1, stockProduct2);
        }
    }
}
