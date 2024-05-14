package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recycleme.login.*;
import com.example.recycleme.util.LogUtil;

/**
 * This is a class for the Login Activity. The class shows a login page as well as a signup fragment.
 *
 * @author Le Thanh Nguyen - u7594144
 * @author Julius Liem
 */
public class LoginActivity extends AppCompatActivity {
    private LoginContext loginContext;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;

    /**
     * This class was written by Julius and Thanh, Julius wrote the Activity first, and then
     * Thanh edited the Activity to accommodate the use of Firebase Auth
     *
     * @author Le Thanh Nguyen - u7594144
     * @author Julius Liem
     */
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