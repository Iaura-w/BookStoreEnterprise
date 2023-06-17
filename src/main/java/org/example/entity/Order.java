package org.example.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "price")
    private float price;
    @Column(name = "status")
    private String status;

    @Column(name = "date_time", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime dateTime;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    public Order() {
    }

    public Order(float price, User user, Set<OrderItem> orderItems) {
        this.price = price;
        this.user = user;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.orderItems.forEach(orderItem -> orderItem.setOrder(this));
    }

    public void addOrderItem(OrderItem orderItem) {
        if (orderItems == null)
            orderItems = new HashSet<>();
        this.orderItems.add(orderItem);
    }

    public void deleteOrderItem(OrderItem orderItem) {
        if (orderItems != null)
            orderItems.remove(orderItem);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", dateTime=" + dateTime +
                ", user=" + user +
                ", orderItems=" + orderItems +
                '}';
    }
}
