package com.example.finalproject.models;
import android.graphics.Bitmap;

public class Owner extends User {
    public Owner() {
        super("", "", "", "");
    }

    // Constructor
    public Owner(String userID, String username, String password, String email) {
        super(userID, username, password, email);
    }

    // Implementing abstract method
    @Override
    public boolean signUp() {
        // Sign-up logic here
        return true;
    }

    // Methods
    public void editRestaurant(String restaurantID, Restaurant updates) {
        // Logic to edit restaurant details
    }
}
