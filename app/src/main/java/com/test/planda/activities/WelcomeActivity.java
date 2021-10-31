package com.test.planda.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.test.planda.R;
import com.test.planda.services.DatabaseService;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        Button createNewAccountButton = findViewById(R.id.createNewAccountButton);
        createNewAccountButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateNewAccountActivity.class);
            startActivity(intent);
        });
    }
}