package org.example.services;

import org.example.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    List<Order> getOrders(String username);

    void saveOrder(Order order);

    void completeOrder(int orderId);

    void paidOrder(int orderId);

    boolean isBookInOrder(int bookId);
}