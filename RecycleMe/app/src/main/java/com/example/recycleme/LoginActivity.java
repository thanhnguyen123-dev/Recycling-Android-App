package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recycleme.login.*;
import com.example.recycleme.util.LogUtil;

public class LoginActivity extends AppCompatActivity {
    private LoginContext loginContext;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.login_button);
        loginContext = LoginContext.getInstance();

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (LogUtil.validateLogin(getApplicationContext(), email, password)) {
                loginContext.login(email, password, AccountAction.LOGIN_ACTION, new LoginState.LoginCallback() {
                    @Override
                    public void onLoginSuccess() {
                        loginContext.setUserEmail(email);
                        updateUI();
                    }

                    @Override
                    public void onLoginFailure(String errorMessage) {
                        Toast.makeText(getApplicationContext(), "Email and password not recognized", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(v -> {
            SignupFragment fragment = new SignupFragment();
            fragment.show(getSupportFragmentManager(), "SignupManager");


        });
    }

    private void updateUI() {
        if (loginContext.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getApplicationContext(), "Email and password not recognized", Toast.LENGTH_SHORT).show();
    }


}