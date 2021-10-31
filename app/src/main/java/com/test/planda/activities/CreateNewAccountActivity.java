package com.test.planda.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.planda.R;
import com.test.planda.services.UserHandler;
import com.test.planda.services.InputValidationService;

public class CreateNewAccountActivity extends AppCompatActivity implements TextWatcher {

    private FirebaseAuth firebaseAuth;
    private UserHandler userHandler;
    private InputValidationService ivs;
    private Button createAccountButton;

    private EditText createName;
    private EditText createEmail;
    private EditText createPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        firebaseAuth = FirebaseAuth.getInstance();
        userHandler = UserHandler.getInstance();
        ivs = InputValidationService.getInstance();


        createName = findViewById(R.id.createName);
        createEmail = findViewById(R.id.createEmail);
        createPassword = findViewById(R.id.createPassword);

        createName.addTextChangedListener(this);
        createEmail.addTextChangedListener(this);
        createPassword.addTextChangedListener(this);

        createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setEnabled(false);

        createAccountButton.setOnClickListener(v ->
                firebaseAuth.createUserWithEmailAndPassword(createEmail.getText().toString(), createPassword.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        //account create successful and sign in
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            userHandler.updateUser(user, createName.getText().toString());
                        }
                        userHandler.startPrimaryActivity(this);

                        Toast.makeText(CreateNewAccountActivity.this, "Your free account is now ready!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        //account creation failed
                        Toast.makeText(CreateNewAccountActivity.this, "Account creation failed. Please Try again.",
                                Toast.LENGTH_LONG).show();
                    }
                }));
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        createAccountButton.setEnabled(ivs.isInputValid(createEmail.getText(),createName.getText().toString(),createPassword.getText().toString()));
    }

    @Override
    public void afterTextChanged(Editable s) {}
}