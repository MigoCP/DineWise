package com.example.finalproject.views.owner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;
import com.example.finalproject.models.Review;
import com.example.finalproject.views.authentication.LogInPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OwnerReviewsPage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private FirebaseDatabase database;
    private DatabaseReference sRef;
    private FirebaseAuth ownerAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_owner_reviews_page);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ownerAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.home) {
                startActivity(new Intent(OwnerReviewsPage.this, OwnerRestaurantPage.class));
                return true;
            } else if (id == R.id.menu) {
                startActivity(new Intent(OwnerReviewsPage.this, OwnerMenuPage.class));
                return true;
            } else if (id == R.id.reservation) {
                startActivity(new Intent(OwnerReviewsPage.this, OwnerReservationsPage.class));
                return true;
            } else if (id == R.id.review) {
                return true;
            } else if (id == R.id.account) {
                startActivity(new Intent(OwnerReviewsPage.this, OwnerTabViewAccount.class));
                return true;
            }
            return false;
        });

        FirebaseUser currentUser = ownerAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(OwnerReviewsPage.this, LogInPage.class));
            finish();
            return;
        }

        String ownerId = currentUser.getUid();
        sRef = database.getReference("reviews");

        Query query = sRef.orderByChild("restaurantId").equalTo(ownerId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Review> reviews = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Review review = snapshot.getValue(Review.class);
                    if (review != null) {
                        reviews.add(review);
                        Log.d("Review", "Review: " + review.toString());
                    }
                }
                Log.d("Review", "Total de reviews found: " + reviews.size());
                // Popular
                LinearLayout reviewsContainer = findViewById(R.id.reviewsContainer);
                populateReviewScrollView(reviewsContainer, reviews);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(OwnerReviewsPage.this, "Error to load reviews", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void populateReviewScrollView(LinearLayout layout, List<Review> reviews) {
        if (reviews.isEmpty()) {
            Toast.makeText(this, "No review found.", Toast.LENGTH_SHORT).show();
        } else {
            for (Review review : reviews) {
                View reviewCard = LayoutInflater.from(this).inflate(R.layout.review_owner_card, layout, false);

                // Bind
                TextView clientName = reviewCard.findViewById(R.id.reviewClientName);
                ImageView ratingImage = reviewCard.findViewById(R.id.reviewRatingImage);
                TextView reviewDate = reviewCard.findViewById(R.id.reviewDate);
                TextView reviewText = reviewCard.findViewById(R.id.reviewText);

                // Set values to the views
                clientName.setText(review.getAuthor());
                reviewDate.setText(review.getDate());
                reviewText.setText(review.getComment());

                // rating image
                int ratingDrawable = getRatingDrawable(review.getRating());
                ratingImage.setImageResource(ratingDrawable);
                layout.addView(reviewCard);
            }
        }
    }

    private int getRatingDrawable(double rating) {
        if (rating >= 5) {
            return R.drawable.five_stars;
        } else if (rating >= 4 && rating < 5) {
            return R.drawable.four_stars;
        } else if (rating >= 3 && rating < 4) {
            return R.drawable.three_stars;
        } else if (rating >= 2 && rating < 3) {
            return R.drawable.two_stars;
        } else {
            return R.drawable.one_star;
        }
    }
}