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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final BookService bookService;
    private final Cart cart;
    private final Map<Integer, String> ordersIdsMap;

    public OrderController(OrderService orderService, BookService bookService, Cart cart) {
        this.orderService = orderService;
        this.bookService = bookService;
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
        List<Book> books = bookService.getBooks(cart.getBookIds());
        order.setBooks(books);
        float price = books.stream().map(book -> book.getPrice()).reduce(0.0f, (a, b) -> a + b);
        order.setPrice(price);
        orderService.saveOrder(order);
        cart.getBookIds().clear();

        PayuResponse payuResponse = sendRequestPayU(order);
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

    @GetMapping("/continue/{orderId}")
    public String payuContinue(@PathVariable int orderId, Model model) {
        String orderIdPayu = ordersIdsMap.get(orderId);
        String orderStatusFromPayu = getOrderStatusFromPayu(orderIdPayu);
        String message = "Order was not paid.";
        if (!orderStatusFromPayu.equals("CANCELED")) {
            orderService.paidOrder(orderId);
            message = "Order was successfully paid.";
        }
        model.addAttribute("message", message);
        return "orderinformation";
    }

    public PayuResponse sendRequestPayU(Order order) {
        try {
            URL url = new URL("https://secure.snd.payu.com/api/v2_1/orders");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            String tokenFromPayu = getTokenFromPayu();
            connection.setRequestProperty("Authorization", "Bearer " + tokenFromPayu);
            connection.setDoOutput(true);

            String jsonInput = createJsonOrder(order);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String redirectUri = extractFromJson(response.toString(), "\"redirectUri\":\"");
                String payuOrderId = extractFromJson(response.toString(), "\"orderId\":\"");
                String orderIdDb = extractFromJson(response.toString(), "\"extOrderId\":\"");
                return new PayuResponse(redirectUri, payuOrderId, Integer.parseInt(orderIdDb));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new PayuResponse();
    }

    public String getOrderStatusFromPayu(String orderIdPayu) {
        try {
            URL url = new URL("https://secure.snd.payu.com/api/v2_1/orders/" + orderIdPayu);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            String tokenFromPayu = getTokenFromPayu();
            connection.setRequestProperty("Authorization", "Bearer " + tokenFromPayu);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String orderStatus = extractFromJson(response.toString(), "\"status\":\"");

                return orderStatus;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private String getTokenFromPayu() {
        try {
            URL url = new URL("https://secure.snd.payu.com/pl/standard/user/oauth/authorize");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            String parameters = "grant_type=client_credentials&client_id=460718&client_secret=22f4175da9f0f72bcce976dd8bd7504f";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = parameters.getBytes("utf-8");
                os.write(input, 0, input.length);
                os.flush();
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String token = extractFromJson(response.toString(), "\"access_token\":\"");
            return token;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private static String extractFromJson(String jsonResponse, String key) {
        int keyStartIndex = jsonResponse.indexOf(key);
        if (keyStartIndex != -1) {
            int keyEndIndex = jsonResponse.indexOf("\"", keyStartIndex + key.length());
            if (keyEndIndex != -1) {
                return jsonResponse.substring(keyStartIndex + key.length(), keyEndIndex);
            }
        }
        return null;
    }

    private static String createJsonOrder(Order order) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"notifyUrl\": \"").append("http://localhost:8080/bookStore/orders/notify").append("/").append(order.getId()).append("\",");
        jsonBuilder.append("\"continueUrl\": \"").append("http://localhost:8080/bookStore/orders/continue").append("/").append(order.getId()).append("\",");
        jsonBuilder.append("\"customerIp\": \"").append("127.0.0.1").append("\",");
        jsonBuilder.append("\"merchantPosId\": \"").append("467079").append("\",");
        jsonBuilder.append("\"description\": \"").append("books").append("\",");
        jsonBuilder.append("\"currencyCode\": \"").append("PLN").append("\",");
        jsonBuilder.append("\"totalAmount\": ").append((int) (Math.round(order.getPrice() * 100))).append(",");
        jsonBuilder.append("\"extOrderId\": \"").append(order.getId()).append("\",");

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

    private static class PayuResponse {
        private String redirectUri;
        private String orderId;
        private int orderIdDb;

        public PayuResponse() {
        }

        public PayuResponse(String redirectUri, String orderId, int orderIdDb) {
            this.redirectUri = redirectUri;
            this.orderId = orderId;
            this.orderIdDb = orderIdDb;
        }

        public String getRedirectUri() {
            return redirectUri;
        }

        public String getOrderId() {
            return orderId;
        }

        public int getOrderIdDb() {
            return orderIdDb;
        }
    }
}
