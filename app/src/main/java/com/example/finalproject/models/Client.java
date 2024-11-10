package com.example.finalproject.models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Client extends User{
    private List<String> favoriteCuisines;
    private List<Review> reviewHistory;

    public Client() {
        super("", "", "", "");
        this.favoriteCuisines = new ArrayList<>();
        this.reviewHistory = new ArrayList<>();
    }

    // Constructor
    public Client(String userID, String email, String password, String role) {
        super(userID, email, password, role);
        this.favoriteCuisines = new ArrayList<>();
        this.reviewHistory = new ArrayList<>();
    }

    @Override
    public boolean signUp() {
        // Sign-up logic here
        return true;
    }

    public List<Restaurant> viewRecommendedRestaurants(List<Restaurant> allRestaurants) {
        // Logic to view recommended restaurants
        return new ArrayList<>();
    }

    public void addReview(String restaurantID, Review review) {
        // Logic to add a review
        reviewHistory.add(review);
    }

    public void likeReview(String reviewID) {
        // Logic to like a review
    }

    public List<String> getFavoriteCuisine() {
        return favoriteCuisines;
    }

    public void addFavoriteCuisine(String cuisine) {
        favoriteCuisines.add(cuisine);
    }

}
