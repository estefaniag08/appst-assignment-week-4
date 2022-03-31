package dev.applaudostudios.examples.assignmentweek4.common;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Estefanía García Gonzalez
 */
public class Item {
    private final long code;
    private final String name;
    private final int units;
    private final Double unitPrice;

    public Item(long code, String name, int units, Double unitPrice) {
        this.code = code;
        this.name = name;
        this.units = units;
        this.unitPrice = unitPrice;
    }

    public double getPrice(){
        return unitPrice;
    }
    public long getCode(){
        return code;
    }
    public String getName(){
        return name;
    }
    public int getUnits(){
        return units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return code == item.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("name='" + name + "'")
                .add("units=" + units)
                .add("unitPrice=" + unitPrice)
                .toString();
    }
}
