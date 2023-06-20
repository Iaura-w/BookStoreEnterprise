package org.example.dao;

import org.example.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private final SessionFactory sessionFactory;

    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getOrders() {
        Session session = sessionFactory.getCurrentSession();
        Query<Order> query = session.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems ORDER BY o.dateTime DESC", Order.class);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrders(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Order> query = session.createQuery("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.user.username=:username ORDER BY o.dateTime DESC", Order.class).setParameter("username", username);
        return query.getResultList();
    }

    @Override
    public void saveOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
    }

    @Override
    public void completeOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, orderId);
        order.setStatus("COMPLETED");
        session.saveOrUpdate(order);
    }

    @Override
    public void paidOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, orderId);
        order.setStatus("PAID");
        session.saveOrUpdate(order);
    }

    @Override
    public void cancelOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.get(Order.class, orderId);
        order.setStatus("CANCELED");
        session.saveOrUpdate(order);
    }
}
