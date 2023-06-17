package org.example.controller;

import org.example.entity.Book;
import org.example.entity.Cart;
import org.example.entity.OrderItem;
import org.example.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final Cart cart;
    private final BookService bookService;

    public CartController(Cart cart, BookService bookService) {
        this.cart = cart;
        this.bookService = bookService;
    }

    @GetMapping
    public String cart(Model model) {
//        List<Integer> cartBookIds = cart.getBookIds();
        List<OrderItem> orderItems = cart.getOrderItems();
        if (orderItems.size() > 0) {
//            List<Book> books = bookService.getBooks(cartBookIds);
            float finalPrice = orderItems.stream().map(orderItem -> orderItem.getBook().getPrice()*orderItem.getQuantity()).reduce(0.0f, (a, b) -> a + b);
            finalPrice = (float) Math.round(finalPrice * 100) / 100f;
//            model.addAttribute("books", books);
            model.addAttribute("orderItems", orderItems);
            model.addAttribute("finalPrice", finalPrice);
        } else {
//            model.addAttribute("books", new ArrayList<>());
            model.addAttribute("orderItems", new ArrayList<>());
            model.addAttribute("finalPrice", 0.0);
        }
        return "cart2";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam(name = "bookId") int bookId) {
//        cart.addBookId(bookId);
        cart.addOrderItem(bookId);
        return "redirect:/cart";
    }

    @PostMapping("/delete")
    public String deleteFromCart(@RequestParam(name = "orderItemId") int id) {
//        cart.deleteBookId(id);
        cart.deleteOrderItem(id);
        return "redirect:/cart";
    }
}