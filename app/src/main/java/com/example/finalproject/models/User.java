package com.example.finalproject.models;

import android.graphics.Bitmap;

public abstract class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role; // Added attribute to indicate user role (e.g., restaurant_owner, user)

    // Constructor
    public User(String id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role; // Set in constructor
    }

    // Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() { // New method
        return role;
    }

    public void setRole(String role) { // New method
        this.role = role;
    }

    // Abstract method for subclasses to implement specific signup logic
    public abstract boolean signUp();

    // Additional methods can be added as needed
}
