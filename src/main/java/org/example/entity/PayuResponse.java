package org.example.entity;

public class PayuResponse {
    private String redirectUri;

    public PayuResponse() {
    }

    public PayuResponse(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

}
