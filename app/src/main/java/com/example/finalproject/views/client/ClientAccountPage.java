package com.example.finalproject.views.client;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientAccountPage extends AppCompatActivity implements View.OnClickListener{
    TextView tvFullName, tvJoinDate, tvContributions;
    ImageButton imBtnCity, imBtnWebsite, imBtnEdit, btnWriteReview, btnUploadPhoto,
            btnMyReserves, btnFavoriteRestaurants;
    Button btnView;

    FirebaseDatabase database;
    DatabaseReference sRef;
    FirebaseAuth ownerAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_client_account_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();
    }

    public void initialize() {
        tvFullName = findViewById(R.id.tvFullName);
        tvJoinDate = findViewById(R.id.tvJoinDate);
    }

    @Override
    public void onClick(View view) {

    }
}