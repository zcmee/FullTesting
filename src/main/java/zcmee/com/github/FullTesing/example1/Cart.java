package zcmee.com.github.FullTesing.example1;

import zcmee.com.github.FullTesing.example1.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Order> orders = new ArrayList<>();

    public void addOrderToCart(Order order) {
        this.orders.add(order);
    }

    public void clearCart() {
        this.orders.clear();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void simulateLargeOrder() {
        for (int i = 0; i < 1_666; ++i) {
            Meal meal = new Meal(i + 1, "Hamburger no " + i);
            Order order =  new Order();
            order.addMealToOrder(meal);
            this.addOrderToCart(order);
        }
        System.out.println("Cart size: " + orders.size());
        clearCart();
    }
}
