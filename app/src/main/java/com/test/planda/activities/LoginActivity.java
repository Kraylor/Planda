package com.test.planda.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.planda.R;
import com.test.planda.services.InputValidationService;
import com.test.planda.services.UserHandler;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private UserHandler userHandler;
    private InputValidationService inputValidationService;
    private static LoginActivity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        userHandler = UserHandler.getInstance();
        inputValidationService = InputValidationService.getInstance();

        EditText email = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        Button signInButton = findViewById(R.id.login);
        signInButton.setOnClickListener(v ->

                firebaseAuth.signInWithEmailAndPassword(
                        inputValidationService.notEmpty(email.getText().toString()),
                        inputValidationService.notEmpty(password.getText().toString()))
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in successful
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            userHandler.updateUser(user);
                        }
                        Toast.makeText(LoginActivity.this, "Login successful!",
                                Toast.LENGTH_LONG).show();
                        userHandler.startPrimaryActivity(this);
                    } else {
                        //login failed
                        Toast.makeText(LoginActivity.this, "Login failed.",
                                Toast.LENGTH_LONG).show();
                    }
                })

        );
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
}