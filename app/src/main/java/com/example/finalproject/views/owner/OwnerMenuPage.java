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

public class OwnerMenuPage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_owner_menu_page);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.home) {
                startActivity(new Intent(OwnerMenuPage.this, OwnerRestaurantPage.class));
                return true;
            } else if (id == R.id.menu) {
                return true;
            } else if (id == R.id.reservation) {
                startActivity(new Intent(OwnerMenuPage.this, OwnerReservationsPage.class));
                return true;
            } else if (id == R.id.review) {
                startActivity(new Intent(OwnerMenuPage.this, OwnerReviewsPage.class));
                return true;
            } else if (id == R.id.account) {
                startActivity(new Intent(OwnerMenuPage.this, OwnerTabViewAccount.class));
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