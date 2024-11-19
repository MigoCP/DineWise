package com.example.finalproject.views.owner;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OwnerRestaurantPage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_owner_restaurant_page);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.home) {
                return true;
            } else if (id == R.id.menu) {
                startActivity(new Intent(OwnerRestaurantPage.this, OwnerMenuPage.class));
                return true;
            } else if (id == R.id.reservation) {
                startActivity(new Intent(OwnerRestaurantPage.this, OwnerReservationsPage.class));
                return true;
            } else if (id == R.id.review) {
                startActivity(new Intent(OwnerRestaurantPage.this, OwnerReviewsPage.class));
                return true;
            } else if (id == R.id.account) {
                startActivity(new Intent(OwnerRestaurantPage.this, OwnerTabViewAccount.class));
                return true;
            }
            return false;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}