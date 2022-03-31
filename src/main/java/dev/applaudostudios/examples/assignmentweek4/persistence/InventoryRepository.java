package dev.applaudostudios.examples.assignmentweek4.persistence;

import java.util.Iterator;
import java.util.List;

/**
 * @autor Estefanía García Gonzalez
 * @param <T> The type of element to be stored in the repository
 */
public abstract class InventoryRepository<T> {
    public abstract boolean save(T element);
    public abstract boolean update(T element);
    public abstract int count();
    public abstract T find(int index);
    public abstract Iterator<T> iterator();
    public abstract List<T> getAllSortedBy(String field);
}
