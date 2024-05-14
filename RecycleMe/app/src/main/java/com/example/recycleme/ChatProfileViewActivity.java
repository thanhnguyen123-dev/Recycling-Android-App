package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recycleme.util.UserProfileUtil;

/**
 * An activity to view other user profiles from ChatsMainActivity
 * @author Le Thanh Nguyen - u7594144
 * */
public class ChatProfileViewActivity extends AppCompatActivity {
    private ImageView otherUserProfilePicImageView;
    private TextView otherUsernameTextView;
    private String otherUserId;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_profile_view);

        otherUsernameTextView = findViewById(R.id.otherProfileUsername);
        otherUserProfilePicImageView = findViewById(R.id.otherUserImageView);

        String otherUsername =  getIntent().getStringExtra("USER_NAME").toString();
        otherUsernameTextView.setText(otherUsername);
        otherUserId = getIntent().getStringExtra("USER_ID").toString();
        UserProfileUtil.retrieveUserImage(otherUserId, getApplicationContext(), otherUserProfilePicImageView);

        backButton = findViewById(R.id.viewProfileBackButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ChatsMainActivity.class);
            startActivity(intent);
        });
    }
}