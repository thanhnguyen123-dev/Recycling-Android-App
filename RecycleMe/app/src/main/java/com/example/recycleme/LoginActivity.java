package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.recycleme.login.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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

            loginContext.login(email, password, new LoginState.LoginCallback() {
                @Override
                public void onLoginSuccess() {
                    // Authentication successful, update UI
                    updateUI();
                }

                @Override
                public void onLoginFailure(String errorMessage) {
                    // Authentication failed, show error message
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }




    private void updateUI() {
        if (loginContext.isLoggedIn()) {
            // create  new fragment that will be displayed on screen
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getApplicationContext(), "Username and password not recognized", Toast.LENGTH_SHORT).show();
    }

}