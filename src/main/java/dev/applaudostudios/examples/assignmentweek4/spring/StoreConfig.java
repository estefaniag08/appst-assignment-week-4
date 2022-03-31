package dev.applaudostudios.examples.assignmentweek4.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import dev.applaudostudios.examples.assignmentweek4.persistence.InventoryRepository;
import dev.applaudostudios.examples.assignmentweek4.persistence.model.Product;

import dev.applaudostudios.examples.assignmentweek4.logic.store.Inventory;
import dev.applaudostudios.examples.assignmentweek4.logic.payment.PaymentMethodFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreConfig {

    @Bean
    public PaymentMethodFactory paymentMethodFactory() {
        return new PaymentMethodFactory();
    }

    @Bean
    @Autowired
    public Inventory<Product> inventory(InventoryRepository<Product> inventoryRepository){
        return new Inventory<>(inventoryRepository);
    }

}
