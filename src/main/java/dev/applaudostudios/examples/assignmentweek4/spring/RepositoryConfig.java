package dev.applaudostudios.examples.assignmentweek4.spring;

import dev.applaudostudios.examples.assignmentweek4.persistence.InventoryRepository;
import dev.applaudostudios.examples.assignmentweek4.persistence.connection.ProductRepositoryPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import dev.applaudostudios.examples.assignmentweek4.persistence.model.Product;
import dev.applaudostudios.examples.assignmentweek4.persistence.implementation.ProductRepositoryImpl;

@Configuration
public class RepositoryConfig {

    @Bean(initMethod = "initializeRepository")
    public InventoryRepository<Product> inventoryRepository(){
        return new ProductRepositoryImpl<>();
    }

    @Bean(initMethod = "populateRepository")
    @Autowired
    public ProductRepositoryPopulator productRepositoryPopulator(InventoryRepository<Product> productRepository){
        return new ProductRepositoryPopulator(productRepository);
    }
}
