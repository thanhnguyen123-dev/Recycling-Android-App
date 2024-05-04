package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.test.internal.util.LogUtil;

import com.example.recycleme.login.*;
import com.example.recycleme.util.LogToastUtil;

public class LoginActivity extends BaseActivity {
    private LoginContext loginContext;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_login, contentFrameLayout);
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.login_button);
        loginContext = LoginContext.getInstance();

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (LogToastUtil.isEmpty(email, password)) {
                Toast.makeText(getApplicationContext(), "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if (LogToastUtil.isLessThanSixCharacter(password)) {
                Toast.makeText(getApplicationContext(), "Password has to be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }
            if (LogToastUtil.invalidEmail(email)) {
                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                return;
            }

            loginContext.login(email, password, AccountAction.LOGIN_ACTION, new LoginState.LoginCallback() {
                @Override
                public void onLoginSuccess() {
                    updateUI();
                }

                @Override
                public void onLoginFailure(String errorMessage) {
                    Toast.makeText(getApplicationContext(), "Email and password not recognized", Toast.LENGTH_SHORT).show();

                }
            });
        });

        signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(v -> {
            SignupFragment fragment = new SignupFragment();
            fragment.show(getSupportFragmentManager(), "SignupManager");


        });
    }

    private void updateUI() {
        if (loginContext.isLoggedIn()) {
            // create  new fragment that will be displayed on screen
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getApplicationContext(), "Email and password not recognized", Toast.LENGTH_SHORT).show();
    }


}