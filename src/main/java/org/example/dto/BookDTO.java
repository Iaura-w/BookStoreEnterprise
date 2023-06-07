package org.example.dto;


import java.util.List;

public class BookDTO {
    private String name;

    private int id;
    private String publisher;

    private float price;

    private int categoryid;

    private List<Integer> authorsIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public List<Integer> getAuthorsIds() {
        return authorsIds;
    }

    public void setAuthorsIds(List<Integer> authorsIds) {
        this.authorsIds = authorsIds;
    }
}