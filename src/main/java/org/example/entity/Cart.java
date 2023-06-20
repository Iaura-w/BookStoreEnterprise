package org.example.entity;

import org.example.services.BookService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class Cart {
    private final BookService bookService;
    private final Set<OrderItem> orderItems = new HashSet<>();

    public Cart(BookService bookService) {
        this.bookService = bookService;
    }

    public void addOrderItem(int bookId) {
        Book book = bookService.getBook(bookId);
        OrderItem orderItem;

        if (isBookInOrderItems(book)) {
            orderItem = getOrderItem(book);
            orderItem.setQuantity(orderItem.getQuantity() + 1);
        } else {
            orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setQuantity(1);
            orderItems.add(orderItem);
        }
    }

    public void deleteOrderItem(int bookId) {
        Book book = bookService.getBook(bookId);
        OrderItem orderItem = new OrderItem();
        if (isBookInOrderItems(book)) {
            orderItem = getOrderItem(book);
        }
        orderItems.remove(orderItem);
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void cleanCart() {
        orderItems.clear();
    }

    private boolean isBookInOrderItems(Book book) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getBook().getId() == book.getId()) {
                return true;
            }
        }
        return false;
    }

    private OrderItem getOrderItem(Book book) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getBook().getId() == book.getId()) {
                return orderItem;
            }
        }
        return new OrderItem();
    }

    public void increaseQuantityOrderItem(int bookId) {
        Book book = bookService.getBook(bookId);
        OrderItem orderItem;

        if (isBookInOrderItems(book)) {
            orderItem = getOrderItem(book);
            orderItem.setQuantity(orderItem.getQuantity() + 1);
        }
    }

    public void decreaseQuantityOrderItem(int bookId) {
        Book book = bookService.getBook(bookId);
        OrderItem orderItem;

        if (isBookInOrderItems(book)) {
            orderItem = getOrderItem(book);
            int quantity = orderItem.getQuantity();
            if (quantity > 1)
                orderItem.setQuantity(quantity - 1);
        }
    }
}