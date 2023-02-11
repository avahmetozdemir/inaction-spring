package com.lawman.inaction.advertisement.dto;

import java.util.Set;

public class CreateAdvertisementRequest {

    private String title;

    private String description;

    private Double price;

    private Long userId;

    private Set<String> hashtags;

    public CreateAdvertisementRequest() {
    }

    public CreateAdvertisementRequest(String title, String description, Double price, Long userId, Set<String> hashtags) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.userId = userId;
        this.hashtags = hashtags;
    }

    public String getTitle() {

        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<String> getHashtags() {
        return hashtags;
    }
}
