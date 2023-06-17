package org.example.services;

import org.example.entity.Order;

import java.util.Set;

public interface OrderService {
    Set<Order> getOrders();

    Set<Order> getOrders(String username);

    void saveOrder(Order order);

    void completeOrder(int orderId);

    void paidOrder(int orderId);

    boolean isBookInOrder(int bookId);

    void cancelOrder(int id);
}