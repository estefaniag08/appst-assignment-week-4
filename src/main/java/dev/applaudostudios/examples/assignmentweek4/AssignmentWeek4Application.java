package dev.applaudostudios.examples.assignmentweek4;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.applaudostudios.examples.assignmentweek4.common.Item;

import dev.applaudostudios.examples.assignmentweek4.logic.payment.PaymentMethodFactory;
import dev.applaudostudios.examples.assignmentweek4.logic.orders.OrderItem;
import dev.applaudostudios.examples.assignmentweek4.logic.orders.Order;

import dev.applaudostudios.examples.assignmentweek4.logic.store.Inventory;
import dev.applaudostudios.examples.assignmentweek4.logic.store.SimpleCart;


@SpringBootApplication
public class AssignmentWeek4Application implements CommandLineRunner {
    private final ProcessLogger<String> cartLogger = new ProcessLogger();
    @Autowired
    private Inventory<?> inventory;
    @Autowired
    private PaymentMethodFactory paymentMethodFactory;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(AssignmentWeek4Application.class, args);
    }

    @Override
    public void run(String... args) {
        List<OrderItem> listOfItems = new ArrayList<>();
        Order order;
        SimpleCart cart;

        String creditCard;
        String code;
        String units;
        String menu = "1";

        do {
            System.out.print("Please add your credit card number: ");
            creditCard = scanner.nextLine();
        } while (!creditCard.matches("^[+]?\\d*$"));

        System.out.println("Lets add items to the cart. Write down the code and the units you want.");
        for (Item item : inventory.showInventory().values()) {
            System.out.println(item);
        }

        do {
            do {
                System.out.print("Code: ");
                code = scanner.nextLine();
            } while (!code.matches("^[+]?\\d*$") || !inventory.showInventory().containsKey(Long.parseLong(code)));

            do {
                System.out.print("Units: ");
                units = scanner.nextLine();
            } while (!units.matches("^[+]?\\d*$"));

            listOfItems.add(new OrderItem(inventory.showInventory().get(Long.parseLong(code)), Integer.parseInt(units)));
            do {
                System.out.println("0. Process cart.");
                System.out.println("1. Add another item.");
                menu = scanner.nextLine();
            } while (!menu.equals("0") && !menu.equals("1"));
        } while (!Objects.equals(menu, "0"));

        order = new Order(listOfItems);
        cart = new SimpleCart(paymentMethodFactory.getPaymentMethod(""), inventory);
        //cart.attach(cartLogger);
        cart.setOrder(order);
        cart.processCart(creditCard);

        for (Item item : inventory.showInventory().values()) {
            System.out.println(item);
        }
    }

}
