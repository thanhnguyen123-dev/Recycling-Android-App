package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {
    private Button signupButton;
    private Button loginButton;
    private EditText editTextEmailAddress;
    private EditText editTextPassword;
    private EditText getEditTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editTextEmailAddress = findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword = findViewById(R.id.editTextTextSignupPassword);
        getEditTextConfirmPassword = findViewById(R.id.editTextTextConfirmPassword);


        loginButton = findViewById(R.id.loginButtonForSignup);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });



    }
}