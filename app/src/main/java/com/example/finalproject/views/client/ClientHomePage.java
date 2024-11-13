package com.example.finalproject.views.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.database.Database;
import com.example.finalproject.models.Restaurant;
import com.example.finalproject.models.RestaurantCardViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ClientHomePage extends AppCompatActivity {

    private LinearLayout topRestaurantsLayout, nearMeLayout;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home_page);

        // Initialize the database object
        database = new Database();

        // Initialize layout references
        topRestaurantsLayout = findViewById(R.id.topRestaurantsContainer);
        nearMeLayout = findViewById(R.id.nearMeContainer);

        // Display user information
        displayUserInfo();

        // Fetch restaurant data from Firebase
        fetchRestaurants();
    }

    private void displayUserInfo() {
        // Retrieve user details from the Intent
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");

        // Display user details in TextViews
        TextView tvUserName = findViewById(R.id.tvUserName);
        TextView tvUserEmail = findViewById(R.id.tvUserEmail);
        TextView tvUserPassword = findViewById(R.id.tvUserPassword);

        // Set the text with the retrieved data
        tvUserName.setText("Name: " + (name != null ? name : "N/A"));
        tvUserEmail.setText("Email: " + (email != null ? email : "N/A"));
        tvUserPassword.setText("Password: " + (password != null ? password : "N/A"));
    }

    private void fetchRestaurants() {
        database.getAllRestaurants(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<RestaurantCardViewModel> topRestaurants = new ArrayList<>();
                List<RestaurantCardViewModel> nearMeRestaurants = new ArrayList<>();

                // Loop through each restaurant entry in the database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Restaurant restaurant = snapshot.getValue(Restaurant.class);
                    if (restaurant != null) {
                        RestaurantCardViewModel card = new RestaurantCardViewModel(
                                restaurant.getName(),
                                restaurant.getImage()
                        );
                        topRestaurants.add(card);  // Assuming all restaurants go to "Top Restaurants" for simplicity
                        nearMeRestaurants.add(card);  // Replicate for "Near Me" as well
                    }
                }

                // Populate both sections
                populateRestaurantScrollView(topRestaurantsLayout, topRestaurants);
                populateRestaurantScrollView(nearMeLayout, nearMeRestaurants);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error (e.g., log error or show a message to the user)
            }
        });
    }

    private void populateRestaurantScrollView(LinearLayout layout, List<RestaurantCardViewModel> restaurants) {
        for (RestaurantCardViewModel restaurant : restaurants) {
            View restaurantCard = LayoutInflater.from(this).inflate(R.layout.restaurant_card, layout, false);

            ImageView imageView = restaurantCard.findViewById(R.id.restaurantImage);
            TextView textView = restaurantCard.findViewById(R.id.restaurantName);

            textView.setText(restaurant.getName());
            Glide.with(this).load(restaurant.getImageUrl()).into(imageView);

            layout.addView(restaurantCard);
        }
    }
}
