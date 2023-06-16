package org.example.dao;

import org.example.entity.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrders();

    List<Order> getOrders(String username);

    void saveOrder(Order order);

    void completeOrder(int orderId);

    void paidOrder(int orderId);

    void cancelOrder(int orderId);
}