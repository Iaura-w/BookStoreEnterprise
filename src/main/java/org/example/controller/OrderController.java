package org.example.controller;

import org.example.entity.Book;
import org.example.entity.Cart;
import org.example.entity.Order;
import org.example.entity.User;
import org.example.services.BookService;
import org.example.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final BookService bookService;
    private final Cart cart;

    public OrderController(OrderService orderService, BookService bookService, Cart cart) {
        this.orderService = orderService;
        this.bookService = bookService;
        this.cart = cart;
    }


    @GetMapping
    public String listUserOrders(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<Order> orders = orderService.getOrders();

        if (authentication.getAuthorities().contains("ROLE_USER")) {
            orders = orderService.getOrders(username);
        }
        model.addAttribute("orders", orders);
        model.addAttribute("dateFormat", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "orders";
    }

    @PostMapping
    public String saveOrder(Authentication authentication) {
        Order order = new Order();
        order.setStatus("CREATED");
        order.setUser(new User(authentication.getName()));
        List<Book> books = bookService.getBooks(cart.getBookIds());
        order.setBooks(books);
        float price = books.stream().map(book -> book.getPrice()).reduce(0.0f, (a, b) -> a + b);
        order.setPrice(price);
        orderService.saveOrder(order);
        cart.getBookIds().clear();
        return "redirect:/orders";
    }

    @PostMapping("/complete")
    public String completeOrder(@RequestParam(name = "orderId") int id) {
        orderService.completeOrder(id);
        return "redirect:/orders";
    }
}
