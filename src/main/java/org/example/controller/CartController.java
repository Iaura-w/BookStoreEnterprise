package org.example.controller;

import org.example.entity.Cart;
import org.example.entity.OrderItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Set;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final Cart cart;

    public CartController(Cart cart) {
        this.cart = cart;
    }

    @GetMapping
    public String cart(Model model) {
        Set<OrderItem> orderItems = cart.getOrderItems();
        if (orderItems.size() > 0) {
            float finalPrice = orderItems.stream().map(orderItem -> orderItem.getBook().getPrice() * orderItem.getQuantity()).reduce(0.0f, (a, b) -> a + b);
            finalPrice = (float) Math.round(finalPrice * 100) / 100f;
            model.addAttribute("orderItems", orderItems);
            model.addAttribute("finalPrice", finalPrice);
        } else {
            model.addAttribute("orderItems", new ArrayList<>());
            model.addAttribute("finalPrice", 0.0);
        }
        return "cart2";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam(name = "bookId") int bookId) {
        cart.addOrderItem(bookId);
        return "redirect:/cart";
    }

    @PostMapping("/delete")
    public String deleteFromCart(@RequestParam(name = "bookId") int bookId) {
        cart.deleteOrderItem(bookId);
        return "redirect:/cart";
    }
}