package com.example.finalproject.views.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finalproject.R;
import com.example.finalproject.database.Database;
import com.example.finalproject.views.client.ClientHomePage;
import com.example.finalproject.views.owner.OwnerHomePage;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory;

public class LogInPage extends AppCompatActivity {

    private FirebaseAuth auth;
    private Database database;
    private String userType;
    private EditText edEmail, edPassword;
    private TextView tvTitle;
    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Initialize Firebase App Check in debug mode
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(DebugAppCheckProviderFactory.getInstance());


        auth = FirebaseAuth.getInstance();
        database = new Database();

        userType = getIntent().getStringExtra("userType");

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        tvTitle = findViewById(R.id.tvTitle);
        btnLogIn = findViewById(R.id.btnLogIn);

        tvTitle.setText(userType.equals("client") ?
                "Log In to see your favorite restaurants" :
                "Log In to see how your business is doing");

        btnLogIn.setOnClickListener(this::performLogIn);
    }

    private void performLogIn(View view) {
        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser currentUser = auth.getCurrentUser();
                if (currentUser != null) {
                    String userId = currentUser.getUid();
                    Log.d("LogInPage", "Authenticated user ID: " + userId);
                    verifyUserRole(userId);
                }
            } else {
                Toast.makeText(LogInPage.this, "Authentication failed. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void verifyUserRole(String userId) {
        database.usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String role = snapshot.child("role").getValue(String.class);
                    // Map "client" to "user" and "owner" to "restaurant_owner"
                    String expectedRole = userType.equals("client") ? "user" : "restaurant_owner";

                    if (role != null && role.equals(expectedRole)) {
                        Toast.makeText(LogInPage.this, "Login successful.", Toast.LENGTH_SHORT).show();
                        if ("client".equals(userType)) {
                            startActivity(new Intent(LogInPage.this, ClientHomePage.class));
                        } else {
                            startActivity(new Intent(LogInPage.this, OwnerHomePage.class));
                        }
                        finish(); // Close the login page after redirection
                    } else {
                        auth.signOut();
                        Toast.makeText(LogInPage.this, "Access denied: incorrect role.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    auth.signOut();
                    Toast.makeText(LogInPage.this, "User record not found in database.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LogInPage.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
