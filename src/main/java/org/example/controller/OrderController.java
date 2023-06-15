package org.example.controller;

import org.example.entity.Book;
import org.example.entity.Cart;
import org.example.entity.Order;
import org.example.entity.User;
import org.example.services.BookService;
import org.example.services.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
        List<Book> books = bookService.getBooks(cart.getBookIds());
        order.setBooks(books);
        float price = books.stream().map(book -> book.getPrice()).reduce(0.0f, (a, b) -> a + b);
        order.setPrice(price);
        orderService.saveOrder(order);
        cart.getBookIds().clear();

        String redirectUri = sendRequestPayU(order);
        model.addAttribute("order", order);
        model.addAttribute("redirectUri", redirectUri);
//        return "redirect:/orders";
        return "payu";
    }

    @PostMapping("/complete")
    public String completeOrder(@RequestParam(name = "orderId") int id) {
        orderService.completeOrder(id);
        return "redirect:/orders";
    }

    @GetMapping("/continue/{orderId}")
    public String payuContinue(@PathVariable int orderId) {
        orderService.paidOrder(orderId);
        return "orderdetails";
    }


//    @GetMapping(value = "/notify/{orderId}")
//    public String getPayStatus(@PathVariable String orderId, HttpServletResponse httpServletResponse) {
//        System.out.println("notify : " + httpServletResponse.getStatus());
//        System.out.println("notify : " + httpServletResponse);
//
//        return "orderdetails";
////        return httpServletResponse.toString();
//    }

    public String sendRequestPayU(Order order) {
        try {
            URL url = new URL("https://secure.snd.payu.com/api/v2_1/orders");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer d9a4536e-62ba-4f60-8017-6053211d3f47");
            connection.setDoOutput(true);

            String jsonInput = createJsonOrder(order);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();

            System.out.println("response code: " + responseCode);
            System.out.println("response message: " + responseMessage);
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response);
                String redirectUri = extractFromJson(response.toString(), "\"redirectUri\":\"");
                return redirectUri;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private static String extractFromJson(String jsonResponse, String key) {
        int redirectUriStartIndex = jsonResponse.indexOf(key);
        if (redirectUriStartIndex != -1) {
            int redirectUriEndIndex = jsonResponse.indexOf("\"", redirectUriStartIndex + key.length());
            if (redirectUriEndIndex != -1) {
                return jsonResponse.substring(redirectUriStartIndex + key.length(), redirectUriEndIndex);
            }
        }
        return null;
    }

    private static String createJsonOrder(Order order) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"notifyUrl\": \"").append("http://localhost:8080/bookStore/orders/notify").append("\",");
        jsonBuilder.append("\"continueUrl\": \"").append("http://localhost:8080/bookStore/orders/continue").append("/").append(order.getId()).append("\",");
        jsonBuilder.append("\"customerIp\": \"").append("127.0.0.1").append("\",");
        jsonBuilder.append("\"merchantPosId\": \"").append("300746").append("\",");
        jsonBuilder.append("\"description\": \"").append("books").append("\",");
        jsonBuilder.append("\"currencyCode\": \"").append("PLN").append("\",");
        jsonBuilder.append("\"totalAmount\": ").append((int) (order.getPrice() * 100)).append(",");

        jsonBuilder.append("\"buyer\": {");
        jsonBuilder.append("\"email\": \"").append(order.getUser().getUsername()).append("@example.com").append("\",");
        jsonBuilder.append("\"phone\": \"").append("123456789").append("\",");
        jsonBuilder.append("\"firstName\": \"").append(order.getUser().getUsername()).append("\",");
        jsonBuilder.append("\"lastName\": \"").append(order.getUser().getUsername()).append("\",");
        jsonBuilder.append("\"language\": \"").append("pl").append("\"");
        jsonBuilder.append("},");

        jsonBuilder.append("\"products\": [");
        for (int i = 0; i < order.getBooks().size(); i++) {
            Book book = order.getBooks().get(i);
            jsonBuilder.append("{");
            jsonBuilder.append("\"name\": \"").append(book.getName()).append("\",");
            jsonBuilder.append("\"unitPrice\": ").append((int) (book.getPrice() * 100)).append(",");
            jsonBuilder.append("\"quantity\": ").append("1");
            jsonBuilder.append("}");
            if (i < order.getBooks().size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }
}
