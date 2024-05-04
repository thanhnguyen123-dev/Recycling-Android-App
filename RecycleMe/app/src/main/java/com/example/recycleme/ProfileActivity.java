package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.recycleme.login.LoginContext;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends BaseActivity {
    private LoginContext loginContext;
    private TextView userEmailTextView;
    private Button logOutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        loginContext = LoginContext.getInstance();
        userEmailTextView = findViewById(R.id.user_email);
        String emailText = loginContext.getUserEmail();
        userEmailTextView.setText(emailText);


        logOutButton = findViewById(R.id.logout_button);


        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginContext.logout();
                updateUI();
            }
        });
    }

    private void updateUI() {
        if (!loginContext.isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Successfully logout", Toast.LENGTH_SHORT).show();
        }
    }



}