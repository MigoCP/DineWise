package com.example.finalproject.views.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.finalproject.R;

public class LogInVerification extends AppCompatActivity implements View.OnClickListener{
    RadioGroup rgLogInChoice;
    RadioButton rbRestaurantManager, rbUser, rbAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in_verification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initialize();
    }

    private void initialize() {
        rgLogInChoice = findViewById(R.id.rgLogInChoice);
        rbUser = findViewById(R.id.rbUser);
        rbAdmin = findViewById(R.id.rbAdmin);
        rbRestaurantManager = findViewById(R.id.rbRestaurantManager);
        rgLogInChoice.setOnClickListener(this);
        rbRestaurantManager.setOnClickListener(this);
        rbAdmin.setOnClickListener(this);
        rbUser.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.rbAdmin){
            Intent intent = new Intent(this, AdminLogInPage.class);
            startActivity(intent);
        }
        if(id==R.id.rbUser){
            Intent intent = new Intent(this, LogInPage.class);
            startActivity(intent);
        }
        if(id==R.id.rbRestaurantManager){
            Intent intent = new Intent(this, RestaurantLogInPage.class);
            startActivity(intent);
        }
    }
}