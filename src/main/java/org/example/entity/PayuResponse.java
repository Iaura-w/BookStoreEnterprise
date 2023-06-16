package org.example.entity;

public class PayuResponse {
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
