package dev.applaudostudios.examples.assignmentweek4.persistence.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.applaudostudios.examples.assignmentweek4.persistence.InventoryRepository;
import dev.applaudostudios.examples.assignmentweek4.persistence.model.Product;

/**
 * Populates the repository from a JSON file
 *
 * @author Estefanía García Gonzalez
 */
public class ProductRepositoryPopulator {

    private final InventoryRepository<Product> repository;

    public ProductRepositoryPopulator(InventoryRepository<Product> productRepository) {
        this.repository = productRepository;
    }

    /**Populates the repository loadibng the Json archive and mapping to Product objects
     *
     * @throws IOException
     */
    private void populateRepository() throws IOException {
        InputStream productFile = getClass().getClassLoader().getResourceAsStream("database/products.json");
        ObjectMapper objectMapper = new ObjectMapper();

        List<Product> products = objectMapper.readValue(productFile, new TypeReference<>() {
        });
        for (Product p : products) {
            repository.save(p);
        }
    }
}
