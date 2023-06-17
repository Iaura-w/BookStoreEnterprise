package org.example.entity;

import org.example.services.BookService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Cart {
    //    private final List<Integer> bookIds = new ArrayList<>();
//
//    public void addBookId(Integer id) {
//        bookIds.add(id);
//    }
//
//    public void deleteBookId(Integer id) {
//        bookIds.remove(id);
//    }
//
//    public List<Integer> getBookIds() {
//        return bookIds;
//    }
    private final BookService bookService;
    private final List<OrderItem> orderItems = new ArrayList<>();

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
        }
        orderItems.add(orderItem);
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

    public void deleteOrderItem(int id) {
//        orderItems.remove()
        orderItems.remove(new OrderItem(id));
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}