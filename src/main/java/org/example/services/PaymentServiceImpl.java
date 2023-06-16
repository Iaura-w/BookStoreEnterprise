package org.example.services;

import org.example.entity.Book;
import org.example.entity.Order;
import org.example.entity.PayuResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class PaymentServiceImpl implements PaymentService {
    public static final String PAYU_API_ORDERS_URL = "https://secure.snd.payu.com/api/v2_1/orders/";
    public static final String PAYU_AUTHORIZATION_URL = "https://secure.snd.payu.com/pl/standard/user/oauth/authorize";

    @Override
    public PayuResponse sendRequestPayU(Order order) {
        try {
            URL url = new URL(PAYU_API_ORDERS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            String tokenFromPayu = getAuthorizationTokenFromPayu();
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

    @Override
    public String getOrderStatusFromPayu(String orderIdPayu) {
        try {
            URL url = new URL(PAYU_API_ORDERS_URL + orderIdPayu);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            String tokenFromPayu = getAuthorizationTokenFromPayu();
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

    private String getAuthorizationTokenFromPayu() {
        try {
            URL url = new URL(PAYU_AUTHORIZATION_URL);
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
}
