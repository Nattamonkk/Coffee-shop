package com.example.coffeeshop;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CoffeeShopController {

    private List<Product> products = new ArrayList<>();

    public CoffeeShopController() {

        products.add(
                new Product(1, "Americano", 50)
        );

        products.add(
                new Product(2, "Latte", 60)
        );

        products.add(
                new Product(3, "Cappuccino", 65)
        );

        products.add(
                new Product(4, "Chocolate Cake", 80)
        );
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return products;
    }

    @PostMapping("/order")
    public Order createOrder(
            @RequestBody OrderRequest request) {

        Order order =
                new Order(request.customerName);

        for (OrderRequestItem item : request.items) {

            Product product = products.stream()
                    .filter(p -> p.getId() == item.productId)
                    .findFirst()
                    .orElse(null);

            if (product != null) {
                order.addItem(
                        product,
                        item.quantity
                );
            }
        }

        return order;
    }

    public static class OrderRequest {

        public String customerName;

        public List<OrderRequestItem> items;
    }

    public static class OrderRequestItem {

        public int productId;

        public int quantity;
    }
}