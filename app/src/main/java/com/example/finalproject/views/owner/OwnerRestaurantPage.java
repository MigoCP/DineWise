package com.example.finalproject.views.owner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;
import com.example.finalproject.views.start.IntroPage3;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class OwnerRestaurantPage extends AppCompatActivity implements View.OnClickListener, ValueEventListener, DialogInterface.OnClickListener {

    ImageView imRestaurant;
    TextView tvTitleNameRestaurant, tvRangePrice, tvRating, tvReview, tvLocation, tvContact;
    ImageButton btnLogOut,imBtnWebsite, imBtnMenu, imBtnReserve;
    private BottomNavigationView bottomNavigationView;

    FirebaseDatabase database;
    DatabaseReference sRef;
    FirebaseAuth ownerAuth;
    AlertDialog.Builder alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_owner_restaurant_page);

        initialize();

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

        ownerAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String ownerId = ownerAuth.getCurrentUser().getUid();

        sRef = database.getReference("restaurants");

        Query query = sRef.orderByChild("ownerId").equalTo(ownerId);
        query.addListenerForSingleValueEvent(this);

    }

    private void initialize() {
        imRestaurant = findViewById(R.id.imRestaurant);
        tvTitleNameRestaurant = findViewById(R.id.tvTitleNameRestaurant);
        tvRangePrice = findViewById(R.id.tvRangePrice);
        tvRating = findViewById(R.id.tvRating);
        tvReview = findViewById(R.id.tvReview);
        tvLocation = findViewById(R.id.tvLocation);
        tvContact = findViewById(R.id.tvContact);
        imBtnWebsite = findViewById(R.id.imBtnWebsite);
        imBtnMenu = findViewById(R.id.imBtnMenu);
        imBtnReserve = findViewById(R.id.imBtnReserve);
        btnLogOut = findViewById(R.id.btnLogOut);

        imBtnMenu.setOnClickListener(this);
        imBtnReserve.setOnClickListener(this);
        imBtnWebsite.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);

        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Log Out ");
        alertDialog.setMessage("Are you sure you want to sign out? (y/n)");
        alertDialog.setPositiveButton("Yes", this);
        alertDialog.setNegativeButton("No", this);

    }
    @Override
    public void onClick(View view) {
        if (view == btnLogOut) {
            // Show the logout confirmation dialog
            if (!alertDialog.create().isShowing()) {
                alertDialog.show();
            }
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                // load restaurant data
                String name = snapshot.child("name").getValue(String.class);
                String priceRange = snapshot.child("priceRange").getValue(String.class);
                String website = snapshot.child("website").getValue(String.class);
                String address = snapshot.child("address").getValue(String.class);
                String phoneNumber = snapshot.child("phoneNumber").getValue(String.class);
                String imageUrl = snapshot.child("image").getValue(String.class);

                // show data
                if (name != null) tvTitleNameRestaurant.setText(name);
                if (priceRange != null) tvRangePrice.setText(priceRange);
                //to work on
               // if (website != null) textUpdateLinkWebSite.setText(website);
                if (address != null) tvLocation.setText(address);
                if (phoneNumber != null) tvContact.setText(phoneNumber);

                if (imageUrl != null && !imageUrl.isEmpty()) {
                    // not working
                    Picasso.get()
                            .load(imageUrl)

                            .into(imRestaurant);
                }
            }
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int button) {
        if (button == DialogInterface.BUTTON_POSITIVE) {
            Intent intent = new Intent(this, IntroPage3.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else if (button == DialogInterface.BUTTON_NEGATIVE)
            dialogInterface.dismiss();
    }
}