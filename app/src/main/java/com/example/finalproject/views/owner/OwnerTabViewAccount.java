package com.example.finalproject.views.owner;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnerTabViewAccount extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    ImageView imageRestaurant;
    TextView tvTitleNameRestaurant, textRangePrice,textUpdateLinkWebSite,textUpdateAddress,textUpdatePhone;
    ImageButton btnUpdateRestaurantName, btnUpdateRangePrice, btnUpdateLinkWebSite, btnUpdateAddress,btnUpdatePhone;
    EditText editRestaurantName, editRangePrice, editWebsite, editAddress,editPhone;

    FirebaseDatabase database;
    DatabaseReference sRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_owner_tab_view_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();

        database = FirebaseDatabase.getInstance();
        sRef = database.getReference("restaurants");
        sRef.addListenerForSingleValueEvent(this);

    }

    private void initialize() {
        imageRestaurant = findViewById(R.id.imageRestaurant);
        tvTitleNameRestaurant = findViewById(R.id.tvTitleNameRestaurant);
        textRangePrice = findViewById(R.id.textRangePrice);
        textUpdateLinkWebSite = findViewById(R.id.textUpdateLinkWebSite);
        textUpdateAddress = findViewById(R.id.textUpdateAddress);
        textUpdatePhone = findViewById(R.id.textUpdatePhone);
        editRestaurantName = findViewById(R.id.editRestaurantName);
        editRangePrice = findViewById(R.id.editRangePrice);
        editWebsite = findViewById(R.id.editWebsite);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        btnUpdateRestaurantName = findViewById(R.id.btnUpdateRestaurantName);
        btnUpdateRestaurantName.setOnClickListener(this);
        btnUpdateRangePrice = findViewById(R.id.btnUpdateRangePrice);
        btnUpdateRangePrice.setOnClickListener(this);
        btnUpdateLinkWebSite = findViewById(R.id.btnUpdateLinkWebSite);
        btnUpdateLinkWebSite.setOnClickListener(this);
        btnUpdateAddress = findViewById(R.id.btnUpdateAddress);
        btnUpdateAddress.setOnClickListener(this);
        btnUpdatePhone = findViewById(R.id.btnUpdatePhone);
        btnUpdatePhone.setOnClickListener(this);


    }

    // NEED TO TEST THE CODE (COMMENTED) BELOW AFTER DO THE RELATION IN FIREBASE

    @Override
    public void onClick(View view) {
        /*
        if(view == btnUpdateRestaurantName){
            String newName = editRestaurantName.getText().toString().trim();
            if (!newName.isEmpty()){
                sRef.child("name").setValue(newName);
                tvTitleNameRestaurant.setText(newName);
            }
        } else if (view == btnUpdateRangePrice) {
            String newRangePrice = editRangePrice.getText().toString().trim();
            if (!newRangePrice.isEmpty()) {
                sRef.child("priceRange").setValue(newRangePrice);
                textRangePrice.setText(newRangePrice);
            }
        } else if (view == btnUpdateLinkWebSite) {
            String newWebsite = editWebsite.getText().toString().trim();
            if (!newWebsite.isEmpty()) {
                sRef.child("website").setValue(newWebsite);
                textUpdateLinkWebSite.setText(newWebsite);
            }
        } else if (view == btnUpdateAddress) {
            String newAddress = editAddress.getText().toString().trim();
            if (!newAddress.isEmpty()) {
                sRef.child("address").setValue(newAddress);
                textUpdateAddress.setText(newAddress);
            }
        } else if (view == btnUpdatePhone) {
            String newPhone = editPhone.getText().toString().trim();
            if (!newPhone.isEmpty()) {
                sRef.child("phoneNumber").setValue(newPhone);
                textUpdatePhone.setText(newPhone);
            }
        }*/
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        /*
        if (snapshot.exists()) {
            String name = snapshot.child("name").getValue(String.class);
            String priceRange = snapshot.child("priceRange").getValue(String.class);
            String website = snapshot.child("website").getValue(String.class);
            String address = snapshot.child("address").getValue(String.class);
            String phoneNumber = snapshot.child("phoneNumber").getValue(String.class);

            if (name != null) tvTitleNameRestaurant.setText(name);
            if (priceRange != null) textRangePrice.setText(priceRange);
            if (website != null) textUpdateLinkWebSite.setText(website);
            if (address != null) textUpdateAddress.setText(address);
            if (phoneNumber != null) textUpdatePhone.setText(phoneNumber);
        }*/

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}