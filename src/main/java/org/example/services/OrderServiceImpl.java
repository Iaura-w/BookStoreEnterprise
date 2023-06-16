package org.example.services;

import org.example.dao.OrderDAO;
import org.example.entity.Book;
import org.example.entity.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    @Transactional
    public List<Order> getOrders() {
        return orderDAO.getOrders();
    }

    @Override
    @Transactional
    public List<Order> getOrders(String username) {
        return orderDAO.getOrders(username);
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderDAO.saveOrder(order);
    }

    @Override
    @Transactional
    public void completeOrder(int orderId) {
        orderDAO.completeOrder(orderId);
    }

    @Override
    @Transactional
    public void paidOrder(int orderId) {
        orderDAO.paidOrder(orderId);
    }

    @Override
    @Transactional
    public boolean isBookInOrder(int bookId) {
        List<Order> orders = getOrders();
        for (Order order : orders) {
            for (Book book : order.getBooks()) {
                if (book.getId() == bookId) {
                    return true;
                }
            }
        }
        return false;
    }
}
