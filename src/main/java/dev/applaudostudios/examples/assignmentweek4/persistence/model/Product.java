package dev.applaudostudios.examples.assignmentweek4.persistence.model;

import java.beans.ConstructorProperties;
import java.util.Objects;

/**The Product model for the repository.
 * @author Estefanía García Gonzalez
 */
public class Product {
    private final long code;
    private final String name;
    private final Double unitPrice;
    private int stock;

    @ConstructorProperties({"code", "name", "stock", "unitPrice"})
    public Product(long code, String name, int stock, Double unitPrice) {
        this.code = code;
        this.name = name;
        this.stock = stock;
        this.unitPrice = unitPrice;
    }

    public boolean addStock(int stock){
        if(stock>0){
            this.stock += stock;
            return true;
        }
        return false;
    }

    public boolean reserveStock(int stock){
        if(stock>0){
            this.stock = this.stock - stock;
            return true;
        }
        return false;
    }

    public long getCode(){
        return code;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "******************" + "\n" +
                "code=" + code + "\n" +
                "name=" + name + "\n" +
                "stock=" + stock + "\n" +
                "price=" + unitPrice + "\n";
    }
}
