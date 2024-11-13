package com.example.finalproject.models;

public class RestaurantCardViewModel {
    private String name;
    private String imageUrl;

    public RestaurantCardViewModel(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
