package org.example.controller;

import org.example.entity.Book;
import org.example.entity.Cart;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.entity.PayuResponse;
import org.example.entity.User;
import org.example.services.BookService;
import org.example.services.OrderService;
import org.example.services.PaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final BookService bookService;
    private final PaymentService paymentService;
    private final Cart cart;
    private final Map<Integer, String> ordersIdsMap;

    public OrderController(OrderService orderService, BookService bookService, PaymentService paymentService, Cart cart) {
        this.orderService = orderService;
        this.bookService = bookService;
        this.paymentService = paymentService;
        this.cart = cart;
        ordersIdsMap = new HashMap<>();
    }


    @GetMapping
    public String listUserOrders(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<Order> orders = orderService.getOrders();
        if (hasRoleUser(authentication)) {
            orders = orderService.getOrders(username);
        }
        model.addAttribute("orders", orders);
        model.addAttribute("dateFormat", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "orders";
    }

    private static boolean hasRoleUser(Authentication authentication) {
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_USER".equals(auth.getAuthority()))
                return true;
        }
        return false;
    }

    @PostMapping
    public String saveOrder(Authentication authentication, Model model) {
        Order order = new Order();
        order.setStatus("CREATED");
        order.setUser(new User(authentication.getName()));

//        List<Book> books = bookService.getBooks(cart.getBookIds());
        List<OrderItem> orderItems = cart.getOrderItems();
        order.setOrderItems(orderItems);
//        float price = books.stream().map(book -> book.getPrice()).reduce(0.0f, (a, b) -> a + b);
        float price = orderItems.stream().map(orderItem -> orderItem.getBook().getPrice()*orderItem.getQuantity()).reduce(0.0f, Float::sum);
        order.setPrice(price);
        orderService.saveOrder(order);
//        cart.getBookIds().clear();
        cart.getOrderItems().clear();

        PayuResponse payuResponse = paymentService.sendRequestPayU(order);
        ordersIdsMap.put(payuResponse.getOrderIdDb(), payuResponse.getOrderId());

        model.addAttribute("order", order);
        model.addAttribute("redirectUri", payuResponse.getRedirectUri());
        return "payu";
    }

    @PostMapping("/complete")
    public String completeOrder(@RequestParam(name = "orderId") int id) {
        orderService.completeOrder(id);
        return "redirect:/orders";
    }

    @PostMapping("/cancel")
    public String cancelOrder(@RequestParam(name = "orderId") int id) {
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }

    @GetMapping("/continue/{orderId}")
    public String payuContinue(@PathVariable int orderId, Model model) {
        String orderIdPayu = ordersIdsMap.get(orderId);
        String orderStatusFromPayu = paymentService.getOrderStatusFromPayu(orderIdPayu);
        String message = "Order was not paid.";

        if (!orderStatusFromPayu.equals("CANCELED")) {
            orderService.paidOrder(orderId);
            message = "Order was successfully paid.";
        }
        model.addAttribute("message", message);
        return "orderinformation";
    }
}
