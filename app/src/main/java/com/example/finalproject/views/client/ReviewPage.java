package com.example.finalproject.views.client;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;
import com.example.finalproject.database.Database;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ReviewPage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review_page);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.home) {
                startActivity(new Intent(ReviewPage.this, ClientHomePage.class));
                return true;
            } else if (id == R.id.search) {
                startActivity(new Intent(ReviewPage.this, SearchPage.class));
                return true;
            } else if (id == R.id.review) {
                return true;
            } else if (id == R.id.account) {
                startActivity(new Intent(ReviewPage.this, ClientAccountPage.class));
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