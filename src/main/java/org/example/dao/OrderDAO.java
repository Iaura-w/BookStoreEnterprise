package org.example.dao;

import org.example.entity.Order;

import java.util.Set;

public interface OrderDAO {
    Set<Order> getOrders();

    Set<Order> getOrders(String username);

    void saveOrder(Order order);

    void completeOrder(int orderId);

    void paidOrder(int orderId);

    void cancelOrder(int orderId);
}