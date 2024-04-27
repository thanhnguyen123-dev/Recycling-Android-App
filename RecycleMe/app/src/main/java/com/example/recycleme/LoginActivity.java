package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.recycleme.login.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.Serializable;

public class LoginActivity extends BaseActivity {

    private LoginContext loginContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_login, contentFrameLayout);

        EditText emailEditText = findViewById(R.id.editTextTextEmailAddress);
        EditText passwordEditText = findViewById(R.id.editTextTextPassword);
        Button loginButton = findViewById(R.id.login_button);
        this.loginContext = LoginContext.getInstance();

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            loginContext.login(email,password);

            updateUI(email, password);
        });
    }

    private void updateUI(String email, String password) {
        if (loginContext.isLoggedIn()) {
            // create  new fragment that will be displayed on screen
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
        } else {
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Email and password cannot be empty, please try again!", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(getApplicationContext(), "Username and password not recognized", Toast.LENGTH_SHORT).show();
        }
    }

}